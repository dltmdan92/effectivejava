package ObjectCreationDescruction.builder;

/**
 * 자바빈즈 패턴
 * setter로 객체를 구성할 수 있다.
 *
 * 점층적 생성자 패턴의 단점을 극복하였다.
 * 다만, 객체 하나를 만들려면 메서드를 여러개 호출해야 하고, 객체가 완전히 생성되기 전까지는 일관성이 무너진 상태에 놓이게 된다.
 * 또한 클래스를 Immutable하게 만들 수 없다. (쓰레드 안전성을 얻으려면 추가 작업도 필요하다)
 */
public class JavaBeansPattern {

    private int servingSize = -1;
    private int servings = -1;
    private int calories = 0;
    private int fat = 0;
    private int sodium = 0;
    private int carbohydrate = 0;

    public JavaBeansPattern() {
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }
}
