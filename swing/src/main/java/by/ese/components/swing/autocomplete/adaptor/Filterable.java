package by.ese.components.swing.autocomplete.adaptor;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;

public interface Filterable<T> {

    /**
     * Applies string filter to model
     * Item to String conversion made by stringConverter
     *
     * @param text            filter text
     * @param stringConverter item to string converter
     * @param comparator      string to string comparator
     * @return null or value
     */
    Optional<T> applyFilter(String text, Function<T, String> stringConverter, BiPredicate<String, String> comparator);
}
