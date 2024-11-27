package weather.data.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DataList<T> implements List<T> {

  private List<T> list;

  public DataList() {
    list = new ArrayList<>();
  }

  @Override
  public int size() {
    return list.size();
  }

  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return list.contains(o);
  }

  @Override
  public Iterator<T> iterator() {
    return list.iterator();
  }

  @Override
  public T[] toArray() {
    return (T[]) list.toArray();
  }

  @Override
  public boolean add(T o) {
    return list.add(o);
  }

  @Override
  public boolean remove(Object o) {
    return list.remove(o);
  }

  @Override
  public boolean addAll(Collection c) {
    return list.addAll(c);
  }

  @Override
  public boolean addAll(int index, Collection c) {
    return list.addAll(index, c);
  }

  @Override
  public void clear() {
    list.clear();
  }

  @Override
  public boolean equals(Object o) {
    return list.equals(o);
  }

  @Override
  public int hashCode() {
    return list.hashCode();
  }

  @Override
  public T get(int index) {
    return list.get(index);
  }

  @Override
  public T set(int index, Object element) {
    return list.set(index, (T) element);
  }

  @Override
  public void add(int index, Object element) {
    list.add(index, (T) element);
  }

  @Override
  public T remove(int index) {
    return list.remove(index);
  }

  @Override
  public int indexOf(Object o) {
    return list.indexOf(o);
  }

  @Override
  public int lastIndexOf(Object o) {
    return list.lastIndexOf(o);
  }

  @Override
  public ListIterator<T> listIterator() {
    return list.listIterator();
  }

  @Override
  public ListIterator<T> listIterator(int index) {
    return list.listIterator(index);
  }

  @Override
  public List<T> subList(int fromIndex, int toIndex) {
    return list.subList(fromIndex, toIndex);
  }

  @Override
  public boolean retainAll(Collection c) {
    return list.retainAll(c);
  }

  @Override
  public boolean removeAll(Collection c) {
    return list.removeAll(c);
  }

  @Override
  public boolean containsAll(Collection c) {
    return new HashSet<>(list).containsAll(c);
  }

  @Override
  public T[] toArray(Object[] a) {
    return (T[]) list.toArray(a);
  }
}
