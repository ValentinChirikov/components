package by.ese.components.domains;


import java.lang.reflect.Field;

/**
 * Created by Valentin on 27.06.2017.
 */
public class FieldColumn {
    public Field field;
    public ColumnDescription column;

    public FieldColumn(Field field, ColumnDescription column) {
        this.field = field;
        this.column = column;
    }
}
