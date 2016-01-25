/*
211. Add and Search Word - Data structure design My Submissions Question
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.

click to show hint.

You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree) first.
*/

public class WordDictionary {
    private class TrieNode{
        public TrieNode [] children;
        public String word;
        
        public TrieNode(){
            children = new TrieNode[26];
            word = "";
        }
    }
    
    private TrieNode root;
    
    public WordDictionary()
    {
        root = new TrieNode();
    }
    
    // Adds a word into the data structure.
    public void addWord(String word) {
        if(word == null || word.length() == 0) return;
        
        TrieNode current = root;
        for(char c : word.toCharArray())
        {
            if(current.children[c-'a'] == null)
            {
                current.children[c-'a'] = new TrieNode();
            }
            current = current.children[c-'a'];
        }
        current.word = word;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        
        if(word == null || word.length() == 0) return false;
        
        TrieNode current = root;
        List<String> res = new LinkedList<String>();
        collect(word, 0, current, res);
        return res.size()>0;
    }
    
    private void collect(String word, int i, TrieNode root, List<String> res)
    {
        if(i == word.length())
        {
            if(root != null && root.word.length()>0)
            {
                res.add(root.word);
            }
            return;
        }
        
        if(root == null) return;
        
        char c = word.charAt(i);
        
        if(c == '.')
        {
            for(int j = 0; j < 26; j++)
            {
                collect(word, i+1, root.children[j], res);
            }
        }else
        {
            collect(word, i+1, root.children[c-'a'], res);
        }
        
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");