package by.ese.components.utils;

/**
 * Created by Valentin on 02.03.2017.
 */
@FunctionalInterface
public interface Callback {
    Callback EMPTY = () -> {};
    void notifyCaller();
}