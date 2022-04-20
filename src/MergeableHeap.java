/**
 * Interface consisting of basic functions for implementation of a mergeable heap
 * @param <E> generic parameter
 */
public interface MergeableHeap<E> {

    HeapNode<E> getHead();

    void setHead(HeapNode<E> HeapNode);

    boolean isEmpty();

    HeapNode<E> max();

    void merge(BinomialHeap<E> heap2);

    void insert(int key, E value);

    HeapNode<E> removeMax();

}
