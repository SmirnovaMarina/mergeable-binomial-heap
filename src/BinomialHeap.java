/**
 * Class for implementation of a mergeable MAX heap by a binomial heap
 * @param <E> generic parameter
 */
public class BinomialHeap<E> implements MergeableHeap<E> {

    private HeapNode<E> head; // node whose sibling node is the first binomial tree in the heap

    BinomialHeap() {
        head = new HeapNode<E>();
    }

    public HeapNode<E> getHead() {
        return head;
    }

    public void setHead(HeapNode<E> HeapNode) {
        head = HeapNode;
    }

    /**
     * Method to check whether the heap is empty or not
     * @return true if the heap is empty; false if heap is not empty
     */
    public boolean isEmpty() {
        if (head.getSibling() == null) {
            return true;
        }
        return false;
    }

    /**
     * Method to find the node with the max key
     * @return pair key-value of the node with the max key
     */
    public Pair<E> max() {

        HeapNode<E> HeapNode = new HeapNode<E>();
        HeapNode<E> cur = head.getSibling();
        int max = Integer.MIN_VALUE;

        while (cur != null) {

            if (cur.getKey() > max) {
                max = cur.getKey();
                HeapNode = cur;
            }
            cur = cur.getSibling();
        }

        Pair<E> pair = new Pair<>();
        try{
            pair = new Pair<>(HeapNode.getKey(), HeapNode.getValue());
        } catch(NullPointerException e){
            System.out.println("The heap was empty.");
        }

        return pair;
    }

    /**
     * Method links 2 roots of binomial trees B(k-1) with the same degree (k-1);
     * HeapNode z becomes the root of a binomial tree B(k) with degree k;
     * HeapNode y becomes the leftmost child of binomial tree B(k)
     *
     * @param y is the root HeapNode of binomial tree
     * @param z is the root HeapNode of another binomial tree
     */
    public void link(HeapNode<E> y, HeapNode<E> z) {
        y.setParent(z);
        y.setSibling(z.getChild());
        z.setChild(y);
        z.incDegree();
    }

    /**
     * Method to merge root lists of two binomial heaps
     * @param heap2 is the second heap which we merge with
     * @return merged binomial heap with repeated degrees of root nodes
     */
    public HeapNode<E> mergeRootLists(BinomialHeap<E> heap2) {

        BinomialHeap<E> newHeap = new BinomialHeap<>();

        HeapNode<E> curNew = newHeap.getHead();
        HeapNode<E> cur1 = head;
        HeapNode<E> cur2 = heap2.getHead();

        if (isEmpty()) {
            newHeap.setHead(heap2.getHead());
        } else {
            if (heap2.isEmpty()) {
                newHeap.setHead(head);
            } else {
                cur1 = cur1.getSibling();
                cur2 = cur2.getSibling();
                while (cur1 != null && cur2 != null) {

                    if (cur1.getDegree() <= cur2.getDegree()) {
                        curNew.setSibling(cur1);
                        curNew = curNew.getSibling();
                        cur1 = cur1.getSibling();
                    } else {
                        curNew.setSibling(cur2);
                        curNew = curNew.getSibling();
                        cur2 = cur2.getSibling();
                    }
                }

                if (cur1 == null && cur2 != null) {
                    curNew.setSibling(cur2);
                }
                if (cur2 == null && cur1 != null) {
                    curNew.setSibling(cur1);
                }
            }
        }

        return newHeap.getHead();
    }

    /**
     * Method to merge two binomial  heaps
     * @param heap2 is the second heap which we merge with
     */
    public void merge(BinomialHeap<E> heap2) {

        BinomialHeap<E> newHeap = new BinomialHeap<>();
        newHeap.setHead(mergeRootLists(heap2)); // new heap is a merged binomial heap with repeated degrees of root nodes

        try{

            HeapNode<E> cur = newHeap.getHead().getSibling();
            HeapNode<E> prev = null;
            HeapNode<E> next = cur.getSibling();

            while (next != null) {

                // if two root nodes have different degrees or there are three roots with the same degree in a row
                if ((cur.getDegree() != next.getDegree()) || (next.getSibling() != null && next.getSibling().getDegree() == cur.getDegree())) {
                    prev = cur;
                    cur = next;
                } else {
                    // if key of cur is greater, than next key, then it becomes a parent of next node
                    if (cur.getKey() >= next.getKey()) {
                        cur.setSibling(next.getSibling());
                        link(next, cur);
                    } else {
                        // if key of cur is smaller, than next key, then it becomes a child of next node
                        if (prev == null) {
                            newHeap.getHead().setSibling(next);
                        } else {
                            prev.setSibling(next);
                        }
                        link(cur, next);
                        cur = next;
                    }
                }
                next = cur.getSibling();
            }

            // the first(the current) heap becomes the merged heap
            setHead(newHeap.getHead());
        } catch (NullPointerException e){
            System.out.println("The new heap was empty.");
        }
    }

    /**
     * Method to insert a new key-value pair into the binomial heap
     * @param key is priority of a node
     * @param value is value of a node to store
     */
    public void insert(int key, E value) {

        BinomialHeap<E> newHeap = new BinomialHeap<>();
        HeapNode<E> HeapNode = new HeapNode<>(value, key);
        newHeap.getHead().setSibling(HeapNode);

        merge(newHeap); // merge old heap and heap with the new node
    }

    /**
     * Method to find a node occurring before the node with max key
     * @return a node occurring before the node with max key
     */
    public HeapNode<E> findPreMax() {

        int max = Integer.MIN_VALUE;
        HeapNode<E> cur = head;
        HeapNode<E> maxPreHeapNode = new HeapNode<E>();

        while (cur.getSibling() != null) {
            if (cur.getSibling().getKey() > max) {
                max = cur.getSibling().getKey();
                maxPreHeapNode = cur;
            }
            cur = cur.getSibling();
        }

        return maxPreHeapNode;
    }

    /**
     * Method to remove a node with max key
     * @return a node with max key
     */
    public HeapNode<E> removeMax() {

        if (isEmpty()) {
            return null;
        }

        HeapNode<E> maxPreHeapNode = findPreMax();
        HeapNode<E> maxHeapNode = maxPreHeapNode.getSibling();
        maxPreHeapNode.setSibling(maxHeapNode.getSibling()); // delete max node
        /*
            create a new heap consisting of children of max HeapNode;
            set its head to be the rightmost child(to preserve increasing order of
            degrees of binomial trees in the new heap)
         */
        BinomialHeap<E> newHeap = new BinomialHeap<>();
        HeapNode<E> cur = maxHeapNode.getChild();
        HeapNode<E> auxilNext = null;
        HeapNode<E> auxilPrev = null;
        try {
            if (cur != null) {
                auxilNext = cur.getSibling();
            }
            while (auxilNext != null) {
                cur.setSibling(auxilPrev);
                auxilPrev = cur;
                cur = auxilNext;
                auxilNext = cur.getSibling();
            }
            cur.setSibling(auxilPrev);

        } catch (NullPointerException e){
            System.out.println("The max node does not have children");
        }

        newHeap.getHead().setSibling(cur);
        merge(newHeap); // merge heap with max node children and the heap which we delete max node's tree from
        return maxHeapNode;
    }

    /**
     * Method to find a node that has the longest path to the root;
     * Only for testing and debugging
     * @param root is a root node of a binomial tree
     * @return the leftmost leaf node
     */
    public HeapNode<E> traverseTree(HeapNode<E> root) {

        HeapNode<E> cur = root;
        while (cur.getDegree() > 0) {
            cur = cur.getChild();
        }
        return cur;
    }

    /**
     * Method to print pair key-value of a heap;
     * Only for debugging and testing
     */
    public void printHeap() {
        HeapNode<E> curRoot = head.getSibling();
        HeapNode<E> cur;

        if (curRoot == null) {
            System.out.println("empty heap");
        }

        while (curRoot != null) {
            System.out.print("curRoot value is " + curRoot.getValue() + " ");
            System.out.print("curRoot key is " + curRoot.getKey() + " ");
            System.out.println("curRoot degree is " + curRoot.getDegree() + " ");

            cur = traverseTree(curRoot);
            System.out.println("cur key after traverse" + cur.getKey());

            while (cur != curRoot) {
                System.out.print("cur value is " + cur.getValue() + " ");
                System.out.println("cur key is " + cur.getKey());

                while (cur.getSibling() != null) {
                    cur = cur.getSibling();
                    System.out.print("cur value is " + cur.getValue() + " ");
                    System.out.println("cur key is " + cur.getKey());

                }
                System.out.println("the end of the layer");
                cur = cur.getParent();
            }

            System.out.println("the end of curRoot");
            curRoot = curRoot.getSibling();
        }
    }
}