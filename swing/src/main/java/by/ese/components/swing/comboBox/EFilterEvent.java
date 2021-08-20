package by.ese.components.swing.comboBox;

import javax.swing.event.ListDataEvent;

public class EFilterEvent extends ListDataEvent {
    /**
     * Identifies filter applied
     */
    public static final int FILTER_APPLIED = 3;

    public EFilterEvent(Object source, int type, int index0, int index1) {
        super(source, type, index0, index1);
    }
}
