/*
216. Combination Sum III My Submissions Question
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Ensure that numbers within the set are sorted in ascending order.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]
*/

public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        return helper1(k, n);
    }
    
    private List<List<Integer>> helper1(int k, int n){
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if( n < 1 || n >45){
            return ans;
        }
        List<Integer> candidate = new ArrayList<Integer>();
        dfs(1, n, k, candidate, ans);
        return ans;
    }
    
    private void dfs(int start, int sum,  int k, List<Integer> candidate, List<List<Integer>>ans)
    {
        if(candidate.size() == k && sum == 0){
            List<Integer> oneSol = new ArrayList<Integer>();
            for(Integer num : candidate){
                oneSol.add(num);
            }
            ans.add(oneSol);
            return;
        }else if(candidate.size() > k || sum < 0 || candidate.size() + 9 - start + 1 < k){
            return;
        }else{
            
            for(int i = start; i <=9 && candidate.size() <k; i++){
                candidate.add(i);
                dfs(i+1, sum-i,k,candidate, ans);// next round, we start from i+1, instead of start+1
                candidate.remove(candidate.size()-1);
            }
        }
    }
}