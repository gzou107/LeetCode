/*
159. Longest Substring with At Most Two Distinct Characters My Submissions Question
Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.
*/

public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        return help0(s);
    }
    
    private int help0(String s)
    {
       if(s == null || s.length() == 0) return 0;
       
       int [] count = new int[256];
       int start = 0;
       int distinct = 0;
       int max = 0;
       
       for(int j = 0; j < s.length(); j++)
       {
           char c = s.charAt(j);
           if(count[c] == 0){
               distinct++;
           }
           count[c]++;
           
           while(distinct > 2)
           {
               count[s.charAt(start)]--;
               
               if(count[s.charAt(start)] == 0){
                   distinct--;
               }
               start++;
           }
           // now the invariant is maintained
           max = Math.max(max, j - start + 1);
       }
       
       return max;
    }
    
    private int help(String s)
    {
        if(s == null || s.length() == 0) return 0;
        int [] m = new int[256];
        Arrays.fill(m, 0);// records the char count
        
        int max = 1; //record longest length
        int count = 0;//number of distinct char, at most == 2
        int start = 0; // start of a combination
        int end = 0;
        
        for(int i=0; i <s.length(); i++ )
        {
            char c = s.charAt(i);
            if(m[c] == 0)
            {
                m[c] = 1;
                if(count < 2)
                {
                    count++;
                    end = i;
                    if(count == 2)
                    {
                        max = Math.max(max, end - start + 1);
                    }
                    
                }else
                {
                    // we need move forward start, until count == 2 again
                    while(count == 2)
                    {
                        if(m[s.charAt(start)] == 1)
                        {
                            m[s.charAt(start)]--;
                            start++;
                            break;
                        }else{
                             m[s.charAt(start)]--;
                             start++;
                        }
                    }
                }
            }else{
                m[c]++;
                end = i;
                max = Math.max(max, end - start + 1);
            }
        }
        
        return max;
    }
}