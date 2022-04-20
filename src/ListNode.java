/**
 * Class that represents each element(a single TO-DO list) in a list of TO-DO lists
 * @param <E> generic parameter
 */
public class ListNode<E> {

    private BinomialHeap<E> toDo; // TO-DO list implemented with a binomial heap
    private ListNode<E> next; // next TO-DO list
    private String date; // date of a TO-DO list
    private int dd, mm, yy;

    ListNode(){
        next = null;
        toDo = null;
        date = null;
        dd = 0;
        mm = 0;
        yy = 0;
    }

    ListNode(TaskInfo taskInfo){
        next = null;
        toDo = new BinomialHeap<>();
        this.date = taskInfo.getDateStr();
        this.dd = taskInfo.getDd();
        this.mm = taskInfo.getMm();
        this.yy = taskInfo.getYy();
    }

    void setToDo(BinomialHeap<E> heap){
        toDo = heap;
    }

    void setNext(ListNode<E> node){
        next = node;
    }

    void setDate(String date){
        this.date = date;
    }

    void setDd(int dd){
        this.dd = dd;
    }

    void setMm(int mm){
        this.mm = mm;
    }

    void setYy(int yy){
        this.yy = yy;
    }

    ListNode<E> getNext(){ return next; }

    BinomialHeap<E> getToDo(){
        return toDo;
    }

    String getDate(){
        return date;
    }

    int getDd(){
        return dd;
    }

    int getMm(){
        return mm;
    }

    int getYy(){
        return yy;
    }

    boolean isEmpty(){
        if (toDo.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * Method to compare dates of two TO-DO lists
     * @param node is a TO-DO list which we compare with
     * @return true if this date is older, than node's date
     *         otherwise, false
     */
    boolean isSmaller(ListNode<E> node){
        if (yy < node.getYy()){
            return true;
        }
        if (yy > node.getYy()){
            return false;
        }
        if (mm < node.getMm()){
            return true;
        }
        if (mm > node.getMm()){
            return false;
        }
        if (dd < node.getDd()){
            return true;
        }
        if (dd > node.getDd()){
            return false;
        }
        return false;
    }

    /**
     * Method to print tasks of a single TO-DO list in descending order of priority;
     * Also to construct a binomial heap containing tasks of all TO-DO lists
     * @param commonHeap is a binomial heap containing tasks of all TO-DO lists
     */
    public void printToDo(BinomialHeap<E> commonHeap){

        HeapNode<E> cur;

        while (!isEmpty()){
            cur = this.getToDo().removeMax();
            commonHeap.insert(cur.getKey(), cur.getValue());
            System.out.println("\t"+cur.getValue());
        }

    }

}