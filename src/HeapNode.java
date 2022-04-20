/**
 * Class for a single element in a mergeable heap;
 * Each element is a node in a binomial heap
 * @param <E> generic parameter
 */
public class HeapNode<E> {

    private int degree; // the depth of a binomial tree
    private int key; // integer key for denoting priority of a node
    private E value; // value stored in each node of a binomial tree
    private HeapNode<E> parent; // parent node at the next level up
    private HeapNode<E> child; // the leftmost child node
    private HeapNode<E> sibling; // the next(to the right) node at the same level

    HeapNode(){
        degree = -1;
        key = -1;
        value = null;
        parent = null;
        child = null;
        sibling = null;
    }

    HeapNode(E value, int key){
        degree = 0;
        this.key = key;
        this.value = value;
        parent = null;
        child = null;
        sibling = null;
    }

    /**
     * Method to set the degree of a node
     * @param degree is a given degree
     */
    void setDegree(int degree){
        this.degree = degree;
    }

    /**
     * Method to set the key of a node
     * @param key is a given key
     */
    void setKey(int key){
        this.key = key;
    }

    /**
     * Method to set the value of a node
     * @param value is a given value
     */
    void setValue(E value){
        this.value = value;
    }

    /**
     * Method to set the parent node
     * @param parent is a given node
     */
    void setParent(HeapNode<E> parent){
        this.parent = parent;
    }

    /**
     * Method to set the child node
     * @param child is a given node
     */
    void setChild(HeapNode<E> child){
        this.child = child;
    }

    /**
     * Method to set the sibling node
     * @param sibling is a given node
     */
    void setSibling(HeapNode<E> sibling){
        this.sibling = sibling;
    }

    int getKey(){
        return key;
    }

    E getValue(){
        return value;
    }

    int getDegree(){
        return degree;
    }

    HeapNode<E> getParent(){
        return parent;
    }

    HeapNode<E> getChild(){
        return child;
    }

    HeapNode<E> getSibling(){
        return sibling;
    }

    void incDegree(){
        degree += 1;
    }
}
