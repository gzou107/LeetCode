/*
Merge Sorted Array My Submissions Question
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
*/
public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if( m < 0 || n < 0) return;
        //if( nums1.length < m + n) return;
        
        int i = m - 1;
        int j = n - 1;
        int index = m + n -1;
        
        while(j >= 0 ){
            if(i < 0 || nums2[j] >= nums1[i]){ // always check boundary before accessing []!!!
                nums1[index--] = nums2[j--];
            }else{
                nums1[index--] = nums1[i--];
            }
        }
        
    }
}