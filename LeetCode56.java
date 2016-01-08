/*
56. Merge Intervals My Submissions Question
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        return helper(intervals);
    }
    
    private List<Interval> helper(List<Interval> intervals)
    {
        List<Interval> res = new ArrayList<>();
        if(intervals == null || intervals.size() == 0) return res;
        
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2)
            {
                return i1.start - i2.start;
            }
        });
        
        Interval prev = intervals.get(0);
        for(int i = 0; i < intervals.size(); i++)
        {
            if(intervals.get(i).start <= prev.end)
            {
                prev.end = Math.max(prev.end, intervals.get(i).end);
            }else{
                res.add(prev);
                prev = intervals.get(i);
            }
        }
        
        res.add(prev);
        return res;
    }
}