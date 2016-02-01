/*
331. Verify Preorder Serialization of a Binary Tree My Submissions Question
One way to serialize a binary tree is to use pre-oder traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false
*/

public class Solution {
    public boolean isValidSerialization(String preorder) {
        return help(preorder);
    }
    
    private boolean help(String preorder)
    {
        if(preorder == null || preorder.length() == 0) return true;
        
        Map<Integer, Integer> pos2Count = new HashMap<>();
        String [] temp = preorder.split(",");
        
        if(temp.length == 1){
            return temp[0].equals("#");
        }
      
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i < temp.length; i++)
        {
            if(!temp[i].equals("#"))
            {
                pos2Count.put(i, 0);
                s.push(i);
            }else
            {
                if(s.isEmpty())
                {
                    return false;
                }
                pos2Count.put(s.peek(), pos2Count.get(s.peek())+1 );
                
                if(pos2Count.get(s.peek()) == 2)
                {
                    pos2Count.remove(s.pop());
                    
                    // handle its parent
                    if(!s.isEmpty())
                    {
                       pos2Count.put(s.peek(), pos2Count.get(s.peek())+1);
                    }
                    
                    while(!s.isEmpty() && pos2Count.get(s.peek()) == 2 )
                    {
                        pos2Count.remove(s.pop());
                        if(!s.isEmpty())
                        {
                            pos2Count.put(s.peek(), pos2Count.get(s.peek()) + 1);
                        }
                    }
                }
            }
        }
        
        return s.isEmpty();
    }
}