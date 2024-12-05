public class Node {
    protected Task task;
    protected Node prev;
    protected Node next;

    public Node(Node prev, Task task, Node next) {
        this.task = task;
        this.prev = prev;
        this.next = next;
    }
}
