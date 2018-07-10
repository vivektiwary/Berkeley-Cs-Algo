public class ArrayDeque<T> {
  private static final int INITIAL_START_PTR = 3;
  private static final int INITIAL_END_PTR = 4;
  private static final int STARTING_SIZE = 0;

  private T[] items;
  private int nextFront;
  private int nextEnd;
  private int size;

  public ArrayDeque() {
    items = (T[]) new Object[8];
    nextFront = INITIAL_START_PTR;
    nextEnd = INITIAL_END_PTR;
    size = STARTING_SIZE;
  }

  private void copyTillEndPtr(T[] a, int endPtr) {
    System.arraycopy(items, 0, a, 0, endPtr);
  }

  private void copyFromStartPtr(T[] a, int startPrt) {
    System.arraycopy(items, nextFront, a, startPrt, items.length - nextFront);
  }

  private void resizeWithPointerChange(int capacity) {
    T[] a = (T[]) new Object[capacity];
    copyTillEndPtr(a, nextEnd);

    int startPos = (capacity - (items.length - nextFront));
    copyFromStartPtr(a, startPos);
    nextFront = startPos;
    items = a;
  }

  private void resize(int capacity) {
    if (nextEnd < nextFront) {
      resizeWithPointerChange(capacity);
    } else {
      T[] a = (T[]) new Object[capacity];
      System.arraycopy(items, 0, a, 0, items.length);
      items = a;
    }
  }

  private int decrement(int ptr) {
    ptr = (ptr - 1) % items.length;
    if (ptr == -1) {
      ptr = items.length - 1;
    }
    return ptr;
  }

  public void addFirst(T x) {
    if (decrement(nextFront) == nextEnd) {
      resize(items.length * 2);
    }
    items[nextFront] = x;
    nextFront = decrement(nextFront);
    size += 1;
  }

  public void addLast(T x) {
    if ((nextEnd + 1) % 8 == nextFront) {
      resize(items.length * 2);
    }
    items[nextEnd] = x;
    nextEnd = (nextEnd + 1) % items.length;
    size += 1;
  }

  public boolean isEmpty() {
    return size == 0;
  }
  public int size() {
    return size;
  }

  public void printDeque() {
    int start = nextFront + 1;
    while (start != nextEnd) {
      System.out.println(items[start]);
      start = (start + 1) % 8;
    }
  }

  public T removeFirst() {
    T removedItem = items[nextFront + 1];
    nextFront = (nextFront + 1) % 8;
    size -= 1;
    return removedItem;
  }

  public T removeLast() {
    T removedItem = items[nextEnd - 1];
    nextEnd = decrement(nextEnd);
    size -= 1;
    return removedItem;
  }

  public T get(int index) {
    return items[index];
  }

  public static void main(String[] args) {
    ArrayDeque<Integer> ad = new ArrayDeque<>();
    ad.addFirst(10);
    ad.addFirst(20);
    ad.addFirst(30);
    ad.addLast(5);
    ad.addLast(50);
    ad.addLast(80);
    ad.addLast(100);
    ad.addLast(120);
    // ad.printDeque();
  }
}
