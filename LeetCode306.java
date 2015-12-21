/*
306. Additive Number My Submissions Question
Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

For example:
"112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
1 + 99 = 100, 99 + 100 = 199
Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Follow up:
How would you handle overflow for very large input integers?

Credits:
*/

public class Solution {
    public boolean isAdditiveNumber(String num) {
        //return helper1(num);
        
        return helper2(num);
    }
    
    private boolean helper1(String num){
        if(num == null || num.length() < 3){
            return false;
        }
        
        // string is of [0,i), [i, j) and [j, num.length)
        for(int i = 1; i <= num.length()/2; i++){
            for(int j = i + 1; j <= num.length() - 1; j++){
                
                if(dfs(num, 0, i, j, num.length())){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(String num, int first, int second, int third, int n){
           if(second - first > 1 && num.charAt(first) == '0') return false;
           if(third - second > 1 && num.charAt(second) == '0') return false;
           if(num.charAt(third) == '0') return false;
           
           Long n1 = Long.parseLong(num.substring(first, second));
           Long n2 = Long.parseLong(num.substring(second, third));
           
           Long temp = n1 + n2;
           if(temp == Long.parseLong(num.substring(third)) && num.charAt(third) != '0') return true; // we need stop here if it matches the end, oterwise, we will always return false
           
           int maxLen = Math.max(second-first, third-second);
           
           if(third + maxLen <= num.length()){
               boolean status = false;
               
               if(temp == Long.parseLong(num.substring(third, third + maxLen))){
                   status = dfs(num,second, third, third+maxLen, n);
               }
               
               if(!status && third + maxLen + 1 <= num.length() && temp == Long.parseLong(num.substring(third, third + maxLen + 1)))
               {
                   status = dfs(num, second, third, third + maxLen + 1, n);
               }
               return status;
           }
           return false;
    }
    
    private boolean helper2(String num){
        if(num == null || num.length() < 3) return false;
        
        for(int i = 1; i <= num.length()/2; i++)
        {
            for(int j = i + 1; j <= num.length() - 1; j++)
            {
                if( i >=2 && num.charAt(0) == '0')
                {
                    continue;
                }
                
                if( j -i >=2 && num.charAt(i) == '0')
                {
                    continue;
                }
                
                Long n1 = Long.parseLong(num.substring(0,i));
                Long n2 = Long.parseLong(num.substring(i, j));
                Long temp = n1 + n2;
                String t = String.valueOf(temp);
                String curr = n1.toString() + n2.toString() + t;
                
                if(!num.substring(j).startsWith(String.valueOf(temp))){
                    continue;
                }
                
                while(curr.length() < num.length()){
                    n1 = n2;
                    n2 = temp;
                    temp = n1 + n2;
                    t = String.valueOf(temp);
                    curr += t;
                }
                
                if(curr.equals(num)){
                    return true;
                }
            
            }
        }
        
        return false;
    }
}