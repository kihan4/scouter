// Generated by delombok at Sun Feb 26 12:31:38 KST 2017
package scouter.bytebuddy.matcher;

/**
 * An element matcher that matches the {@code null} value.
 *
 * @param <T> The type of the matched entity.
 */
public class NullMatcher<T> extends ElementMatcher.Junction.AbstractBase<T> {
    @Override
    public boolean matches(T target) {
        return target == null;
    }

    @Override
    public String toString() {
        return "isNull()";
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof NullMatcher)) return false;
        final NullMatcher<?> other = (NullMatcher<?>) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof NullMatcher;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public int hashCode() {
        int result = 1;
        return result;
    }
}
