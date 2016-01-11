/*
133. Clone Graph My Submissions Question
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
*/

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return dfs(node);
       // return bfs2(node);
    }
    
    private UndirectedGraphNode dfs(UndirectedGraphNode node)
    {
        if(node == null) return null;
        
        Map<UndirectedGraphNode, UndirectedGraphNode> m = new HashMap<>();
        
        dfs(node, m);
        return m.get(node);
    }
    
    private UndirectedGraphNode dfs(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> m)
    {
        if(m.containsKey(node))
        {
            return m.get(node);
        }
        
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        m.put(node, newNode);
        for(UndirectedGraphNode nei : node.neighbors)
        {
            if(!m.containsKey(nei))
            {
                newNode.neighbors.add(dfs(nei, m));
            }else{ // be careful here, we need add into its neighbor instead of doing nothing, which is typical dfs
                newNode.neighbors.add(m.get(nei));
            }
        }
        
        return newNode;
    }
    
    private UndirectedGraphNode bfs(UndirectedGraphNode node)
    {
        if(node == null) return node;
        
        Map<UndirectedGraphNode, UndirectedGraphNode> m = new HashMap<>();
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        q.add(node);
        UndirectedGraphNode topCopy = new UndirectedGraphNode(node.label);
        m.put(node, topCopy);
            
        while(!q.isEmpty())
        {
            UndirectedGraphNode top = q.poll();
            // doing nothing here, we only do everything when we add into the queue!!!
            
            for(UndirectedGraphNode nei : top.neighbors)
            {
                if(m.containsKey(nei))
                {
                    m.get(top).neighbors.add(m.get(nei));
                }else
                {
                    UndirectedGraphNode neiCopy = new UndirectedGraphNode(nei.label);
                    m.put(nei, neiCopy);
                    m.get(top).neighbors.add(neiCopy);
                    q.add(nei);
                }
            }
        }
        
        return topCopy;
    }
}