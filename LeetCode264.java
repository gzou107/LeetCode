/*
264. Ugly Number II My Submissions Question
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number.

Hint:

The naive approach is to call isUgly for every number until you reach the nth one. Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
The key is how to maintain the order of the ugly numbers. Try a similar approach of merging from three sorted lists: L1, L2, and L3.
Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
*/
public class Solution {
    public int nthUglyNumber(int n) {
        return helper1(n);
    }
    
    private class Node implements Comparable<Node>{
        public int val;
        public int index;
        public int prime;
        
        public Node(int val, int index, int prime)
        {
            this.val = val;
            this.index = index;
            this.prime = prime;
        }
        
         @Override
        public int compareTo( Node other){
            if(this.val < other.val) return -1;
            else if(this.val > other.val) return 1;
            else return 0;
        }
    }
    
    private int helper1(int n)
    {
        int [] ans = new int[n+1];
        ans[0] = 1;
        
        PriorityQueue<Node> q = new PriorityQueue<Node>(3);
        q.add(new Node(2,1,2));
        q.add(new Node(3,1,3));
        q.add(new Node(5,1,5));
        
        int i = 1;
        while(i <= n)
        {
            // we need find the 
            Node curr = q.peek();
            ans[i++] = curr.val;
            
            // clean the priority queue
            do{
                curr = q.poll();
                q.add(new Solution.Node(ans[curr.index++] * curr.prime, curr.index, curr.prime));
            }while(!q.isEmpty() && q.peek().val == ans[i-1]);
        };
        
        return ans[n-1];
     }
}