package by.ese.components.swing.list;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.List;

@SuppressWarnings("unused")
public class EListCellEditor<T> extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private static final String EDIT = "edit";

    private List<T> data;
    private int dataHash;
    private final Class<T> clazz;

    private String title = "";
    private Frame frame;
    private JButton button;

    /**
     * A table of objects that display and edit the contents of a cell,
     * indexed by class as declared in <code>getColumnClass</code>
     * in the <code>TableModel</code> interface.
     */
    transient protected Hashtable defaultEditorsByColumnClass;

    /**
     * A table of objects that display the contents of a cell,
     * indexed by class as declared in <code>getColumnClass</code>
     * in the <code>TableModel</code> interface.
     */
    transient protected Hashtable defaultRenderersByColumnClass;


    /**
     * Instantiate EListCellEditor with clazz
     * @param clazz Table model underlying domain model class
     */
    @SuppressWarnings("WeakerAccess")
    public EListCellEditor(Class<T> clazz) {
        this.clazz = clazz;
        this.defaultRenderersByColumnClass = new Hashtable(2);
        this.defaultEditorsByColumnClass = new Hashtable(2);
        initComponents();
    }

    /**
     * Instantiate EListCellEditor with clazz and title
     * @param clazz table model class
     * @param title Dialog title
     */
    public EListCellEditor(Class<T> clazz, String title) {
        this(clazz);
        this.title = title;
    }


    /**
     * Sets a default cell editor to be used if no editor has been set in
     * a <code>TableColumn</code>. If no editing is required in a table, or a
     * particular column in a table, uses the <code>isCellEditable</code>
     * method in the <code>TableModel</code> interface to ensure that this
     * <code>JTable</code> will not start an editor in these columns.
     * If editor is <code>null</code>, removes the default editor for this
     * column class.
     *
     * @param columnClass set the default cell editor for this columnClass
     * @param editor      default cell editor to be used for this columnClass
     * @see TableModel#isCellEditable
     * @see #setDefaultRenderer
     */
    public void setDefaultEditor(Class<?> columnClass, TableCellEditor editor) {
        if (editor != null) {
            defaultEditorsByColumnClass.put(columnClass, editor);
        } else {
            defaultEditorsByColumnClass.remove(columnClass);
        }
    }

    /**
     * Sets a default cell renderer to be used if no renderer has been set in
     * a <code>TableColumn</code>. If renderer is <code>null</code>,
     * removes the default renderer for this column class.
     *
     * @param columnClass set the default cell renderer for this columnClass
     * @param renderer    default cell renderer to be used for this
     *                    columnClass
     * @see #setDefaultEditor
     */
    public void setDefaultRenderer(Class<?> columnClass, TableCellRenderer renderer) {
        if (renderer != null) {
            defaultRenderersByColumnClass.put(columnClass, renderer);
        } else {
            defaultRenderersByColumnClass.remove(columnClass);
        }
    }
    /**
     * Init GUI components
     * Button facade get created over cell
     */
    private void initComponents() {
        button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);
        frame = JOptionPane.getRootFrame();
    }

    /**
     * Button facade action listener handler
     *
     * @param e button facade action event
     */
    public void actionPerformed(ActionEvent e) {
        if (EDIT.equals(e.getActionCommand())) {
            dataHash = data.hashCode();
            EListDialogEditor dialog = new EListDialogEditor<>(frame, button, title, clazz, data);

            defaultEditorsByColumnClass.forEach((v, k) -> dialog.table.setDefaultEditor((Class) v, (TableCellEditor) k));
            defaultRenderersByColumnClass.forEach((v, k) -> dialog.table.setDefaultRenderer((Class) v, (TableCellRenderer) k));

            dialog.setVisible(true);

            if (dataHash != data.hashCode()) {
                fireEditingStopped();
            } else {
                fireEditingCanceled();
            }
        }
    }

    public Object getCellEditorValue() {
        return data;
    }

    @SuppressWarnings("unchecked")
    public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column) {
        data = (List<T>) value;
        return button;
    }
}
