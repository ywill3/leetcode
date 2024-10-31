package com.will.leetcode.P150To200;

public class Q165 {
    public int compareVersion(String version1, String version2) {
        String version1s[] = version1.split("\\.");
        String version2s[] = version2.split("\\.");
        int max = Math.max(version1s.length,version2s.length);
        for (int i=0;i<max;i++) {
            int v1 = i < version1s.length ? Integer.parseInt(version1s[i]) : 0;
            int v2 = i < version2s.length ? Integer.parseInt(version2s[i]) : 0;
            if (v1 > v2) {
                return 1;
            } else if (v1 < v2) {
                return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Q165 q = new Q165();
        System.out.println(q.compareVersion("1.2","1.10"));
        System.out.println(q.compareVersion("1.01","1.001"));
        System.out.println(q.compareVersion("1.0","1.0.0.0"));
    }
}
