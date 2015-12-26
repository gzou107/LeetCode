/*
229. Majority Element II My Submissions Question
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.

Hint:

How many majority elements could it possibly have?
Do you have a better hint? Suggest it!
*/

public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        
        return helper1(nums);
    }
    
    private List<Integer> helper1(int [] nums){
        List<Integer> ans = new ArrayList<Integer>();
        int a = 0;
        int aCount = 0;
        int b = 0;
        int bCount = 0;
        
        
        for(int i = 0; i < nums.length; i++){
            if( a  == nums[i]){
                aCount++;
            }else if( b == nums[i]){
                bCount++;
            }else if(aCount == 0){
                a = nums[i];
                aCount++;
            }else if(bCount == 0){
                b = nums[i];
                bCount++;
            }else{
                // here we will drop all 3 elements, if these 3 are distinct, and are of size 1
                // we do not do any assignment here as no candidate is good
                aCount--;
                bCount--;
            }
        }
        
        aCount = 0;
        bCount = 0;
        for(int i = 0; i< nums.length; i++){
            if(a == nums[i]){
                aCount++;
            }
            
            if( b == nums[i]){
                bCount++;
            }
        }
        
        if( aCount > nums.length/3){
            ans.add(a);
        }
        if(bCount > nums.length/3){
            if(a != b){
             ans.add(b);
            }
        }
        
        return ans;
    }
}