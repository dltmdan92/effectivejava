package ObjectCreationDescruction.builder;

/**
 * 빌더 패턴
 * 이 클래스는 Immutable하다.
 * Builder의 setter 메서드들은 빌더 자신을 반환하기 때문에 연쇄적으로 호출할 수 있다.
 * 유연성이 좋다. (가변인자)
 * 매개변수 중 다수가 필수가 아니거나 동일 type이면 builder 패턴의 효과가 매우 좋다.
 * 점층적 생성자 보다 코드가 간결하고, 자바빈즈보다 훨씬 안전하다.
 *
 * BUT 성능이 약간 떨어진다.
 */
public class BuilderPattern {

    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // 필수 매개 변수
        private final int servingSize;
        private final int servings;

        // 선택 매개 변수 - 기본값으로 초기화 한다.
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder fat(int val) {
            fat = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public BuilderPattern build() {
            return new BuilderPattern(this);
        }
    }

    public BuilderPattern(Builder builder) {
        this.servingSize = builder.servingSize;
        this.servings = builder.servings;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.sodium = builder.sodium;
        this.carbohydrate = builder.carbohydrate;
    }
}
