package tech.intellispaces.java.reflection.samples;

import tech.intellispaces.java.reflection.samples.TestEnum;
import tech.intellispaces.java.reflection.support.TesteeType;

@TesteeType
public @interface AnnotationWithArrayOfEnumElement {

  TestEnum[] arrayOfEnumElement();
}