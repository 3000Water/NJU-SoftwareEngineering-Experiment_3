package weather.data.database;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataMap<K, V> implements Map<K, V> {

  private Map<K, V> map;

  public DataMap() {
    map = new HashMap<>();
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public boolean isEmpty() {
    return map.isEmpty();
  }

  @Override
  public boolean containsKey(Object key) {
    return map.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value) {
    return map.containsValue(value);
  }

  @Override
  public V get(Object key) {
    for (K k : map.keySet()) {
      if (k.equals(key)) {
        return map.get(k);
      }
    }
    return null;
  }

  @Override
  public V put(K key, V value) {
    return map.put((K) key, (V) value);
  }

  @Override
  public V remove(Object key) {
    return map.remove(key);
  }

  @Override
  public void putAll(Map<? extends K, ? extends V> m) {
    map.putAll(m);
  }

  @Override
  public void clear() {
    map.clear();
  }

  @Override
  public Set<K> keySet() {
    return map.keySet();
  }

  @Override
  public Collection<V> values() {
    return map.values();
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    return map.entrySet();
  }
}
