package tech.intellispacesframework.javastatements.statement.custom;

import tech.intellispacesframework.javastatements.statement.reference.CustomTypeReference;
import tech.intellispacesframework.javastatements.statement.reference.NamedTypeReference;

import java.util.List;
import java.util.Optional;

/**
 * The enum.
 */
public interface EnumStatement extends CustomType {

  /**
   * Implemented interfaces.
   */
  List<CustomTypeReference> implementedInterfaces();

  @Override
  default List<NamedTypeReference> typeParameters() {
    return List.of();
  }

  @Override
  default Optional<EnumStatement> asEnum() {
    return Optional.of(this);
  }

  default CustomType asCustomType() {
    return this;
  }
}