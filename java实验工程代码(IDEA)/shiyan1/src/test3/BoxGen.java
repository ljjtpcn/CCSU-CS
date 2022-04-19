package test3;

public class BoxGen<K, V> {
    private K key;
    private V value;

    public BoxGen() {
    }

    public BoxGen(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BoxGen{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

}
class son<K, V> extends BoxGen<K, V>{

}
