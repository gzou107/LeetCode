/*
146. LRU Cache My Submissions Question
Total Accepted: 60550 Total Submissions: 387218 Difficulty: Hard
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
*/

public class LRUCache {
    
    private class Node{
        public Node prev;
        public Node next;
        public int key;
        public int val;
        
        public Node(int key, int val)
        {
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }
    
    private Node head;
    private Node tail;
    Map<Integer, Node> m;
    private int size;
    private int capacity;
    
    public LRUCache(int capacity) {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        
        m = new HashMap<Integer, Node>();
        size = 0;
        this.capacity = capacity;
    }
    
    public int get(int key) 
    {
        Node t = m.get(key);
        
        if(t == null) return -1;
        
        moveToHead(t);
        return t.val;
    }
    
    // remove last node
    private void removeLast(Node t)
    {
       Node last = tail.prev;
       last.prev.next = tail;
       tail.prev = last.prev; // we're dropping last one here
       m.remove(last.key);
    }
    
    // put a non-head node to head
    private void moveToHead(Node t)
    {
        t.next.prev = t.prev;
        t.prev.next = t.next;
        appendHead(t);
    }
    
    // put an indepent node into front of list
    private void appendHead(Node t)
    {
        t.next = head.next;
        head.next.prev = t;
        head.next = t;
        t.prev = head;
    }
    
    public void set(int key, int value) 
    {
        if(!m.containsKey(key))
        {
            Node newNode = new Node(key, value);
            m.put(key, newNode);
            appendHead(newNode);
            size++;
            
            if(size > capacity)
            {
                removeLast(tail);
                size--;
            }
        }else{
            Node node = m.get(key);
            node.val = value;
            moveToHead(node);
        }
    }
}