package com.will.leetcode.P150To200;

import java.util.ArrayList;
import java.util.List;

public class Q166 {
    public static void main(String[] args) {
        Q166 q = new Q166();
        System.out.println(q.fractionToDecimal(2,5));
        System.out.println(q.fractionToDecimal(1, 2));
        System.out.println(q.fractionToDecimal(2, 1));
        System.out.println(q.fractionToDecimal(2, 3));
        System.out.println(q.fractionToDecimal(4, 333));
        System.out.println(q.fractionToDecimal(1,6));
        System.out.println(q.fractionToDecimal(50,-8));
        System.out.println(q.fractionToDecimal(7,-12));
        System.out.println(q.fractionToDecimal(-1,-2147483648));
        System.out.println(q.fractionToDecimal(-2147483648,1));
    }

    public String fractionToDecimal(int numerator, int denominator) {
        List<Long> tmpYu = new ArrayList<>();
        List<Long> tmpChu = new ArrayList<>();
        boolean negFlag = false;
        if (numerator*1.0/denominator<0){
            negFlag=true;
        }
        long chu = Math.abs(numerator*1L/denominator*1L);
        long yu = Math.abs(numerator*1L%(denominator*1L));
        int startRepeat = -1;
        while (yu!=0){
            if(tmpYu.contains(yu)){
                // repeat
                startRepeat = tmpYu.indexOf(yu);
                break;
            }else{
                tmpChu.add(chu);
                tmpYu.add(yu);
                chu = Math.abs(yu)*10L/Math.abs(denominator*1L);
                yu = (Math.abs(yu)*10L)%Math.abs(denominator*1L);
            }
        }
        tmpChu.add(chu);
        tmpYu.add(yu);
        StringBuilder sb = new StringBuilder();
        if (negFlag){
            sb.append("-");
        }
        sb.append(tmpChu.get(0));
        if (tmpChu.size()>1){
            sb.append(".");
        }
        if (startRepeat==-1){
            for (int i=1;i<tmpChu.size();i++){
                sb.append(tmpChu.get(i));
            }
        }else {
            for (int i=1;i<startRepeat+1;i++){
                sb.append(tmpChu.get(i));
            }
            sb.append("(");
            for (int i=startRepeat+1;i<tmpChu.size();i++){
                sb.append(tmpChu.get(i));
            }
            sb.append(")");
        }

        return sb.toString();
    }

}
