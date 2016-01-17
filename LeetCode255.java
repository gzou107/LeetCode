/*
255. Verify Preorder Sequence in Binary Search Tree My Submissions Question
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?
*/

public class Solution {
    public boolean verifyPreorder(int[] preorder) {
        return helper1(preorder);
    }
    // https://segmentfault.com/a/1190000003874375
    // 二叉搜索树先序遍历序列的特点是降序的部分一定是向左走的，一旦开始升序说明开始向右走了，则上一个降序的点则限定了后面的数的最小值。如
    // 继续降序，说明又向左走了，这样等到下次向右走得时候也要再次更新最小值。
    private boolean helper0(int [] number)
    {
        if(number == null || number.length <= 1) return true;
        
        Stack<Integer> s = new Stack<Integer>();
        int min = Integer.MIN_VALUE;
        for(int i = 0; i < number.length; i++)
        {
            if(number[i] < min) return false;
            
            while(!s.isEmpty() && s.peek() < number[i])
            {
                min = s.pop();
            }
            
            s.push(number[i]);
        }
        
        return true;
    }
    
    // const space, but we make change to number, not a good idea in general
    private boolean helper1(int [] number)
    {
        int idx = -1;
        int min = Integer.MIN_VALUE;
        for(int i = 0; i < number.length; i++)
        {
            if(number[i] < min) return false;
            
            while(idx >= 0 && number[i] > number[idx]){
                min = number[idx--];
            }
            number[++idx] = number[i];
        }
        return true;
    }
}