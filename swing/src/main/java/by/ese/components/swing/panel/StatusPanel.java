package by.ese.components.swing.panel;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 *
 * @author Valentin
 */
public final class StatusPanel extends JPanel {

    private final JTextField statusField;
    private final Map<String, JProgressBar> progressBars;

    public StatusPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
        statusField = new JTextField(16);
        statusField.setBorder(null);
        statusField.setEditable(false);
                     
        add(statusField);

        progressBars = new HashMap<>();
    }

    public StatusPanel(LayoutManager layout) {
        this(layout, true);
    }

    public StatusPanel(boolean isDoubleBuffered) {
        this(new FlowLayout(), isDoubleBuffered);
    }

    public StatusPanel() {
        this(true);
    }

    public Map<String, JProgressBar> getProgressBars() {
        return progressBars;
    }

    public void setStatusText(String text) {
        statusField.setText(text);
    }

}
