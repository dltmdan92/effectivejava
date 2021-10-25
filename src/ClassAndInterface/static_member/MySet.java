package ClassAndInterface.static_member;

import java.util.AbstractSet;
import java.util.Iterator;

public class MySet<E> extends AbstractSet<E> {
    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>();
    }

    @Override
    public int size() {
        return 0;
    }

    /**
     * 비정적 멤버 클래스
     * - 주로 어댑터를 정의할 때 자주 쓰인다.
     * 즉, 어떤 클래스의 인스턴스를 감싸 마치 다른 클래스의 인스턴스 처럼 보이게 하는 뷰로 사용
     *
     * 멤버 클래스에서 바깥 인스턴스에 접근할 일이 없다면 무조건 static을 붙여서 정적 멤버 클래스로 만들자.
     */
    private class MyIterator<E> implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }

    private static class StaticIterator<E> implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }
}
