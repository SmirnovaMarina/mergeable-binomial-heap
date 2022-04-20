/**
 * Class for returning a key-value pair of a heap node
 * @param <E> generic parameter
 */
public class Pair<E> {

    private int key;
    private E value;

    Pair(){
        key = 0;
        value = null;
    }

    Pair(int key, E value){
        this.key = key;
        this.value = value;
    }

    int getKey(){
        return key;
    }

    E getValue(){
        return value;
    }
}
