/**
 * Class that implements list of TO-DO lists by singly linked list
 * @param <E> generic parameter
 */
public class ListOfLists<E> {

    private Main.ListNode<String> listHead; // list's node that contain the first TO-DO list
    private int size = 0;

    ListOfLists() {
        listHead = null;
    }

    Main.ListNode<String> getListHead() {
        return listHead;
    }

    void setListHead(Main.ListNode<String> node) {
        listHead = node;
    }

    /**
     * Method to determine whether there is a TO-DO list of a certain date in the list of lists
     * @param taskInfo is information about a task, especially date
     * @return null if there is no a TO-DO list of a certain date;
     *         otherwise, node with such TO-DO list
     */
    Main.ListNode<String> findDate(Main.TaskInfo taskInfo) {

        Main.ListNode<String> cur = listHead;

        while (cur != null) {
            if (cur.getDate().equals(taskInfo.getDateStr())) {
                return cur;
            }
            cur = cur.getNext();
        }

        return cur;
    }

    /**
     * Method to find a correct position of a TO-DO in the list of lists
     * according to ascending order of dates
     * @param node is a given node which we find position of
     * @return node that should be in the list before a given node
     */
    Main.ListNode<String> findPosition(Main.ListNode<String> node) {

        Main.ListNode<String> cur = listHead;
        Main.ListNode<String> prev = null;

        while (cur != null && cur.isSmaller(node)) {
            prev = cur;
            cur = cur.getNext();
        }
        return prev;
    }

    /**
     * Method to insert a TO-DO list into the list of lists
     * @param node represents a TO-DO list to be added into the list of lists
     */
    void insertNode(Main.ListNode<String> node) {

        Main.ListNode<String> pos = findPosition(node);
        if (pos == null) {
            // insert in the beginning
            node.setNext(listHead);
            listHead = node;
        } else {
            node.setNext(pos.getNext());
            pos.setNext(node);
        }
    }

    /**
     * Method to add task in a list of lists
     * @param taskInfo is information about a task to be added
     */
    void addTask(Main.TaskInfo taskInfo) {

        Main.ListNode<String> newNode = findDate(taskInfo);

        if (newNode == null) {
            // create ListNode with task; insert newNode into the list
            newNode = new Main.ListNode<>(taskInfo);
            newNode.getToDo().insert(taskInfo.getPriority(), taskInfo.getTask());
            // insert to the right position in listOfLists (ascending order of dates)
            insertNode(newNode);
            size += 1;
        } else {
            newNode.getToDo().insert(taskInfo.getPriority(), taskInfo.getTask());
        }
    }

    /**
     * Method to delete task from a certain TO-DO list
     * @param taskInfo is information about a task to be deleted
     */
    void removeTask(Main.TaskInfo taskInfo) {

        Main.ListNode<String> cur = findDate(taskInfo);
        if (cur != null) {
            cur.getToDo().removeMax();
        }
    }

    /**
     * Method to print TO-DO lists in the list of lists
     * For testing and debugging only
     */
    void printList() {

        Main.ListNode<String> cur = listHead;

        while (cur != null) {
            System.out.println("cur date is " + cur.getDate());
            System.out.println("cur heap is");
            cur.getToDo().printHeap();
            System.out.println("the end of node");
            cur = cur.getNext();
        }
    }

}