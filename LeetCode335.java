/*
335. Self Crossing My Submissions Question
You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to the north, then x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so on. In other words, after each move your direction changes counter-clockwise.

Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.

Example 1:
Given x = [2, 1, 1, 2],
┌───┐
│   │
└───┼──>
    │

Return true (self crossing)
Example 2:
Given x = [1, 2, 3, 4],
┌──────┐
│      │
│
│
└────────────>

Return false (not self crossing)
Example 3:
Given x = [1, 1, 1, 1],
┌───┐
│   │
└───┼>

Return true (self crossing)
*/

public class Solution {
    public boolean isSelfCrossing(int[] x) {
        return help0(x);
    }
    
    private boolean help0(int[]x)
    {
        // https://www.hrwhisper.me/leetcode-self-crossing/
        // https://leetcode.com/discuss/88038/java-o-n-o-1-0ms-solution-with-explanation
       int n = x.length;
		if (n < 4) return false;
		int t1 = 0, t2 = x[0], t3 = x[1], t4 = x[2], t5;
		boolean increase = t4 > t2 ? true : false;
		for (int i = 3; i < n; i++) {
			t5 = x[i];
			if (increase && t3 >= t5) {
				if (t5 + t1 < t3 || i + 1 < n && x[i + 1] + t2 < t4)
					increase = false;
				else if (i + 1 < n)
					return true;
			}
			else if (!increase && t3 <= t5)
				return true;
			t1 = t2;
			t2 = t3;
			t3 = t4;
			t4 = t5;
		}
		return false;
    }
}