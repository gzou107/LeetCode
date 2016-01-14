/*
245. Shortest Word Distance III My Submissions Question
This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “makes”, word2 = “coding”, return 1.
Given word1 = "makes", word2 = "makes", return 3.

Note:
You may assume word1 and word2 are both in the list.
*/


public class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) 
    {
        return helper3(words, word1, word2);
    }

    public int helper0(String [] words, String word1, String word2)
    {
        int min = Integer.MAX_VALUE;
        int d = 0;
        if(word1.equals(word2))
        {
            int pos = -1;
            for(int i = 0; i < words.length; i++)
            {
                if(words[i].equals(word1))
                {
                    if(pos != -1)
                    {
                        d = i - pos;
                        min = min > d ? d : min;
                    }
                    
                    pos = i;
                }
            }
        }else
        {
            int pos1 = -1;
            int pos2 = -1;
            
            for(int i = 0; i < words.length; i++)
            {
                if(words[i].equals(word1))
                {
                    pos1 = i;
                    
                    if(pos2 != -1)
                    {
                        d = pos1 - pos2;
                        min = min > d ? d : min;
                    }
                }else if(words[i].equals(word2))
                {
                    pos2 = i;
                    
                    if(pos1 != -1)
                    {
                        d = pos2 - pos1;
                        min = min > d ? d : min;
                    }
                }
            }
           
        }
        
        return min;
    }
    
    public int helper1(String [] words, String word1, String word2)
    {
        int idx1 = -1, idx2 = -1, distance = Integer.MAX_VALUE, turn = 0, inc = (word1.equals(word2) ? 1 : 0);
        for(int i = 0; i < words.length; i++){
            if(words[i].equals(word1) && turn % 2 == 0){
                idx1 = i;
                if(idx2 != -1) distance = Math.min(distance, idx1 - idx2);
                turn += inc;
            } else if(words[i].equals(word2)){
                idx2 = i;
                if(idx1 != -1) distance = Math.min(distance, idx2 - idx1);
                turn += inc;
            }
        }
        return distance;
    }
    
    public int helper2(String [] words, String word1, String word2)
    {
        Map<String, List<Integer>> m = new HashMap<String, List<Integer>>();
        
        for(int i = 0; i < words.length; i++)
        {
            if(!m.containsKey(words[i]))
            {
                m.put(words[i], new ArrayList<>());
            }
            m.get(words[i]).add(i);
        }
        
        List<Integer> l1 = m.get(word1);
        
        int min = Integer.MAX_VALUE;
        int d = Integer.MAX_VALUE;
         
        if(word1.equals(word2))
        {
            for(int i = 0; i < l1.size() -1; i++)
            {
                d = l1.get(i + 1) - l1.get(i);
                min = min > d ? d : min; 
            }
        }else{
            List<Integer> l2 = m.get(word2);
            int i = 0;
            int j = 0;
           
            
            while(i < l1.size() && j < l2.size())
            {
                if(l1.get(i) < l2.get(j))
                {
                    d = l2.get(j) - l1.get(i++);
                }else{
                    d = l1.get(i) - l2.get(j++);
                }
                min = min > d ? d : min;
            }
        }
        
        return min;
    }
}