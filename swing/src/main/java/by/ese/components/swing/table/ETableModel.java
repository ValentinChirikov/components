package by.ese.components.swing.table;

import by.ese.components.domains.ColumnDescription;
import com.google.common.primitives.Primitives;
import com.querydsl.core.util.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.table.AbstractTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Table model created from class Properties(fields with getters/setters) converted to Column's by {@link by.ese.components.swing.table.DomainModelConverter}
 *
 * @param <T> items type
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ETableModel<T> extends AbstractTableModel {

    private static final Logger LOG = LoggerFactory.getLogger(ETableModel.class);
    protected final Class<T> clazz;
    protected final boolean primitive;
    protected List<T> data;
    protected final ColumnDescription[] columnDescriptions;
    protected final DomainModelConverter<T> mc;

    /**
     * Simple constructor, columns populated by class properties introspection
     *
     * @param clazz Class
     */
    public ETableModel(Class<T> clazz) {
        this(clazz, Collections.synchronizedList(new LinkedList<>()));
    }

    /**
     * Simple constructor, columns populated by class properties introspection
     *
     * @param clazz Class
     * @param data  Data
     */
    @SuppressWarnings("unchecked")
    public ETableModel(Class<T> clazz, List<T> data) {
        this(clazz, data, new IntrospectorModelConverter());
    }

    /**
     * Columns populated by model converter, data stored in LinkedList
     *
     * @param clazz Class
     * @param mc    Model converter
     */
    public ETableModel(Class<T> clazz, DomainModelConverter<T> mc) {
        this(clazz, Collections.synchronizedList(new LinkedList<>()), mc);
    }

    /**
     * Columns populated by model converter
     *
     * @param clazz Class
     * @param data  Data
     * @param mc    Model converter
     */
    public ETableModel(Class<T> clazz, List<T> data, DomainModelConverter<T> mc) {
        if (clazz == null) throw new AssertionError("clazz must be non-null");
        if (data == null) throw new AssertionError("data must be non-null");
        if (mc == null) throw new AssertionError("mc must be non-null");
        this.clazz = clazz;
        this.data = data;
        this.mc = mc;
        this.primitive = clazz.isPrimitive() || clazz == String.class || Primitives.isWrapperType(clazz);

        columnDescriptions = mc.convert(clazz);
    }

    /**
     * Clone object with empty data
     *
     * @param tm Table model
     */
    @SuppressWarnings("CopyConstructorMissesField, unchecked")
    public ETableModel(ETableModel tm) {
        this(tm.clazz, Collections.synchronizedList(new LinkedList<>()), tm.mc);
    }

    @Override
    public int getColumnCount() {
        return columnDescriptions.length;
    }

    public void addRow(T rowData) {
        data.add(rowData);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void removeRow(int rowIndex) {
        data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void clearData() {
        data.clear();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    public List<T> getData() {
        return data;
    }

    public void addData(List<T> data) {
        this.data.addAll(data);
        fireTableDataChanged();
    }

    /**
     * Set table data and fire table data changed event
     *
     * @param data Data
     */
    public void setData(List<T> data) {
        this.data = Collections.synchronizedList(data);
        fireTableDataChanged();
    }


    @Override
    public Class<?> getColumnClass(int column) {
        if (primitive) {
            return clazz;
        }

        Field f = ReflectionUtils.getFieldOrNull(clazz, getColumnFieldName(column));
        if (f == null) {
            return Object.class;
        } else {
            return f.getType();
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnDescriptions[column].caption;
    }

    public String getColumnFieldName(int column) {
        return columnDescriptions[column].name;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnDescriptions[columnIndex].isEditable;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            if (primitive) {
                data.set(rowIndex, (T) aValue);
            } else {
                T value = data.get(rowIndex);
                new PropertyDescriptor(getColumnFieldName(columnIndex), clazz).getWriteMethod().invoke(value, aValue);
            }
        } catch (Exception e) {
            LOG.error("Failed to set cell value", e);
        }

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if (primitive) {
            return data.get(rowIndex);
        }

        try {
            return new PropertyDescriptor(getColumnFieldName(columnIndex), clazz).getReadMethod().invoke(data.get(rowIndex));
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            LOG.error("Failed to get cell value", e);
            throw new RuntimeException(e);
        }

    }
}
