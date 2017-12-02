package com.company.practice;

/*
 * Sort linked list node[1] -> node[2] -> ... -> node[N] into node[1] -> node[N] -> node[2] -> node[N-1] -> ...
 */
public class AlternateLinkedList implements IProblem {

    public String getName() {
        return "Alternate Linked List";
    }

    public void run() {

        Node[] nodes = new Node[11];
        int count = 5;
        for(int i = count-1; i>=0; i--) {
            nodes[i] = new Node();
            nodes[i].Value = i;
            nodes[i].Next = nodes[i+1];
        }

        LinkedListSorter sorter = new LinkedListSorter();
        sorter.sort(nodes[0]);

        Node cur = nodes[0];
        while(cur != null ) {
            System.out.print(cur.Value + " ");
            cur = cur.Next;
        }
    }

}

class Node {
    Node Next;
    int Value;
}

class LinkedListSorter {

    public Node sort(Node head) {
        if(head == null )
            return null;

        Node mid = findMid(head);
        if(mid == null)
            return head;

        Node midNext = mid.Next;
        mid.Next = null;

        midNext = reverse(midNext);
        head = combine(head, midNext);

        return head;
    }

    private Node findMid(Node head) {
        if(head == null)
            return null;

        Node cur1 = head.Next, cur2 = head;
        while(true) {
            if(cur1 != null)
                cur1 = cur1.Next;
            else
                break;

            if(cur1 != null)
                cur1 = cur1.Next;
            else
                break;

            cur2 = cur2.Next;
        }

        return cur2;
    }

    private Node reverse(Node head) {
        Node cur = head, temp;
        head = null;
        while(cur != null) {
            temp = cur.Next;
            cur.Next = head;
            head = cur;
            cur = temp;
        }
        return head;
    }

    private Node combine(Node head1, Node head2) {
        Node head = null, tail = null, temp;
        while (head1 != null && head2 != null) {
            if(head == null)
                head = head1;

            if( tail != null )  {
                tail.Next = head1;
            }

            tail = head1;
            head1 = head1.Next;

            tail.Next = head2;
            tail = head2;
            head2 = head2.Next;
        }

        if (head1 != null ) {
           if(head == null)
               head = head1;

           if(tail != null)
               tail.Next = head1;
           tail = head1;
        }

        tail.Next = null;
        return head;
    }

}

