package by.ese.components.swing.menu;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Extension to JMenuItem to accept menu item text, mnemonic and action listener in constructor args.
 */
public class EMenuItem extends JMenuItem {
    public EMenuItem(String text, int mnemonic, ActionListener l) {
        super(text, mnemonic);
        super.addActionListener(l);
    }
}
