package ObjectCreationDescruction.dependencyInjection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 싱글턴을 잘못 사용한 예 여러 사전을 쓸 수 없다.
// 유연 X
public class WrongSingleton {
    private final Map<String, String> dictionary = new HashMap<>();

    private WrongSingleton() {}

    public static WrongSingleton INSTANCE = new WrongSingleton();
    public static List<String> suggestions(String type) {
        return null;
    }
}
