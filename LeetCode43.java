/*
43. Multiply Strings My Submissions Question
Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.
*/

public class Solution {
    public String multiply(String num1, String num2) {
        return help(num1, num2);
    }
    // for add, substraction and multiplication
    private String help0(String num1, String num2)
    {
        if(num1 == null || num2 == null) return null;
        int len1 = num1.length(), len2 = num2.length();
        // 结果的位数最多可能是两个乘数位数之和
        int len3 = len1 + len2;
        int[] res = new int[len3];
        int carry = 0, i = 0, j = 0;
        for(i = len1 - 1; i >= 0; i--)
        {
            // 先置carry位为0
            carry = 0;
            for(j = len2 - 1; j >= 0; j--)
            {
                // 每一位的乘积，等于之前这一位的结果，加上carry，再加上这一位和乘数的乘积
                int product = carry + res[i + j + 1] + (num1.charAt(i) - '0')*(num2.charAt(j) - '0');
                res[i + j + 1] = product % 10;
                carry = product / 10;
            }
            // 把最后的carry位加上
            res[i + j + 1] = carry;
        }
        StringBuilder resstr = new StringBuilder();
        i = 0;
        // 跳过前面无用的0
        while(i < len3 - 1 && res[i] == 0){
            i++;
        }
        for(; i < len3; i++){
            resstr.append(res[i]);
        }
        return resstr.toString();
    }
    private String help(String nums1, String nums2){
        
        if(nums1 == null || nums1.length() == 0) return nums2;
        if(nums2 == null || nums2.length() == 0) return nums1;
        
        int [] result = new int[nums1.length() + nums2.length()];
        int carry = 0;
        int i = 0;
        int j = 0;
        
        // stardard way to handle multiplication
        for( i = nums1.length()-1; i >= 0; i--)
        {
            carry = 0;
            for( j = nums2.length()-1; j >=0; j--)
            {
                int t = (nums1.charAt(i) - '0') * (nums2.charAt(j) -'0') + result[i+j+1] + carry;
                
                result[i+j+1] = t%10;
                carry = t/10;
            }
            // 把最后的carry位加上
            result[i + j + 1] = carry; //!!! not lost it if not forwar
        }
        
        
        StringBuilder sb = new StringBuilder();
        i = 0;
        // only to nums1.length() + nums2.length()-1
        while(i < nums1.length() + nums2.length()-1 && result[i] == 0){
            i++;
        }
        
        for(; i< nums1.length() + nums2.length(); i++)
        {
            sb.append(result[i]);
        }
        return sb.toString();
    }
}