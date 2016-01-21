/*
254. Factor Combinations My Submissions Question
Total Accepted: 5164 Total Submissions: 15689 Difficulty: Medium
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note: 
Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Examples: 
input: 1
output: 
[]
input: 37
output: 
[]
input: 12
output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
input: 32
output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
Show Company Tags
Show Tags
Show Similar Problems

*/

public class Solution {
    public List<List<Integer>> getFactors(int n) {
        return help(n);
    }
    
    private List<List<Integer>> help(int n)
    {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(n <= 3) return res;
        
        List<Integer> candidate = new LinkedList<Integer>();
        dfs(n, 1, res, candidate);
        
        return res;
    }
    
    private void dfs(int n, int lower, List<List<Integer>> res, List<Integer> candidate)
    {
        if(lower != 1)
        {
            candidate.add(n);
            res.add(new ArrayList<Integer>(candidate));
            candidate.remove(candidate.size()-1);
            //return; can't return here, need continue disect the rest
        }
        
        int upper = (int)Math.sqrt(n);// Can't add 1 here!!!
        for(int i = Math.max(2, lower); i <= upper; i++)
        {
            if(n%i == 0){
                candidate.add(i);
                dfs(n/i, i, res, candidate);
                candidate.remove(candidate.size()-1);
            }
        }
    }
}