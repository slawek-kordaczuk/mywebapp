package com.mycompany.mywebapp.common;

public abstract class ColumnDefinition<T> {
  public abstract void render(T t, StringBuilder sb);

  public boolean isClickable() {
    return true;
  }

  public boolean isSelectable() {
    return true;
  }
}
