// Generated by delombok at Sun Feb 26 12:31:38 KST 2017
package scouter.bytebuddy.dynamic.scaffold;

import scouter.bytebuddy.description.method.MethodDescription;
import scouter.bytebuddy.description.type.TypeDescription;
import scouter.bytebuddy.implementation.Implementation;
import scouter.bytebuddy.implementation.attribute.AnnotationValueFilter;
import scouter.bytebuddy.implementation.bytecode.ByteCodeAppender;
import scouter.bytebuddy.jar.asm.ClassVisitor;
import scouter.bytebuddy.jar.asm.MethodVisitor;

/**
 * A type initializer is responsible for defining a type's static initialization block.
 */
public interface TypeInitializer extends ByteCodeAppender {
    /**
     * Indicates if this type initializer is defined.
     *
     * @return {@code true} if this type initializer is defined.
     */
    boolean isDefined();

    /**
     * Expands this type initializer with another byte code appender. For this to be possible, this type initializer must
     * be defined.
     *
     * @param byteCodeAppender The byte code appender to apply as the type initializer.
     * @return A defined type initializer.
     */
    TypeInitializer expandWith(ByteCodeAppender byteCodeAppender);

    /**
     * Creates a method pool record that applies this type initializer while preserving the record that was supplied.
     *
     * @param record The record to wrap.
     * @return A new record that represents the supplied record while also executing this type initializer.
     */
    TypeWriter.MethodPool.Record wrap(TypeWriter.MethodPool.Record record);


    /**
     * A drain for writing a type initializer.
     */
    interface Drain {
        /**
         * Applies the drain.
         *
         * @param classVisitor          The class visitor to apply the initializer to.
         * @param typeInitializer       The type initializer to write.
         * @param implementationContext The corresponding implementation context.
         */
        void apply(ClassVisitor classVisitor, TypeInitializer typeInitializer, Implementation.Context implementationContext);


        /**
         * A default implementation of a type initializer drain that creates a initializer method.
         */
        class Default implements Drain {
            /**
             * The instrumented type.
             */
            protected final TypeDescription instrumentedType;
            /**
             * The method pool to use.
             */
            protected final TypeWriter.MethodPool methodPool;
            /**
             * The annotation value filter factory to use.
             */
            protected final AnnotationValueFilter.Factory annotationValueFilterFactory;

            /**
             * Creates a new default type initializer drain.
             *
             * @param instrumentedType             The instrumented type.
             * @param methodPool                   The method pool to use.
             * @param annotationValueFilterFactory The annotation value filter factory to use.
             */
            public Default(TypeDescription instrumentedType, TypeWriter.MethodPool methodPool, AnnotationValueFilter.Factory annotationValueFilterFactory) {
                this.instrumentedType = instrumentedType;
                this.methodPool = methodPool;
                this.annotationValueFilterFactory = annotationValueFilterFactory;
            }

            @Override
            public void apply(ClassVisitor classVisitor, TypeInitializer typeInitializer, Implementation.Context implementationContext) {
                typeInitializer.wrap(methodPool.target(new MethodDescription.Latent.TypeInitializer(instrumentedType))).apply(classVisitor, implementationContext, annotationValueFilterFactory);
            }

            @java.lang.Override
            @java.lang.SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean equals(final java.lang.Object o) {
                if (o == this) return true;
                if (!(o instanceof TypeInitializer.Drain.Default)) return false;
                final TypeInitializer.Drain.Default other = (TypeInitializer.Drain.Default) o;
                if (!other.canEqual((java.lang.Object) this)) return false;
                final java.lang.Object this$instrumentedType = this.instrumentedType;
                final java.lang.Object other$instrumentedType = other.instrumentedType;
                if (this$instrumentedType == null ? other$instrumentedType != null : !this$instrumentedType.equals(other$instrumentedType)) return false;
                final java.lang.Object this$methodPool = this.methodPool;
                final java.lang.Object other$methodPool = other.methodPool;
                if (this$methodPool == null ? other$methodPool != null : !this$methodPool.equals(other$methodPool)) return false;
                final java.lang.Object this$annotationValueFilterFactory = this.annotationValueFilterFactory;
                final java.lang.Object other$annotationValueFilterFactory = other.annotationValueFilterFactory;
                if (this$annotationValueFilterFactory == null ? other$annotationValueFilterFactory != null : !this$annotationValueFilterFactory.equals(other$annotationValueFilterFactory)) return false;
                return true;
            }

            @java.lang.SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            protected boolean canEqual(final java.lang.Object other) {
                return other instanceof TypeInitializer.Drain.Default;
            }

            @java.lang.Override
            @java.lang.SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public int hashCode() {
                final int PRIME = 59;
                int result = 1;
                final java.lang.Object $instrumentedType = this.instrumentedType;
                result = result * PRIME + ($instrumentedType == null ? 43 : $instrumentedType.hashCode());
                final java.lang.Object $methodPool = this.methodPool;
                result = result * PRIME + ($methodPool == null ? 43 : $methodPool.hashCode());
                final java.lang.Object $annotationValueFilterFactory = this.annotationValueFilterFactory;
                result = result * PRIME + ($annotationValueFilterFactory == null ? 43 : $annotationValueFilterFactory.hashCode());
                return result;
            }
        }
    }


    /**
     * Canonical implementation of a non-defined type initializer.
     */
    enum None implements TypeInitializer {
        /**
         * The singleton instance.
         */
        INSTANCE;

        @Override
        public boolean isDefined() {
            return false;
        }

        @Override
        public TypeInitializer expandWith(ByteCodeAppender byteCodeAppenderFactory) {
            return new TypeInitializer.Simple(byteCodeAppenderFactory);
        }

        @Override
        public TypeWriter.MethodPool.Record wrap(TypeWriter.MethodPool.Record record) {
            return record;
        }

        @Override
        public Size apply(MethodVisitor methodVisitor, Implementation.Context implementationContext, MethodDescription instrumentedMethod) {
            return new Size(0, 0);
        }
    }


    /**
     * A simple, defined type initializer that executes a given {@link ByteCodeAppender}.
     */
    class Simple implements TypeInitializer {
        /**
         * The byte code appender to apply as the type initializer.
         */
        private final ByteCodeAppender byteCodeAppender;

        /**
         * Creates a new simple type initializer.
         *
         * @param byteCodeAppender The byte code appender to apply as the type initializer.
         */
        public Simple(ByteCodeAppender byteCodeAppender) {
            this.byteCodeAppender = byteCodeAppender;
        }

        @Override
        public boolean isDefined() {
            return true;
        }

        @Override
        public TypeInitializer expandWith(ByteCodeAppender byteCodeAppender) {
            return new TypeInitializer.Simple(new Compound(this.byteCodeAppender, byteCodeAppender));
        }

        @Override
        public TypeWriter.MethodPool.Record wrap(TypeWriter.MethodPool.Record record) {
            return record.prepend(byteCodeAppender);
        }

        @Override
        public Size apply(MethodVisitor methodVisitor, Implementation.Context implementationContext, MethodDescription instrumentedMethod) {
            return byteCodeAppender.apply(methodVisitor, implementationContext, instrumentedMethod);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public boolean equals(final java.lang.Object o) {
            if (o == this) return true;
            if (!(o instanceof TypeInitializer.Simple)) return false;
            final TypeInitializer.Simple other = (TypeInitializer.Simple) o;
            if (!other.canEqual((java.lang.Object) this)) return false;
            final java.lang.Object this$byteCodeAppender = this.byteCodeAppender;
            final java.lang.Object other$byteCodeAppender = other.byteCodeAppender;
            if (this$byteCodeAppender == null ? other$byteCodeAppender != null : !this$byteCodeAppender.equals(other$byteCodeAppender)) return false;
            return true;
        }

        @java.lang.SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        protected boolean canEqual(final java.lang.Object other) {
            return other instanceof TypeInitializer.Simple;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final java.lang.Object $byteCodeAppender = this.byteCodeAppender;
            result = result * PRIME + ($byteCodeAppender == null ? 43 : $byteCodeAppender.hashCode());
            return result;
        }
    }
}
