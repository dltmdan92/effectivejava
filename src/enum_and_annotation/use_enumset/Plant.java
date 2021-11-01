package enum_and_annotation.use_enumset;

public class Plant {
    enum LifeCycle {
        ANNUAL, PERENNIAL, BIENNIAL
    }

    private final String name;
    final LifeCycle lifeCycle;

    public Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public LifeCycle getLifeCycle() {
        return lifeCycle;
    }
}
