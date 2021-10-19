package CommonMethods.clone;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 이 클래스를 복제할 수 있도록 만들어보자.
 *
 * 단순히 super.clone을 호출하면
 * size 필드는 올바른 값을 갖겠지만, elements 필드는 원본 Stack 인스턴스와 똑같은 배열을 참조할 것이다.
 *
 * clone 메서드는 사실상 생성자와 같은 효과를 내야한다.
 * --> 즉 clone은 원본 객체에 아무런 해를 끼치지 않는 동시에 복제된 객체의 불변식을 보장해야 한다.
 */
public class Stack {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object popWithMemoryLeak() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return elements[--size];
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    @Override
    public Stack clone() {
        try {
            Stack result = (Stack) super.clone();
            // 만약에 elements field가 final 이었다면, 작동하지 않는다.
            // Cloneable 아키텍처는 가변 객체를 참조하는 필드는 final로 선언하라는 일반 용법과 충돌한다.
            result.elements = elements.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
