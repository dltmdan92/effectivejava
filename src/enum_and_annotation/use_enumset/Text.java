package enum_and_annotation.use_enumset;

import java.util.EnumSet;
import java.util.Set;

public class Text {
    // 비트 필드 열거 상수 - 구닥다리 기법!
    // 열거한 값들이 집합으로 사용될 경우, 예전에는 각 상수에 서로 다른 2의 거듭제곱 값을 할당한 정수 열거 패턴을 사용해왔다.
    public static final int STYLE_BOLD          = 1 << 0; // 1
    public static final int STYLE_ITALIC        = 1 << 1; // 2
    public static final int STYLE_UNDERLINE     = 1 << 2; // 4
    public static final int STYLE_STRIKETHROUGH = 1 << 3; // 8

    // 매개변수 styles은 0개 이상의 STYLE_ 상수를 비트별 OR한 값이다.
    public static void applyStyles(int styles) {

    }

    // 집합을 사용하는 경우, 비트 필드를 사용하지 말고 EnumSet을 사용하도록 한다.
    public enum Style {
        BOLD, ITALIC, UNDERLINE, STRIKETHROUGH
    }

    // 어떤 Set을 넘겨도 되나, 여기서는 EnumSet이 가장 좋다.
    // Set으로 받는 이유는 interface로 받는게 좋은 습관이기 때문이다.
    public static void applyStyles(Set<Style> styles) {

    }

    public static void main(String[] args) {
        applyStyles(STYLE_BOLD | STYLE_ITALIC); // 여러 상수를 이렇게 집합으로 모으는 것을 bit field 라고 한다.
        applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
    }
}
