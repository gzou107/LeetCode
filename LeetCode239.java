/*
239. Sliding Window Maximum My Submissions Question
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?

Hint:

How about using a data structure such as deque (double-ended queue)?
The queue size need not be the same as the window’s size.
Remove redundant elements and the queue should store only elements that need to be considered.
*/

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //return helper0(nums, k);
        return helper(nums, k);
    }
    
    private int [] helper0(int [] nums, int k)
    {
        if(nums == null || nums.length <= 1 || k <= 1 ) return nums;
        
        int [] ans = new int[nums.length -k + 1];
        Deque<Integer> q = new LinkedList<>();
        int len = 0;
        
        for(int i = 0; i < nums.length; i++)
        {
            if(!q.isEmpty() && i - q.peekFirst() > k - 1){
                q.removeFirst();
            }
            
            while(!q.isEmpty() && nums[q.peekLast()] <= nums[i])
            {
                q.removeLast();
            }
            
            q.add(i);
            if(i >= k -1)
            {
                ans[len++] = nums[q.peek()];
            }
        }
        
        return ans;
    }
    private int [] helper(int [] nums, int k)
    {
        // we use a sliding window to keep only the potential maximum value
        if (k == 1 || nums == null || nums.length <= 1) return nums;
        
        Deque<Integer> q = new LinkedList<>();
        for(int i = 0; i < k; i++)
        {
            if(q.isEmpty())
            {
                q.add(i);
            }else{
                while(!q.isEmpty() && nums[q.peekLast()] < nums[i])
                {
                    q.removeLast();
                }
               q.add(i);
            }
        }
        
        int [] ans = new int[nums.length - k + 1];
        int len = 0;
        ans[len++] = nums[q.peekFirst()];
        
        for(int i = k; i < nums.length; i++)
        {
            while(!q.isEmpty() && i - q.peekFirst() > k-1){
                q.removeFirst();
            }
            
            while(!q.isEmpty() && nums[q.peekLast()] < nums[i])
            {
                q.removeLast();
            }
            q.add(i);
             
            ans[len++] = nums[q.peekFirst()];
           
        }
        
        return ans;
    }
}