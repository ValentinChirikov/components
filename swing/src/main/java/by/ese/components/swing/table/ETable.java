package by.ese.components.swing.table;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * Enhanced JTable, accepts ETableModel as TableModel
 */
@SuppressWarnings("unused")
public class ETable extends JTable {

    public ETable() {
        super();
        init();
    }

    public ETable(ETableModel<?> dm) {
        super(dm);
        init();
    }

    @Override
    public void createDefaultColumnsFromModel() {
        TableModel m = getModel();
        if(m instanceof ETableModel) {
            ETableModel eM = (ETableModel) m;
            // Remove any current columns
            TableColumnModel cm = getColumnModel();
            while (cm.getColumnCount() > 0) {
                cm.removeColumn(cm.getColumn(0));
            }

            // Create new columns from the data model info
            // set column identifiers equal to domain model field name
            for (int i = 0; i < eM.getColumnCount(); i++) {
                TableColumn newColumn = new TableColumn(i);
                newColumn.setIdentifier(eM.getColumnFieldName(i));
                addColumn(newColumn);
            }
        } else {
            super.createDefaultColumnsFromModel();
        }
    }

    private void init() {
        Action escapeAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEditing()) {

                    TableCellEditor cellEditor = getCellEditor();
                    if (cellEditor != null) {
                        cellEditor.cancelCellEditing();
                    }
                }
            }
        };
        KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(escape, "ESCAPE");
        getActionMap().put("ESCAPE", escapeAction);
    }
    
    public void setModel(ETableModel dataModel) {
        super.setModel(dataModel);
        for (int i = 0; i < dataModel.columnDescriptions.length; i++) {
            columnModel.getColumn(i).setPreferredWidth(dataModel.columnDescriptions[i].width);
        }
    }

    /**
     * Get first selected row model index
     * @return table model row index
     */
    public int getSelectedModelIndex() {
        int selectedRow = getSelectedRow();
        if(selectedRow == -1) {
            return -1;
        } else {
            return convertRowIndexToModel(selectedRow);
        }
    }

    @Override
    public void setModel(TableModel dataModel) {
        if (dataModel instanceof ETableModel) {
            ETableModel dm = (ETableModel) dataModel;
            this.setModel(dm);
        } else {
            super.setModel(dataModel);
        }

    }

}
