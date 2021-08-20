/*
 * $Id$
 *
 * Copyright 2004 Sun Microsystems, Inc., 4150 Network Circle,
 * Santa Clara, California 95054, U.S.A. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package by.ese.components.swing.autocomplete.adaptor;

import by.ese.components.swing.autocomplete.converter.ObjectToStringConverter;

import java.util.Comparator;

/**
 * Abstract AutoCompleteAdaptor
 * implements : item selection, list contains, text mark methods, items search
 */
public abstract class AbstractAutoCompleteAdaptor implements AutoCompleteAdaptor {

    // Note: these comparators do not impose any ordering - e.g. they do not ensure that sgn(compare(x, y)) == -sgn(compare(y, x))
    private static final Comparator<String> EQUALS_IGNORE_CASE = (o1, o2) -> o1.equalsIgnoreCase(o2) ? 0 : -1;
    private static final Comparator<String> STARTS_WITH_IGNORE_CASE = (o1, o2) -> {
        if (o1.length() < o2.length()) return -1;
        return o1.regionMatches(true, 0, o2, 0, o2.length()) ? 0 : -1;
    };
    private static final Comparator<String> EQUALS = (o1, o2) -> o1.equals(o2) ? 0 : -1;
    private static final Comparator<String> STARTS_WITH = (o1, o2) -> o1.startsWith(o2) ? 0 : -1;
    /**
     * the string representation in use for the currently selected item
     */
    private String selectedItemAsString;

    @Override
    public String getSelectedItemAsString() {
        return this.selectedItemAsString;
    }

    @Override
    public void setSelectedItemAsString(String itemAsString) {
        this.selectedItemAsString = itemAsString;
    }

    @Override
    public boolean listContainsSelectedItem() {
        Object selectedItem = getSelectedItem();
        for (int i = 0, n = getItemCount(); i < n; i++) {
            if (getItem(i) == selectedItem) return true;
        }
        return false;
    }

    @Override
    public void markEntireText() {
        markText(0);
    }

    @Override
    public void markText(int start) {
        getTextComponent().setCaretPosition(getTextComponent().getText().length());
        getTextComponent().moveCaretPosition(start);
    }

    /**
     * Searches for an item that matches the given pattern. The match is not case-sensitive
     * and will only match at the beginning of each item's string representation.
     *
     * @param pattern the pattern that should be matched
     * @return the first item that matches the pattern or <code>null</code> if no item matches
     */
    @Override
    public LookupResult lookupItem(String pattern, ObjectToStringConverter stringConverter) {
        Object selectedItem = getSelectedItem();

        LookupResult lookupResult;

        // first try: case sensitive
        lookupResult = lookupItem(pattern, stringConverter, EQUALS);
        if (lookupResult != null) return lookupResult;

        lookupResult = lookupOneItem(selectedItem, pattern, stringConverter, STARTS_WITH);
        if (lookupResult != null) return lookupResult;

        lookupResult = lookupItem(pattern, stringConverter, STARTS_WITH);
        if (lookupResult != null) return lookupResult;

        // second try: ignore case
        lookupResult = lookupItem(pattern, stringConverter, EQUALS_IGNORE_CASE);
        if (lookupResult != null) return lookupResult;

        lookupResult = lookupOneItem(selectedItem, pattern, stringConverter, STARTS_WITH_IGNORE_CASE);
        if (lookupResult != null) return lookupResult;

        lookupResult = lookupItem(pattern, stringConverter, STARTS_WITH_IGNORE_CASE);
        if (lookupResult != null) return lookupResult;

        // no item starts with the pattern => return null
        return new LookupResult(null, "");
    }

    private LookupResult lookupItem(String pattern, ObjectToStringConverter stringConverter, Comparator<String> comparator) {
        // iterate over all items and return first match
        for (int i = 0, n = getItemCount(); i < n; i++) {
            Object currentItem = getItem(i);
            LookupResult result = lookupOneItem(currentItem, pattern, stringConverter, comparator);
            if (result != null) return result;
        }
        return null;
    }

    private LookupResult lookupOneItem(Object item, String pattern, ObjectToStringConverter stringConverter, Comparator<String> comparator) {
        String[] possibleStrings = stringConverter.getPossibleStringsForItem(item);
        if (possibleStrings != null) {
            for (int j = 0; j < possibleStrings.length; j++) {
                if (comparator.compare(possibleStrings[j], pattern) == 0) {
                    return new LookupResult(item, possibleStrings[j]);
                }
            }
        }
        return null;
    }
}
