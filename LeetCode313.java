/*
Super Ugly Number My Submissions Question
Total Accepted: 2791 Total Submissions: 9568 Difficulty: Medium
Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

Note:
(1) 1 is a super ugly number for any given primes.
(2) The given numbers in primes are in ascending order.
(3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
*/

public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        
        PriorityQueue<Solution.Node> q = new PriorityQueue<Solution.Node>();
        for(int i = 0; i < primes.length; i++){
            q.add(new Solution.Node(primes[i], primes[i], 0));
        }
        
        int[] ans = new int[n];
        ans[0] = 1;
        
        for(int i = 1; i< n; i++){
            Solution.Node min = q.peek();
            ans[i] = min.val;
            
            do{
                min = q.poll();
                min.val = ans[++min.pos]*min.prime; // we need increase curr.index to advance to next factor
                q.add(min);
            }while(!q.isEmpty() && q.peek().val == ans[i]); // we may have duplicate value in q, which are all equals to ans[i], so we need remove them before entering next loop
            
        }
        
        return ans[n-1];
    }
    
        private class Node implements Comparable<Node>{
        private int val;
        private int prime;
        private int pos;
        
        public Node(int val, int prime, int pos){
            this.val = val;
            this.prime = prime;
            this.pos = pos;
        }
        
        public int compareTo(Node other){
            return this.val > other.val?1:-1;
        }
    }

}