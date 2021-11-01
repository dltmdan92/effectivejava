package enum_and_annotation.use_enumset;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class PlantMain {
    public static void main(String[] args) {
        // 배열은 제네릭과 호환되지 않으니, 비검사 형변환을 수행한다. 그리고 깔끔히 컴파일 되지 않는다.
        Set<Plant>[] plantsByLifeCycle1 = (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];

        // 배열 및 정수형 인덱스를 사용하는 경우 ArrayIndexOutOfException 발생할 우려도 있음
        for (int i = 0; i < plantsByLifeCycle1.length; i++) {
            plantsByLifeCycle1[i] = new HashSet<>();
        }

        List<Plant> garden = List.of(new Plant("hello", Plant.LifeCycle.ANNUAL),
                new Plant("world", Plant.LifeCycle.PERENNIAL),
                new Plant("!", Plant.LifeCycle.BIENNIAL)
        );

        // ordinal()을 배열 인덱스로 사용 - 따라 하지 말것!
        for (Plant p : garden) {
            plantsByLifeCycle1[p.lifeCycle.ordinal()].add(p);
        }

        // Solution
        // 위의 방식 대신 아래 방식으로 하라.
        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle2 = new EnumMap<>(Plant.LifeCycle.class);

        for (Plant.LifeCycle lc : Plant.LifeCycle.values())
            plantsByLifeCycle2.put(lc, new HashSet<>());


        for (Plant p : garden)
            plantsByLifeCycle2.get(p.lifeCycle).add(p);

        // 위의 EnumMap : 언제나 식물의 생애주기당 하나씩의 중첩 맵을 만듦
        // 아래 Stream : 해당 생애주기에 속하는 식물이 있을 때만 만든다.
        EnumMap<Plant.LifeCycle, Set<Plant>> map = garden.stream()
                .collect(groupingBy(p -> p.lifeCycle,
                        () -> new EnumMap<>(Plant.LifeCycle.class), toSet()));

    }
}
