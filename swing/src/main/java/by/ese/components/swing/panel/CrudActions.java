package by.ese.components.swing.panel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author Valentin
 */
public interface CrudActions{
    /**
     * Create action name
     */
    String CREATE = "Create";
    /**
     * Edit action name
     */
    String EDIT = "Edit";
    /**
     * Refresh action name
     */
    String REFRESH = "Refresh";
    /**
     * Delete action name
     */
    String DELETE = "Delete";
    /**
     * Redo action name
     */
    String REDO = "Redo";
    /**
     * Undo action name
     */
    String UNDO = "Undo";
    /**
     * Commit action name
     */
    String COMMIT = "Commit";

    Action create();

    Action edit();

    Action delete();

    Action refresh();

    Action undo();

    Action redo();

    Action commit();
    
    /**
     * Create Action handler
     * @param e event
     */
    default void create(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Update Action handler
     * @param e event
     */
    default void edit(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Delete Action handler
     * @param e event
     */
    default void delete(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Refresh Action handler
     * @param e event
     */
    default void refresh(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Undo Action handler
     * @param e event
     */
    default void undo(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Redo Action handler
     * @param e event
     */
    default void redo(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Commit Action handler
     * @param e event
     */
    default void commit(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}
