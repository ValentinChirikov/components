/*
 * (C) Copyright 5 дек. 2018 г. Valentin Chirikov (http://ese.by/) 
 * valc@ese.by +375 44 7629763
 */
package by.ese.components.swing.panel;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

/**
 *
 * @author Valentin
 */
public abstract class AbstractCrudActions implements CrudActions {

    private final String CREATE_SMALL_ICON = "/by/ese/components/swing/panel/ic_add_black_16.png";
    private final String EDIT_SMALL_ICON = "/by/ese/components/swing/panel/ic_edit_black_16.png";
    private final String DELETE_SMALL_ICON = "/by/ese/components/swing/panel/ic_remove_black_16.png";
    private final String REFRESH_SMALL_ICON = "/by/ese/components/swing/panel/ic_refresh_black_16.png";
    private final String UNDO_SMALL_ICON = "/by/ese/components/swing/panel/ic_undo_black_16.png";
    private final String REDO_SMALL_ICON = "/by/ese/components/swing/panel/ic_redo_black_16.png";
    private final String COMMIT_SMALL_ICON = "/by/ese/components/swing/panel/ic_done_black_16.png";

    /**
     * Create Action
     */
    private final Action create;

    /**
     * Edit Action
     */
    private final Action edit;

    /**
     * Delete Action
     */
    private final Action delete;

    /**
     * Refresh Action
     */
    private final Action refresh;

    /**
     * Undo Action
     */
    private final Action undo;

    /**
     * Redo Action
     */
    private final Action redo;

    /**
     * Commit Action
     */
    private final Action commit;

    //setup actions with default names, icons, 
    {
        create = new AbstractAction(CREATE, new ImageIcon(getClass().getResource(CREATE_SMALL_ICON))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                create(e);
            }
        };

        edit = new AbstractAction(EDIT, new ImageIcon(getClass().getResource(EDIT_SMALL_ICON))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit(e);
            }
        };

        delete = new AbstractAction(DELETE, new ImageIcon(getClass().getResource(DELETE_SMALL_ICON))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete(e);
            }
        };

        refresh = new AbstractAction(REFRESH, new ImageIcon(getClass().getResource(REFRESH_SMALL_ICON))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh(e);
            }
        };

        undo = new AbstractAction(UNDO, new ImageIcon(getClass().getResource(UNDO_SMALL_ICON))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                undo(e);
            }
        };

        redo = new AbstractAction(REDO, new ImageIcon(getClass().getResource(REDO_SMALL_ICON))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                redo(e);
            }
        };

        commit = new AbstractAction(COMMIT, new ImageIcon(getClass().getResource(COMMIT_SMALL_ICON))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                commit(e);
            }
        };

        create.putValue(Action.SHORT_DESCRIPTION, CREATE);
        edit.putValue(Action.SHORT_DESCRIPTION, EDIT);
        delete.putValue(Action.SHORT_DESCRIPTION, DELETE);
        refresh.putValue(Action.SHORT_DESCRIPTION, REFRESH);
        undo.putValue(Action.SHORT_DESCRIPTION, UNDO);
        redo.putValue(Action.SHORT_DESCRIPTION, REDO);
        commit.putValue(Action.SHORT_DESCRIPTION, COMMIT);
        
        create.putValue(Action.MNEMONIC_KEY, KeyEvent.getExtendedKeyCodeForChar('c'));
        edit.putValue(Action.MNEMONIC_KEY, KeyEvent.getExtendedKeyCodeForChar('e'));
        delete.putValue(Action.MNEMONIC_KEY, KeyEvent.getExtendedKeyCodeForChar('d'));
        refresh.putValue(Action.MNEMONIC_KEY, KeyEvent.getExtendedKeyCodeForChar('r'));
        undo.putValue(Action.MNEMONIC_KEY, KeyEvent.getExtendedKeyCodeForChar('u'));
        redo.putValue(Action.MNEMONIC_KEY, KeyEvent.getExtendedKeyCodeForChar('o'));
        commit.putValue(Action.MNEMONIC_KEY, KeyEvent.getExtendedKeyCodeForChar('t'));
    }

    @Override
    public Action create() {
        return create;
    }

    @Override
    public Action edit() {
        return edit;
    }

    @Override
    public Action delete() {
        return delete;
    }

    @Override
    public Action refresh() {
        return refresh;
    }

    @Override
    public Action undo() {
        return undo;
    }

    @Override
    public Action redo() {
        return redo;
    }

    @Override
    public Action commit() {
        return commit;
    }

}
