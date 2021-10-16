package CommonMethods.equals;

import java.util.Objects;

/**
 * equals의 원칙 중 대칭성을 위배하는 case
 *
 * 대칭성 : 두 객체는 서로에 대한 동치 여부에 똑같이 답해야 한다.
 */
public class CaseInsensitiveString {
    private final String s;

    public String getS() {
        return s;
    }

    public CaseInsensitiveString(String s) {
        this.s = Objects.requireNonNull(s);
    }

    public boolean equalsWrong(Object o) {
        if (o instanceof CaseInsensitiveString)
            return this.s.equalsIgnoreCase(((CaseInsensitiveString) o).getS());
        if (o instanceof String) // 한 방향으로만 작동한다!
            // 문제는 CaseInsensitiveString의 equals는 일반 String을 알고 있지만
            // String의 equals는 CaseInsensitiveString의 존재를 모른다는데 있다. --> 대칭성을 명백히 위반!
            return s.equalsIgnoreCase((String) o);
        return false;
    }

    // SOLUTION
    // String과 equals 비교하겠다는 허황된 꿈을 버리도록 하자.
    public boolean equalsRight(Object o) {
        return o instanceof CaseInsensitiveString && ((CaseInsensitiveString) o).getS().equalsIgnoreCase(this.s);
    }

}
