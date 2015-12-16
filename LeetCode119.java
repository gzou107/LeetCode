/*
Pascal's Triangle II My Submissions Question
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
*/

public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<Integer>();
        if( rowIndex == 0){
            ans.add(1);
            return ans;
        }else if(rowIndex == 1){
            ans.add(1);
            ans.add(1);
            return ans;
        }
        
        int[] prev = new int[rowIndex+1];
        prev[0] = 1;
        int[] curr = new int[rowIndex+1];
        curr[0] = 1;
        curr[1] = 1;
        
        int i = 2;
        while(i <= rowIndex){
            prev[i] = 1;
            int j = i - 1;
            while(j > 0){
                prev[j] = curr[j] + curr[j-1];
                j--;
            }
            prev[0] = 1;
            curr = prev.clone();
            i++;
        }
        // We can't use Arrays.toList(curr) due to type mismatch issue
        for(i = 0; i < curr.length; i++){
            ans.add(curr[i]);
        }
        return ans;
    }
}