package generator.util;

import java.util.HashMap;

/**
 * Created by Jeppe Vinberg on 07-01-2016.
 */
public class DoubleHashMap<T, E> {

    private HashMap<T, E> map1;
    private HashMap<E, T> map2;

    public DoubleHashMap(){
        map1 = new HashMap<>();
        map2 = new HashMap<>();
    }

    public void putA(T t, E e){
        map1.put(t, e);
    }

    public void putB(E e, T t){
        map2.put(e, t);
    }

    public E getA(T t){
        return map1.get(t);
    }

    public T getB(E e){
        return map2.get(e);
    }

    public HashMap<T, E> getMapA(){
        return map1;
    }

    public HashMap<E, T> getMapB(){
        return map2;
    }


}
