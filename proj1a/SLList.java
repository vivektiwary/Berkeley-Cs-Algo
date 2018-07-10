public class SLList {
    /**
     * typically we put nested class definition at the top
     *
     * we use nested class when a class does not stand on its own
     * IntNode should not exist without SLList
     *
     * When we have public access for nested class, any external class can
     * make instance of it
     *
     * But if we make the access private, no external class would be able to make any instance for
     * the nested class
     *
     * If the nested class never uses any details of the outer class, use 'static'
     *
     * When we make our nested class private, the access specifier does not really matter.
     */
    private static class IntNode {
        private int item;
        private IntNode next;

        IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }

        int getItem() {
            return item;
        }
    }

    // private IntNode first;

    /* The first item (if it exist) is at sentinel.next */
    private IntNode sentinel;
    private int size;

    public SLList() {
        sentinel = new IntNode(99, null); // we are not going to ask for its item, so any value would be good.
        size = 0;
    }

    public SLList(int x) {
        sentinel = new IntNode(99, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    public int getFirst() {
        return sentinel.next.getItem();
    }

    public void addLast(int x) {
        size += 1;

        IntNode p = sentinel;

        while (p.next != null) {
            p = p.next;
        }

        p.next = new IntNode(x, null);
    }

    // private static int size(IntNode p) {
    //     if (p.next == null) {
    //         return 1;
    //     }
    //     return 1 + size(p.next);
    // }

    /**
     * SLList is not a recursive data structur, So we won't be able to
     * use recursion on it in its current form
     *
     * It is a common pattern in java to create a private static method
     * which takes a recursive data structure.
     */
    public int size() {
        // return size(first);
        return size;
    }

    public static void main(String[] args) {
        // SLList l = new SLList(15);
        SLList l = new SLList();
        // l.addFirst(10);
        // l.addFirst(5);
        l.addLast(5);


        // System.out.println(l.getFirst());
        System.out.println(l.size());
    }
}
