create table user_login_log(
    user_id    bigint  comment '用户id',
    dt         string  comment '登录日期'
)COMMENT '用户登录日志表';



--次日留存率 次日用户中在当日登录的数量/当日用户数量
with tmp as (
select user_id,dt
from user_login_log
where dt >= '7days' and dt<='yes'
group by user_id,dt
)

select
    count(tod.user_id)/count( yes.user_id) as rate
from tmp yes
left join tmp tod on yes.user_id = tod.user_id and yes.dt = date_sub(tod.dt,1)
where dt = 'yes'

--3日留存率
select
    count( tod.user_id)/count( yes.user_id) as rate
from tmp yes
left join tmp tod on yes.user_id = tod.user_id and yes.dt = date_sub(tod.dt,3)
where dt = 'yes'

--7日留存率
select
    count(tod.user_id)/count( yes.user_id) as rate
from tmp yes
left join tmp tod on yes.user_id = tod.user_id and yes.dt = date_sub(tod.dt,7)
where dt = 'yes'



