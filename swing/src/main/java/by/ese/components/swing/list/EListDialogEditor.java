package by.ese.components.swing.list;


import by.ese.components.swing.panel.AbstractCrudActions;
import by.ese.components.swing.panel.CrudControls;
import by.ese.components.swing.table.ETableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

public class EListDialogEditor<T> extends JDialog implements ActionListener {

    private static final String ACTION_DISPOSE_NAME = "dispose";
    private final Class<T> clazz;
    private final List<T> data;
    private final Component locationComp;
    JTable table;
    private ETableModel<T> tableModel;
    private CrudControls controls;

    /**
     * @param frame        Parent frame
     * @param locationComp component in relation with this window placed(near table cell that we edit)
     * @param title        Frame title
     * @param clazz        List element Class
     * @param data         Table data
     */
    @SuppressWarnings({"WeakerAccess"})
    public EListDialogEditor(Frame frame,
                             Component locationComp,
                             String title,
                             Class<T> clazz,
                             List<T> data) {
        super(frame, title, true);
        this.clazz = clazz;
        this.data = data;
        this.locationComp = locationComp;

        initComponents();

        controls.setActions(new AbstractCrudActions() {
            @Override
            public void create(ActionEvent e) {
                try {
                    tableModel.addRow(clazz.newInstance());
                } catch (InstantiationException | IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void delete(ActionEvent e) {
                tableModel.removeRow(table.convertRowIndexToModel(table.getSelectedRow()));
            }

            @Override
            public void commit(ActionEvent e) {
                dispose();
            }
        });

        controls.setBtnCreateLabel("");
        controls.setBtnDeleteLabel("");
        controls.setBtnCommitLabel("");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), ACTION_DISPOSE_NAME);
        getRootPane().getActionMap().put(ACTION_DISPOSE_NAME, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        controls = new CrudControls();
        controls.setBtnEditVisible(false);
        controls.setBtnRedoVisible(false);
        controls.setBtnRefreshVisible(false);
        controls.setBtnUndoVisible(false);

        tableModel = new ETableModel(clazz, data);
        table = new JTable(tableModel);
        //setEditors
        //setRenderers
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(300, 80));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(controls, c);
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(scrollPane, c);

        getContentPane().add(panel);

        pack();
        setLocationRelativeTo(locationComp);
    }

    public void actionPerformed(ActionEvent e) {
    }
}
