package tech.intellispacesframework.javastatements.sample;

import tech.intellispacesframework.javastatements.support.TesteeType;

import java.util.Collection;

@TesteeType
public record RecordWithMethodUsingWildcardThatExtendsOtherClass() {

  public void methodUsingWildcardThatExtendsOtherClass(Collection<? extends Number> arg) {
  }
}