/*
135. Candy My Submissions Question
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?
*/

public class Solution {
    public int candy(int[] ratings) {
        return help0(ratings);
    }
    // greedy solution, two passes make sure it satisfies the requirements
    private int help0(int [] ratings)
    {
        if(ratings == null) return 0;
        
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        for(int i = 1; i < ratings.length; i++)
        {
            if(ratings[i] > ratings[i-1])
            {
                candies[i] = Math.max(candies[i], candies[i-1]+1);
            }
        }
        
        for(int i = ratings.length-1; i>0; i--)
        {
            if(ratings[i] < ratings[i-1])
            {
                candies[i-1] = Math.max(candies[i-1], candies[i]+1);
            }
        }
        
        int min = 0;
        for(int num : candies)
        {
            min += num;
        }
        return min;
    }
    // TLE, O(n^2) algorithm
    private int help1(int[] ratings)
    {
        if(ratings == null) return 0;
        int[] count = new int[ratings.length];
        count[0] = 1;
        for(int i = 1; i < ratings.length; i++)
        {
            if(ratings[i] == ratings[i-1])
            {
                count[i] = count[i-1];
            }else if(ratings[i] < ratings[i-1])
            {
                count[i] = 1;
                int j = i;
                while(j >= 1 && ratings[j-1] > ratings[j] && count[j-1] <= ratings[j])
                {
                    count[j-1] += 1;
                }
            }else{
                count[i] = count[i-1]+1;
            }
        }
        int res = 0;
        for(int num : count)
        {
            res += num;
        }
        return res;
    }
}