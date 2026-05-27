import org.jspecify.annotations.NullMarked;

@NullMarked
module com.nicogabriel.ariee.core {
    requires static transitive org.jspecify;

    exports com.nicogabriel.ariee.core;
    exports com.nicogabriel.ariee.core.exception;
}
