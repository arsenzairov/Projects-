package SampleWork;

import java.util.Set;

/**
 * Created by Arsen on 10/26/17.
 */
public class MyHashMap<String,Integer> implements Map61B<String,Integer> {

    Entry[] entry;
    int size;
    int count;

    public MyHashMap(){
        this(5);
    }
    public MyHashMap(int initialSize){
        entry = new Entry[initialSize];
        size = initialSize;

    }
    public MyHashMap(int initialSize, float loadFactor){

    }

    private static class Entry {

        Object key;
        Object value;
        Entry next;

        public Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Entry get(Object key) {
            Entry cur = this;
            while (cur != null) {
                if (cur.key.equals(key)) {
                    return cur;
                }
                cur = cur.next;
            }
            return null;
        }
    }


    @Override
    public void clear() {
       entry = null;
    }

    @Override
    public boolean containsKey(String key) {
        return get(key) != null;
    }

    @Override
    public Integer get(String key) {
        int a = hashCode(key);
        Entry temp = entry[a];
        if (temp==null) {return null;}
        while(!temp.key.equals(key) && temp != null && temp.next != null){
            temp = temp.next;
        }
        if (temp.key.equals(key)) { return (Integer) temp.value;}
        return null;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public void put(String key, Integer value) {

        int i = hashCode(key);
        if (entry[i] == null) {
            entry[i] = new Entry(key,value,entry[i]);
            count++;

        }
        else if(containsKey(key)){
            Entry temp = entry[i].get(key);
            temp.value = value;
        }
        else {
            entry[i] = new Entry(key, value, entry[i]);
            count++;
        }
    }


    public int hashCode(String key) {
        return (key.hashCode() & 0x7fffffff) % size;
    }

    @Override
    public Integer remove(String key) {
        return null;
    }

    @Override
    public Integer remove(String key, Integer value) {
        return null;
    }

    @Override
    public Set<String> keySet() {
        return null;
    }
}
