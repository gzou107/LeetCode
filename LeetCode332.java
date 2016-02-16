/*
332. Reconstruct Itinerary My Submissions Question
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.
*/

public class Solution {
    public List<String> findItinerary(String[][] tickets) {
        return help(tickets);
    }
    
    private List<String> help(String[][] tickets)
    {
        List<String> res = new LinkedList<>();
        if(tickets == null || tickets.length == 0) return res;
        
        Map<String, PriorityQueue<String>> its = new HashMap<>(); //stores all the to according to lexical order
        for(String[] pair : tickets){
            String from = pair[0];
            String to = pair[1];
            if(!its.containsKey(from)){
                its.put(from, new PriorityQueue<String>());
            }
            its.get(from).add(to);
        }
        
        String curr = "JFK";
        Stack<String> drawBack = new Stack<>();
        for(int i = 0; i < tickets.length; i++)
        {
            // remove curr if it's not on thr optimal solution 
            // two cases: only dead point (no leaving, only arrival at it)
            //   or it has leaving tickets, but has been used already.
            //   need backtrack!!!
            while(!its.containsKey(curr) || its.get(curr).isEmpty() )
            {
                drawBack.push(curr);
                curr = res.remove(res.size()-1);
            }
            
            res.add(curr);
            curr = its.get(curr).poll();
        }
        res.add(curr);
        while(!drawBack.isEmpty()){
            res.add(drawBack.pop());
        }
        return res;
    }
}