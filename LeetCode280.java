/*
280. Wiggle Sort My Submissions Question
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
*/
public class Solution {
    public void wiggleSort(int[] nums) {
        
        for(int i = 1; i < nums.length; i++)
        {
            if(i%2 == 1 && nums[i] < nums[i-1] || i % 2  == 0 && nums[i]> nums[i-1])
            {
                    int t = nums[i];
                    nums[i] = nums[i-1];
                    nums[i-1] = t;
            }
        }
     //  help0(nums);
       // help(nums);
    }
    
    private void help0(int [] nums)
    {
        //if(nums == null || nums.length <= 1) return;
        
        for(int i = 0; i < nums.length-1; i++)
        {
            if(i%2 == 0 && nums[i] > nums[i+1] || i % 2  == 1 && nums[i]< nums[i+1])
            {
                    int t = nums[i];
                    nums[i] = nums[i+1];
                    nums[i+1] = t;
            }
        }
    }
    private void help(int [] nums)
    {
        if(nums == null || nums.length <= 1) return;
        
        Arrays.sort(nums);
        for(int i = 2; i < nums.length; i+=2)
        {
            int temp = nums[i-1];
            nums[i-1] = nums[i];
            nums[i] = temp;
        }
    }
}