/*
246. Strobogrammatic Number My Submissions Question
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.

Show Company Tags
Show Tags
Show Similar Problems

*/

public class Solution {
    public boolean isStrobogrammatic(String num) {
        return helper(num);
        //return helper1(num);
    }
    
    private boolean helper(String num)
    {
        for(int i = 0, j = num.length()-1; i<=j; i++, j--)
        {
            if(!"00 11 88 696".contains(num.charAt(i) + ""+ num.charAt(j)))
            {
                return false;
            }
        }
        
        return true;
    }
    private boolean helper1(String num)
    {
        if(num == null || num.length() == 0) return true;
        if(num.length() == 1)
        {
            return num.equals("0") || num.equals("1") || num.equals("8");
        }
        
        
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        m.put(0,0);
        m.put(1,1);
        m.put(6,9);
        m.put(8,8);
        m.put(9,6);
        
        for(int i = 0, j = num.length()-1; i <= j; i++, j--)
        {
            int c1 = num.charAt(i) - '0';
            int c2 = num.charAt(j) - '0';
            if(i == j)
            {
                return c1==0 || c1 == 1 || c1 == 8;
            }
            
            if(!m.containsKey(c1) || !m.containsKey(c2) || m.get(c1) != c2) 
            {
                return false;
            }
        }
        
        return true;
    }
}