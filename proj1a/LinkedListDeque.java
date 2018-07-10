public class LinkedListDeque<T> {
  private static class Node<T> {
    T item;
    Node<T> prev;
    Node<T> next;
    Node(T i, Node<T> p, Node<T> n) {
      item = i;
      prev = p;
      next = n;
    }
  }

  private Node<T> sentinel;
  private int size;

  public LinkedListDeque() {
    sentinel = new Node<>(null, null, null);
    sentinel.prev = sentinel;
    sentinel.next = sentinel;

    size = 0;
  }

  public void addFirst(T x) {
    Node<T> newNode = new Node<>(x, sentinel, sentinel.next);
    sentinel.next.prev = newNode;
    sentinel.next = newNode;
    size += 1;
  }

  public void addLast(T x) {
    Node<T> newNode = new Node<>(x, sentinel.prev, sentinel);
    sentinel.prev.next = newNode;
    sentinel.prev = newNode;

    size += 1;
  }

  public boolean isEmpty() {
    return (sentinel.next == sentinel && sentinel.prev == sentinel);
  }

  public int size() {
    return size;
  }

  public void printDeque() {
    Node<T> p = sentinel.next;
    while (p != sentinel) {
      System.out.print(p.item + " ");
      p = p.next;
    }
    System.out.println();
  }

  public T removeFirst() {
    Node<T> removedNode = sentinel.next;
    T removedItem = removedNode.item;

    sentinel.next = sentinel.next.next;
    sentinel.next.prev = sentinel;

    removedNode = null;
    size -= 1;

    return removedItem;
  }

  public T removeLast() {
    Node<T> removedNode = sentinel.prev;
    T removedItem = removedNode.item;

    sentinel.prev = removedNode.prev;
    sentinel.prev.next = sentinel;

    removedNode = null;
    size -= 1;

    return removedItem;
  }

  private T getFromEnd(int indexFromEnd) {
    Node<T> last = sentinel.prev;
    int counter = 0;
    while (counter < indexFromEnd) {
      last = last.prev;
      counter += 1;
    }
    return last.item;
  }

  private T getFromFront(int indexFromFront) {
    Node<T> p = sentinel.next;
    int counter = 0;
    while (counter < indexFromFront) {
      p = p.next;
      counter += 1;
    }
    return p.item;
  }


  public T get(int index) {
    // trying to access item which is not in the deque
    if (index >= size || index < 0) {
      return null;
    }

    int itemPosFromEnd = size - index - 1;

    if (index > itemPosFromEnd) {
      return getFromEnd(itemPosFromEnd);
    } else {
      return getFromFront(index);
    }
  }

  private T getRecursive(Node<T> p, int index) {
    if (index == 0) {
      return p.item;
    }

    return getRecursive(p.next, index - 1);
  }

  public T getRecursive(int index) {
    if (index >= size || index < 0) {
      return null;
    }

    return getRecursive(sentinel.next, index);
  }

  public static void main(String[] args) {
    LinkedListDeque<Integer> lld = new LinkedListDeque<>();
    lld.addFirst(10);
    lld.addLast(20);
    lld.addFirst(5);
    // System.out.println(lld.removeFirst());
    // System.out.println(lld.removeLast());
    // System.out.println(lld.getRecursive(1));
    // System.out.println(lld.get(2));
//    System.out.println(lld.isEmpty());
  }
}
