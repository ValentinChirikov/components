package by.ese.components.swing.autocomplete.adaptor;

import by.ese.components.swing.autocomplete.converter.ObjectToStringConverter;

import javax.swing.text.JTextComponent;

/**
 * This is the interface that binds the mechanism for automatic completion to
 * a data model, a selection model (e.g. those used by JList, JComboBox and JTable)
 * and the JTextComponent for which the automatic completion should happen.
 * It is used to search and select a matching item and to mark the completed text
 * inside the JTextComponent. Using this interface the mechanism for automatic
 * completion is independent from the underlying data and selection model.
 *
 * @see ComboBoxAdaptor
 * @see ListAdaptor
 */
public interface AutoCompleteAdaptor {
    /**
     * Returns the currently selected item.
     *
     * @return the selected item
     */
    Object getSelectedItem();

    /**
     * Sets the selected item.
     *
     * @param item the item that is to be selected
     */
    void setSelectedItem(Object item);

    /**
     * Returns the string representation in use for the currently selected item.
     *
     * @return the string representation in use for the currently selected item
     */
    String getSelectedItemAsString();

    /**
     * Sets the string representation in use for the currently selected item.
     *
     * @param itemAsString the string representation in use for the currently selected item
     */
    void setSelectedItemAsString(String itemAsString);

    /**
     * Returns the number of items in the list.
     *
     * @return the number of items in the list
     */
    int getItemCount();

    /**
     * Returns the item at a given index. It is supposed that <code>0&lt;=index&lt;<b>getItemCount()</b></code>.
     *
     * @param index the index of the item that is to be returned
     * @return the item at the given <code>index</code>
     */
    Object getItem(int index);

    /**
     * Returns true if the list contains the currently selected item.
     *
     * @return true if the list contains the currently selected item.
     */
    boolean listContainsSelectedItem();

    /**
     * Returns the text component that is being used for the automatic completion.
     *
     * @return the text component being used for the automatic completion
     */
    JTextComponent getTextComponent();

    /**
     * Marks/selects the entire text that is displayed inside the text component.
     */
    void markEntireText();

    /**
     * Marks/selects the text that is displayed inside the text component starting from the
     * character with index <tt>start</tt>.
     *
     * @param start index of the first character that should be marked
     */
    void markText(int start);

    /**
     * Searches for an item that matches the given pattern. The AbstractAutoCompleteAdaptor
     * is used to access the candidate items. The match is not case-sensitive
     * and will only match at the beginning of each item's string representation.
     *
     * @param pattern the pattern that should be matched
     * @param stringConverter Object to String converter
     * @return the first item that matches the pattern or <code>null</code> if no item matches
     */
    LookupResult lookupItem(String pattern, ObjectToStringConverter stringConverter);

    /**
     * Wrapper for AutoCompleteAdaptor lookup results
     */
    class LookupResult {
        public Object matchingItem;
        public String matchingString;

        public LookupResult(Object matchingItem, String matchingString) {
            this.matchingItem = matchingItem;
            this.matchingString = matchingString;
        }
    }
}
