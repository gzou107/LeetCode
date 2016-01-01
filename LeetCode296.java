/*
296. Best Meeting Point My Submissions Question
A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.

Hint:

Try to solve it in one dimension first. How can this solution apply to the two dimension case?
*/

public class Solution {
    public int minTotalDistance(int[][] grid) {
        
        return helper(grid);
    }
    
    private int helper(int[][]d)
    {
        List<Integer>x = new ArrayList<Integer>();
        List<Integer>y = new ArrayList<Integer>();
        
        for(int i = 0; i<d.length; i++){
            for(int j = 0; j<d[0].length; j++){
                if(d[i][j]==1){
                    x.add(i);
                }
            }
        }
        
        for(int i = 0; i < d[0].length; i++){
            for(int j = 0; j < d.length; j++)
            {
                if(d[j][i] == 1){
                    y.add(i);
                }
            }
        }
        
        return getMin(x) + getMin(y);
    }
    
    private int getMin(List<Integer>d)
    {
        int l =0;
        int r = d.size() -1;
        int sum = 0;
        while(l < r)
        {
            sum += d.get(r--) - d.get(l++);
        }
        return sum;
    }
    
    private int helper1(int[][]distance)
    {
        List<Integer>x = new ArrayList<Integer>();
        List<Integer>y = new ArrayList<Integer>();
        for(int i = 0; i<distance.length; i++){
            for(int j = 0; j<distance[0].length; j++){
                if(distance[i][j]==1){
                    x.add(i);
                    y.add(j);
                }
            }
        }
        
        Collections.sort(x);
        Collections.sort(y);
        int idx = x.get(x.size()/2);
        int ydx = y.get(y.size()/2);
        int total = 0;
        for(int i = 0; i < x.size(); i++)
        {
            total += x.get(i) < idx ? idx - x.get(i) : x.get(i) - idx;
        }
        
        for(int i = 0; i < y.size(); i++)
        {
            total += y.get(i) < ydx ? ydx - y.get(i) : y.get(i) - ydx;
        }
        
        return total;
    }
}