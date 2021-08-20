package by.ese.components.swing.autocomplete.adaptor;

import by.ese.components.swing.comboBox.EComboBoxModel;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;

public interface Searchable<T> {
    /**
     * Find first item matching text.
     * Item to String conversion made by stringConverter
     * Comparison made by comparator, where first param is searched text, second is item converted to String
     * @param text searched text
     * @param stringConverter item to string converter
     * @param comparator string to string comparator
     * @return Optional found item
     */
    Optional<T> findFirst(String text, Function<T, String> stringConverter, BiPredicate<String, String> comparator);

    /**
     * Find all items matching text
     * @see EComboBoxModel#findFirst(String, Function, BiPredicate)
     * @param text searched text
     * @param stringConverter item to string converter
     * @param comparator string to string comparator
     * @return list of found items
     */
    List<T> findAll(String text, Function<T, String> stringConverter, BiPredicate<String, String> comparator);
}
