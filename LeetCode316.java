public class Solution {
    public String removeDuplicateLetters(String s) {
        if(s == null || s.length() <=1)
        {
            return s;
        }
        
        int []totalCount = new int[26];
        Arrays.fill(totalCount, 0);
        
        int []currentCount = new int[26];
        Arrays.fill(currentCount, 0);
        
        boolean[] exist = new boolean[26];
        Arrays.fill(exist, false);
        
        Stack<Character> st = new Stack<Character>();
        
        for(int i = 0; i < s.length(); i++){
            totalCount[s.charAt(i)-'a']++;
        }
        
        for(int i = 0; i < s.length(); i++)
        {
            while(!st.isEmpty() && st.peek() > s.charAt(i) && currentCount[st.peek()-'a'] < totalCount[st.peek()- 'a'] && !exist[s.charAt(i)-'a']){
                exist[st.peek()-'a'] = false;
                st.pop();
            }
            
            if(!exist[s.charAt(i)-'a']){
                exist[s.charAt(i)-'a'] = true;
                st.push(s.charAt(i));
            }
            
            currentCount[s.charAt(i)-'a']++;
        }
        
        StringBuilder res = new StringBuilder();
        while(!st.isEmpty()){
            res.append(st.pop());
        }
        
        return res.reverse().toString();
    }
}