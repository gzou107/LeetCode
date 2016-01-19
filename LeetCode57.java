/*
57. Insert Interval My Submissions Question
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        //return help1(intervals, newInterval);
        //return help0(intervals, newInterval);
        return help2(intervals, newInterval);
    }
    
    public List<Interval> help0(List<Interval> intervals, Interval newInterval)
    {
        if(intervals == null || intervals.size() == 0){
            List<Interval> res = new ArrayList<Interval>(1);
            res.add(newInterval);
            return res;
        }
        
        int i = 0;
        while(i < intervals.size() && intervals.get(i).end < newInterval.start){
            i++;
        }
        
        while(i < intervals.size() && intervals.get(i).start <= newInterval.end)
        {
            newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
            newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
            intervals.remove(i);
        }
        
        intervals.add(i, newInterval);
        return intervals;
    }
    
    public List<Interval> help1(List<Interval> intervals, Interval newInterval)
    {
        if(intervals == null || intervals.size() == 0){
            List<Interval> res = new ArrayList<Interval>(1);
            res.add(newInterval);
            return res;
        }
        
        int i = 0;
        // instead of linear, we can do binary search to find first interval whose
        // end is bigger or equals to newInterval.start (first overlap interval) 
        if(intervals.get(0).start > newInterval.end){
            i = 0;
        }else if(intervals.get(intervals.size() - 1).end < newInterval.start) {
            i = intervals.size();
        }else{
            // such index must between [0, intervals.size()-1], both inclusive
            int l = 0;
            int r = intervals.size() - 1;
            
            while (l < r)
            {
                int m = l + (r-l)/2;
                
                if(intervals.get(m).end < newInterval.start){
                    l = m + 1;
                }else{
                    r = m;
                }
            }
            i = l;
        }
        
        while(i < intervals.size() && intervals.get(i).start <= newInterval.end)
        {
            newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
            newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
            intervals.remove(i);
        }
        
        intervals.add(i, newInterval);
        return intervals;
    }
    
    public List<Interval> help2(List<Interval> intervals, Interval newInterval)
    {
        List<Interval> res = new LinkedList<>();
        boolean isAdded = false;
        
        for(int i = 0; i < intervals.size(); i++)
        {
            if(intervals.get(i).end < newInterval.start){
                res.add(intervals.get(i));
            }else if(intervals.get(i).start > newInterval.end){
                res.add(newInterval);
                newInterval = intervals.get(i);
            }else{
                newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
                newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            }
        }
        
        res.add(newInterval);

        return res;
    }
    
    public List<Interval> help3(List<Interval> intervals, Interval newInterval)
    {
        List<Interval> res = new LinkedList<>();
        boolean isAdded = false;
        
        for(int i = 0; i < intervals.size(); i++)
        {
            if(intervals.get(i).end < newInterval.start){
                res.add(intervals.get(i));
            }else if(intervals.get(i).start > newInterval.end){
                if(!isAdded){
                    res.add(newInterval);
                    isAdded = true;
                }
                res.add(intervals.get(i));
            }else{
                newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
                newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            }
        }
        
        if(!isAdded){
            res.add(newInterval);
        }
        
        return res;
    }
}