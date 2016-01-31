/*
127. Word Ladder My Submissions Question
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

public class Solution {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        return help0(beginWord, endWord, wordList);
        //return help2(beginWord, endWord, wordList);
        //return help3(beginWord, endWord, wordList);
        
    }
   private int help00(String s, String e, Set<String>dict)
    {
        Set<String>begin = new HashSet<>();
        Set<String>end = new HashSet<>();
        Set<String>visited = new HashSet<>();
        begin.add(s);
        end.add(e);
        
        int d = 1;
        while(!begin.isEmpty() && !end.isEmpty())
        {
            if(begin.size() > end.size())
            {
                Set<String> t = begin;
                begin = end;
                end = t;
            }
            
           Set<String> temp = new HashSet<>();
            for(String st : begin)
            {               
                char [] arr = st.toCharArray();
                for(int i = 0; i < arr.length; i++)
                {
                    char old = arr[i];
                    for(char letter = 'a'; letter <='z'; letter++)
                    {
                        arr[i] = letter;
                        String nSt = new String(arr);
						/// use end to verify whether we can stop search now
                        if(end.contains(nSt)){
                            return d+1;
                        }
                        if(dict.contains(nSt) && !visited.contains(nSt))
                        {
                            visited.add(nSt);
                            temp.add(nSt);
                        }
                    }
                    arr[i] = old;
                }
            }
            
            begin = temp;
            d++;
        }
        
        return 0;
    }
	
    private int help(String b, String e, Set<String> dict)
    {
        if(b==null) return e==null?1:0;
        if(b=="") return e ==""?1:0;
        if(b.equals(e)) return 1;
        if(b.length() != e.length()) return 0;
         
        List<String> words = new LinkedList<>();
        words.add(b);
        int d = 1;
        dict.remove(b);
        
        
        while(!words.isEmpty())
        {
            int size = words.size();
            
            for(int i = 0; i < size; i++)
            {
                String top = words.remove(0);
                if(top.equals(e))
                {
                    return d;
                }
                
                char [] current = top.toCharArray();
                for(int j = 0; j < top.length(); j++)
                {
                    char c = top.charAt(j);
                    for(char k = 'a'; k < 'z'; k++)
                    {
                        current[j] = k;
                        String nTop = new String(current);
                        if(dict.contains(nTop))
                        {
                            dict.remove(nTop);
                            words.add(nTop);
                        }
                    }
                    current[j] = c;
                }
            }
            
            d++;
        }
        
        return 0;
    }
     private int help3(String start, String end, Set<String> dict) 
    {
          // Use queue to help BFS
          Queue<String> queue = new LinkedList<String>();
          queue.add(start);
          queue.add(null);
        
          // Mark visited word
          dict.remove(start);
        
          int level = 1;
        
          while (!queue.isEmpty()) {
            String str = queue.poll();
        
            if (str != null) {
              // Modify str's each character (so word distance is 1)
              for (int i = 0; i < str.length(); i++)
              {
                char[] chars = str.toCharArray();
        
                for (char c = 'a'; c <= 'z'; c++) {
                  chars[i] = c;
        
                  String word = new String(chars);
        
                  // Found the end word
                  if (word.equals(end)) return level + 1;
        
                  // Put it to the queue
                  if (dict.contains(word) ) {
                    queue.add(word);
                    dict.remove(word);
                  }
                }
              }
            } else {
              level++;
        
              if (!queue.isEmpty()) { 
                queue.add(null);
              }
            }
          }
        
          return 0;
    }
    
    private int help2(String start, String end, Set<String> dict) 
    {
          // Use queue to help BFS
          Queue<String> queue = new LinkedList<String>();
          queue.add(start);
          queue.add(null);
        
          // Mark visited word
          Set<String> visited = new HashSet<String>();
          visited.add(start);
        
          int level = 1;
        
          while (!queue.isEmpty()) {
            String str = queue.poll();
        
            if (str != null) {
              // Modify str's each character (so word distance is 1)
              for (int i = 0; i < str.length(); i++) {
                char[] chars = str.toCharArray();
        
                for (char c = 'a'; c <= 'z'; c++) {
                  chars[i] = c;
        
                  String word = new String(chars);
        
                  // Found the end word
                  if (word.equals(end)) return level + 1;
        
                  // Put it to the queue
                  if (dict.contains(word) && !visited.contains(word)) {
                    queue.add(word);
                    visited.add(word);
                  }
                }
              }
            } else {
              level++;
        
              if (!queue.isEmpty()) { 
                queue.add(null);
              }
            }
          }
        
          return 0;
    }
    
    private int help1(String beginWord, String endWord, Set<String>wordDict)
    {
        Queue<String> queue = new LinkedList<String>();
        // step用来记录跳数
        int step = 2;
        queue.offer(beginWord);
        wordDict.remove(beginWord);
        
        while(!queue.isEmpty())
        {
            int size = queue.size();
            // 控制size来确保一次while循环只计算同一层的节点，有点像二叉树level order遍历
            for(int j = 0; j < size; j++){
               char[] currWord = queue.poll().toCharArray();
                // 循环这个词从第一位字母到最后一位字母
                for(int i = 0; i < currWord.length; i++){
                    // 循环这一位被替换成25个其他字母的情况
                    char t = currWord[i];
                    for(char letter = 'a'; letter <= 'z'; letter++){
                        currWord[i] = letter;
                        String newWord = new String(currWord);
                        if(endWord.equals(newWord)){
                            return step;    
                        } else if(wordDict.contains(newWord)){
                            wordDict.remove(newWord);
                            queue.offer(newWord);
                        }
                    }
                    currWord[i] = t; //have to restore it!!!
                } 
            }
            step++;
        }
        return 0;
    }
}