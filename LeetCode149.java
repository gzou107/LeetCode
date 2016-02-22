/*
149. Max Points on a Line My Submissions Question
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
*/

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    public int maxPoints(Point[] points) {
        return help0(points);
        //return help1(points);
    }
    
    // https://leetcode.com/discuss/9929/a-java-solution-with-notes
    private int help0(Point[]points)
    {
        // Map<Integer, Map<Integer, Integer>> m: stores for EACH point, the slope representation (x, y), and th
        // associated count
        if(points == null) return 0;
        if(points.length <= 2) return points.length;
        
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int max = 0;
        for(int i = 0; i < points.length; i++)
        {
            Point p = points[i];
            map.clear();
            int overlap = 0;
            int lMax = 0;
            for(int j = i + 1; j<points.length; j++)
            {
                Point q = points[j];
                int dx = q.x - p.x;
                int dy = q.y - p.y;
                
                if(dx == 0 && dy == 0)
                {
                    overlap++;
                    continue;
                }
                
                int dc = getGCD(dx, dy);
                if(dc != 0){
                    dx /= dc;
                    dy /= dc;
                }
                
                if(!map.containsKey(dx))
                {
                    map.put(dx, new HashMap<Integer, Integer>());
                    map.get(dx).put(dy, 1);
                }else{
                    if(!map.get(dx).containsKey(dy)){
                        map.get(dx).put(dy, 0);
                    }
                    map.get(dx).put(dy, map.get(dx).get(dy)+1);
                }
                lMax = Math.max(lMax, map.get(dx).get(dy));
            }
            
            max = Math.max(max, lMax + overlap + 1);
        }
        return max;
    }
    
   private int help1(Point[]points)
   {
       if(points == null) return 0;
       if(points.length <= 2) return points.length;
       
       int max = 0;
       for(int i = 0; i < points.length; i++)
       {
           Point p = points[i];
           int count = 0;
           int duplicates = 0;
           
           for(int j = i + 1; j < points.length; j++)
           {
               Point q = points[j];    
               if(p.x == q.x && p.y == q.y)
               {
                   duplicates++;
                   max = Math.max(max, duplicates+1);
                   continue;
               }
               
               count = 1;
               for(int k = j + 1; k < points.length; k++)
               {
                   Point r = points[k];
                   count += isColinear(p,q,r)?1:0;
               }
               
               max = Math.max(max, count+duplicates+1);
           }
       }
       return max;
   }
   
   private int getGCD(int m, int n)
   {
       if(n == 0) return m;
       return getGCD(n, m%n);
   }
   
   private boolean isColinear(Point p, Point q, Point r)
   {
       int val = (q.y - r.y)*(p.x-r.x) - (p.y - r.y) * (q.x - r.x);
       return val == 0;
   }
}