package by.ese.components.swing.autocomplete.converter;

public interface ObjectToStringConverter {
    /**
     * Returns all possible <tt>String</tt> representations for a given item.
     * The default implementation wraps the method <tt>getPreferredStringForItem</tt>.
     * It returns an empty array, if the wrapped method returns <tt>null</tt>. Otherwise
     * it returns a one dimensional array containing the wrapped method's return value.
     *
     * @param item the item to convert
     * @return possible <tt>String</tt> representation for the given item.
     */
    default String[] getPossibleStringsForItem(Object item) {
        String preferred = getPreferredStringForItem(item);
        return preferred == null ? new String[0] : new String[]{preferred};
    }

    /**
     * Returns the preferred <tt>String</tt> representations for a given item.
     *
     * @param item the item to convert
     * @return the preferred <tt>String</tt> representation for the given item.
     */
    default String getPreferredStringForItem(Object item) {
        return item == null ? null : item.toString();
    }
}
