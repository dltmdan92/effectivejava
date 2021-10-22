package ClassAndInterface.useInterface;

import java.util.Map;
import java.util.Objects;

/**
 * 골격 구현 클래스 (SkeletonMapEntry 라고 네이밍해도 좋을듯)
 * 골격 구현 클래스는 기본적으로 상속해서 갖다써야 한다.
 *
 * interface와 abstract class 이렇게 두 개의 장점을 모두 활용하는 케이스
 *
 * @param <K>
 * @param <V>
 */
public abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {

    // 변경 가능한 엔트리
    // 이 메서드는 반드시 재정의해야 한다.
    @Override
    public K getKey() {
        throw new UnsupportedOperationException();
    }

    // 변경 가능한 엔트리
    // 이 메서드는 반드시 재정의해야 한다.
    @Override
    public V getValue() {
        throw new UnsupportedOperationException();
    }

    // 변경 가능한 엔트리
    // 이 메서드는 반드시 재정의해야 한다.
    @Override
    public V setValue(V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Map.Entry))
            return false;
        Map.Entry<?, ?> e = (Map.Entry) obj;
        return Objects.equals(e.getKey(), getKey()) && Objects.equals(e.getValue(), getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
