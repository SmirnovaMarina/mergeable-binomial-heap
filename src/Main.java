/**
 * @author Smirnova Marina
 * Codeforces submission number:  52891387
 *
 * Tests for program:
 * 1.
 * 6
 * 10.04.19 DSA_Read_Leacture 15
 * 10.04.19 DSA_Read_Tutorial 10
 * 10.04.19 DSA_Assigment 25
 * 12.04.19 DSA_Visist_Lab 20
 * 12.04.19 DSA_Do_Lab_Task 10
 * DO 12.04.19
 *
 * 2.
 * 6
 * 11.04.19 Motivation_Letter 15
 * 12.04.19 Read_article 10
 * 12.04.19 Presentation 25
 * 12.04.19 Solution_Analyses 25
 * DO 12.04.19
 * DO 11.04.19
 *
 */

import java.util.Scanner;

/**
 *  Class to execute the whole program
 */
public class Main {

    static ListOfLists<String> listOfLists; // list of TO-DO lists
    static BinomialHeap<String> commonHeap; // heap for merging all tasks

    /**
     * Method to read input: tasks and commands
     * @param listOfLists is a list that will hold TO-DO lists
     * @param <E> generic parameter; will be String, because values of a TO-DO list is of type String
     */
    public static <E> void read(ListOfLists<E> listOfLists){

       Scanner scanner = new Scanner(System.in);
       String line;
       String strArray[];
       int i = 0;
       int n = scanner.nextInt();
       String date;
       scanner.nextLine();

       while (i<n){

           line = scanner.nextLine();
           strArray = line.split(" ");

           // there are 3 components in a line -> create a new task
           if (strArray.length == 3){
                TaskInfo task = new TaskInfo(strArray[0], strArray[1], strArray[2]);
                listOfLists.addTask(task);
           }
           // otherwise there are 2 components -> delete task
           else{
               TaskInfo task = new TaskInfo(strArray[1]);
               listOfLists.removeTask(task);
           }

           i++;
       }
    }

    /**
     * Method to print all TO-DO lists one by one
     * @param listOfLists is a list that will hold TO-DO lists
     * @param commonHeap is a binomial heap that contains all tasks of all TO-DO lists
     */
    public static void printToDoLists(ListOfLists<String> listOfLists, BinomialHeap<String> commonHeap){

        ListNode<String> cur = listOfLists.getListHead();

        while(cur != null){

            System.out.println("TODOList "+ cur.getDate());
            cur.printToDo(commonHeap);
            cur = cur.getNext();
        }
    }

    /**
     * Method to print a commonHeap which contains all tasks of all TO-DO lists
     */
    public static void printMergedList(){

        System.out.println("TODOList ");

        HeapNode<String> cur;
        while(!commonHeap.isEmpty()){
            cur = commonHeap.removeMax();
            System.out.println("\t"+cur.getValue());
        }

    }

    public static void main(String args[]){
        listOfLists = new ListOfLists<>();
        commonHeap = new BinomialHeap<>();
        read(listOfLists);
        printToDoLists(listOfLists, commonHeap);
        printMergedList();
    }
}
