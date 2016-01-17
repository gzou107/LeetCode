/*
269. Alien Dictionary My Submissions Question
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
*/

public class Solution {
    
    public String alienOrder(String[] words) 
    {
      //return help0(words);
      return help1(words);
    }
    
    private String help0(String[] words)
    {
        Map<Character, AlienChar> graph = new HashMap<Character, AlienChar>();
        buildGraph(words, graph);
        // 在建好的图中根据拓扑排序遍历
        String order = findOrder(graph);
        return order.length() == graph.size() ? order : "";
    }
    
    private void buildGraph(String[] words, Map<Character, AlienChar> graph)
    {
        // 初始化图，每个字母都初始化入度为0
        initializeGraph(words, graph);
        for(int wordIdx = 0; wordIdx < words.length - 1; wordIdx++)
        {
            String before = words[wordIdx];
            String after = words[wordIdx + 1];
            // 找到相邻两个单词第一个不一样的字母
            char prev = '0';
            char next = '0';
            for(int letterIdx = 0; letterIdx < before.length() && letterIdx < after.length(); letterIdx++)
            {
                if(before.charAt(letterIdx) != after.charAt(letterIdx))
                {
                    prev = before.charAt(letterIdx);
                    next = after.charAt(letterIdx);
                    addEdge(prev, next, graph);
                    break;
                }
            }
            
        }
    }
    
    private void initializeGraph(String[] words, Map<Character, AlienChar> graph){
        for(String word : words){
            for(int idx = 0; idx < word.length(); idx++){
                if(!graph.containsKey(word.charAt(idx))){
                    graph.put(word.charAt(idx), new AlienChar(word.charAt(idx)));
                }
            }
        }
    }
    
    private void addEdge(char prev, char next, Map<Character, AlienChar> graph){
        AlienChar prevAlienChar = graph.get(prev);
        AlienChar nextAlienChar = graph.get(next);
        nextAlienChar.indegree += 1;
        prevAlienChar.later.add(nextAlienChar);
        graph.put(prev, prevAlienChar);
        graph.put(next, nextAlienChar);
    }
    
    private String findOrder(Map<Character, AlienChar> graph){
        StringBuilder order = new StringBuilder();
        Queue<AlienChar> queue = new LinkedList<AlienChar>();
        for(Character c : graph.keySet()){
            if(graph.get(c).indegree == 0){
                queue.offer(graph.get(c));
            }
        }
        while(!queue.isEmpty()){
            AlienChar curr = queue.poll();
            order.append(curr.val);
            for(AlienChar next : curr.later){
                if(--next.indegree == 0){
                    queue.offer(next);
                }
            }
        }
        return order.toString();
    }

    class AlienChar 
    {
        char val;
        ArrayList<AlienChar> later;
        int indegree;
        public AlienChar(char c){
            this.val = c;
            this.later = new ArrayList<AlienChar>();
            this.indegree = 0;
        }
    }

    private String help1(String [] words)
    {
        if(words == null) return "";
        
         Map<Character, Set<Character>> m = new HashMap<Character, Set<Character>>();
         
         // initialize the graph
         for(String s : words)
         {
             for(int i = 0; i < s.length(); i++)
             {
                 char c = s.charAt(i);
                 if(!m.containsKey(c))
                 {
                     m.put(c, new HashSet<Character>());
                 }
             }
         }
         
         // now build the edge
         for(int idx = 0; idx < words.length - 1; idx++)
         {
            for(int j = 0; j < words[idx].length() && j < words[idx+1].length(); j++)
            {
                char a = words[idx].charAt(j);
                char b = words[idx+1].charAt(j);
                
                if( a != b){
                    m.get(a).add(b);
                    break;
                }
                
            }
         }
        
        top t = new top(m);
        
        return t.order();
    }
    
    private class top
    {
      private boolean [] visited;
      private boolean [] onstack;
      private Stack<Character> order;
      private boolean hasCycle;
      
      public top(Map<Character, Set<Character>> m)
      {
          visited = new boolean[26];
          onstack = new boolean[26];
          hasCycle = false;
          order = new Stack<Character>();
          
          for(Character c : m.keySet())
          {
              if(!visited[c-'a']){
                  dfs(m, c);
              }
          }
      }
      
      private void dfs(Map<Character, Set<Character>> m, char s)
      {
          visited[s-'a'] = true;
          onstack[s-'a'] = true;
          
          if(m.containsKey(s))
          {
              for(char n : m.get(s))
              {
                  if(hasCycle){
                      return;
                  }else if(onstack[n-'a']){
                      hasCycle = true;
                      return;
                  }else if(!visited[n-'a']){
                      dfs(m, n);
                  }
              }
          }
          
          onstack[s-'a'] = false;
          order.push(s);
      }
      
      private String order()
      {
          if(hasCycle) return "";
          
          StringBuilder res = new StringBuilder();
          while(!order.isEmpty()){
              res.append(order.pop());
          }
          
          return res.toString();
      }
    }
}