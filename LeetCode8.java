/*
String to Integer (atoi) My Submissions Question
Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.

spoilers alert... click to show requirements for atoi.

Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
*/
public class Solution {
    public int myAtoi(String s) {
        
       //return helper1(s);
       
       return helper2(s);
     }
     
     public int helper1(String s){
          if(s == null || s.length() == 0){
            return 0;
        }
        
        int i = 0;
        while(i < s.length() && s.charAt(i) == ' '){
            i++;
        }
        
        if(i == s.length()) return 0;
        boolean isNegative = false;
        if(s.charAt(i)=='-') {
            isNegative = true;
            i++;
        }
        if( i < s.length() && s.charAt(i)=='+')
        {
            if(isNegative)
            {
                return 0;
            }
            i++;
            if( i < s.length() && s.charAt(i) == '-')
            {
                return 0;
            }
        }
        while(i < s.length() && s.charAt(i) == '0')
        {
            i++;
        }
        
        long ans = 0;
        while( i < s.length())
        {
            char ch = s.charAt(i);
            if(Character.isDigit(ch))
            {
                ans = ans * 10 + s.charAt(i++) - '0';
                if(ans > Integer.MAX_VALUE)
                {
                    return isNegative?Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
            }else{
                break;
            }
                
        }
          if(isNegative){
              return -(int)ans;
          }
          return (int)ans;
     }
     
     public int helper2(String s){
          if(s == null || s.length() == 0){
            return 0;
        }
        
        int i = 0;
        while(i < s.length() && s.charAt(i) == ' '){
            i++;
        }
        
        if(i == s.length()) return 0;
        boolean isNegative = false;
        if(s.charAt(i)=='-') {
            isNegative = true;
            i++;
        }
        if( i < s.length() && s.charAt(i)=='+')
        {
            if(isNegative)
            {
                return 0;
            }
            i++;
            if( i < s.length() && s.charAt(i) == '-')
            {
                return 0;
            }
        }
        while(i < s.length() && s.charAt(i) == '0')
        {
            i++;
        }
        
        int ans = 0;
        while( i < s.length())
        {
            char ch = s.charAt(i);
            if(Character.isDigit(ch))
            {
                // overflow detection
                if(ans > Integer.MAX_VALUE/10 || (ans == Integer.MAX_VALUE/10 && Integer.MAX_VALUE % 10 < s.charAt(i) - '0'))
                {
                    return isNegative?Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                ans = ans * 10 + s.charAt(i++) - '0';
            }else
            {
               break;
            }
                
        }
      
          if(isNegative){
              return -ans;
          }
          return ans;
     }
}