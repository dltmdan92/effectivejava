package ObjectCreationDescruction.clearReference.memoryLeak;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 메모리 누수가 일어나는 위치는 어디인가??
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

    // 메모리 누수 발생하는 케이스
    public Object popWithMemoryLeak() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        // 스택에서 꺼내진 객체들을 GC가 회수하지 않는다.
        // 스택이 다 쓴 참조를 여전히 가지고 있기 때문임. (활성 영역 밖의 참조들)
        // 특히 Stack 클래스 같은 경우, 위처럼 elements 배열로 저장소 pool을 만들어 원소들을 관리한다.
        // 이런식으로 자기 메모리를 직접 관리하는데 이런 경우가 메모리 누수 발생 가능성이 높다.
        return elements[--size];
    }

    // 메모리 누수 방지 로직 적용
    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[size] = null; // 다 쓴 참조 해제
        // 비활성 영역에 있는 이렇게 쓸모없는 요소에 대해서 참조를 해제시켜서 GC에 알려야 한다. 해제 대상 여부를 프로그래머는 알아도 GC는 알 길이 없기 때문이다.
        // 다 쓴 참조를 해제 함으로 써, 만약 null 처리한 참조를 실수로 사용하려 하면 프로그램은 즉시 NullPointerException을 던져줄 수 있다.
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
