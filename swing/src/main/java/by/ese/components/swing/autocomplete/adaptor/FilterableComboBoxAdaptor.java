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
import by.ese.components.swing.comboBox.EComboBox;

import javax.swing.*;
import java.util.Optional;
import java.util.function.BiPredicate;

/**
 * An implementation of the AbstractAutoCompleteAdaptor that is suitable for JComboBox.
 *
 * @author Thomas Bierhance
 * @author Karl Schaefer
 */
@SuppressWarnings("nls")
public class FilterableComboBoxAdaptor extends ComboBoxAdaptor {

    /**
     * the combobox being adapted
     */
    private final ComboBoxModel comboBoxModel;
    private final BiPredicate<String, String> filter;

    /**
     * Creates a new ComboBoxAdaptor for the given combobox.
     *
     * @param comboBox the combobox that should be adapted
     */
    public FilterableComboBoxAdaptor(EComboBox comboBox) {
        this(comboBox, (text, item) -> {
            if (text.isEmpty()) return true;
            return item.toLowerCase().contains(text.toLowerCase());
        });
    }

    public FilterableComboBoxAdaptor(EComboBox comboBox, BiPredicate<String, String> filter) {
        super(comboBox);
        this.filter = filter;
        this.comboBoxModel = comboBox.getModel();
    }

    @Override
    public LookupResult lookupItem(String pattern, ObjectToStringConverter stringConverter) {
        if (Filterable.class.isAssignableFrom(comboBoxModel.getClass())) {
            Filterable filterable = (Filterable) comboBoxModel;
            Optional filtered = filterable.applyFilter(pattern, stringConverter::getPreferredStringForItem, filter);
            if (filtered.isPresent()) {
                return new LookupResult(filtered.get(), pattern);
            } else {
                return new LookupResult(null, "");
            }

        } else if (Searchable.class.isAssignableFrom(comboBoxModel.getClass())) {
            Searchable searchable = (Searchable) comboBoxModel;
            Optional found = searchable.findFirst(pattern, stringConverter::getPreferredStringForItem, filter);
            if (found.isPresent()) {
                return new LookupResult(found.get(), pattern);
            } else {
                return new LookupResult(null, "");
            }
        } else {
            return super.lookupItem(pattern, stringConverter);
        }
    }

/*

        //red color when no match
        filterEditor.getFilterLabel()
                .setForeground(model.getSize() == 0 ?
                        Color.red : UIManager.getColor("Label.foreground"));
        //add unmatched items
        filteredItems.forEach(model::addElement);
    }
*/
}