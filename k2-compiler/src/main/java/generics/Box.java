package generics;

public class Box<E> {

  private E value;

  public E getValue() {
    return value;
  }

  public void setValue(E value) {
    this.value = value;
  }
}