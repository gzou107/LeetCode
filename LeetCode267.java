/*267. Palindrome Permutation II My Submissions Question
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].

Hint:

If a palindromic permutation exists, we just need to generate the first half of the string.
To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II or Next Permutation.
*/

public class Solution {
    public List<String> generatePalindromes(String s) {
        return help0(s);
    }
    
    private List<String> help0(String s)
    {
        List<String> res = new ArrayList<String>();
        if(s == null || s.length() == 0){
            return res;
        }
        
        char [] chars = s.toCharArray();
        //Arrays.sort(chars);
        char oddChar=(char)-1;
        int[]count = new int[256];
        for(int i = 0; i < chars.length; i++){
            count[chars[i]]++;
        }
        
        int oddCount = 0;
        for(int i = 0; i < 256; i++)
        {
            if(count[i] % 2 == 1)
            {
                oddCount++;
                oddChar = (char)i;
            }
        }
        if(oddCount > 1)
        {
            return res;
        }
        
        // now create the minimum half of palindrome
        boolean isOddLength = s.length()%2 == 1;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; oddCount == 1 && i <=count[oddChar]/2; i++){
            sb.append(oddChar);
        }
        for(int i = 0; i < 256; i++)
        {
            if(i != (int)oddChar)
            {
                for(int j = 0; j<count[i]/2; j++)
                {
                    sb.append((char)i);
                }
            }
        }
        
        // now we have a startpoint 
        // now we have a minimum
        String start = oddCount >0? sb.deleteCharAt(0).toString(): sb.toString();
        String end = start;

        if(oddCount >0) {
            res.add(reverseString(start) + oddChar  + start);
            start = nextPermutation(start);

            while (!end.equals(start))
            {
                res.add(reverseString(start) + oddChar  + start);
                start = nextPermutation(start);
            }
        }else{
            res.add(reverseString(start) + start);
            start = nextPermutation(start);

            while (!end.equals(start))
            {
                res.add(reverseString(start) + start);
                start = nextPermutation(start);
            }
        }
        return res;
    }

    private String reverseString(String s)
    {
        return new StringBuilder(s).reverse().toString();
    }
    
    private String nextPermutation(String src1)
    {
        // step 1: find first inversion, if no inversion exist, we do nothing
        // step 2: find the first one which is great than inverted element, and swap it
        // step 3: reverse the inverted to the end
        // example: 46827531, first inverted pair(7,2), and first bigger than 2 is 3
        // reverted -> 46837521, reverted after 3 --> 46831257
        if(src1.length()<2) return src1;
        
        char[] src = src1.toCharArray();
        int i = src.length-2;
        for(; i>=0; i--){
            if(src[i] <src[i+1]){
                break;
            }
        }
        if(i == -1) {
            for(int m = 0, n= src.length-1; m < n; m++, n--)
            {
                swap(src, m, n);
            }
            return new String(src);
        }
        // find first greater
        int j = src.length -1;
        for(; j>i; j--)
        {
            if(src[j] > src[i])
            {
                break;
            }
        }
        //swap it
        swap(src, i, j);
        // now reverse the tail
        for(int m = i+1, n = src.length-1; m <n; m++, n--)
        {
            swap(src, m, n);
        }
        return new String(src);
    }
    private String nextPermutation2(String src1)
    {
        // step 1: find first inversion, if no inversion exist, we do nothing
        // step 2: find the first one which is great than inverted element, and swap it
        // step 3: reverse the inverted to the end
        // example: 46827531, first inverted pair(7,2), and first bigger than 2 is 3
        // reverted -> 46837521, reverted after 3 --> 46831257
        if(src1.length() <=1) return src1;
        
        char [] src = src1.toCharArray();
        int i = src.length-1;
        for(; i>0; i--){
            if(src[i] >src[i-1]){
                break;
            }
        }
        if(i == 0){
            for(int m = 0, n = src.length-1; m < n; m++, n--){
                swap(src, m, n);
            }
            return new String(src);
        }
        // find first greater
        int j = src.length -1;
        for(; j>=i; j--)
        {
            if(src[j] > src[i])
            {
                break;
            }
        }
        //swap it
        swap(src, i, j);
        // now reverse the tail
        for(int m = i +1, n = src.length-1; m <n; m++, n--)
        {
            swap(src, m, m);
        }
        return new String(src);
    }
    
    private void swap(char[] src, int i, int j)
    {
       char t = src[i];
       src[i] = src[j];
       src[j] = t;
    }
}