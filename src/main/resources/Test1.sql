
--------------------------------------------------------------------
有如下数据记录直播平台主播上播及下播时间，根据该数据计算出平台最高峰同时直播人数。

+----------+----------------------+----------------------+
| user_id  |      start_time      |       end_time       |
+----------+----------------------+----------------------+
| 1        | 2024-04-29 01:00:00  | 2024-04-29 02:01:05  |
| 2        | 2024-04-29 01:05:00  | 2024-04-29 02:03:18  |
| 3        | 2024-04-29 02:00:00  | 2024-04-29 04:03:22  |
| 4        | 2024-04-29 03:15:07  | 2024-04-29 04:33:21  |
| 5        | 2024-04-29 03:34:16  | 2024-04-29 06:10:45  |
| 6        | 2024-04-29 05:22:00  | 2024-04-29 07:01:08  |
| 7        | 2024-04-29 06:11:03  | 2024-04-29 09:26:05  |
| 3        | 2024-04-29 08:00:00  | 2024-04-29 12:34:27  |
| 1        | 2024-04-29 11:00:00  | 2024-04-29 16:03:18  |
| 8        | 2024-04-29 15:00:00  | 2024-04-29 17:01:05  |
+----------+----------------------+----------------------+
select max(cnt)
from (
    select user_id,ctime,sum(flag) over(order by ctime asc) as cnt
    from (
        select user_id,start_time as ctime,1 as flag
        from test.t1_livestream_log
        union all
        select user_id,end_time as ctime,-1 as flag
        from test.t1_livestream_log
    ) a
) b


--------------------------------------------------------------------
有如下数据记录直播平台主播上播及下播时间，根据该数据计算出平台每分钟最大直播人数。

+----------+----------------------+----------------------+
| user_id  |      start_time      |       end_time       |
+----------+----------------------+----------------------+
| 1        | 2024-04-29 01:00:00  | 2024-04-29 02:01:05  |
| 2        | 2024-04-29 01:05:00  | 2024-04-29 02:03:18  |
| 3        | 2024-04-29 02:00:00  | 2024-04-29 04:03:22  |
| 4        | 2024-04-29 03:15:07  | 2024-04-29 04:33:21  |
| 5        | 2024-04-29 03:34:16  | 2024-04-29 06:10:45  |
| 6        | 2024-04-29 05:22:00  | 2024-04-29 07:01:08  |
| 7        | 2024-04-29 06:11:03  | 2024-04-29 09:26:05  |
| 3        | 2024-04-29 08:00:00  | 2024-04-29 12:34:27  |
| 1        | 2024-04-29 11:00:00  | 2024-04-29 16:03:18  |
| 8        | 2024-04-29 15:00:00  | 2024-04-29 17:01:05  |
+----------+----------------------+----------------------+

with t_all as(
--开播记录
select
    user_id,
    start_time as action_time,
    1 as change_cnt
from test.t1_livestream_log
union all
--下播记录
select
    user_id,
    end_time as action_time,
    -1 as change_cnt
from test.t1_livestream_log
--心跳数据
union all
SELECT
    0 as user_id,
    from_unixtime(unix_timestamp('2024-04-29','yyyy-MM-dd')+item*60,'yyyy-MM-dd HH:mm:ss') as action_time,
    0 as change_cnt
from (select posexplode(split(space(24*60),' ')) as (item,value)) t
)
select
    date_format(action_time,'yyyy-MM-dd HH:mm') as act_minute,
    max(online_cnt) as minute_max_cnt
from (select user_id,
             action_time,
             change_cnt,
             sum(change_cnt) over (order by action_time asc) online_cnt
      from t_all
      )t1
group by date_format(action_time,'yyyy-MM-dd HH:mm')



----------------------------------------------------------------------------------------------------------------------------------------
有如下数据，记录每天每只股票的收盘价格，请查出每只股票的波峰和波谷的日期和价格； 波峰：股票价格高于前一天和后一天价格时为波峰 波谷：股票价格低于前一天和后一天价格是为波谷

样例数据

+------------+-------------+--------+
|  ts_code   | trade_date  | close  |
+------------+-------------+--------+
| 000001.SZ  | 20220104    | 16.66  |
| 000002.SZ  | 20220104    | 20.49  |
| 000001.SZ  | 20220105    | 17.15  |
| 000002.SZ  | 20220105    | 21.17  |
| 000001.SZ  | 20220106    | 17.12  |
| 000002.SZ  | 20220106    | 21.05  |
| 000001.SZ  | 20220107    | 17.2   |
| 000002.SZ  | 20220107    | 21.89  |
| 000001.SZ  | 20220110    | 17.19  |
| 000002.SZ  | 20220110    | 22.16  |
| 000001.SZ  | 20220111    | 17.41  |
| 000002.SZ  | 20220111    | 22.3   |
| 000001.SZ  | 20220112    | 17.0   |
| 000002.SZ  | 20220112    | 22.05  |
| 000001.SZ  | 20220113    | 16.98  |
| 000002.SZ  | 20220113    | 21.53  |
| 000001.SZ  | 20220114    | 16.33  |
| 000002.SZ  | 20220114    | 20.7   |
| 000001.SZ  | 20220117    | 16.22  |
| 000002.SZ  | 20220117    | 20.87  |
+------------+-------------+--------+


with tmp as
(select
    ts_code
    ,trade_date
    ,close
    ,lag(close) over(partition by ts_code order by trade_date asc) as pre_close
    ,lead(close) over(partition by ts_code order by trade_date asc) as next_close
from test.t3_stock_test
)
select ts_code,trade_date,close
        ,case when close >pre_close and close>next_close then '波峰'
              when close <pre_close and close<next_close then '波谷'
         else '其他' end as type
from tmp


----------------------------------------------------------------------------------------------------------------------------------------
已知有表记录了每个大厅的活动开始日期和结束日期，每个大厅可以有多个活动。请编写一个SQL查询合并在同一个大厅举行的所有重叠的活动，如果两个活动至少有一天相同，那他们就是重叠的

样例数据

+----------+-------------+-------------+
| hall_id  | start_date  |  end_date   |
+----------+-------------+-------------+
| 1        | 2023-01-13  | 2023-01-20  | 0 0
| 1        | 2023-01-14  | 2023-01-16  | 0 0
| 1        | 2023-01-14  | 2023-01-17  | 0 0
| 1        | 2023-01-18  | 2023-01-25  | 0 0
| 1        | 2023-01-20  | 2023-01-26  | 0 0
| 2        | 2022-12-09  | 2022-12-23  | 0 0
| 2        | 2022-12-13  | 2022-12-17  | 0 0
| 2        | 2022-12-20  | 2022-12-24  | 0 0
| 2        | 2022-12-25  | 2022-12-30  | 1 1
| 3        | 2022-12-01  | 2023-01-30  | 0 0
+----------+-------------+-------------+
结果

+----------+-------------+-------------+
| hall_id  | start_date  |  end_date   |
+----------+-------------+-------------+
| 1        | 2023-01-13  | 2023-01-26  |
| 2        | 2022-12-09  | 2022-12-24  |
| 2        | 2022-12-25  | 2022-12-30  |
| 3        | 2022-12-01  | 2023-01-30  |
+----------+-------------+-------------+

with tmp as (
    select
        hall_id
        ,start_date
        ,end_date
        ,max(end_date) over(partition by hall_id order by start_date asc,end_date asc rows between unbounded preceding and 1 preceding) as max_end_date
    from test.t4_hall_event
)
,tmp1 as (
select hall_id
        ,start_date
        ,end_date
        ,max_end_date
        ,if(max_end_date>=start_date,0,1) as is_merge
from tmp
)
,tmp2 as (
select hall_id
       ,start_date
       ,end_date
       ,max_end_date
       ,is_merge
       ,sum(is_merge) over(partition by hall_id order by start_date asc,end_date asc) as group_id
from tmp1
)
select  hall_id
       ,min(start_date) as min_start_date
       ,max(end_date) as max_end_date
from tmp2
group by hall_id,group_id


----------------------------------------------------------------------------------------------------------------------------------------
现有订单表t5_order，包含订单ID，订单时间,下单用户，当前订单是否有效

+---------+----------------------+----------+-----------+
| ord_id  |       ord_time       | user_id  | is_valid  |
+---------+----------------------+----------+-----------+
| 1       | 2023-12-11 12:01:03  | a        | 1         |
| 2       | 2023-12-11 12:02:06  | a        | 0         |
| 3       | 2023-12-11 12:03:15  | a        | 0         |
| 4       | 2023-12-11 12:04:20  | a        | 1         |
| 5       | 2023-12-11 12:05:03  | a        | 1         |
| 6       | 2023-12-11 12:01:02  | b        | 1         |
| 7       | 2023-12-11 12:03:03  | b        | 0         |
| 8       | 2023-12-11 12:04:01  | b        | 1         |
| 9       | 2023-12-11 12:07:03  | b        | 1         |
+---------+----------------------+----------+-----------+

+---------+----------------------+----------+-----------+--------------------+
| ord_id  |       ord_time       | user_id  | is_valid  | last_valid_ord_id  |
+---------+----------------------+----------+-----------+--------------------+
| 1       | 2023-12-11 12:01:03  | a        | 1         | NULL               |
| 4       | 2023-12-11 12:04:20  | a        | 1         | 1                  |
| 5       | 2023-12-11 12:05:03  | a        | 1         | 4                  |
| 6       | 2023-12-11 12:01:02  | b        | 1         | NULL               |
| 8       | 2023-12-11 12:04:01  | b        | 1         | 6                  |
| 9       | 2023-12-11 12:07:03  | b        | 1         | 8                  |
+---------+----------------------+----------+-----------|                   |
请查询出每笔订单的上一笔有效订单,期望查询结果如下：

+---------+----------------------+----------+-----------+--------------------+
| ord_id  |       ord_time       | user_id  | is_valid  | last_valid_ord_id  |
+---------+----------------------+----------+-----------+--------------------+
| 1       | 2023-12-11 12:01:03  | a        | 1         | NULL               |
| 2       | 2023-12-11 12:02:06  | a        | 0         | 1                  |
| 3       | 2023-12-11 12:03:15  | a        | 0         | 1                  |
| 4       | 2023-12-11 12:04:20  | a        | 1         | 1                  |
| 5       | 2023-12-11 12:05:03  | a        | 1         | 4                  |
| 6       | 2023-12-11 12:01:02  | b        | 1         | NULL               |
| 7       | 2023-12-11 12:03:03  | b        | 0         | 6                  |
| 8       | 2023-12-11 12:04:01  | b        | 1         | 6                  |
| 9       | 2023-12-11 12:07:03  | b        | 1         | 8                  |
+---------+----------------------+----------+-----------+--------------------+


with tmp as (
    select ord_id
           ,ord_time
           ,user_id
           ,is_valid
    from test.t5_order
    where is_valid = 1
)
,tmp2 as (
    select  t5_order.ord_id
           ,t5_order.ord_time
           ,t5_order.user_id
           ,t5_order.is_valid
           ,tmp.ord_id as last_valid_ord_id
           ,tmp.ord_time as last_valid_ord_time
    from test.t5_order as t5_order
    left join tmp on t5_order.user_id = tmp.user_id and t5_order.ord_time > tmp.ord_time

)
,tmp3 as (
    select
        ord_id
        ,ord_time
        ,user_id
        ,is_valid
        ,last_valid_ord_id
        ,last_valid_ord_time
        ,row_number() over(partition by ord_id,user_id order by last_valid_ord_time desc) as rn
    from tmp2
)
select
    ord_id
    ,ord_time
    ,user_id
    ,is_valid
    ,last_valid_ord_id
from tmp3
where rn = 1

----------------------------------------------------------------------------------------------------------------------------------------
现有用户登录日志表，记录了每个用户登录的IP地址，请查询共同使用过3个及以上IP的用户对；

+----------+-----------------+----------------------+
| user_id  |       ip        |      time_stamp      |
+----------+-----------------+----------------------+
| 2        | 223.104.41.101  | 2023-08-24 07:00:00  |
| 1        | 223.104.41.101  | 2023-08-24 16:00:00  |
| 3        | 223.104.41.101  | 2023-08-24 16:02:00  |
| 3        | 223.104.41.103  | 2023-08-24 18:11:00  |
| 2        | 223.104.41.103  | 2023-08-24 19:00:00  |
| 1        | 223.104.41.104  | 2023-08-24 19:00:00  |
| 2        | 223.104.41.104  | 2023-08-24 16:30:00  |
| 4        | 223.104.41.126  | 2023-08-24 13:00:00  |
| 5        | 223.104.41.126  | 2023-08-24 11:00:00  |
| 1        | 223.104.41.121  | 2023-08-24 17:00:00  |
| 4        | 223.104.41.122  | 2023-08-24 10:00:00  |
| 2        | 223.104.41.122  | 2023-08-24 17:05:00  |
| 3        | 223.104.41.122  | 2023-08-24 19:07:00  |
| 1        | 223.104.41.122  | 2023-08-24 21:00:00  |
+----------+-----------------+----------------------+

with tmp as (
select user_id,ip
from login_log
group by user_id,ip
)
,tmp2 as (
select
    a.ip
    ,if(b.user_id is null,'',concat(a.user_id,'_',b.user_id)) as id_couple
from tmp a
left join login_log b on a.ip = b.ip
where a.user_id<b.user_id
)
select id_couple
from tmp2
group by id_couple
having count(distinct ip)>=3


---------------------------------------------------------------------------------------
现有各省地级市的gdp数据,求从高到底累加刚好超过各省GDP40%的地市名称，临界地市也需要。 例如:

浙江省的杭州24% 宁波 20% ,杭州+宁波=44% 大于40% 取出杭州、宁波

江苏省的苏州19% 南京 14% 无锡 12%，苏州+南京=33% ，苏州+南京+无锡=45%，取出 苏州、南京、无锡

+-------+-------+-----------+
| prov  | city  |  gdp_amt  |
+-------+-------+-----------+
| 浙江    | 杭州    | 20059.00  |
| 浙江    | 宁波    | 16452.80  |
| 浙江    | 温州    | 8730.60   |
| 浙江    | 绍兴    | 7791.00   |
| 浙江    | 嘉兴    | 7062.45   |
| 浙江    | 台州    | 6240.68   |
| 浙江    | 金华    | 6011.27   |
| 浙江    | 湖州    | 4015.10   |
| 浙江    | 衢州    | 2125.20   |
| 浙江    | 舟山    | 2100.80   |
| 浙江    | 丽水    | 1964.40   |
| 江苏    | 苏州    | 24653.37  |
| 江苏    | 南京    | 17421.40  |
| 江苏    | 无锡    | 15456.19  |
| 江苏    | 南通    | 11813.27  |
| 江苏    | 常州    | 10116.36  |
| 江苏    | 徐州    | 8900.44   |
| 江苏    | 扬州    | 7423.26   |
| 江苏    | 盐城    | 7403.87   |
| 江苏    | 泰州    | 6731.66   |
| 江苏    | 镇江    | 5264.07   |
| 江苏    | 淮安    | 5015.06   |
| 江苏    | 宿迁    | 4398.07   |
| 江苏    | 连云港   | 4363.61   |
+-------+-------+-----------+

with tmp as (
    select
        prov
        ,sum(gdp_amt) as all_gdp
    from test.t1_gdp
    group by prov
)
,tmp2 as (
select
    gdp_city.prov
    ,gdp_city.city
    ,gdp_city.gdp_amt
    ,sum(gdp_city.gdp_amt) over(partition by gdp_city.prov order by gdp_city.gdp_amt) as cum_amt
    ,tmp.all_gdp*0.6 as avg_gdp_amt
from test.t1_gdp  as gdp_city
left join tmp on gdp_city.prov = tmp.prov
)
,tmp3 as (
select
    prov
   ,city
from tmp2
where cum_amt<=avg_gdp_amt
)
select
    gdp_city.prov
    ,gdp_city.city
from test.t1_gdp  as gdp_city
left join tmp3 on gdp_city.prov = tmp3.prov and gdp_city.city = tmp3.city
where tmp3.city is null

---------------------------------------------------------------------------------------
有一张表t2_id记录了id，id不重复，但是会存在间断，求出连续段的起始位置和结束位置。

+-----+
| id  |
+-----+
| 1   |1  0
| 2   |2  0
| 3   |3  0
| 5   |4  1
| 6   |5  1
| 8   |6  2
| 10  |7  3
| 12  |8  4
| 13  |9  4
| 14  |10 4
| 15  |11 4
+-----+

select
    min(id) as min_id,
    max(id) as max_id
from (
select
    id
    ,row_number() over(order by id) as rn
from test.t2_id
) a
group by id-rn

---------
select group_type,
       min(id) as start_pos,
       max(id) as end_pos
from (select id,
             sum(if(diff = 1, 0, 1)) over (order by id) as group_type
      from (select id,
                   id - lag(id) over (order by id) as diff
            from t2_id) t) tt
group by group_type



---------------------------------------------------------------------------------------
有一张表t3_id记录了id，id不重复，但是会存在间断，求出连续段的最后一个数及每个连续段的个数。

+-----+
| id  |
+-----+
| 1   |
| 2   |
| 3   |
| 5   |
| 6   |
| 8   |
| 10  |
| 12  |
| 13  |
| 14  |
| 15  |
+-----+

select
    count(id) as cnt,
    max(id) as max_id
from (
select
    id
    ,row_number() over(order by id) as rn
from test.t3_id
) a
group by id-rn



--------------------------------------------------------------------
有营销活动记录表，记录了每个品牌每次营销活动的开始日期和营销活动的结束日期，现需要统计出每个品牌的总营销天数。
 注意： 1:苹果第一行数据的营销结束日期比第二行数据的营销开始日期要晚，这部分有重叠的日期的要去重计算。
 2:苹果第二行数据的营销结束日期和第三行的开始日期不连续，2019-09-07以及2019-09-08不统计到营销天数中。 样例数据

+--------+-------------+-------------+
| brand  | start_date  |  end_date   |
+--------+-------------+-------------+
| 华为   | 2018-08-04  | 2018-08-05  |
| 华为   | 2018-08-04  | 2020-12-25  |
| 小米   | 2018-08-15  | 2018-08-20  |
| 小米   | 2020-01-01  | 2020-01-05  |
| 苹果   | 2018-09-01  | 2018-09-05  |
| 苹果   | 2018-09-03  | 2018-09-06  |
| 苹果   | 2018-09-09  | 2018-09-15  |
+--------+-------------+-------------+
结果

+--------+-----------+
| brand  | act_days  |
+--------+-----------+
| 华为     | 875       |
| 小米     | 11        |
| 苹果     | 13        |
+--------+-----------+

with tmp as (
 select
    brand
    ,start_date
    ,end_date
    ,max(end_date) over(partition by brand order by start_date asc,end_date asc rows between unbounded preceding and 1 preceding) as max_end_date
 from test.t1_marketing_act
)
,tmp1 as (
    select
    brand
    ,start_date
    ,end_date
    ,max_end_date
    ,sum(if(start_date <=max_end_date,0,1)) over(partition by brand order by start_date asc,end_date asc) as group_id
from tmp
)
,tmp2 as (
    select
    brand
    ,group_id
    ,(unix_timestamp(max(end_date),'yyyy-MM-dd') - unix_timestamp(min(start_date),'yyyy-MM-dd'))/60/60/24+1 as days
from tmp1
group by brand,group_id
)
select
    brand
    ,sum(days) as days
from tmp2
group by brand


---------------------------------------------------------------------------------------------------------------------
已知有

购买记录表t2_order,包含自增id:id,用户ID:user_id，商品ID:goods_id,订单时间：order_time,商品类别：goods_type;

用户收藏记录表t2_collect_log,包含自增id，用户ID:user_id，商品ID：goods_id，收藏时间 collect_time

请用一句sql语句得出以下查询结果，得到所有用户的商品行为特征，其中用户行为分类为4种：是否已购买、购买未收藏、收藏未购买、收藏且购买。

购买记录表t2_order

+-----+----------+-----------+-------------------+-------------+
| id  | user_id  | goods_id  |    order_time     | goods_type  |
+-----+----------+-----------+-------------------+-------------+
| 1   | 1        | 201       | 2020/11/14 10:00  | 1           |
| 2   | 2        | 203       | 2020/11/15 12:00  | 2           |
| 3   | 3        | 203       | 2020/11/16 10:00  | 1           |
| 4   | 4        | 203       | 2020/11/17 10:00  | 1           |
| 5   | 5        | 203       | 2020/11/18 10:00  | 1           |
| 6   | 6        | 203       | 2020/11/18 11:00  | 1           |
| 7   | 7        | 204       | 2020/11/18 12:00  | 1           |
| 8   | 8        | 205       | 2020/11/18 11:30  | 1           |
| 9   | 9        | 206       | 2020/12/1 10:00   | 1           |
| 10  | 4        | 207       | 2020/12/2 10:00   | 3           |
| 11  | 5        | 208       | 2020/12/3 10:00   | 1           |
| 12  | 6        | 209       | 2020/12/4 8:00    | 2           |
| 13  | 7        | 203       | 2020/12/5 10:00   | 2           |
| 14  | 8        | 203       | 2020/12/6 10:00   | 3           |
| 15  | 9        | 203       | 2020/12/7 15:00   | 4           |
| 16  | 1        | 204       | 2020/12/8 10:00   | 5           |
| 17  | 2        | 204       | 2020/12/9 10:00   | 5           |
| 18  | 3        | 206       | 2020/12/10 10:00  | 5           |
| 19  | 4        | 208       | 2020/12/11 10:00  | 5           |
| 20  | 5        | 209       | 2020/12/12 19:00  | 5           |
+-----+----------+-----------+-------------------+-------------+
收藏记录表t2_collect_log

+-----+----------+-----------+-------------------+
| id  | user_id  | goods_id  |   collect_time    |
+-----+----------+-----------+-------------------+
| 1   | 1        | 203       | 2020/11/14 12:00  |
| 2   | 9        | 203       | 2020/11/15 10:00  |
| 3   | 4        | 203       | 2020/11/16 10:00  |
| 4   | 5        | 203       | 2020/11/17 10:00  |
| 5   | 6        | 203       | 2020/11/17 11:00  |
| 6   | 7        | 204       | 2020/11/17 12:00  |
| 7   | 8        | 205       | 2020/11/18 11:30  |
| 8   | 9        | 212       | 2020/12/1 10:00   |
| 9   | 4        | 207       | 2020/12/2 10:00   |
| 10  | 5        | 213       | 2020/12/3 10:00   |
| 11  | 6        | 209       | 2020/12/4 8:00    |
| 12  | 7        | 203       | 2020/12/5 10:00   |
| 13  | 8        | 203       | 2020/12/6 10:00   |
| 14  | 9        | 203       | 2020/12/7 15:00   |
| 15  | 1        | 203       | 2020/12/8 10:00   |
| 16  | 2        | 204       | 2020/12/9 10:00   |
| 17  | 3        | 205       | 2020/12/10 8:00   |
| 18  | 4        | 208       | 2020/12/11 10:00  |
| 19  | 5        | 209       | 2020/12/10 19:00  |
| 20  | 7        | 201       | 2020/12/11 19:00  |
+-----+----------+-----------+-------------------+
期望结果

+----------+-----------+---------+------------------+------------------+------------------+
| user_id  | goods_id  | is_buy  | buy_not_collect  | collect_not_buy  | buy_and_collect  |
+----------+-----------+---------+------------------+------------------+------------------+
| 1        | 201       | 1       | 1                | 0                | 0                |
| 1        | 203       | 0       | 0                | 1                | 0                |
| 1        | 204       | 1       | 1                | 0                | 0                |
| 2        | 203       | 1       | 1                | 0                | 0                |
| 2        | 204       | 1       | 0                | 0                | 1                |
| 3        | 203       | 1       | 1                | 0                | 0                |
| 3        | 205       | 0       | 0                | 1                | 0                |
| 3        | 206       | 1       | 1                | 0                | 0                |
| 4        | 203       | 1       | 0                | 0                | 1                |
| 4        | 207       | 1       | 0                | 0                | 1                |
| 4        | 208       | 1       | 0                | 0                | 1                |
| 5        | 203       | 1       | 0                | 0                | 1                |
| 5        | 208       | 1       | 1                | 0                | 0                |
| 5        | 209       | 1       | 0                | 0                | 1                |
| 5        | 213       | 0       | 0                | 1                | 0                |
| 6        | 203       | 1       | 0                | 0                | 1                |
| 6        | 209       | 1       | 0                | 0                | 1                |
| 7        | 201       | 0       | 0                | 1                | 0                |
| 7        | 203       | 1       | 0                | 0                | 1                |
| 7        | 204       | 1       | 0                | 0                | 1                |
| 8        | 203       | 1       | 0                | 0                | 1                |
| 8        | 205       | 1       | 0                | 0                | 1                |
| 9        | 203       | 1       | 0                | 0                | 1                |
| 9        | 206       | 1       | 1                | 0                | 0                |
| 9        | 212       | 0       | 0                | 1                | 0                |
+----------+-----------+---------+------------------+------------------+------------------+
with tmp as (
select
    user_id,
    goods_id,
    max(order_time) as max_order_time
from test.t2_order
group by user_id,goods_id

)
,tmp1 as (
select
    user_id,
    goods_id,
    max(collect_time) as max_collect_time
from test.t2_collect_log
group by user_id,goods_id
)
select
    coalesce(tmp.user_id,tmp1.user_id) as user_id
    ,coalesce(tmp.goods_id,tmp1.goods_id) as goods_id
    ,if(tmp.user_id is not null,1,0) as is_buy
    ,if(tmp.user_id is not null and tmp1.user_id is null,1,0) as buy_not_collect
    ,if(tmp1.user_id is not null and tmp.user_id is null,1,0) as collect_not_buy
    ,if(tmp.user_id is not null and tmp1.user_id is not null,1,0) as buy_and_collect
from tmp
full join tmp1 on tmp.user_id=tmp1.user_id and tmp.goods_id=tmp1.goods_id

----------------------------------------------------------------------------------------------
现有一张订单表 t3_order 有订单ID、用户ID、商品ID、购买商品数量、购买时间，请查询出每个用户的第一条记录和最后一条记录。样例数据如下：

+-----------+----------+-------------+-----------+------------------------+
| order_id  | user_id  | product_id  | quantity  |     purchase_time      |
+-----------+----------+-------------+-----------+------------------------+
| 1         | 1        | 1001        | 1         | 2023-03-13 08:30:00.0  |
| 2         | 1        | 1002        | 1         | 2023-03-13 10:45:00.0  |
| 3         | 1        | 1001        | 1         | 2023-03-13 10:45:01.0  |
| 4         | 2        | 1001        | 3         | 2023-03-13 14:20:00.0  |
| 5         | 3        | 1003        | 1         | 2023-03-13 16:15:00.0  |
| 6         | 3        | 1002        | 1         | 2023-03-13 12:10:00.0  |
| 7         | 3        | 1001        | 1         | 2023-03-13 12:10:01.0  |
| 8         | 4        | 1002        | 2         | 2023-03-13 09:00:00.0  |
| 9         | 4        | 1003        | 1         | 2023-03-13 11:30:00.0  |
| 10        | 4        | 1004        | 3         | 2023-03-13 13:40:00.0  |
| 11        | 4        | 1001        | 1         | 2023-03-13 17:25:00.0  |
| 12        | 4        | 1002        | 2         | 2023-03-13 15:05:00.0  |
| 13        | 4        | 1004        | 1         | 2023-03-13 11:55:00.0  |
+-----------+----------+-------------+-----------+------------------------+

with tmp as (
    select
    user_id
    ,order_id
    ,product_id
    ,quantity
    ,purchase_time
    ,first_value(order_id) over(partition by user_id order by purchase_time asc) as first_order_id
    ,first_value(order_id) over(partition by user_id order by purchase_time desc) as last_order_id
from test.t3_order
)
select
    order_id
    ,user_id
    ,product_id
    ,quantity
    ,purchase_time
from tmp
where order_id=first_order_id
union all
select
    order_id
    ,user_id
    ,product_id
    ,quantity
    ,purchase_time
from tmp
where order_id=last_order_id and first_order_id!=last_order_id


---------------------------------------------------------------------------------
有某城市网吧上网记录表，包含字段：网吧id，访客id（身份证号），上线时间，下线时间。

规则1：如果两个用户在同一个网吧上线时间或者下线时间间隔在10分钟以内，则两个用户可能认识；
规则2：如果两个用户在三家以上的网吧出现过【规则1】可能认识的情况，则两人一定认识；
请计算该市中两人一定认识的组合数

+---------+----------+----------------------+----------------------+
| bar_id  | user_id  |      login_time      |     logoff_time      |
+---------+----------+----------------------+----------------------+
| 1       | 001      | 2023-08-01 09:00:00  | 2023-08-01 10:00:00  |
| 1       | 003      | 2023-08-01 09:04:00  | 2023-08-01 11:00:00  |
| 2       | 004      | 2023-08-01 10:00:00  | 2023-08-01 12:02:00  |
| 1       | 006      | 2023-08-01 10:00:00  | 2023-08-01 12:00:00  |
| 2       | 005      | 2023-08-01 10:10:00  | 2023-08-01 11:00:00  |
| 2       | 001      | 2023-08-01 11:01:00  | 2023-08-01 12:00:00  |
| 2       | 002      | 2023-08-01 11:03:00  | 2023-08-01 14:00:00  |
| 3       | 002      | 2023-08-02 15:00:00  | 2023-08-02 17:06:00  |
| 3       | 001      | 2023-08-02 16:01:00  | 2023-08-02 17:07:00  |
| 3       | 004      | 2023-08-02 16:02:00  | 2023-08-02 18:00:00  |
| 3       | 003      | 2023-08-02 20:00:00  | 2023-08-02 22:00:00  |
| 4       | 001      | 2023-08-03 17:00:00  | 2023-08-03 19:00:00  |
| 4       | 002      | 2023-08-03 18:00:00  | 2023-08-03 21:00:00  |
| 4       | 003      | 2023-08-03 18:05:00  | 2023-08-03 22:00:00  |
| 4       | 004      | 2023-08-03 19:00:00  | 2023-08-03 18:58:00  |
+---------+----------+----------------------+----------------------+

select a.user_id ,b.user_id
from test.t2_netbar a
left join test.t2_netbar b on a.bar_id = b.bar_id and a.user_id < b.user_id
and (
   (unix_timestamp(a.login_time)+10*60>=unix_timestamp(b.login_time) and a.login_time<=b.login_time)
or (unix_timestamp(a.login_time)-10*60<=unix_timestamp(b.login_time) and a.login_time>=b.login_time)
or (unix_timestamp(a.logoff_time)+10*60>=unix_timestamp(b.logoff_time) and a.logoff_time<=b.logoff_time)
or (unix_timestamp(a.logoff_time)-10*60<=unix_timestamp(b.logoff_time) and a.logoff_time>=b.logoff_time)
)
where b.bar_id is not null
group by a.user_id ,b.user_id
having count(distinct a.bar_id)>=3


---------------------------------------------------------------------------------------------------
现有三张表分别为：

用户关注表t1_follow(user_id,follower_id)记录用户ID及其关注的人ID，请给用户1推荐他关注的用户喜欢的音乐名称

+----------+--------------+
| user_id  | follower_id  |
+----------+--------------+
| 1        | 2            |
| 1        | 4            |
| 1        | 5            |
+----------+--------------+
用户喜欢的音乐t1_music_likes(user_id,music_id)

+----------+-----------+
| user_id  | music_id  |
+----------+-----------+
| 1        | 10        |
| 2        | 20        |
| 2        | 30        |
| 3        | 20        |
| 3        | 30        |
| 4        | 40        |
| 4        | 50        |
+----------+-----------+
音乐名字表t1_music(music_id,music_name)

+-----------+-------------+
| music_id  | music_name  |
+-----------+-------------+
| 10        | a           |
| 20        | b           |
| 30        | c           |
| 40        | d           |
| 50        | e           |
+-----------+-------------+


select b.user_id
    ,concat_ws('_',collect_set(c.music_name)) as musics
from test.t1_music_likes a
inner join test.t1_follow b on a.user_id=b.follower_id and b.user_id=1
left join test.t1_music c on a.music_id=c.music_id
group by b.user_id


----------------------------------------------------------------------------
有两个表，朋友关系表t2_user_friend，用户步数表t2_user_steps。朋友关系表包含两个字段，用户id，用户好友的id；用户步数表包含两个字段，用户id，用户的步数

查询： 占据多少个好友的封面（在好友的列表中排行第一，且必须超过好友的步数）

-- t2_user_friend 数据
+----------+------------+
| user_id  | friend_id  |
+----------+------------+
| 1        | 2          |
| 1        | 3          |
| 2        | 1          |
| 2        | 3          |
| 2        | 4          |
| 2        | 5          |
| 3        | 1          |
| 3        | 4          |
| 3        | 5          |
| 4        | 2          |
| 4        | 3          |
| 4        | 5          |
| 5        | 2          |
| 5        | 3          |
| 5        | 4          |
+----------+------------+
--t2_user_friend数据
+---------------------+-------------------+
| t2_user_steps.user_id  | t2_user_steps.steps  |
+---------------------+-------------------+
| 1                   | 100               |
| 2                   | 95                |
| 3                   | 90                |
| 4                   | 80                |
| 5                   | 10                |
+---------------------+-------------------+

with tmp as (
    select
    a.user_id
    ,a.friend_id
    ,b.steps as user_step
    ,c.steps as friend_step
    ,first_value(a.friend_id) over(partition by a.user_id order by c.steps desc) as f_id
from test.t2_user_friend a
left join test.t2_user_steps b on a.user_id=b.user_id
left join test.t2_user_steps c on a.friend_id = c.user_id
)
,tmp2 as (
select
    f_id
    ,count(distinct user_id ) as cnt
from tmp
where user_step<friend_step
group by f_id
)
,tmp3 as (
    select user_id
    from  test.t2_user_friend
    group by user_id
)
select
    tmp3.user_id
    ,coalesce(tmp2.cnt,0) as res
from tmp3
left join tmp2 on tmp3.user_id =tmp2.f_id


-----------------------------------------------------------------------
现有一张用户支付表：t3_user_pay包含字段订单ID,用户ID,商户ID,支付时间,支付金额。
如果同一用户在同一商户存在多笔订单，且中间该用户没有其他商户的支付记录，则认为是连续订单，请把连续订单进行合并，时间取最早支付时间，金额求和。

样例数据如下：

+----------+---------+-------------+---------------------+--------------+
| order_id | user_id | merchant_id |      pay_time        |  pay_amount  |
+----------+---------+-------------+---------------------+--------------+
| 001      | user_01 | merchant_01 | 2023-03-01 12:30:00 | 50.0         |
| 002      | user_01 | merchant_01 | 2023-03-01 13:45:00 | 75.5         |
| 003      | user_01 | merchant_02 | 2023-03-01 14:00:00 | 100.0        |
| 004      | user_01 | merchant_03 | 2023-03-02 09:15:00 | 25.0         |
| 005      | user_01 | merchant_03 | 2023-03-02 10:30:00 | 150.25       |
| 006      | user_01 | merchant_01 | 2023-03-02 11:00:00 | 500.0        |
| 007      | user_01 | merchant_02 | 2023-03-03 08:00:00 | 80.0         |
| 008      | user_02 | merchant_01 | 2023-03-03 09:30:00 | 120.0        |
| 009      | user_02 | merchant_02 | 2023-03-04 13:45:00 | 65.0         |
| 010      | user_02 | merchant_03 | 2023-03-04 14:00:00 | 150.0        |
| 011      | user_02 | merchant_03 | 2023-03-05 11:30:00 | 20.0         |
| 012      | user_02 | merchant_03 | 2023-03-05 12:00:00 | 105.0        |
| 013      | user_03 | merchant_02 | 2023-03-05 13:15:00 | 250.0        |
| 014      | user_03 | merchant_01 | 2023-03-06 09:45:00 | 30.0         |
| 015      | user_03 | merchant_02 | 2023-03-06 10:00:00 | 90.5         |
+----------+---------+-------------+---------------------+--------------+

with tmp as (
select
    user_id
    ,merchant_id
    ,pay_time
    ,pay_amount
    ,row_number() over(partition by user_id order by pay_time) as u_rn
    ,row_number() over(partition by user_id,merchant_id order by pay_time) as u_m_rn
from test.t3_user_pay
)
select
    user_id
    ,merchant_id
    ,min(pay_time) as min_pay_time
    ,sum(pay_amount) as sum_pay_amount
from tmp
group by user_id,merchant_id,u_rn-u_m_rn

----------------------------------------------------------------------------------------------
现有一张股票价格表t4_stock_data有3个字段分别是股票代码(stock_code),日期(trade_date)，收盘价格(closing_price) ，
请找出满足连续5天以上（含）每天上涨超过5%的股票,并给出连续满足天数及开始和结束日期。 备注：不考虑停牌或其他情况，仅仅关注每天连续5天上涨超过5%的股票。

样例数据

+-------------+-------------+----------------+
| stock_code  | trade_date  | closing_price  |
+-------------+-------------+----------------+
| AAPL        | 2023-02-26  | 100.00         |
| AAPL        | 2023-02-27  | 105.00         |
| AAPL        | 2023-02-28  | 110.25         |
| AAPL        | 2023-03-01  | 115.78         |
| AAPL        | 2023-03-02  | 121.59         |
| AAPL        | 2023-03-03  | 128.73         |
| AAPL        | 2023-03-04  | 137.00         |
| AAPL        | 2023-03-05  | 144.67         |
| AAPL        | 2023-03-06  | 147.64         |
| GOOG        | 2023-02-26  | 2000.00        |
| GOOG        | 2023-02-27  | 2100.00        |
| GOOG        | 2023-02-28  | 2205.00        |
| GOOG        | 2023-03-01  | 2313.25        |
| GOOG        | 2023-03-02  | 2431.01        |
| GOOG        | 2023-03-03  | 2547.56        |
| GOOG        | 2023-03-04  | 2680.19        |
| GOOG        | 2023-03-05  | 2814.20        |
| GOOG        | 2023-03-06  | 2955.91        |
+-------------+-------------+----------------+

with tmp as (
select
    stock_code
    ,trade_date
    ,closing_price
    ,lag(closing_price) over(partition by stock_code order by trade_date) as pre_closing_price
from test.t4_stock_data
)
,tmp2 as (
    select
    stock_code
    ,trade_date
    ,closing_price
    ,pre_closing_price
    ,if(pre_closing_price is null,0,(closing_price-pre_closing_price)/pre_closing_price*100) as per
    ,if(if(pre_closing_price is null,0,(closing_price-pre_closing_price)/pre_closing_price*100)>=5,0,1) as is_continue
from tmp
)
,tmp3 as (
    select
    stock_code
    ,trade_date
    ,closing_price
    ,pre_closing_price
    ,per
    ,is_continue
    ,sum(is_continue) over(partition by stock_code order by trade_date) as group_id
from tmp2
)
select stock_code
        ,count(1) as days
        ,min(trade_date) as min_trade_date
        ,max(trade_date) as max_trade_date
from tmp3
where pre_closing_price is not null
group by stock_code,group_id
having count(1)>=5
