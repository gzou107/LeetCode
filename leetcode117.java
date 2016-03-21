/*
117. Populating Next Right Pointers in Each Node II My Submissions Question
Total Accepted: 58353 Total Submissions: 178378 Difficulty: Hard
Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode nextHead = new TreeLinkNode(0);
            nextHead.next = root;
            while(nextHead.next != null)
            {
                TreeLinkNode tail = nextHead;
                TreeLinkNode n = nextHead.next;
                nextHead.next = null;
                for(; n != null; n = n.next){
                    if(n.left != null){
                        tail.next = n.left;
                        tail = tail.next;
                    }

                    if(n.right != null){
                        tail.next = n.right; 
                        tail = tail.next;
                    }
                }
            }
    }
}