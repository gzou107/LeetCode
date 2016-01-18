/*
321. Create Maximum Number My Submissions Question
Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.

Example 1:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
return [9, 8, 6, 5, 3]

Example 2:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
return [6, 7, 6, 0, 4]

Example 3:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
return [9, 8, 9]
*/

public class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        return help(nums1, nums2, k);
    }
    // http://algobox.org/create-maximum-number/
    private int[] help(int [] nums1, int [] nums2, int k)
    {
        int [] res = new int[k];
        int m = nums1.length;
        int n = nums2.length;
        for(int i = Math.max(0, k-n); i <=m && i <= k; i++)
        {
            int [] candidate = merge2D( maxNumber1D(nums1, i),  maxNumber1D(nums2, k - i), k);
            if(greater(candidate, 0, res, 0))
            {
                res = candidate.clone();
            }
        }
        return res;
    }
    // find k elements from nums1, which is guaranteed to be longer or equals to k
    private int [] maxNumber1D(int [] nums, int k)
    {
        int [] res = new int[k];
        int len = nums.length;
        for(int i = 0, j = 0; i < len; i++)
        {
            while(len -i + j > k && j >0 && res[j-1] < nums[i])
            {
                j--;
            }
            if(j < k)
            {
                res[j++] = nums[i];
            }
        }
        
        return res;
    }
    // merge the nums1 and nums2, and the sum of two length is k
    private int [] merge2D(int [] nums1, int [] nums2, int k)
    {
        int [] res = new int[k];
        for(int i = 0, j =0, r = 0; r <k; r++)
        {
            res[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        }
        return res;
    }
    
    // select bigger of the two when looking behind
    private boolean greater(int [] nums1, int i, int [] nums2, int j)
    {
        while(i < nums1.length && j < nums2.length && nums1[i] == nums2[j])
        {
            i++;
            j++;
        }
        
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }
    
}