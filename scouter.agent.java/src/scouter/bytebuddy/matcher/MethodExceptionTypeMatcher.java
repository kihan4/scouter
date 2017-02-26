// Generated by delombok at Sun Feb 26 12:31:38 KST 2017
package scouter.bytebuddy.matcher;

import scouter.bytebuddy.description.method.MethodDescription;
import scouter.bytebuddy.description.type.TypeList;

/**
 * An element matcher that matches the exceptions that are declared by a method.
 *
 * @param <T> The type of the matched entity.
 */
public class MethodExceptionTypeMatcher<T extends MethodDescription> extends ElementMatcher.Junction.AbstractBase<T> {
    /**
     * The matcher to apply to the matched method's exceptions.
     */
    private final ElementMatcher<? super TypeList.Generic> matcher;

    /**
     * Creates a new matcher for a method's exceptions.
     *
     * @param matcher The matcher to apply to the matched method's exceptions.
     */
    public MethodExceptionTypeMatcher(ElementMatcher<? super TypeList.Generic> matcher) {
        this.matcher = matcher;
    }

    @Override
    public boolean matches(T target) {
        return matcher.matches(target.getExceptionTypes());
    }

    @Override
    public String toString() {
        return "exceptions(" + matcher + ")";
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof MethodExceptionTypeMatcher)) return false;
        final MethodExceptionTypeMatcher<?> other = (MethodExceptionTypeMatcher<?>) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$matcher = this.matcher;
        final java.lang.Object other$matcher = other.matcher;
        if (this$matcher == null ? other$matcher != null : !this$matcher.equals(other$matcher)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof MethodExceptionTypeMatcher;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $matcher = this.matcher;
        result = result * PRIME + ($matcher == null ? 43 : $matcher.hashCode());
        return result;
    }
}
