/*
208. Implement Trie (Prefix Tree) My Submissions Question
Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.
*/

class TrieNode {
    // Initialize your data structure here.
    public boolean isWord;
    public TrieNode [] children;
    
    public TrieNode() {
        isWord = false;
        children = new TrieNode[26];
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if(root == null || word.length() == 0) return;
        
        TrieNode current = root;
        for(int i = 0; i < word.length(); i++)
        {
            char c = word.charAt(i);
            if(current.children[c-'a'] == null)
            {
                current.children[c-'a'] = new TrieNode();
            }
            current = current.children[c-'a'];
        }
        // handle last char
        current.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) 
    {
        if(word == null || word.length() == 0) return false;
        
        TrieNode current = root;
        for(int i = 0;  i <word.length(); i++)
        {
            char c = word.charAt(i);
            if(current.children[c-'a'] == null)
            {
                return false;
            }
            current = current.children[c-'a'];
        }
        
        return current.isWord;
        /*
        if( i == word.length() && current.isWord)
        {
            return true;
        }
        return false;
        */
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) 
    {
        if(prefix == null || prefix.length() == 0 ) return true;
        
        TrieNode current = root;
        for(int i = 0; i < prefix.length(); i++)
        {
            char c = prefix.charAt(i);
            if(current.children[c-'a'] == null){
                return false;
            }
            current = current.children[c-'a'];
        }
        
        return true;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");

class TrieNode {
    public boolean isWord; 
    public TrieNode[] children = new TrieNode[26];
    public TrieNode() {}
}

public class Trie {
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode ws = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c - 'a'] == null){
                ws.children[c - 'a'] = new TrieNode();
            }
            ws = ws.children[c - 'a'];
        }
        ws.isWord = true;
    }

    public boolean search(String word) {
        TrieNode ws = root; 
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return ws.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode ws = root; 
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return true;
    }