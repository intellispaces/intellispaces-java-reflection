package intellispaces.common.javastatement.reference;

import intellispaces.common.javastatement.common.DependenciesFunctions;
import intellispaces.common.javastatement.customtype.CustomType;
import tech.intellispaces.action.cache.CachedSupplierActions;
import tech.intellispaces.action.supplier.SupplierAction;
import tech.intellispaces.entity.exception.NotImplementedExceptions;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

abstract class AbstractTypeReference implements TypeReference {
  private final SupplierAction<Collection<CustomType>> dependenciesGetter;
  private final SupplierAction<Collection<String>> dependencyTypesGetter;
  private final SupplierAction<String> actualSimpleGetter;
  private final SupplierAction<String> actualDeclarationGetter;
  private final SupplierAction<String> formalFullDeclarationGetter;
  private final SupplierAction<String> formalBriefDeclarationGetter;

  AbstractTypeReference() {
    this.dependenciesGetter = CachedSupplierActions.get(DependenciesFunctions::getTypeReferenceDependencies, this);
    this.dependencyTypesGetter = CachedSupplierActions.get(AbstractTypeReference::collectDependencyTypenames, this);
    this.actualSimpleGetter = CachedSupplierActions.get(TypeReferenceFunctions::getSimpleTypeDeclaration, this);
    this.actualDeclarationGetter = CachedSupplierActions.get(TypeReferenceFunctions::getActualTypeDeclaration, this);
    this.formalFullDeclarationGetter = CachedSupplierActions.get(TypeReferenceFunctions::getFormalFullTypeReferenceDeclaration, this);
    this.formalBriefDeclarationGetter = CachedSupplierActions.get(TypeReferenceFunctions::getFormalBriefTypeReferenceDeclaration, this);
  }

  @Override
  public Collection<CustomType> dependencies() {
    return dependenciesGetter.get();
  }

  @Override
  public Collection<String> dependencyTypenames() {
    return dependencyTypesGetter.get();
  }

  private Collection<String> collectDependencyTypenames() {
    return dependencies().stream()
        .map(CustomType::canonicalName)
        .collect(Collectors.toSet());
  }

  @Override
  public String simpleDeclaration() {
    return actualSimpleGetter.get();
  }

  @Override
  public String simpleDeclaration(Function<String, String> nameMapper) {
    return TypeReferenceFunctions.getSimpleTypeDeclaration(this, nameMapper);
  }

  @Override
  public String actualDeclaration() {
    return actualDeclarationGetter.get();
  }

  @Override
  public String actualDeclaration(Function<String, String> nameMapper) {
    return TypeReferenceFunctions.getActualTypeDeclaration(this, nameMapper);
  }

  @Override
  public String actualBlindDeclaration(Function<String, String> nameMapper) {
    return TypeReferenceFunctions.getActualBlindTypeReferenceDeclaration(this, nameMapper);
  }

  @Override
  public String formalFullDeclaration() {
    return formalFullDeclarationGetter.get();
  }

  @Override
  public String formalBriefDeclaration() {
    return formalBriefDeclarationGetter.get();
  }

  @Override
  public String prettyDeclaration() {
    throw NotImplementedExceptions.withCode("GG8tDA");
  }
}
