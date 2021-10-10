package ObjectCreationDescruction.builder;

/**
 * 점층적 생성자 패턴,
 * 생성자가 과도하게 많아짐에 따라 단점이 많이 발생하게 된다.
 */
public class GradualContructorPattern {

    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public GradualContructorPattern(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public GradualContructorPattern(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public GradualContructorPattern(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public GradualContructorPattern(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public GradualContructorPattern(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }
}
