package tech.intellispaces.java.reflection.type;

import tech.intellispaces.java.reflection.reference.TypeReference;
import tech.intellispaces.general.exception.NotImplementedExceptions;
import tech.intellispaces.general.exception.UnexpectedExceptions;
import tech.intellispaces.general.type.AbstractClassType;

import java.util.List;

class ReferenceBasedDerivedType<T> extends AbstractClassType<T> implements DerivedType<T> {
  private final TypeReference base;
  private final List<TypeReference> qualifiers;

  ReferenceBasedDerivedType(TypeReference base, List<TypeReference> qualifiers) {
    this.base = base;
    this.qualifiers = qualifiers;
  }

  @Override
  public TypeReference typeReference() {
    if (qualifiers.isEmpty()) {
      return base;
    }
    throw NotImplementedExceptions.withCode("BrAAig");
  }

  @Override
  public TypeReference baseTypeReference() {
    return base;
  }

  @Override
  public List<TypeReference> qualifierTypeReferences() {
    return qualifiers;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Class<T> baseClass() {
    if (base.isCustomTypeReference()) {
      return (Class<T>) base.asCustomTypeReferenceOrElseThrow().targetClass();
    } else {
      throw UnexpectedExceptions.withMessage("Unsupported reference type: {0}",
        base.statementType().typename());
    }
  }

  @Override
  @SuppressWarnings("unchecked, rawtypes")
  public List<tech.intellispaces.general.type.Type<?>> qualifierTypes() {
    return (List) qualifiers.stream()
      .map(DerivedTypeBaseOnReferenceImpl::new)
      .toList();
  }
}
