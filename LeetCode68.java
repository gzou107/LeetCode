/*
68. Text Justification My Submissions Question
Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

click to show corner cases.

Corner Cases:
A line other than the last line might contain only one word. What should you do in this case?
In this case, that line should be left-justified.
*/

public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        return help(words, maxWidth);
    }
    
    private List<String> help(String[]words, int max)
    {
        List<String>res = new ArrayList<>();
        if(words == null || words.length == 0) return res;
        
        for(int i =0, w; i < words.length; i = w)
        {
            int len = -1;
            for(w = i; w < words.length && len + words[w].length() +1 <= max; w++)
            {
                len += words[w].length() + 1;
            }
            
            int space = 1;
            int extra = 0;
            if(w != i + 1 && w != words.length)
            {
                space = (max - len)/(w-1-i) + 1;
                extra = (max - len) %(w-1-i);
            }
            
            StringBuilder oneLine = new StringBuilder(words[i]);
            for(int j = i + 1; j < w; j++)
            {
                for(int s = space; s>0; s--) oneLine.append(' ');
                if(extra-- > 0) oneLine.append(' ');
                oneLine.append(words[j]);
            }
            
            int left = max - oneLine.length();
            while(left-- > 0) oneLine.append(' ');
            res.add(oneLine.toString());
        }
        
        return res;
    }
}