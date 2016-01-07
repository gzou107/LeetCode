/*
314. Binary Tree Vertical Order Traversal My Submissions Question
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its vertical order traversal as:
[
  [9],
  [3,15],
  [20],
  [7]
]
Given binary tree [3,9,20,4,5,2,7],
    _3_
   /   \
  9    20
 / \   / \
4   5 2   7
return its vertical order traversal as:
[
  [4],
  [9],
  [3,5,2],
  [20],
  [7]
]
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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        return helper0(root);
       // return helper(root);
    }
    
    // as we know the depth must be consecutive, hence, we can always find the min and max of
    // the distance, and then set the root distance to be -min(right shift by -min)
    // and then does the bfs
    int min = 0;
    int max = 0;
    private List<List<Integer>> helper0(TreeNode root)
    {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null) return res;
        
        computeD(root, 0);
        for(int i = 0; i < max - min + 1; i++){
            res.add(new ArrayList<Integer>());
        }
        
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        Queue<Integer> depth = new LinkedList<Integer>();
        q.add(root);
        depth.add(-min);
        
        while(!q.isEmpty())
        {
            TreeNode top = q.remove();
            int d = depth.remove();
            
            // no need allocate memory any more, no need to book keeping the depth anymore
            res.get(d).add(top.val);
            
            if(top.left != null){
                q.add(top.left);
                depth.add(d-1);
            }
            
            if(top.right != null){
                q.add(top.right);
                depth.add(d+1);
            }
        }
        
        return res;
    }
    
    private void computeD(TreeNode root, int dep)
    {
        if(root == null) return;
        
        min = min < dep? min : dep;
        max = max < dep? dep : max;
        
        computeD(root.left, dep-1);
        computeD(root.right, dep + 1);
    }
    private List<List<Integer>> helper(TreeNode root)
    {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null){
            return res;
        }
        
        TreeMap<Integer, List<Integer>> m = new TreeMap<Integer, List<Integer>>();
        //dfs(root, 0, m); dfs does not guarantee the order from top to bottom
        bfs(root,m);
        for(Integer distance : m.keySet()){
            res.add(m.get(distance));
        }
        return res;
    }
    
    // dfs does not work as we need do from top to bottom
    private void dfs(TreeNode root, int depth, TreeMap<Integer, List<Integer>> m)
    {
        if(root == null) return;
        if(!m.containsKey(depth)){
            m.put(depth, new ArrayList<Integer>());
        }
        m.get(depth).add(root.val);
        
        dfs(root.left, depth - 1, m);
        dfs(root.right, depth + 1, m);
    }
    
    private class Node
    {
        public TreeNode node;
        public int depth;
        public Node(TreeNode node, int depth)
        {
            this.node = node;
            this.depth = depth;
        }
    }
    // we need use bfs, and also maintain the depth information, and depth -1 if add left child, depth +1 when adding right child
    private void bfs(TreeNode root, TreeMap<Integer, List<Integer>> m)
    {
        if(root == null) return;
        
        LinkedList<Node> q = new LinkedList<Node>();
        q.add(new Node(root, 0));
        
        while(!q.isEmpty())
        {
            //int sz = q.size();
            //for(int i = 0; i < sz; i++)
            {
                Node top = q.remove();
                
                if(!m.containsKey(top.depth)){
                    m.put(top.depth, new ArrayList<Integer>());
                }
                m.get(top.depth).add(top.node.val);
                
                if(top.node.left != null){
                    q.add(new Node(top.node.left, top.depth -1 ));
                }
                
                if(top.node.right != null){
                    q.add(new Node(top.node.right, top.depth + 1));
                }
            }
        }

    }
}