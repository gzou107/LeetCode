/*
75. Sort Colors My Submissions Question
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
*/

public class Solution {
    public void sortColors(int[] nums) {
        sort0(nums);
    }
    
    private void sort0(int [] nums)
    {
        // invariant
        // [0, zero) stores all the zero
        // [zero, one) stores all the ones
        // (two, nums.length -1] stores all the twos
        int zero = 0; 
        int one = 0;
        int two = nums.length-1;
        
        while(one <= two)
        {
            if(nums[one] == 0)
            {
                nums[one] = nums[zero];
                nums[zero] = 0;
                zero++;
                one++;
            }else if(nums[one] == 1)
            {
                one++;
            }else{
                nums[one] = nums[two];
                nums[two] = 2;
                two--;
            }
        }
    }
    
    private void sort(int [] nums)
    {
        // invariant
        // [0, zero) stores all the zero
        // [zero, one) stores all the ones
        // (two, nums.length -1] stores all the twos
        int zero = 0; 
        int one = 0;
        int two = nums.length-1;
        
        while(one <= two)
        {
            if(nums[one] == 0)
            {
                swap(nums, zero, one);
                zero++;
                one++;
            }else if(nums[one] == 1)
            {
                one++;
            }else{
                swap(nums, one, two);
                two--;
            }
        }
    }
    
    private void swap(int [] nums, int i, int j)
    {
        int t= nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}