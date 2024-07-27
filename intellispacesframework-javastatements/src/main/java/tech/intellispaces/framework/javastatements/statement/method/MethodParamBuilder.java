package tech.intellispaces.framework.javastatements.statement.method;

import tech.intellispaces.framework.javastatements.statement.instance.AnnotationInstance;
import tech.intellispaces.framework.javastatements.statement.type.Type;

import java.util.List;
import java.util.Objects;

public final class MethodParamBuilder {
  private String name;
  private Type type;
  private List<AnnotationInstance> annotations = List.of();

  MethodParamBuilder() {}

  public MethodParamBuilder name(String name) {
    this.name = name;
    return this;
  }

  public MethodParamBuilder type(Type type) {
    this.type = type;
    return this;
  }

  public MethodParamBuilder annotations(List<AnnotationInstance> annotations) {
    this.annotations = annotations;
    return this;
  }

  public MethodParam get() {
    validate();
    return new MethodParamImpl(name, type, annotations);
  }

  private void validate() {
    Objects.requireNonNull(name);
    Objects.requireNonNull(type);
  }
}
