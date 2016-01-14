/*
243. Shortest Word Distance My Submissions Question
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

public class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        
        return helper1(words, word1,word2);
    }
    
	    private int helper3(String[] words, String word1, String word2)
    {
          int index = -1, minDistance = Integer.MAX_VALUE;
           for (int i = 0; i < words.length; i++) 
           {
              if (words[i].equals(word1) || words[i].equals(word2)) {
                 if (index != -1 && !words[index].equals(words[i])) {
                    minDistance = Math.min(minDistance, i - index);
                  }
                  index = i;
              }
           }
           return minDistance;
    }
	
    private int helper2(String[] words, String word1, String word2)
    {
        int min = words.length -1;
        int pos1 = -1;
        int pos2 = -1;
       
        for(int i = 0; i < words.length; i++)
        {
            if(words[i].equals(word1))
            {
                pos1 = i;
            }
            
            if(words[i].equals(word2))
            {
                pos2 = i;
            }
            
            if(pos1 >= 0 && pos2 >=0)
            {
                int d = pos1 < pos2 ? pos2 - pos1 : pos1 - pos2;
                min = min > d ? d : min;
            }
        }
        
        return min;
    }
    
    private int helper1(String[] words, String word1, String word2)
    {
        int min = words.length -1;
        int pos1 = -1;
        int pos2 = -1;
       
        for(int i = 0; i < words.length; i++)
        {
            if(words[i].equals(word1))
            {
                pos1 = i;
                if(pos1 >= 0 && pos2 >=0)
                {
                    int d = pos1 < pos2 ? pos2 - pos1 : pos1 - pos2;
                    min = min > d ? d : min;
                }
            }else if(words[i].equals(word2))
            {
                pos2 = i;
                if(pos1 >= 0 && pos2 >=0)
               {
                    int d = pos1 < pos2 ? pos2 - pos1 : pos1 - pos2;
                    min = min > d ? d : min;
               }
            }
        }
        
        return min;
    }
    
    private int helper(String[] words, String word1, String word2)
    {
        Map<String, Integer>m = new HashMap<String, Integer>();
        int min = words.length -1;
        for(int i = 0; i < words.length; i++)
        {
            if(words[i].equals(word1) || words[i].equals(word2))
            {
                m.put(words[i], i);
            }
            
            if(m.containsKey(word1) && m.containsKey(word2))
            {
                int d = Math.abs(m.get(word1) - m.get(word2));
                min = min > d ? d : min;
            }
        }
        
        return min;
    }
}