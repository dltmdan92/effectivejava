package enum_and_annotation.use_enumset;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public enum Phase {
    SOLID, LIQUID, GAS, PLASMA;

    public enum Transition1 {
        MELT, FREEZE, BOIL, CONDENSE, SUBLIME, DEPOSIT;

        // 행은 from의 ordinal을, 열은 to의 ordinal을 인덱스로 쓴다.
        private static final Transition1[][] TRANSITIONS1 = {
                {null, MELT, SUBLIME},
                {FREEZE, null, BOIL},
                {DEPOSIT, CONDENSE, null}
        };

        // 한 상태에서 다른 상태로의 전이를 반환한다.
        public static Transition1 from(Phase from, Phase to) {
            // 컴파일러는 ordinal과 배열 인덱스의 관계를 알 도리가 없다.
            // Phase, Transition 을 수정하면서, 표 TRANSITIONS를 수정하지 않거나, 수정할 때 실수하면 런타임 오류가 난다.
            // 이 방식은 절대 사용하지 말고, EnumMap을 써라.
            return TRANSITIONS1[from.ordinal()][to.ordinal()];
        }
    }


    public enum Transition2 {
        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID), BOIL(LIQUID, GAS),
        CONDENSE(GAS, LIQUID), SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID),
        IONIZE(GAS, PLASMA), DEIONIZE(PLASMA, GAS); // 배열 사용하는 로직에 비해 type 추가가 매우 자유롭다.

        private final Phase from;
        private final Phase to;

        Transition2(Phase from, Phase to) {
            this.from = from;
            this.to = to;
        }

        // 상전이 맵을 초기화 한다.
        // Collector (수집기)를 두 번 사용했다. 1. groupingBy, 2. toMap
        private static final Map<Phase, Map<Phase, Transition2>> m =
                Stream.of(values()).collect(groupingBy(t -> t.from,
                        () -> new EnumMap<>(Phase.class),
                        toMap(t -> t.to, t -> t,
                                (x, y) -> y, () -> new EnumMap<>(Phase.class))));

        public static Transition2 from(Phase from, Phase to) {
            return m.get(from).get(to);
        }
    }
}
