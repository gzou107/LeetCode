/*
215. Kth Largest Element in an Array My Submissions Question
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

Credits:
Special thanks to @mithmatt for adding this problem and creating all test cases.
*/

public class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
      //  return helper0(nums, k);
    }
    
    public int helper0(int [] nums, int k)
    {
        int l = 0;
        int r = nums.length - 1;
        int rank = nums.length - k;

        while(l < r)
        {
           int idx = partition(nums, l, r);
           
           if(idx == rank) {
               return nums[idx];
           }else if(idx < rank)
           {
               l = idx + 1;
           }else{
               r = idx - 1;
           }
        }
        return nums[rank];// !!! bug here, we should return nums[rank] rather than nums[0]!!!
    }
    
    // we must make sure hi - lo >= 1, at least two elements here
    // a[lo,..., j-1] <= a[j] <= a[j+1, ...,hi]
    private int partition(int [] nums, int l, int r)
    {
        int pivot = nums[l];
        int left = l + 1;
        int right = r;
        while(left <= right) // must be equals here, as we always swap with right, hence we need adjust right idex !!!
        {
            while(left < r && pivot > nums[left])
            {
                left++;
            }
            
            while(right > l && pivot <= nums[right])
            {
                right--;
            }
            
            if(left >= right){
                break;
            }
            
            swap(nums, left, right);
            
        }
        
        swap(nums, l, right);
        return right;
    }
    
    private void swap(int [] nums, int l, int r)
    {
        int t = nums[l];
        nums[l] = nums[r];
        nums[r] = t;
    }
    
    /*
        // we must make sure hi - lo >= 1, at least two elements here
    // a[lo,..., j-1] <= a[j] <= a[j+1, ...,hi]
    private int par3(int[] a, int lo, int hi)
    {
        int i = lo;
        int j = hi + 1;
        while(true) 
        {
            while(i < hi && less(a[++i], a[lo]));
            while(j > lo && less(a[lo], a[--j]));
            if(i >= j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, lo, j);// as we always swap with j, so, j
        return j;
    }
    
    private boolean less(int v, int w)
    {
        return v < w;
    }
    private int par2(int [] nums, int lo, int hi)
    {
        int i = lo;
        int j = hi + 1;
        
        while(true)
        {
            while(i < hi && nums[++i] < nums[lo]) // i can not compare with j, as j starts with hi+1
            {
               // i++;
            }
            
            while(j > lo && nums[--j] > nums[lo]) // j can not compare with i, as i need decrease to lo, as we always swap with j
            {
                //j--;
            }
            
            if( i >= j){
                break;
            }
            
            swap(nums, i, j);
        }
        
        swap(nums, lo, j); // we always swap with j pointed element here!!!
        
        return j; 
    }
    */
}