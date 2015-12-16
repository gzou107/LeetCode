/*
Pascal's Triangle My Submissions Question
Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

public class Solution {
    public List<List<Integer>> generate(int numRows) {
        
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if(numRows <= 0){
            return ans;
        }
        
        List<Integer> first = new ArrayList<Integer>();
        first.add(1);
        ans.add(first);
        if(numRows == 1) return ans;

        int i = 1;
        while(i < numRows){
            List<Integer> newRow = new ArrayList<Integer>();
            newRow.add(1);
            List<Integer> prev = ans.get(ans.size()-1);
            
            for(int j = 1; j < i; j++){
                newRow.add(prev.get(j-1) + prev.get(j));
            }
            newRow.add(1);
            ans.add(newRow);
            
            i++;
        }
        
        return ans;
    }
}