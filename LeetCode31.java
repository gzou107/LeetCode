/*
31. Next Permutation My Submissions Question
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

public class Solution {
    public void nextPermutation(int[] nums) {
        help(nums);
    }
    
    private void help(int [] nums)
    {
        // we first read from end to tail to find the first reversion
        //   namely nums[i] < nums[i+1]
        //   now from [len-1,..., i+1] find the first number greater than nums[i]
        //   swap nums[i] and nums[j]
        //   reverse all the numbers between i+1 to len-1
        // if no reversion find, then reverse the entire array
        int inv = firstInversion(nums);
        if(inv == -1){
            swap(nums, 0, nums.length-1);
        }else
        {
            int j = findFirstGreater(nums, inv);
            
            int t = nums[inv];
            nums[inv] = nums[j];
            nums[j] = t;
            
            swap(nums, inv+1, nums.length-1);
        }
    }
    
    private int firstInversion(int []nums)
    {
        for(int i = nums.length-1; i > 0; i--)
        {
            if(nums[i-1] < nums[i]){
                return i-1;
            }
        }
        
        return -1;
    }
    
    private int findFirstGreater(int [] nums, int i)
    {
        int j = 0;
        for( j= nums.length -1; j >i && nums[j] <= nums[i];){
            j--;
        }
        
        return j;
    }
    
    private void swap(int [] nums, int i, int j)
    {
        while(i < j)
        {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
            i++;
            j--;
        }
    }
}