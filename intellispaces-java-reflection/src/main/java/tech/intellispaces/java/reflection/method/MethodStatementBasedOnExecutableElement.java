package tech.intellispaces.java.reflection.method;

import tech.intellispaces.java.reflection.JavaStatements;
import tech.intellispaces.java.reflection.StatementType;
import tech.intellispaces.java.reflection.StatementTypes;
import tech.intellispaces.java.reflection.context.TypeContext;
import tech.intellispaces.java.reflection.context.TypeContexts;
import tech.intellispaces.java.reflection.customtype.CustomType;
import tech.intellispaces.java.reflection.reference.NotPrimitiveReference;
import tech.intellispaces.java.reflection.session.Session;
import tech.intellispaces.action.Actions;
import tech.intellispaces.action.cache.CachedSupplierActions;
import tech.intellispaces.action.supplier.SupplierAction;
import tech.intellispaces.general.exception.NotImplementedExceptions;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Map;

/**
 * Adapter of {@link ExecutableElement} to {@link MethodStatement}.
 */
class MethodStatementBasedOnExecutableElement implements MethodStatement {
  private final SupplierAction<CustomType> ownerGetter;
  private final SupplierAction<MethodSignature> signatureGetter;
  private final SupplierAction<List<MethodStatement>> overrideMethodsGetter;

  MethodStatementBasedOnExecutableElement(ExecutableElement executableElement, Session session) {
    this(
        executableElement,
        CachedSupplierActions.get(
            JavaStatements::customTypeStatement, (TypeElement) executableElement.getEnclosingElement()),
        TypeContexts.empty(),
        session
    );
  }

  MethodStatementBasedOnExecutableElement(
      ExecutableElement executableElement, CustomType owner, TypeContext typeContext, Session session
  ) {
    this(executableElement, Actions.supplierAction(owner), typeContext, session);
  }

  MethodStatementBasedOnExecutableElement(
      ExecutableElement executableElement, SupplierAction<CustomType> ownerGetter, TypeContext typeContext, Session session
  ) {
    this.ownerGetter = ownerGetter;
    this.signatureGetter = CachedSupplierActions.get(
        MethodSignatures::of, executableElement, typeContext, session);
    this.overrideMethodsGetter = CachedSupplierActions.get(MethodFunctions::getOverrideMethods, this);
  }

  @Override
  public StatementType statementType() {
    return StatementTypes.Method;
  }

  @Override
  public CustomType owner() {
    return ownerGetter.get();
  }

  @Override
  public MethodSignature signature() {
    return signatureGetter.get();
  }

  @Override
  public List<MethodStatement> overrideMethods() {
    return overrideMethodsGetter.get();
  }

  @Override
  public MethodStatement effective(Map<String, NotPrimitiveReference> typeMapping) {
    return new MethodStatementImpl(owner(), signature().effective(typeMapping));
  }

  @Override
  public String prettyDeclaration() {
    throw NotImplementedExceptions.withCode("QLOEfw");
  }
}