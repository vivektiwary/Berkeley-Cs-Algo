public class IntList {
    private int first;
    private IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }

    /** return the size of the list iteratively */
    public int iterativeSize() {
        IntList p = this;
        int totalSize = 0;
        while (p != null) {
            totalSize += 1;
            p = p.rest;
        }
        return totalSize;
    }

    public int get(int index) {
        if (index == 0) {
            return first;
        }
        return this.rest.get(index - 1);
    }

    public static void main(String[] args) {
        IntList l = new IntList(15, null);
        l = new IntList(10, l);
        l = new IntList(5, l);

        // System.out.println(l.size());
        // System.out.println(l.iterativeSize());
        System.out.println(l.get(1));
    }
}
