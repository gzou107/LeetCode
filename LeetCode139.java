/*
139. Word Break My Submissions Question
Total Accepted: 75599 Total Submissions: 311697 Difficulty: Medium
Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
*/

public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
 
     return help0(s, dict);
     /*
     Map<String, Boolean> m = new HashMap<String, Boolean>();
      help1(s, dict, m);
     return m.get(s);
     */
     
     //return help2(s, dict);
    }
    
    public boolean help1(String s, Set<String> dict, Map<String,Boolean> visited)
    {
        if(s == null || s.length() == 0) return true;
        
        if(visited.containsKey(s)){
            return visited.get(s);
        }
        
        for(int i = 0; i <=s.length(); i++)
        {
            if(dict.contains(s.substring(0, i)))
            {
                 boolean sub = help1(s.substring(i), dict, visited);
                 if(sub)
                 {
                    visited.put(s, true);
                    return true;
                 }
            }
        }
        visited.put(s, false);
        return false;
    }
    
    public boolean help0(String s, Set<String> dict)
    {
       if(s == null || s.length() == 0) return true;
       
       // break[i] represents from [0, i), where i is not included
       boolean [] br = new boolean[s.length()+1];
       br[0] = true;
       
       for(int i = 1; i <= s.length(); i++)
       {
           for(int j = 0; j < i; j++)
           {
               if(br[j] && dict.contains(s.substring(j, i))){
                   br[i] = true;
                   break;
               }
           }
       }
       
        return br[s.length()];
    }
        // TLE
    private class Trie
    {
        private class TrieNode
        {
            public boolean isWord;
            public TrieNode [] children;
            
            public TrieNode(){
                children = new TrieNode[256];
            }
        }
        
        private TrieNode root;
        public Trie(){
            root = new TrieNode();
        }
        
        public void insert(String word)
        {
            TrieNode current = root;
            for(int i = 0; i < word.length(); i++)
            {
                char c = word.charAt(i);
                if(current.children[c] == null)
                {
                    current.children[c] = new TrieNode();
                }
                current = current.children[c];
            }
            current.isWord = true;
        }
        
        public boolean search(String word)
        {
            TrieNode current = root;
            for(int i = 0; i < word.length(); i++)
            {
                char c = word.charAt(i);
                if(current.children[c] == null)
                {
                    return false;
                }
                current = current.children[c];
            }
            return current.isWord;
        }
        
        // return whether there is any prefix of word starting at startPos
        //  exists in the dict; and save all such pos into candidatePos
        private boolean find(String word, int startPos, List<Integer> candidatePos)
        {
            candidatePos.clear();
            if(startPos == word.length()){
                candidatePos.add(startPos);
                return true;
            }
            
            boolean findOne = false;
            TrieNode current = root;
            for(int i = startPos; i < word.length(); i++)
            {
                char c = word.charAt(i);
                if(current.children[c] == null){
                    return findOne;
                }
                
                if(current.children[c].isWord){
                    candidatePos.add(i+1);
                    findOne = true;
                }
                current = current.children[c];
            }
            return findOne;
        }
        
        public boolean wordBreak(String word)
        {
            if(word == null || word.length() == 0) return true;
            
            List<Integer>candidatePos = new LinkedList<>();
            if(find(word, 0, candidatePos))
            {
                Queue<Integer> q = new LinkedList<>();
                Set<Integer> visited = new HashSet<Integer>();
                
                for(int num : candidatePos){
                    q.offer(num);
                    visited.add(num);
                }
                
                while(!q.isEmpty())
                {
                    int top = q.poll();
                    
                    if(top == word.length()){
                        return true;
                    }
                    
                    if(find(word, top, candidatePos))
                    {
                        for(int num : candidatePos)
                        {
                            if(!visited.contains(num))
                            {
                                q.offer(num);
                            }
                        }
                    }
                }
                
            }
            
            return false;
        }
    }
    // TLE
    public boolean help2(String s, Set<String> dict)
    {
        if(s == null || s.length() == 0) return true;
        
        for(int i = 0; i <= s.length(); i++)
        {
            if(dict.contains(s.substring(0, i)) && help2(s.substring(i), dict)){
                return true;
            }
        }
        return false;
    }
}