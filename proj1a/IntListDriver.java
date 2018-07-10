public class IntListDriver {
    public static void main(String[] args) {
        IntList l = new IntList(15, null);
        l = new IntList(10, l);
        l = new IntList(5, l);
    }
}
