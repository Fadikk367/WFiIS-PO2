public class Stack<T> {
  private int maxSize;
  private int currentSize;
  private T[] data;

  public Stack(int maxSize) {
    this.maxSize = maxSize;
    this.currentSize = 0;

    @SuppressWarnings("unchecked")
    final T[] stack = (T[]) new Object[maxSize];
    this.data = stack;
  }

  public boolean isEmpty() {
    return this.currentSize == 0;
  }

  public boolean isFull() {
    return this.currentSize == this.maxSize;
  }

  public void push(T item) throws StackOverflowException {
    if (isFull()) {
      throw new StackOverflowException("PRZEPELNIENIE STOSU");
    }

    this.data[this.currentSize] = item;
    this.currentSize++;
  }

  public T pop() throws StackUnderflowException {
    if (isEmpty()) {
      throw new StackUnderflowException("PUSTY STOS");
    }

    this.currentSize--;
    return this.data[this.currentSize];
  }

  public String toString() {
    StringBuilder result = new StringBuilder();
    if (!isEmpty()) {
      for (int i = 0; i < this.currentSize - 1; i++) {
        result.append(this.data[i] + ", ");
      }
      result.append(this.data[this.currentSize - 1]);
    }

    return result.toString();
  }
}
