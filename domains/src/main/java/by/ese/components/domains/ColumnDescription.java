package by.ese.components.domains;


import by.ese.components.utils.ClassUtils;

/**
 * ETableModel column description
 *
 * @author Valentin
 */
@SuppressWarnings("unused")
public class ColumnDescription {

    /**
     * Domain model property name
     * Column identifier
     */
    public String name;
    /**
     * Column caption(header)
     */
    public String caption;
    /**
     * Column width
     */
    public int width;
    /**
     * Is column editable
     */
    public boolean isEditable;

    public ColumnDescription(String name) {
        this(name, ClassUtils.nicify(name));
    }

    public ColumnDescription(String name, String caption) {
        this(name, caption, 75, true);
    }

    public ColumnDescription(String name, int width) {
        this(name, ClassUtils.nicify(name), width, true);
    }

    public ColumnDescription(String name, boolean isEditable) {
        this(name, ClassUtils.nicify(name), 75, isEditable);
    }

    public ColumnDescription(String name, String caption, int width) {
        this(name, caption, width, true);
    }

    public ColumnDescription(String name, int width, boolean isEditable) {
        this(name, ClassUtils.nicify(name), width, isEditable);
    }

    /**
     *
     * @param name Column name
     * @param caption Column caption
     * @param width Column width
     * @param isEditable Is column editable
     */
    @SuppressWarnings("WeakerAccess")
    public ColumnDescription(String name, String caption, int width, boolean isEditable) {
        this.name = name;
        this.caption = caption;
        this.width = width;
        this.isEditable = isEditable;
    }

}
