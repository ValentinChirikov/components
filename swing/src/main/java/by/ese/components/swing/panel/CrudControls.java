package by.ese.components.swing.panel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JSeparator;

/**
 * @author Valentin
 */
@SuppressWarnings({"SpellCheckingInspection", "unused", "WeakerAccess", "FieldCanBeLocal"})
public class CrudControls extends javax.swing.JPanel {

    private static final Logger LOG = Logger.getLogger(CrudControls.class.getName());

    public static final String PROP_BTNCREATELABEL = "btnCreateLabel";
    public static final String PROP_BTNEDITLABEL = "btnEditLabel";
    public static final String PROP_BTNREFRESHLABEL = "btnRefreshLabel";
    public static final String PROP_BTNDELETELABEL = "btnDeleteLabel";
    public static final String PROP_BTNUNDOLABEL = "btnUndoLabel";
    public static final String PROP_BTNREDOLABEL = "btnRedoLabel";
    public static final String PROP_BTNCOMMITLABEL = "btnCommitLabel";

    public static final String PROP_BTNUNDOTOOLTIP = "btnUndoTooltip";
    public static final String PROP_BTNREDOTOOLTIP = "btnRedoTooltip";
    public static final String PROP_BTNCOMMITTOOLTIP = "btnCommitTooltip";
    public static final String PROP_BTNCREATETOOLTIP = "btnCreateTooltip";
    public static final String PROP_BTNEDITTOOLTIP = "btnEditTooltip";
    public static final String PROP_BTNDELETETOOLTIP = "btnDeleteTooltip";
    public static final String PROP_BTNREFRESHTOOLTIP = "btnRefreshTooltip";

    public static final String PROP_BTNCREATEENABLED = "btnCreateEnabled";
    public static final String PROP_BTNEDITENABLED = "btnEditEnabled";
    public static final String PROP_BTNDELETEENABLED = "btnDeleteEnabled";
    public static final String PROP_BTNREFRESHENABLED = "btnRefreshEnabled";
    public static final String PROP_BTNUNDOENABLED = "btnUndoEnabled";
    public static final String PROP_BTNREDOENABLED = "btnRedoEnabled";
    public static final String PROP_BTNCOMMITENABLED = "btnCommitEnabled";
    public static final String PROP_BTNCREATEVISIBLE = "btnCreateVisible";
    public static final String PROP_BTNEDITVISIBLE = "btnEditVisible";
    public static final String PROP_BTNDELETEVISIBLE = "btnDeleteVisible";
    public static final String PROP_BTNREFRESHVISIBLE = "btnRefreshVisible";
    public static final String PROP_BTNUNDOVISIBLE = "btnUndoVisible";
    public static final String PROP_BTNREDOVISIBLE = "btnRedoVisible";
    public static final String PROP_BTNCOMMITVISIBLE = "btnCommitVisible";
    /**
     * Buttons Action Listeners
     */
    private CrudActions actions;

    private boolean btnCreateVisible = true;
    private boolean btnEditVisible = true;
    private boolean btnDeleteVisible = true;
    private boolean btnRefreshVisible = true;
    private boolean btnUndoVisible = true;
    private boolean btnRedoVisible = true;
    private boolean btnCommitVisible = true;
    private javax.swing.JButton btnCommit;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnRedo;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnUndo;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;

    /**
     * Creates new form ETableNewControlsPanel
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public CrudControls() {
        initComponents();

        addPropertyChangeListener(PROP_BTNCREATELABEL, (e) -> actions.create().putValue(Action.NAME, e.getNewValue()));
        addPropertyChangeListener(PROP_BTNEDITLABEL, (e) -> actions.edit().putValue(Action.NAME, e.getNewValue()));
        addPropertyChangeListener(PROP_BTNDELETELABEL, (e) -> actions.delete().putValue(Action.NAME, e.getNewValue()));
        addPropertyChangeListener(PROP_BTNREFRESHLABEL, (e) -> actions.refresh().putValue(Action.NAME, e.getNewValue()));
        addPropertyChangeListener(PROP_BTNUNDOLABEL, (e) -> actions.undo().putValue(Action.NAME, e.getNewValue()));
        addPropertyChangeListener(PROP_BTNREDOLABEL, (e) -> actions.redo().putValue(Action.NAME, e.getNewValue()));
        addPropertyChangeListener(PROP_BTNCOMMITLABEL, (e) -> actions.commit().putValue(Action.NAME, e.getNewValue()));

        addPropertyChangeListener(PROP_BTNCREATETOOLTIP, (e) -> actions.create().putValue(Action.SHORT_DESCRIPTION, e.getNewValue()));
        addPropertyChangeListener(PROP_BTNEDITTOOLTIP, (e) -> actions.edit().putValue(Action.SHORT_DESCRIPTION, e.getNewValue()));
        addPropertyChangeListener(PROP_BTNDELETETOOLTIP, (e) -> actions.delete().putValue(Action.SHORT_DESCRIPTION, e.getNewValue()));
        addPropertyChangeListener(PROP_BTNREFRESHTOOLTIP, (e) -> actions.refresh().putValue(Action.SHORT_DESCRIPTION, e.getNewValue()));
        addPropertyChangeListener(PROP_BTNUNDOTOOLTIP, (e) -> actions.undo().putValue(Action.SHORT_DESCRIPTION, e.getNewValue()));
        addPropertyChangeListener(PROP_BTNREDOTOOLTIP, (e) -> actions.redo().putValue(Action.SHORT_DESCRIPTION, e.getNewValue()));
        addPropertyChangeListener(PROP_BTNCOMMITTOOLTIP, (e) -> actions.commit().putValue(Action.SHORT_DESCRIPTION, e.getNewValue()));

        addPropertyChangeListener(PROP_BTNCREATEENABLED, (e) -> actions.create().setEnabled((boolean) e.getNewValue()));
        addPropertyChangeListener(PROP_BTNEDITENABLED, (e) -> actions.edit().setEnabled((boolean) e.getNewValue()));
        addPropertyChangeListener(PROP_BTNDELETEENABLED, (e) -> actions.delete().setEnabled((boolean) e.getNewValue()));
        addPropertyChangeListener(PROP_BTNREFRESHENABLED, (e) -> actions.refresh().setEnabled((boolean) e.getNewValue()));
        addPropertyChangeListener(PROP_BTNUNDOENABLED, (e) -> actions.undo().setEnabled((boolean) e.getNewValue()));
        addPropertyChangeListener(PROP_BTNREDOENABLED, (e) -> actions.redo().setEnabled((boolean) e.getNewValue()));
        addPropertyChangeListener(PROP_BTNCOMMITENABLED, (e) -> actions.commit().setEnabled((boolean) e.getNewValue()));

        addPropertyChangeListener(PROP_BTNCREATEVISIBLE, (e) -> btnCreate.setVisible((boolean) e.getNewValue()));
        addPropertyChangeListener(PROP_BTNEDITVISIBLE, (e) -> btnEdit.setVisible((boolean) e.getNewValue()));
        addPropertyChangeListener(PROP_BTNDELETEVISIBLE, (e) -> btnDelete.setVisible((boolean) e.getNewValue()));
        addPropertyChangeListener(PROP_BTNREFRESHVISIBLE, (e) -> btnRefresh.setVisible((boolean) e.getNewValue()));
        addPropertyChangeListener(PROP_BTNUNDOVISIBLE, (e) -> btnUndo.setVisible((boolean) e.getNewValue()));
        addPropertyChangeListener(PROP_BTNREDOVISIBLE, (e) -> btnRedo.setVisible((boolean) e.getNewValue()));
        addPropertyChangeListener(PROP_BTNCOMMITVISIBLE, (e) -> btnCommit.setVisible((boolean) e.getNewValue()));

        setActions(new AbstractCrudActions() {
        });
    }

    public Component add(Action a) {
        return super.add(new JButton(a));
    }

    public CrudActions getActions() {
        return actions;
    }

    public boolean isBtnCreateEnabled() {
        return actions.create().isEnabled();
    }

    public void setBtnCreateEnabled(boolean btnCreateEnabled) {
        firePropertyChange(PROP_BTNCREATEENABLED, isBtnCreateEnabled(), btnCreateEnabled);
    }

    public boolean isBtnEditEnabled() {
        return actions.edit().isEnabled();
    }

    public void setBtnEditEnabled(boolean btnEditEnabled) {
        firePropertyChange(PROP_BTNEDITENABLED, isBtnEditEnabled(), btnEditEnabled);
    }

    public boolean isBtnDeleteEnabled() {
        return actions.delete().isEnabled();
    }

    public void setBtnDeleteEnabled(boolean btnDeleteEnabled) {
        firePropertyChange(PROP_BTNDELETEENABLED, isBtnDeleteEnabled(), btnDeleteEnabled);
    }

    public boolean isBtnRefreshEnabled() {
        return actions.refresh().isEnabled();
    }

    public void setBtnRefreshEnabled(boolean btnRefreshEnabled) {
        firePropertyChange(PROP_BTNREFRESHENABLED, isBtnRefreshEnabled(), btnRefreshEnabled);
    }

    public boolean isBtnUndoEnabled() {
        return actions.undo().isEnabled();
    }

    public void setBtnUndoEnabled(boolean btnUndoEnabled) {
        firePropertyChange(PROP_BTNUNDOENABLED, isBtnUndoEnabled(), btnUndoEnabled);
    }

    public boolean isBtnRedoEnabled() {
        return actions.redo().isEnabled();
    }

    public void setBtnRedoEnabled(boolean btnRedoEnabled) {
        firePropertyChange(PROP_BTNREDOENABLED, isBtnRedoEnabled(), btnRedoEnabled);
    }

    public boolean isBtnCommitEnabled() {
        return actions.commit().isEnabled();
    }

    public void setBtnCommitEnabled(boolean btnCommitEnabled) {
        firePropertyChange(PROP_BTNCOMMITENABLED, isBtnCommitEnabled(), btnCommitEnabled);
    }

    public String getBtnRedoTooltip() {
        return (String) actions.redo().getValue(Action.SHORT_DESCRIPTION);
    }

    public void setBtnRedoTooltip(String btnRedoTooltip) {
        firePropertyChange(PROP_BTNREDOTOOLTIP, getBtnRedoTooltip(), btnRedoTooltip);
    }

    public String getBtnCommitTooltip() {
        return (String) actions.commit().getValue(Action.SHORT_DESCRIPTION);
    }

    public void setBtnCommitTooltip(String btnCommitTooltip) {
        firePropertyChange(PROP_BTNCOMMITTOOLTIP, getBtnCommitTooltip(), btnCommitTooltip);
    }

    public String getBtnCreateTooltip() {
        return (String) actions.create().getValue(Action.SHORT_DESCRIPTION);
    }

    public void setBtnCreateTooltip(String btnCreateTooltip) {
        firePropertyChange(PROP_BTNCREATETOOLTIP, getBtnCreateTooltip(), btnCreateTooltip);
    }

    public String getBtnEditTooltip() {
        return (String) actions.edit().getValue(Action.SHORT_DESCRIPTION);
    }

    public void setBtnEditTooltip(String btnEditTooltip) {
        firePropertyChange(PROP_BTNEDITTOOLTIP, getBtnEditTooltip(), btnEditTooltip);
    }

    public String getBtnDeleteTooltip() {
        return (String) actions.delete().getValue(Action.SHORT_DESCRIPTION);
    }

    public void setBtnDeleteTooltip(String btnDeleteTooltip) {
        firePropertyChange(PROP_BTNDELETETOOLTIP, getBtnDeleteTooltip(), btnDeleteTooltip);
    }

    public String getBtnRefreshTooltip() {
        return (String) actions.refresh().getValue(Action.SHORT_DESCRIPTION);
    }

    public void setBtnRefreshTooltip(String btnRefreshTooltip) {
        firePropertyChange(PROP_BTNREFRESHTOOLTIP, getBtnRefreshTooltip(), btnRefreshTooltip);
    }

    public String getBtnUndoTooltip() {
        return (String) actions.undo().getValue(Action.SHORT_DESCRIPTION);
    }

    public void setBtnUndoTooltip(String btnUndoTooltip) {
        firePropertyChange(PROP_BTNUNDOTOOLTIP, getBtnUndoTooltip(), btnUndoTooltip);
    }

    public String getBtnUndoLabel() {
        return (String) actions.undo().getValue(Action.NAME);
    }

    public void setBtnUndoLabel(String btnUndoLabel) {
        firePropertyChange(PROP_BTNUNDOLABEL, getBtnUndoLabel(), btnUndoLabel);
    }

    public String getBtnRedoLabel() {
        return (String) actions.redo().getValue(Action.NAME);
    }

    public void setBtnRedoLabel(String btnRedoLabel) {
        firePropertyChange(PROP_BTNREDOLABEL, getBtnRedoLabel(), btnRedoLabel);
    }

    public String getBtnCommitLabel() {
        return (String) actions.commit().getValue(Action.NAME);
    }

    public void setBtnCommitLabel(String btnCommitLabel) {
        firePropertyChange(PROP_BTNCOMMITLABEL, getBtnCommitLabel(), btnCommitLabel);
    }

    public String getBtnRefreshLabel() {
        return (String) actions.refresh().getValue(Action.NAME);
    }

    public void setBtnRefreshLabel(String btnRefreshLabel) {
        firePropertyChange(PROP_BTNREFRESHLABEL, getBtnRefreshLabel(), btnRefreshLabel);
    }

    public String getBtnDeleteLabel() {
        return (String) actions.delete().getValue(Action.NAME);
    }

    public void setBtnDeleteLabel(String btnDeleteLabel) {
        firePropertyChange(PROP_BTNDELETELABEL, getBtnDeleteLabel(), btnDeleteLabel);
    }

    public String getBtnEditLabel() {
        return (String) actions.edit().getValue(Action.NAME);
    }

    public void setBtnEditLabel(String btnEditLabel) {
        firePropertyChange(PROP_BTNEDITLABEL, getBtnEditLabel(), btnEditLabel);
    }

    public String getBtnCreateLabel() {
        return (String) actions.create().getValue(Action.NAME);
    }

    public void setBtnCreateLabel(String btnCreateLabel) {
        firePropertyChange(PROP_BTNCREATELABEL, getBtnCreateLabel(), btnCreateLabel);
    }

    public boolean getBtnCommitVisible() {
        return btnCommitVisible;
    }

    public void setBtnCommitVisible(boolean btnCommitVisible) {
        boolean oldBtnCommitVisible = this.btnCommitVisible;
        this.btnCommitVisible = btnCommitVisible;
        firePropertyChange(PROP_BTNCOMMITVISIBLE, oldBtnCommitVisible, btnCommitVisible);
    }

    public boolean isBtnRedoVisible() {
        return btnRedoVisible;
    }

    public void setBtnRedoVisible(boolean btnRedoVisible) {
        boolean oldBtnRedoVisible = this.btnRedoVisible;
        this.btnRedoVisible = btnRedoVisible;
        firePropertyChange(PROP_BTNREDOVISIBLE, oldBtnRedoVisible, btnRedoVisible);
    }

    public boolean isBtnUndoVisible() {
        return btnUndoVisible;
    }

    public void setBtnUndoVisible(boolean btnUndoVisible) {
        boolean oldBtnUndoVisible = this.btnUndoVisible;
        this.btnUndoVisible = btnUndoVisible;
        firePropertyChange(PROP_BTNUNDOVISIBLE, oldBtnUndoVisible, btnUndoVisible);
    }

    public boolean isBtnRefreshVisible() {
        return btnRefreshVisible;
    }

    public void setBtnRefreshVisible(boolean btnRefreshVisible) {
        boolean oldBtnRefreshVisible = this.btnRefreshVisible;
        this.btnRefreshVisible = btnRefreshVisible;
        firePropertyChange(PROP_BTNREFRESHVISIBLE, oldBtnRefreshVisible, btnRefreshVisible);
    }

    public boolean isBtnDeleteVisible() {
        return btnDeleteVisible;
    }

    public void setBtnDeleteVisible(boolean btnDeleteVisible) {
        boolean oldBtnDeleteVisible = this.btnDeleteVisible;
        this.btnDeleteVisible = btnDeleteVisible;
        firePropertyChange(PROP_BTNDELETEVISIBLE, oldBtnDeleteVisible, btnDeleteVisible);
    }

    public boolean isBtnEditVisible() {
        return btnEditVisible;
    }

    public void setBtnEditVisible(boolean btnEditVisible) {
        boolean oldBtnEditVisible = this.btnEditVisible;
        this.btnEditVisible = btnEditVisible;
        firePropertyChange(PROP_BTNEDITVISIBLE, oldBtnEditVisible, btnEditVisible);
    }

    public boolean isBtnCreateVisible() {
        return btnCreateVisible;
    }

    public void setBtnCreateVisible(boolean btnCreateVisible) {
        boolean oldBtnCreateVisible = this.btnCreateVisible;
        this.btnCreateVisible = btnCreateVisible;
        firePropertyChange(PROP_BTNCREATEVISIBLE, oldBtnCreateVisible, btnCreateVisible);
    }

    public void setActions(CrudActions actions) {
        this.actions = actions;

        btnCreate.setAction(actions.create());
        btnEdit.setAction(actions.edit());
        btnDelete.setAction(actions.delete());
        btnRefresh.setAction(actions.refresh());

        btnUndo.setAction(actions.undo());
        btnRedo.setAction(actions.redo());

        btnCommit.setAction(actions.commit());
    }

    private void initComponents() {

        btnCreate = new JButton();
        btnEdit = new JButton();
        btnDelete = new JButton();
        jSeparator1 = new JSeparator();

        btnRefresh = new JButton();
        jSeparator2 = new JSeparator();

        btnUndo = new JButton();
        btnRedo = new JButton();
        jSeparator3 = new JSeparator();

        btnCommit = new JButton();

        setPreferredSize(new java.awt.Dimension(400, 28));
        setLayout(new FlowLayout(java.awt.FlowLayout.LEFT, 5, 1));

        add(btnCreate);
        add(btnEdit);
        add(btnDelete);
        add(jSeparator1);

        add(btnRefresh);
        add(jSeparator2);

        add(btnUndo);
        add(btnRedo);
        add(jSeparator3);

        add(btnCommit);
    }

}
