/*
274. H-Index My Submissions Question
Total Accepted: 19732 Total Submissions: 72181 Difficulty: Medium
Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

Hint:

An easy approach is to sort the array first.
What are the possible values of h-index?
A faster approach is to use extra space.
*/

public class Solution {
    public int hIndex(int[] citations) {
        
       // return helper1(citations);
       return helper2(citations);
    }
    
    private int helper1(int[] citations){
       // reverse sort the citation, and find the first position whose position
       // is greater than citation
        Arrays.sort(citations);
        List<Integer> c = new ArrayList<Integer>();
        for(int i : citations){
            c.add(i);
        }
        Collections.reverse(c);
        
        for(int i = 0; i < c.size(); i++){
            if(i >= c.get(i)){
                return i;
            }
        }
        return c.size();
    }
    
    private int helper2(int [] citations){
        int[] stats = new int[citations.length + 1];
        int n = citations.length;
        // 统计各个引用次数对应多少篇文章
        for(int i = 0; i < n; i++){
            stats[citations[i] <= n ? citations[i] : n] += 1;
        }
        int sum = 0;
        // 找出最大的H指数
        for(int i = n; i > 0; i--){
            // 引用大于等于i次的文章数量，等于引用大于等于i+1次的文章数量，加上引用等于i次的文章数量 
            sum += stats[i];
            // !!! at least h papers have the citations greater or equals than h
            if(sum >= i){
                return i;
            }
        }
        return 0;
    }
}