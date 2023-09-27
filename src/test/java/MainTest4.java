import org.junit.Test;

public class MainTest4 {

    @Test
    public void test(){

        Node node1 = new Node(1);
        Node node2 = new Node(2,node1);
        Node node3 = new Node(3,node2);
        Node node4 = new Node(4,node3);
        Node node5 = new Node(5,node4);


        Node left = new Node();
        left.next = node5;

        Node right = new Node();
        right.next = node5.next;
        // l    r

        // 1  2 -> 3 -> 4 -> null
        node5.next = null;

        Node tmp = null;
        while(right.next != null){
            tmp = left.next;
            left .next = right.next;
            right.next = right.next.next;
            left.next.next = tmp;
        }


        left = left.next;
        while (left != null){
            System.out.println(left.val);
            left = left.next;
        }

    }
}

class Node {
    int val;

    Node next;

    public Node(int val,Node next){
        this.val = val;
        this.next = next;
    }

    public Node(int val){
        this.val = val;
    }


    public Node(){
    }
}
