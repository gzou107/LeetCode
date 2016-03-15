/*
4. Median of Two Sorted Arrays My Submissions Question
Total Accepted: 86445 Total Submissions: 472621 Difficulty: Hard
There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
*/

public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return help(nums1, nums2);
    }
	
    private double help(int[]nums1, int[] nums2)
    {
        int len = nums1.length + nums2.length;
        if((len%2) == 0){
            int temp = (findMedian(nums1, 0, nums2, 0, len/2) + findMedian(nums1, 0, nums2, 0, len/2+1) );
            return temp/2.0;
        }else{
            return findMedian(nums1, 0, nums2, 0, len/2+1);
        }
    }
    
    private int findMedian(int[]nums1, int start1, int[]nums2, int start2, int k)
    {
        // compare the value between nums1[start1+k/2-1] agains nums2[start2+k/2-1]
        if(start1 >= nums1.length){
            return nums2[start2+k-1];
        }
        
        if(start2 >= nums2.length){
            return nums1[start1+k-1];
        }
        
        if(k == 1){
            return Math.min(nums1[start1], nums2[start2]);
        }
        
        int vA = start1+k/2-1 >= nums1.length ? Integer.MAX_VALUE : nums1[start1+k/2-1];
        int vB = start2+k/2-1 >= nums2.length ? Integer.MAX_VALUE : nums2[start2+k/2-1];
        
        if(vA < vB){
            return findMedian(nums1, start1+k/2, nums2, start2, k-k/2);//very important, it's not k/2, but k - k/2 !!!!!!
        }else{
             return findMedian(nums1, start1, nums2, start2+k/2, k- k/2);
        }
    }
}