package ObjectCreationDescruction.builder.pizza;

public class Calzone extends Pizza {
    private final boolean sauceInside;

    public static class Builder extends Pizza.Builder<Builder> {
        private boolean sauceInside = false; // 기본 값

        public Builder savceInside() {
            sauceInside = true;
            return this;
        }

        @Override
        public Calzone build() {
            return null;
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private Calzone(Builder builder) {
        super(builder);
        sauceInside = builder.sauceInside;
    }
}
