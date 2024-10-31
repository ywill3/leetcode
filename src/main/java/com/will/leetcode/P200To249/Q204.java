package com.will.leetcode.P200To249;

import java.util.ArrayList;
import java.util.List;

public class Q204 {
    public int countPrimes(int n) {
        int[] tp = new int[n];
        for (int i=2;i*i<n;i++){
            for (int j=2;i*j<n;j++){
                tp[i*j]=1;
            }
        }
        int count=0;
        for (int i=2;i<n;i++){
            if (tp[i]==0){
                count++;
            }
        }
        return count;
    }

    public boolean isPrime(int n){
        // 1 is not prime
        if (n<=1){
            return false;
        }
        // 2 and 3 are prime
        if (n<=3){
            return true;
        }
        // if n is divisible by 2 or 3, it is not prime
        if (n%2==0 || n%3==0){
            return false;
        }
        // check all numbers from 5 to sqrt(n)
        for (int i=5;i*i<=n;i+=6){
            if (n%i==0 || n%(i+2)==0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Q204 q = new Q204();
        for (int i=0;i<100;i++){
            System.out.println(i+":"+q.countPrimes(i));

        }
    }
}
