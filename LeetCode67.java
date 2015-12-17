/*
Add Binary My Submissions Question
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
*/

public class Solution {
    public String addBinary(String a, String b) {
         return helper1(a, b);
         //return helper2(a,b);
    }
    
    public String helper1(String a, String b){
                if(a == null || a.length() == 0){ return b;}
        if(b == null || b.length() == 0){ return a;}
        
        if(a.length() < b.length()){
            String temp = a;
            a = b;
            b = temp;
        }
        StringBuilder ans = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
              
        while( j >= 0){
            int sum = a.charAt(i--) - '0' + b.charAt(j--) - '0' + carry;
            carry = sum / 2;
            sum = sum%2;
            
            ans.append(""+sum);
        }
        
        if(i >=0){
            while(carry >0 && i >=0){
                int sum = a.charAt(i--) - '0' + carry; // remember to - '0'
                carry = sum / 2;
                sum = sum % 2;
                ans.append(""+ sum);
            }
            
            // we have two cases to handle, either i >= 0 or carry > 0,
            // just use two ifs to handle nicely
            if(i >= 0)
            {
                while(i >=0)
                {
                    ans.append(a.charAt(i--));
                }
            }
            
            if(carry>0){
                ans.append("1");
                carry = 0; //need set it to 1 after use, to avoid duplicate addition shown below
            }
            
        }
        
        // we may have a left-over carry, handle here
        if(carry > 0){
                ans.append("1");
            }
        
        return ans.reverse().toString();
    }
    
    public String helper2(String a, String b){
        if(a == null || a.length() == 0) return b;
        if(b == null || b.length() == 0) return a;
        
        
        int i = a.length()-1;
        int j = b.length()-1;
        int carry  = 0;
        StringBuilder ans = new StringBuilder();
        
        while( i >=0 || j >=0 || carry > 0){
            int aV = i >=0? a.charAt(i--) - '0': 0;
            int bV = j >=0? b.charAt(j--) - '0': 0;
            int sum = aV+ bV + carry;
            carry = sum /2;
            sum = sum %2;
            
            ans.append(""+sum);
        }
        
        return ans.reverse().toString();
    }
}