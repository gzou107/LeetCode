/*
275. H-Index II My Submissions Question
Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?

Hint:

Expected runtime complexity is in O(log n) and the input is sorted.
*/
public class Solution {
    public int hIndex(int[] citations) {
        return helper(citations);
    }
    
    private int helper(int [] citations){
        // we need find the first i
        // if sort according to descending order
        //    then we find the first one with i >= citations[i]
        // if sort according to ascending order
        //    then we find the last one with len - i >= citations[i]?
        int len = citations.length;
        int l = 0;
        int r = len - 1;
        // l can equals to right here
        while(l <= r){
            int m = l + (r-l)/2;
            if(citations[m] == len - m){
                return len - m;
            }else if(citations[m] > len - m){
                r = m - 1;
            }else{
                l = m + 1;
            }
        }
        
        return len - l;
    }
}