package mock.brains.basecomponents.core.di.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope annotation for different lifecycles.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface Lifecycle {
    String value() default "";
}
