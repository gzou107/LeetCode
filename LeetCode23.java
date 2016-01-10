/*
23. Merge k Sorted Lists My Submissions Question
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        
        //return helper(lists);
        return helper0(lists);
    }
    
    private ListNode helper(ListNode [] lists)
    {
        if(lists == null || lists.length == 0) return null;
        
       // return mergeH(lists, 0, lists.length - 1);
        return mergeIte(lists, 0, lists.length - 1);
    }
    
    private ListNode mergeIte(ListNode[] lists, int l, int r)
    {
        while( r > 0)
        {
            int begin = 0;
            while(begin < r)
            {
                lists[begin] = merge(lists[begin], lists[r]);
                begin++;
                r--;
            }
        }
        return lists[0];
    }
    
    
    private ListNode mergeH(ListNode[] lists, int l, int r)
    {
        if(l == r){
            return lists[l];
        }
        
        int m = l + (r - l)/2;
        ListNode left = mergeH(lists, l, m);
        ListNode right = mergeH(lists, m+1, r);
        
        ListNode root = merge(left, right);
        return root;
    }
    
    private ListNode merge(ListNode l1, ListNode l2)
    {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        while(l1 != null && l2 != null)
        {
            if(l1.val < l2.val)
            {
                tail.next = l1;
                tail = l1;
                l1 = l1.next;
            }else{
                tail.next = l2;
                tail = l2;
                l2 = l2.next;
            }
        }
        
        if(l1 == null)
        {
            tail.next = l2;
        }
        
        if(l2 == null)
        {
            tail.next = l1;
        }
        
        return dummy.next;
    }
    
    private ListNode helper0(ListNode[] lists)
    {
        if(lists == null || lists.length == 0) return null;
        
        PriorityQueue<ListNode> pq = new PriorityQueue(lists.length, new Comparator<ListNode>()
        {
            public int compare(ListNode l0, ListNode l1)
            {
                return l0.val - l1.val;
            }
        });
        
        for(int i = 0; i < lists.length; i++){
            if(lists[i] != null){
                pq.offer(lists[i]);
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while(!pq.isEmpty())
        {
            ListNode top = pq.poll();
            tail.next = top;
            tail = top;
            if(top.next != null){
                pq.offer(top.next);
            }
        }
        
        return dummy.next;
    }
}