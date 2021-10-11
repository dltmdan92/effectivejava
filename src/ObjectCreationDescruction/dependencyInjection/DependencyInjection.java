package ObjectCreationDescruction.dependencyInjection;

import java.util.List;
import java.util.Map;
import java.util.Objects;

// 의존성 주입을 통해, 여러 사전을 사용할 수 있도록 유연성을 UP 한다.
// 의존 객체 주입은 유연성, 재사용성, 테스트 용이성을 기막히게 개선해준다.
public class DependencyInjection {
    // immutable도 보장한다. final 통해서
    private final Map<String, String> dictionary;

    public DependencyInjection(Map<String, String> dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }

    public static boolean isValid(String word) {
        return true;
    }
    public static List<String> suggestions(String type) {
        return null;
    }
}
