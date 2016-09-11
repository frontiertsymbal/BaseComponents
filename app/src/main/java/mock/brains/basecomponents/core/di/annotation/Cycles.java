package mock.brains.basecomponents.core.di.annotation;

/**
 * Constants variables for modules and providers.
 */
public final class Cycles {

    /**
     * Lifecycle scope annotation constants.
     */
    public static final String ACTIVITY = "activity";
    public static final String SERVICE = "service";
    public static final String FRAGMENT = "fragment";
    public static final String VIEW = "view";

    private Cycles() {
        throw new AssertionError("Unable to instantiate");
    }
}
