/*
Happy Number My Submissions Question
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
*/
public class Solution {
    public boolean isHappy(int n) {
        if(n < 0) return false;
        
        Set<Integer> s = new HashSet<Integer>();
        s.add(n);
        while(true)
        {
            int newN = getSum(n);
            
            if(onlyOne(newN))
            {
                return true;
            }else if(s.contains(newN))
            {
                return false;
            }else
            {
                s.add(newN);
            }
            // update n by its new value
            n = newN;
        }
        
    }
    
    private static int getSum(int n){
        int ans = 0;
        
        while(n > 0){
            int reminder = n % 10;
            ans += reminder * reminder;
            n /= 10;
        }
        
        return ans;
    }
    
    private static boolean onlyOne(int n){
        
        int oneCount = 0;
        while(n > 0)
        {
            int reminder = n % 10;
            if(reminder == 0)
            {
                n /= 10;
            }else if(reminder == 1)
            {
                if(oneCount == 0)
                {
                    oneCount++;
                    n /= 10;
                }else
                {
                    return false;
                }
            }else
            {
                return false;
            }
        }
        
        return true;
    }
}