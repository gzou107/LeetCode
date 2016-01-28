/*
330. Patching Array My Submissions Question
Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.
Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.

Example 1:
nums = [1, 3], n = 6
Return 1.

Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
So we only need 1 patch.

Example 2:
nums = [1, 5, 10], n = 20
Return 2.
The two patches can be [2, 4].

Example 3:
nums = [1, 2, 2], n = 5
Return 0.
*/

public class Solution {
    public int minPatches(int[] nums, int n) {
        return help(nums, n);
    }
    
    public int help(int [] nums, int n)
    {
        // we do greedy approach, we maintain a range which is reachable
        // the first missed number is missed, so we have to added the missed number
        // as all the numbers between [1, missed-1] are reachable, then the new covered area will be [1, miss*2)
        // however, as we are provided by nums, there is a chance we can leverage number in nums to expand the area
        // assuming we have reached [1, missed)
        // the first candiate for missed number is 1
        
        long missedCandidate = 1;// !!!must be long, as it can overflow
        int numberToPatch = 0;
        int i = 0;
        while(missedCandidate <= n)
        {
            if(i < nums.length && nums[i] <= missedCandidate){
                missedCandidate += nums[i++]; // expand the range
            }else{
                // the number[i] > missedCandidate, we have to add/patch
                // the missed candidate, and as we add it, the covered range is increased.
                missedCandidate += missedCandidate;
                numberToPatch++;
            }
        }
        
        return numberToPatch;
    }
}