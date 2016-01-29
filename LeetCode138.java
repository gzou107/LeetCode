/*
138. Copy List with Random Pointer My Submissions Question
Total Accepted: 57091 Total Submissions: 220679 Difficulty: Hard
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
*/

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        return help(head);
    }
    private RandomListNode help0(RandomListNode head)
    {
        if(head==null) return null;
        RandomListNode n1 = head;
        RandomListNode n2;
        // 生成新节点并接在旧节点后面
        while(n1!=null){
            n2 = new RandomListNode(n1.label);
            n2.next = n1.next;
            n1.next = n2;
            n1 = n1.next.next;
        }
        // 给新节点的random字段赋值
        n1 = head;
        n2 = n1.next;
        while(n1!=null){
            n2.random = n1.random != null ? n1.random.next : null;
            n1 = n1.next.next;
            n2 = n1 != null ? n2.next.next : null;
        }
        n1 = head;
        n2 = n1.next;
        RandomListNode res = n2;
        // 将新旧节点分开
        while(n1!=null){
            n1.next = n1.next.next;
            n2.next = n2.next != null ? n2.next.next : null;
            n1 = n1.next;
            n2 = n2.next;
        }
        return res;
    }
    private RandomListNode help(RandomListNode head)
    {
        if(head == null) return head;
        
        //first make a copy for each node
        RandomListNode current = head;
        while(current != null)
        {
            RandomListNode currentCopy = new RandomListNode(current.label);
            currentCopy.next = current.next;
            current.next = currentCopy;
            current = currentCopy.next;
        }
        //second set the corresponding random node
        current = head;
        RandomListNode currentCopy = head.next;
        while(current != null)
        {
            if(current.random != null){
                currentCopy.random = current.random.next;
            }
            current = currentCopy.next;
            if(current != null){
                currentCopy = current.next;
            }
        }
        //split current one list into two
        current = head;
        RandomListNode newHead = head.next;
        RandomListNode newTail = newHead;
        while(current != null)
        {
            current.next = current.next.next;
            newTail.next = newTail.next == null?null : newTail.next.next;
            current = current.next;
            newTail = newTail.next;
        }
        
        return newHead;
    }
}