package ClassAndInterface.compoistion;

import java.util.Collection;
import java.util.Set;

/**
 * wrapper class
 * 다른 Set에 계측 기능을 덧씌운다고 해서 Decorator pattern이라고 한다.
 * @param <E>
 */
public class InstrumentedSet<E> extends ForwardingSet<E> {
    private int addCount = 0;

    public InstrumentedSet(Set<E> s) {
        super(s);
    }

    @Override
    public boolean add(E e) {
        addCount++; // 이 field는 본 클래스에서만 컨트롤 가능한 독립적인 변수임.
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
}
