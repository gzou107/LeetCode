/*
260. Single Number III My Submissions Question
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
*/

public class Solution {
    public int[] singleNumber(int[] nums) {
        
       // return helper1(nums);
        
        return helper2(nums);
    }
    
    private int[] helper1(int [] nums){
        
        int ans = getAllXOR(nums);
        int index = getFirst1Pos(ans);
        // now according to ith bit, we will split the number
        int a = 0;
        int b = 0;
        for(int i = 0; i < nums.length; i++){
            if((nums[i]>>>index & 1 )== 0 ){
                a ^= nums[i];
            }else{
                b ^= nums[i];
            }
        }
        
        int [] result = {a,b};
        return result;
    }
    
    private int[] helper2(int [] nums){
        int ans = 0;
        for(int i = 0; i < nums.length; i++){
            ans ^= nums[i];
        }
        
       // int loweBit = ans - ( ans & (ans-1) );
        int loweBit = ans & (-ans);
        int a = 0;
        int b = 0;
        for(int i = 0; i < nums.length; i++){
            if((nums[i] & loweBit) == 0){
                a ^= nums[i];
            }else{
                b ^= nums[i];
            }
        }
        
        int [] result = {a,b};
        return result;
    }
    private int getAllXOR(int [] nums){
        int ans = nums[0];
        for(int i = 1; i < nums.length; i++){
            ans ^= nums[i];
        }
        
        return ans;
    }
    
    private int getFirst1Pos(int num){
        int i = 0;
        while( (num>>>i & 1) != 1){
            i++;
        }
        return i;
    }
}