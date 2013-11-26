package ui.pages;

public interface Matcher<T> {
    boolean match(String pattern, T object);
}
