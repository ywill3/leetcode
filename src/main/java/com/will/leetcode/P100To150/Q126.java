package com.will.leetcode.P100To150;

import java.util.*;

public class Q126 {
    int[] dp = new int[501];

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        Set<String> visited = new HashSet<>();
        queue.add(beginWord);

        int changes = 1;

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                String word = queue.poll();
                if(word.equals(endWord)) return changes;

                for(int j = 0; j < word.length(); j++){
                    for(int k = 'a'; k <= 'z'; k++){
                        char arr[] = word.toCharArray();
                        arr[j] = (char) k;

                        String str = new String(arr);
                        if(set.contains(str) && !visited.contains(str)){
                            queue.add(str);
                            visited.add(str);
                        }
                    }
                }
            }
            ++changes;
        }
        return 0;
    }
    public List<List<String>> findLadders1(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res=new ArrayList<>();
        List<String> path=new ArrayList<>();
        int minLen = ladderLength(beginWord, endWord, wordList);
        System.out.println(minLen);
        if (!wordList.contains(endWord) || minLen==0){
            return res;
        }
        path.add(beginWord);
        for (int i=0;i<wordList.size();i++){
            dp[i]=Integer.MAX_VALUE;
        }
        digui(beginWord,endWord,wordList,path,res,minLen);
        return res;
    }

    private void digui(String beginWord, String endWord, List<String> wordList, List<String> path, List<List<String>> res,int minLen) {
        String lastWord = path.get(path.size() - 1);
        if (path.size()>minLen){
            return;
        }
/*        if (isOneDiff(lastWord,endWord)){
            ArrayList<String> pt = new ArrayList<>(path);
            pt.add(endWord);
            res.add(pt);
            return;
        }*/
        if (lastWord.equals(endWord)){
            res.add(new ArrayList<>(path));
            return;
        }


        for (int i=0;i<wordList.size();i++){
            String ths = wordList.get(i);
            if (isOneDiff(lastWord,ths) && !path.contains(ths)){
                if (dp[i]>=path.size()){
                    dp[i] = path.size();
                    path.add(ths);
                    digui(beginWord,endWord,wordList,path,res,minLen);
                    path.remove(ths);
                }
            }
        }
    }

    // 解释一下下面这个方法思路
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);

        if (!wordSet.contains(endWord)) {
            return result;
        }

        Map<String, List<String>> graph = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Queue<List<String>> queue = new LinkedList<>();
        queue.add(Arrays.asList(beginWord));

        boolean found = false;

        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            Set<String> currentLevelVisited = new HashSet<>();

            for (int i = 0; i < size; i++) {
                List<String> path = queue.poll();
                String currentWord = path.get(path.size() - 1);

                char[] charArray = currentWord.toCharArray();

                for (int j = 0; j < charArray.length; j++) {
                    char originalChar = charArray[j];

                    for (char c = 'a'; c <= 'z'; c++) {
                        charArray[j] = c;
                        String transformedWord = new String(charArray);

                        if (wordSet.contains(transformedWord) && !visited.contains(transformedWord)) {
                            if (transformedWord.equals(endWord)) {
                                found = true;
                                path.add(endWord);
                                result.add(new ArrayList<>(path));
                                path.remove(path.size() - 1);
                            }

                            currentLevelVisited.add(transformedWord);
                            path.add(transformedWord);
                            queue.add(new ArrayList<>(path));
                            path.remove(path.size() - 1);

                            if (!graph.containsKey(currentWord)) {
                                graph.put(currentWord, new ArrayList<>());
                            }
                            graph.get(currentWord).add(transformedWord);
                        }
                    }

                    charArray[j] = originalChar;
                }
            }

            visited.addAll(currentLevelVisited);
        }

        return result;
    }


    private boolean isOneDiff(String lastWord, String ths) {
        int diff=0;
        for (int i=0;i<lastWord.length();i++){
            if (lastWord.charAt(i)!=ths.charAt(i)){
                diff++;
            }
        }
        if (diff==1){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        Q126 q126 = new Q126();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(List.of("hot","dot","dog","lot","log","cog"));
        System.out.println(q126.findLadders(beginWord,endWord,wordList));
    }
}
