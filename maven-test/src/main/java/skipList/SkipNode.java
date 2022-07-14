package skipList;

public class SkipNode<T> {
    int key;
    T value;
    SkipNode right, down;//左右上下四个方向的指针

    public SkipNode(int key, T value) {
        this.key = key;
        this.value = value;
    }
}
