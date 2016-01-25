/*
212. Word Search II My Submissions Question
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.

click to show hint.

You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.
*/

public class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        return help0(board, words);
       // return help(board, words);
    }
   
    // faster way
    private List<String> help0(char[][] board, String[]words)
    {
        List<String> res = new LinkedList<>();
        
        TrieNode root = buildTrie(words);
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[0].length; j++)
            {
                fDfs(board, i, j, root, res);
            }
        }
        
        return res;
    }
    
    private void fDfs(char[][]board, int i, int j, TrieNode p, List<String> res)
    {
        if(i < 0 || i>= board.length || j <0 || j >= board[0].length || p == null)
        {
            return;
        }
        
        char c = board[i][j];
        if(c == '#' || p.children[c-'a'] == null){
            return;
        }
        
        p = p.children[c-'a'];
        if(p.value.length()>0){
            res.add(p.value);
            p.value = "";
        }
        
        board[i][j]='#';
        fDfs(board, i-1, j, p, res);
        fDfs(board, i+1, j, p, res);
        fDfs(board, i, j-1, p, res);
        fDfs(board, i, j+1, p, res);
        board[i][j] = c;
        
    }
    
    private TrieNode buildTrie(String[] words)
    {
        TrieNode root = new TrieNode();
        
        for(String word : words)
        {
            TrieNode current = root;
            
            for(char c : word.toCharArray())
            {
                if(current.children[c-'a'] == null)
                {
                    current.children[c-'a'] = new TrieNode();
                }
                current = current.children[c-'a'];
            }
            current.value = word;
        }
        return root;
    }
    
    private class TrieNode
    {
        public TrieNode [] children;
        public String value;
        
        public TrieNode(){
            children = new TrieNode[26];
            value ="";
        }
    }
    
    // traditional way, 81ms
    private List<String> help(char[][]board, String[] words){
        List<String> res = new LinkedList<>();
        if(words == null || words.length == 0){
            return res;
        }
        
        Trie t = new Trie();
        for(String s : words)
        {
            t.insert(s);
        }
        
        //now we do dfs search
        boolean [][] visited = new boolean[board.length][board[0].length];
        Set<String> temp = new HashSet<String>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                dfs(board, i, j, new StringBuilder(), visited, t, temp);
            }
        }
        for(String s : temp){
            res.add(s);
        }
        return res;
    }
    
    private void dfs(char[][]board, int i, int j, StringBuilder sb, boolean[][] visited, Trie t, Set<String> res)
    {
        if(i <0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]){
            return;
        }
        
        visited[i][j] = true;
        int len = sb.length();
        sb.append(board[i][j]);
        
        if(!t.hasPrefix(sb.toString())){
            sb.setLength(len);
            visited[i][j] = false;
            return;
        }
        
        if(t.search(sb.toString()))
        {
            res.add(sb.toString());
        }
        
            dfs(board, i-1, j, sb, visited, t, res);
            dfs(board, i+1, j, sb, visited, t, res);
            dfs(board, i, j-1, sb, visited, t, res);
            dfs(board, i, j+1, sb, visited, t, res);

        
        visited[i][j] = false;
        sb.setLength(len);
    }

   
    private class Trie{
        private class TrieNode{
            public TrieNode []children;
            public boolean isWord;
            
            public TrieNode()
            {
                children = new TrieNode[26];
                isWord = false;
            }
        }
        
        private TrieNode root;
        public Trie()
        {
            root = new TrieNode();
        }
        
        public void insert(String word){
            if(word == null || word.length()==0) return;
            
            TrieNode current = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(current.children[c-'a'] == null){
                    current.children[c-'a'] = new TrieNode();
                }
                current = current.children[c-'a'];
            }
            current.isWord = true;
        }
        
        public boolean search(String word)
        {
            if(word == null || word.length() == 0){
                return false;
            }
            
            TrieNode current = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(current.children[c-'a'] == null){
                    return false;
                }
                current = current.children[c-'a'];
            }
            return current.isWord;
        }
        
        public boolean hasPrefix(String word){
            if(word == null || word.length() == 0){
                return false;
            }
            
            TrieNode current = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(current.children[c-'a'] == null){
                    return false;
                }
                current = current.children[c-'a'];
            }
            return true;
        }
    }
    
     /*
    
        class TrieNode {
		String s;
		 boolean isString;
		 HashMap<Character, TrieNode> subtree;
		 public TrieNode() {
			// TODO Auto-generated constructor stub
			 isString = false;
			 subtree = new HashMap<Character, TrieNode>();
			 s = "";
		 }
	};


	class TrieTree{
		TrieNode root ;
		public TrieTree(TrieNode TrieNode) {
			root = TrieNode;
		}
		public void insert(String s) {
			TrieNode now = root;
			for (int i = 0; i < s.length(); i++) {
				if (!now.subtree.containsKey(s.charAt(i))) {
					now.subtree.put(s.charAt(i), new TrieNode());
				}
				now  =  now.subtree.get(s.charAt(i));
			}
			now.s = s;
			now.isString  = true;
		}
		public boolean find(String s){
			TrieNode now = root;
			for (int i = 0; i < s.length(); i++) {
				if (!now.subtree.containsKey(s.charAt(i))) {
					return false;
				}
				now  =  now.subtree.get(s.charAt(i));
			}
			return now.isString ;
		}
	};

	public int []dx = {1, 0, -1, 0};
	public int []dy = {0, 1, 0, -1};
	
	
	
	public void search(char[][] board, int x, int y, TrieNode root, ArrayList<String> ans, String res) {
		
		
		
		
		if(root.isString == true)
		{
			if(!ans.contains(root.s)){
				ans.add(root.s);
			}
		}
		if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y]==0 || root == null)
			return ;
		if(root.subtree.containsKey(board[x][y])){
			for(int i = 0; i < 4; i++){
				char now = board[x][y];
				board[x][y] = 0;
				search(board, x+dx[i], y+dy[i], root.subtree.get(now), ans, res);
				board[x][y] = now;
			}
		}
		
	}
	
	public ArrayList<String> Help2(char[][] board, String[] words) {
		ArrayList<String> ans = new ArrayList<String>();
		
		TrieTree tree = new TrieTree(new TrieNode());
		for(String word : words){
			tree.insert(word);
		}
		
		String res = ""; 
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				search(board, i, j, tree.root, ans, res);
			}
		}
		return ans;
        // write your code here
        
    }
    */
}