/*
Plus One My Submissions Question
Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.
*/
public class Solution {
    public int[] plusOne(int[] digits) {
      // return helper1(digits);
      
      return helper2(digits);
    }
    
    private int[] helper(int [] digits)
    {
        if(digits == null || digits.length == 0){
            int [] ans={1};
            return ans;
        }
        
        int [] ans = new int[digits.length];
        int carry = 0;
        int i = digits.length - 1;
        boolean isAdded = false;
        while(i >= 0){
            int v = (!isAdded ? 1 : 0);
            int sum = digits[i] + carry + v;
            carry = sum / 10;
            sum = sum % 10;
            ans[i] = sum;
            i--;
            isAdded = true;
        }
        
        if(carry > 0){
            int[] temp = new int[digits.length+1];
            temp[0] = 1;
            for(i = 0; i < ans.length;i++){
                temp[i+1] = ans[i];
            }
            return temp;
        }
        
        return ans;
    }
    
    private int[] helper2(int [] digits){
        if(digits == null){
            int [] ans={1};
            return ans;
        }
        
        int [] ans = digits.clone();//Arrays.copyOf(digits, digits.length);
        int carry = 1; // serve as the plus one
        int i = digits.length - 1;
   
        while(i >= 0){
            int sum = digits[i] + carry;
            carry = sum / 10;
            sum = sum % 10;
            ans[i] = sum;
            i--;
            
            if(carry == 0){
                return ans;
            }
        }
        
        if(carry > 0){
            int[] temp = new int[digits.length+1];
            temp[0] = 1;
            for(i = 0; i < ans.length;i++){
                temp[i+1] = ans[i];
            }
            return temp;
        }
        
        return ans;
    }
}