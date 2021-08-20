/*
 * (C) Copyright 6 дек. 2018 г. Valentin Chirikov (http://ese.by/) 
 * valc@ese.by +375 44 7629763
 */
package by.ese.components.swing.comboBox;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import java.awt.*;

/**
 * ComboBox provides "wide" popup for use with JTable CellEditor
 */
public class EComboBox<T> extends JComboBox<T> {

    private boolean layingOut = false;

    /**
     * Instantiate combobox with typed model
     * @param comboBoxModel Model
     */
    public EComboBox(ComboBoxModel<T> comboBoxModel) {
        super(comboBoxModel);
    }

    @Override
    public void contentsChanged(ListDataEvent e) {
        if (e instanceof EFilterEvent) {
            if (e.getType() == EFilterEvent.FILTER_APPLIED) {
                SwingUtilities.invokeLater(() -> {
                    if (isPopupVisible()) {
                        hidePopup();
                        showPopup();
                    }
                });
            }
        } else {
            super.contentsChanged(e);
        }
    }

    @Override
    public void doLayout() {
        try {
            layingOut = true;
            super.doLayout();
        } finally {
            layingOut = false;
        }
    }

    @Override
    public Dimension getSize() {
        Dimension dim = super.getSize();
        if (!layingOut) {
            dim.width = Math.max(dim.width, getPreferredSize().width);
            dim.height = Math.max(dim.height, getHeight() + getMaximumRowCount() * 10);
        }
        return dim;
    }

}
