/*
Rotate Array My Submissions Question
Total Accepted: 53029 Total Submissions: 270890 Difficulty: Easy
Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
*/

public class Solution {
    public void rotate(int[] nums, int k) {
        
        if(nums == null || nums.length <=1 || k % nums.length == 0){
            return;
        }
        
        // swap the elements 
        int len = nums.length;
        k = k % len;
        swap(nums, 0, len-k-1);
        swap(nums, len-k, len-1);
        swap(nums,0, len-1);
        
    }
    
    // swap all the elements within index start and end, both inclusive
    private static void swap(int [] nums, int start, int end)
    {
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}