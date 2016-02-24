/*
30. Substring with Concatenation of All Words My Submissions Question
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).

Show Tags
Show Similar Problems

*/

public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        return help1(s, words);
    }
    
    public List<Integer> help0(String s, String[] words) {
        List<Integer> res = new LinkedList<Integer>();
        if(words == null || words.length == 0 || s == null || s.equals("")) return res;
        HashMap<String, Integer> freq = new HashMap<String, Integer>();
        // 统计数组中每个词出现的次数，放入哈希表中待用
        for(String word : words){
            freq.put(word, freq.containsKey(word) ? freq.get(word) + 1 : 1);
        }
        // 得到每个词的长度
        int len = words[0].length();
        // 错开位来统计
        for(int i = 0; i < len; i++){
            // 建一个新的哈希表，记录本轮搜索中窗口内单词出现次数
            HashMap<String, Integer> currFreq = new HashMap<String, Integer>();
            // start是窗口的开始，count表明窗口内有多少词
            int start = i, count = 0;
            for(int j = i; j <= s.length() - len; j += len){
                String sub = s.substring(j, j + len);
                // 看下一个词是否是给定数组中的
                if(freq.containsKey(sub)){
                    // 窗口中单词出现次数加1
                    currFreq.put(sub, currFreq.containsKey(sub) ? currFreq.get(sub) + 1 : 1);
                    count++;
                    // 如果该单词出现次数已经超过给定数组中的次数了，说明多来了一个该单词，所以要把窗口中该单词上次出现的位置及之前所有单词给去掉
                    while(currFreq.get(sub) > freq.get(sub)){
                        String leftMost = s.substring(start, start + len);
                        currFreq.put(leftMost, currFreq.get(leftMost) - 1);
                        start = start + len;
                        count--;
                    }
                    // 如果窗口内单词数和总单词数一样，则找到结果
                    if(count == words.length){
                        String leftMost = s.substring(start, start + len);
                        currFreq.put(leftMost, currFreq.get(leftMost) - 1);
                        res.add(start);
                        start = start + len;
                        count--;
                    }
                // 如果截出来的单词都不在数组中，前功尽弃，重新开始
                } else {
                    currFreq.clear();
                    start = j + len;
                    count = 0;
                }
            }
        }
        return res;
    }
    // https://segmentfault.com/a/1190000003903467
    private List<Integer> help1(String s, String[]words)
    {
        List<Integer> result = new ArrayList<>();
        if(words == null || words.length == 0 || s == null || s.length() == 0) return result;
        
        Map<String, Integer> targetFreq = new HashMap<>();
        for(String word : words)
        {
            targetFreq.put(word, targetFreq.containsKey(word)?targetFreq.get(word)+1 : 1);
        }
        
        int len = words[0].length();
        for(int i = 0; i < len; i++)
        {
            // map stores the words and its account within the window
            Map<String, Integer> curFreq = new HashMap<>();
            int count = 0;
            // starts indicates the start point of window, matched denotes the number of matched words
            int start = i;
            for(int j = i; j <= s.length() - len; j+= len)
            {
                String sub = s.substring(j, j + len);
                
                if(targetFreq.containsKey(sub))
                {
                    curFreq.put(sub, curFreq.containsKey(sub)?curFreq.get(sub)+1 : 1);
                    count++;
                    
                    while(curFreq.get(sub) > targetFreq.get(sub))
                    {
                        // remove all the invalid ones
                        String leftMost = s.substring(start, start + len);
                        curFreq.put(leftMost, curFreq.get(leftMost)-1);
                        count--;
                        start += len;
                    }
                    
                    if(count == words.length)
                    {
                        // find one solution
                        result.add(start);
                        String leftMost = s.substring(start, start+len);
                        curFreq.put(leftMost, curFreq.get(leftMost)-1);
                        start += len;
                        count--;
                    }
                }else
                {
                   curFreq.clear();
                   count = 0;
                   start = j + len; //next start position
                }
            }
        }
        
        return result;
    }
}