/*
297. Serialize and Deserialize Binary Tree My Submissions Question
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    private final String nullEncoder="#";
    private final String seperator = ",";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder ans = new StringBuilder();
        // preorder
        //helper(root, ans);
        // post order
        post(root, ans);
        return ans.toString();
    }

    // preorder
    private void helper(TreeNode root, StringBuilder res){
        if(root == null) 
        {
            res.append(nullEncoder+seperator);
            return;
        }
        res.append(String.valueOf(root.val) + seperator);
        helper(root.left, res);
        helper(root.right, res);
    }
     // post order
     private void post(TreeNode root, StringBuilder res){
         if(root == null){
             res.append(nullEncoder + seperator);
             return;
         }
         
         post(root.left, res);
         post(root.right, res);
         res.append(String.valueOf(root.val) + seperator);
     }
     
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // preorder
        //return preRebuild(data);
        return postRebuild(data);
    }
    
    private TreeNode postRebuild(String data){
        if(data == null || data.length() == 0){
            return null;
        }
        
        String [] nodes = data.split(seperator);
        Collections.reverse(Arrays.asList(nodes));
        
        Deque<String> d = new ArrayDeque<String>();
        d.addAll(Arrays.asList(nodes));
        return postHelper(d);
        
    }
    
    private TreeNode postHelper(Deque<String> s){
        if( s.isEmpty()) return null;
        String top = s.remove();
        if(top.equals(nullEncoder)){
            return null;
        }
        
        TreeNode root = new TreeNode(Integer.valueOf(top));
        root.right = postHelper(s);
        root.left = postHelper(s);
        return root;
    }
    
    
    private TreeNode preRebuild(String data){
           if(data == null || data.startsWith(nullEncoder)){
            return null;
        }
        
        String [] nodes = data.split(seperator);
        Deque<String> q = new ArrayDeque();
        q.addAll(Arrays.asList(nodes));
        
        TreeNode r = rebuild(q);
        return r;
    }
    private TreeNode rebuild(Deque<String>q){
        String s = q.remove();
        if(s.equals(nullEncoder)){
            return null;
        }
        
        TreeNode root = new TreeNode(Integer.valueOf(s));
        root.left = rebuild(q);
        root.right = rebuild(q);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));