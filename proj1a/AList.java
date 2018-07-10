public class AList {
  private int[] items;
  private int size;

  public AList() {
    items = new int[100];
    size = 0;
  }

  private void resize(int capacity) {
    int [] a = new int[capacity];
    System.arraycopy(items, 0, a, 0, 2 * capacity);
    items = a;
  }

  public void addLast(int x) {
    if (size == items.length) {
      resize( size * 2);
    }
    items[size] = x;
    size += 1;
  }

  public int getLast() {
    return items[size - 1];
  }

  public int get(int i) {
    if (i > size) {
      return -1;
    }
    return items[i];
  }

  public int size() {
    return size;
  }

  public int removeLast() {
    int x = getLast();
    size -= 1;
    return x;
  }
}
