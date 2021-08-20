package by.ese.components.swing.comboBox;

import by.ese.components.utils.HtmlHighlighter;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Renders ComboBox popup list
 *  each odd row has different background
 *  highlights matched filter pattern
 *  list items converted to text by stringConverter function
 */
@SuppressWarnings("unused")
public class EComboBoxRenderer<T> extends DefaultListCellRenderer {
    private static final Color background = new Color(225, 240, 255);
    private static final Color defaultBackground = (Color) UIManager.get("List.background");
    private static final Color defaultForeground = (Color) UIManager.get("List.foreground");
    private Supplier<String> highlightTextSupplier;
    private Function<T, String> stringConverter;

    public EComboBoxRenderer(Supplier<String> highlightTextSupplier) {
        this.highlightTextSupplier = highlightTextSupplier;
        this.stringConverter = (o) -> o == null ? null : o.toString();
    }

    public EComboBoxRenderer(Supplier<String> highlightTextSupplier, Function<T, String> stringConverter) {
        this.highlightTextSupplier = highlightTextSupplier;
        this.stringConverter = stringConverter;
    }

    public EComboBoxRenderer(Function<T, String> stringConverter) {
        this.highlightTextSupplier = null;
        this.stringConverter = stringConverter;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value == null) {
            return this;
        }
        @SuppressWarnings("unchecked") String text = stringConverter.apply((T)value);
        //highlight matched text if we have highlightTextSupplier
        if(highlightTextSupplier != null) {
            text = HtmlHighlighter.highlightText(text, highlightTextSupplier.get());
        }
        this.setText(text);
        //make popup list look nicer, each odd row different background
        if (!isSelected) {
            this.setBackground(index % 2 == 0 ? background : defaultBackground);
        }
        this.setForeground(defaultForeground);
        return this;
    }

}