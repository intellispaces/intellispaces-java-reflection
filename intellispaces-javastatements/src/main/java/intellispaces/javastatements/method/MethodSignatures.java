package intellispaces.javastatements.method;

import intellispaces.javastatements.context.TypeContext;
import intellispaces.javastatements.session.Session;

import javax.lang.model.element.ExecutableElement;
import java.lang.reflect.Method;

public interface MethodSignatures {

  static MethodSignature get(Method method) {
    return new MethodSignatureBasedOnLangMethod(method);
  }

  static MethodSignature of(
      ExecutableElement executableElement, TypeContext typeContext, Session session
  ) {
    return MethodFunctions.getMethodSignature(executableElement, typeContext, session);
  }

  static MethodSignatureBuilder build() {
    return new MethodSignatureBuilder();
  }
}