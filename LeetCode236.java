/*
236. Lowest Common Ancestor of a Binary Tree My Submissions Question
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
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
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return helper1(root, p, q);
    }
    
    // bottom-up approach, solve the left and right sub-tree
    // and make decision based on the return value
    private TreeNode helper1(TreeNode root, TreeNode p, TreeNode q){
        if(root == null || root == p || root == q){
            return root;
        }
        
        TreeNode l = helper1(root.left, p, q);
        TreeNode r = helper1(root.right, p, q);
        
        if(l != null && r != null){
            return root;
        }
        
        return l == null? r : l;
    }
}

/**
2016/01/17 Tableau phone interview !!!
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //return help0(root, p, q);
        //return help1(root, p, q);
        return help2(root, p, q);
    }
    
    private TreeNode help0(TreeNode root, TreeNode p, TreeNode q)
    {
        if(root == null || p == null || q == null) return null;
        if(root == p || root == q) return root;
        
        TreeNode l = help0(root.left, p, q);
        TreeNode r = help0(root.right, p, q);
        
        if(l != null && r != null) return root;
        
        return l == null? r : l;
    }
    
    private TreeNode help1(TreeNode root, TreeNode p, TreeNode q)
    {
        if(root == null) return null;
        if(p == null) return q;
        if(q == null) return p;
        
        List<TreeNode>p1 = new ArrayList<>();
        List<TreeNode>p2 = new ArrayList<>();
        
        findPath(root, p, new ArrayList<TreeNode>(), p1);
        findPath(root, q, new ArrayList<TreeNode>(), p2);
        
        //  now we find the longest common prefix of these two
        int i = 0;
        for( i = 0; i <p1.size() && i < p2.size(); i++)
        {
            if(p1.get(i) != p2.get(i)){
                return p1.get(i-1);
            }
        }
        if(i == p1.size()){
            return p1.get(p1.size()-1);
        }
        
        if( i == p2.size()){
            return p2.get(p2.size()-1);
        }
        return null;
    }
    
    private void findPath(TreeNode root, TreeNode p, List<TreeNode>temp, List<TreeNode>path)
    {
        if(root == p){
            for(TreeNode t : temp){
                path.add(t);
            }
            path.add(root);
            return;
        }    
        temp.add(root);
        if(root.left != null){
            findPath(root.left, p, temp, path);
        }
        
        if(root.right != null){
            findPath(root.right, p, temp, path);
        }
        temp.remove(temp.size()-1);
    }
    
    private TreeNode help2(TreeNode root, TreeNode p, TreeNode q)
    {
        if(root == null) return null;
        if(p == null) return q;
        if(q == null) return p;
   
        wrapperNode res = dfs(root, p, q);
        
        return res.node;
    }
    private wrapperNode dfs(TreeNode root, TreeNode p, TreeNode q)
    {
        if(root == null) return new wrapperNode(null, 0);
        if(root == p || root == q) return new wrapperNode(root, 1);
        
        wrapperNode l =  dfs(root.left, p, q);
        wrapperNode r = dfs(root.right, p, q);
        
        
        if( l.count == 2) return l;
        if( r.count == 2) return r;
        
        if(l.count + r.count == 2){
            return new wrapperNode(root, 2);
        }
        
        if(l.count == 1){
            if(root == p || root == q){
             return new wrapperNode(root, 2);
            }else{
                return new wrapperNode(l.node, 1);
            }
        }
        
        if( r.count == 1){
            if(root == p || root == q){
             return new wrapperNode(root, 2);
            }else{
                return new wrapperNode(r.node, 1);
            }
        }
        
        return new wrapperNode(null, 0);
    }
    
    private class wrapperNode 
    {
        public TreeNode node;
        public int count;
        
        public wrapperNode(TreeNode t, int c)
        {
            this.node = t;
            this.count = c;
        }
    }
}