package ClassAndInterface.compoistion;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * 재사용할 수 있는 Forwarding class - 어디서든 갖다 쓸수 있다.
 * 전달 클래스를 인터페이스당 하나씩만 만들어두어도, 원하는 기능을 덧씌우는(Decorator pattern) 래퍼 클래스들을 손쉽게 만들 수 있다.
 *
 * HashSet을 상속받지 않고, 주입받는다.
 * 기존 클래스를 extend하는 대신 private field에 주입받아서 참조하도록 하자.
 * 이러한 설계를 composition 구성이라고 함.
 * extend할 때 상위클래스의 영향을 최소화한다.
 *
 * @param <E>
 */
public class ForwardingSet<E> implements Set<E> {
    private final Set<E> s;

    public ForwardingSet(Set<E> s) {
        this.s = s;
    }

    @Override
    public int size() {
        return s.size();
    }

    @Override
    public boolean isEmpty() {
        return s.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return s.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return s.iterator();
    }

    @Override
    public Object[] toArray() {
        return s.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return s.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return s.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return s.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return s.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return s.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return s.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return s.removeAll(c);
    }

    @Override
    public void clear() {

    }
}
