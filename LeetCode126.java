/*
126. Word Ladder II My Submissions Question
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

public class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        return help(beginWord, endWord, wordList);
    }
    // https://leetcode.com/discuss/64808/my-concise-java-solution-based-on-bfs-and-dfs
    private List<List<String>> help(String start, String end, Set<String>words)
    {
        List<List<String>> result = new ArrayList<List<String>>();
        // stores how far away each word to the start
        Map<String, Integer> distances = new HashMap<>();
        // stores neighbours of each word
        Map<String, List<String>> neighbours = new HashMap<>();
        for(String word : words)
        {
            neighbours.put(word, new ArrayList<String>());
        }
        words.add(end);
        
        bfs(start, end, words, distances, neighbours);
        dfs(start, end, words, distances, neighbours, new ArrayList<String>(), result);
        return result;
    }
    
    private void bfs(String start, String end, Set<String>words, Map<String, Integer>distances, Map<String, List<String>>neighbours)
    {
        Queue<String> q = new ArrayDeque<>();
        q.offer(start);
        distances.put(start, 0);

        while(!q.isEmpty() )
        {
            int count = q.size();
            boolean found = false;
            
            for(int i = 0; i < count; i++)
            {
                String top = q.poll();
                int distance = distances.get(top);
                
                List<String> neighs = getNeighbours(top, words);
                
                for(String neighbour : neighs)
                {
                    neighbours.get(top).add(neighbour);
                    
                    if(!distances.containsKey(neighbour))
                    {
                        distances.put(neighbour, distance+1);
                        
                        if(neighbour.equals(end))
                        {
                            found = true;
                        }
                        q.offer(neighbour);
                    }
                }
            }
            
            if(found){
                break;
            }
        }
    }
    
    private void dfs(String start, String end, Set<String>words, Map<String, Integer>distances, Map<String, List<String>>neighbours, List<String>candidate, List<List<String>> result)
    {
        candidate.add(start);
        if(start.equals(end))
        {
            result.add(new ArrayList<String>(candidate));
        }else{
            for(String neighbour : neighbours.get(start))
            {
                if(distances.get(neighbour) == distances.get(start) + 1)
                {
                    dfs(neighbour, end, words, distances, neighbours, candidate, result);
                }
            }
        }
        candidate.remove(candidate.size()-1);
    }
    private List<String> getNeighbours(String s, Set<String> words)
    {
        List<String> neighbours = new ArrayList<>();
        char[] chars = s.toCharArray();
        for(int i = 0; i < chars.length; i++)
        {
            char ch = chars[i];
            for(char a='a'; a<='z'; a++)
            {
                // we don't add itself into the neighbours
                if(ch == a){
                    continue;
                }
                chars[i] = a;
                String candidate = String.valueOf(chars);
                if(words.contains(candidate))
                {
                    neighbours.add(candidate);
                }
            }
            chars[i] = ch;
        }
        
        return neighbours;
    }
}