
/*Move Zeroes My Submissions Question
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/
public class Solution {
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length <=1){
            return;
        }
        
        int len = 0;
        for(int j = 0; j < nums.length; j++){
            if(nums[j] != 0){
                nums[len++] = nums[j];
            }
        }
        // remember to set rest to 0
        Arrays.fill(nums, len, nums.length, 0);
    }
	
	   
    private void help(int[]nums)
    {
        if(nums == null || nums.length <= 1) return;
        
        int offset = 0;
        for(int i = 0; i < nums.length; i++)
        {
            if(nums[i] != 0){
                nums[offset++] = nums[i];
                
                if(offset-1 != i){
                    nums[i] = 0;
                }
            }
        }
    }
}