package intellispaces.javastatements.samples;

import intellispaces.javastatements.support.TesteeType;

@TesteeType
public @interface AnnotationWithByteDefaultElement {

  byte byteElementDefault() default 1;
}