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
package by.ese.components.swing.autocomplete;


import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;

/**
 * <p>This is a cell editor that can be used when a combo box (that has been set
 * up for automatic completion) is to be used in a JTable. The
 * {@link DefaultCellEditor DefaultCellEditor} won't work in this
 * case, because each time an item gets selected it stops cell editing and hides
 * the combo box.
 * </p>
 * <p>
 * Usage example:
 * </p>
 * <pre><code>
 * JTable table = ...;
 * JComboBox comboBox = ...;
 * ...
 * TableColumn column = table.getColumnModel().getColumn(0);
 * column.setCellEditor(new ComboBoxCellEditor(comboBox));
 * </code></pre>
 */
public class ComboBoxCellEditor extends DefaultCellEditor {

    /**
     * Creates a new ComboBoxCellEditor.
     *
     * @param comboBox the comboBox that should be used as the cell editor.
     */
    public ComboBoxCellEditor(final JComboBox comboBox) {
        super(comboBox);

        comboBox.removeActionListener(this.delegate);

        this.delegate = new EditorDelegate() {
            @Override
            public void setValue(Object value) {
                comboBox.setSelectedItem(value);
            }

            @Override
            public Object getCellEditorValue() {
                //TODO : hack - return no selection if we have "" in editor, i need to review AutoCompleteComboBoxEditor public Object getItem() { - to return null as supposed
                if ("".equals(comboBox.getSelectedItem())) return null;
                return comboBox.getSelectedItem();
            }

            @Override
            public boolean shouldSelectCell(EventObject anEvent) {
                if (anEvent instanceof MouseEvent) {
                    MouseEvent e = (MouseEvent) anEvent;
                    return e.getID() != MouseEvent.MOUSE_DRAGGED;
                }
                return true;
            }

            @Override
            public boolean stopCellEditing() {
                if (comboBox.isEditable()) {
                    // Commit edited value.
                    comboBox.actionPerformed(new ActionEvent(ComboBoxCellEditor.this, 0, ""));
                }
                return super.stopCellEditing();
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                JTextComponent editorComponent = (JTextComponent) comboBox.getEditor().getEditorComponent();

                if (editorComponent.getDocument() instanceof AutoCompleteDocument) {
                    AutoCompleteDocument document = (AutoCompleteDocument) editorComponent.getDocument();
                    // if auto completion is happening right now, cell editing should not be stopped
                    if (!document.selecting) {
                        ComboBoxCellEditor.this.stopCellEditing();
                    }

                } else {
                    ComboBoxCellEditor.this.stopCellEditing();
                }
            }
        };

        comboBox.addActionListener(this.delegate);
    }
}