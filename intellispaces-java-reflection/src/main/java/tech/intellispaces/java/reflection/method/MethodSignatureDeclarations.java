package tech.intellispaces.java.reflection.method;

public interface MethodSignatureDeclarations {

  static MethodSignatureDeclarationBuilderBasedOnMethodPrototype build(MethodStatement method) {
    return new MethodSignatureDeclarationBuilderBasedOnMethodPrototype(method);
  }
}
