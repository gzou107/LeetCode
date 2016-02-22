/*
134. Gas Station My Submissions Question
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
*/

public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        return help0(gas, cost);
    }
 // https://leetcode.com/discuss/60230/straightforward-linear-solution-space-explanation-proof
    private int help0(int[]gas, int[]cost)
    {
        if(gas == null || cost == null || gas.length == 0 || cost.length == 0 || gas.length != cost.length) return -1;
        
        int left = 0;
        for(int i = 0; i <gas.length; i++ )
        {
            left += gas[i] - cost[i];
        }
        if(left < 0) return -1;
        
        int accu = 0;
        int start = 0;
        for(int i = 0; i < gas.length; i++)
        {
            int extra = gas[i] - cost[i];
            if(accu + extra < 0)
            {
                start = i + 1;
                accu = 0;
            }else{
                accu+= extra;
            }
        }
        
        return start;
    }
}