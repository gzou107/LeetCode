/*
60. Permutation Sequence My Submissions Question
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
*/

public class Solution {
    public String getPermutation(int n, int k) {
        return help0(n, k);
    }
    
    private String help0(int n, int k)
    {
        int fac = 1;
        List<Integer> candidate = new LinkedList<>();
        for(int i = 1; i <= n; i++){
            candidate.add(i);
            fac *= i;
        }
        
        k--;
        StringBuilder res = new StringBuilder();
        for(int j = 0; j < n; j++){
            fac /= (n - j);
            // !!!get the index of first
            int first = k/fac;
            // add into the result, and remove it from candidate
            res.append(candidate.remove(first));
            // reset k
            k = k % fac;
        }
        
        return res.toString();
    }
}