
/*
Remove Element My Submissions Question
Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.
*/
public class Solution {
    public int removeElement(int[] nums, int val) {
        
        int len = 0;
        int idx = 0;
        if(nums == null) return 0;
        
        while(idx < nums.length){
            if(nums[idx] != val){
                nums[len++] = nums[idx];
            }
            idx++;
        }
        return len;
    }
}