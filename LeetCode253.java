/*
253. Meeting Rooms II My Submissions Question
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2
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
    public int minMeetingRooms(Interval[] intervals) {
        return helper0(intervals);
       // return helper(intervals);
    }
    
    private int helper0(Interval[] intervals)
    {
        if(intervals == null || intervals.length == 0) return 0;
        
        int[]start = new int[intervals.length];
        int[]end = new int[intervals.length];
        for(int i = 0; i < intervals.length; i++)
        {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        
        int i =0;
        int j = 0;
        int curMax = 0;
        int max = 0;
        // idea of merge sort
        while(i < start.length || j < end.length)
        {
            if(i >= start.length)
            {
                curMax--;
                j++;
            }else if(j >= end.length)
            {
                curMax++;
                i++;
            }else if(start[i] < end[j])
            {
                curMax++;
                i++;
            }else{
                curMax--;
                j++;
            }
            
            max = Math.max(max, curMax);
        }
        
        return max;
    }
     private int helper(Interval[] intervals)
     {
         if(intervals == null || intervals.length ==0) return 0;
         
         int max = 0;
         PriorityQueue<Interval> q = new PriorityQueue<Interval>(new Comparator<Interval>()
         {
             public int compare(Interval one, Interval other)
             {
                 return one.end - other.end;
             }
         });
         
         Arrays.sort(intervals, new Comparator<Interval>(){
             public int compare(Interval one, Interval other)
             {
                 return one.start - other.start;
             }
             });
             
        for(int i = 0; i < intervals.length; i++)
        {
            while(!q.isEmpty() && q.peek().end<= intervals[i].start){
                q.remove();
            }
            q.add(intervals[i]);
            max = Math.max(max, q.size());
        }
         return max;
     }
}