/*
140. Word Break II My Submissions Question
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
*/

public class Solution {
    
 // Solution 1
    public List<String> wordBreak(String s, Set<String> dict) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        return helpBreak(s,dict,map);
    }
    
    private List<String> helpBreak(String word, Set<String> dict, Map<String, List<String>> memo){
        if(memo.containsKey(word)){
            return memo.get(word);
        }
        
        List<String> res = new LinkedList<>();
        int len = word.length();
        if(len == 0){
            memo.put(word, res);
            return res;
        }
        
        for(int i = 1; i <= word.length(); i++)
        {
            String prefix = word.substring(0, i);
            
            if(dict.contains(prefix))
            {
                if(i == len)
                {
                    res.add(prefix);
                }else{
                    List<String> right = helpBreak(word.substring(i), dict, memo);

                    for(String temp : right)
                    {
                        temp = prefix + " " + temp;
                        res.add(temp);
                    }
                }
            }
        }
        
        memo.put(word, res);
        return res;
    }

// Solution 2
     private Map<String, List<String>> cache = new HashMap<String, List<String>>();
     
     public List<String> wordBreak1(String s, Set<String> dict)
     {
         if(cache.containsKey(s)){
             return cache.get(s);
         }
         List<String> res = new LinkedList<String>();
         if(dict.contains(s)){
             res.add(s);
         }
         
         for(int i = 1; i<= s.length(); i++)
         {
             String left = s.substring(0, i);
             String right = s.substring(i);
             if(dict.contains(left) && containsSuffix(right, dict))
             {
                 for(String temp : wordBreak(right, dict))
                 {
                     res.add(left + " " + temp);
                 }
             }
         }
         cache.put(s, res);
         return res;
     }
    
    private boolean containsSuffix(String s, Set<String> dict)
    {
        for(int i = 0; i < s.length(); i++){
            if(dict.contains(s.substring(i))){
                return true;
            }
        }
        return false;
    }
    
   // Solution 3 
   private class Result{
        public boolean isBreakable;
        public List<String> result;
        public String sig;
        
        public Result(boolean isBreakable, List<String> result, String signature)
        {
            this.isBreakable = isBreakable;
            this.result = result;
            this.sig = signature;
        }
    }
    
    public List<String> wordBreak2(String s, Set<String> wordDict) {
        return help(s, wordDict);
    }
    
    public List<String> help(String s, Set<String> dict){
        
        List<String> re = new LinkedList<String>();
        if(s == null || s.length() == 0) return re;
        
        Map<String, Result> m = new HashMap<String, Result>();
        Map<String, List<String>> buffer = new HashMap<String, List<String>>();
        
        isBreak(s, dict, m, buffer);
        
        return buffer.containsKey(s) ? buffer.get(s) : re;
    }
    

    private Result isBreak(String s,  Set<String> dict, Map<String, Result> visited, Map<String, List<String>>res){
        
        if(s == null || s.equals(""))
        {
            return new Result(true, new LinkedList<String>(), s);
        }
        
        if(visited.containsKey(s))
        {
            return visited.get(s);
        }
        
        boolean isBreakable = false;
        StringBuilder t = new StringBuilder();
       
        for(int i = 0; i <= s.length(); i++)
        {
           List<String> temp = new LinkedList<String>();
             
            if(dict.contains(s.substring(0, i)))
            {
                t.append(s.substring(0, i));
                
                Result re = isBreak(s.substring(i), dict, visited, res);
                if(re.isBreakable)
                {
                    if(re.result.size() == 0){
                        temp.add(t.toString());
                    }else{
                        for(String st : re.result)
                        {
                            int len0 = t.length();
                            t.append(" ").append(st);
                            temp.add(t.toString());
                            t.setLength(len0);
                        }
                    }
                    
                    if(!res.containsKey(s))
                    {
                        res.put(s, new LinkedList<String>());
                    }
                    res.get(s).addAll(temp);
                    isBreakable = true; 
                }
                t.setLength(0);
            }
        }
        
        Result re = new Result(isBreakable, res.get(s), s);
        visited.put(s, re);
        
        return re;
    }
}