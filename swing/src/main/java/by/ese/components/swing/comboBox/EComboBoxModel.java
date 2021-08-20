package by.ese.components.swing.comboBox;

import by.ese.components.swing.autocomplete.adaptor.Filterable;
import by.ese.components.swing.autocomplete.adaptor.Searchable;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListDataListener;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Valentin on 19.02.16.
 * Derived from DefaultComboboxModel
 *
 * @param <T> items type
 */
public class EComboBoxModel<T> extends AbstractListModel<T> implements MutableComboBoxModel<T>, Serializable, Searchable<T>, Filterable<T> {
    private Vector<T> objects;
    /**
     * Original model items
     */
    private Vector<T> originals;

    private Object selectedObject;

    /**
     * Constructs an empty EComboBoxModel object.
     */
    public EComboBoxModel() {
        objects = new Vector<>();
        originals = new Vector<>();
    }

    /**
     * Constructs am EComboBoxModel object initialized with an array of objects.
     *
     * @param items an array of Object objects
     */
    public EComboBoxModel(final T[] items) {
        objects = new Vector<>(items.length);

        int i, c;
        for (i = 0, c = items.length; i < c; i++) {
            objects.addElement(items[i]);
        }

        originals = (Vector<T>) objects.clone();

        if (getSize() > 0) {
            selectedObject = getElementAt(0);
        }
    }

    /**
     * Constructs an EComboBoxModel object initialized with a vector.
     *
     * @param v a Vector object ...
     */
    public EComboBoxModel(Vector<T> v) {
        objects = v;
        originals = (Vector<T>) objects.clone();

        fireIntervalAdded(this, 0, objects.size() - 1);

        if (getSize() > 0) {
            selectedObject = getElementAt(0);
        }
    }

    /**
     * Constructs an EComboBoxModel object initialized with a List
     *
     * @param items a List object
     */
    public EComboBoxModel(List<T> items) {
        this(new Vector<>(items));
    }

    /**
     * Get items
     *
     * @return List
     */
    List<T> getItems() {
        return objects;
    }

    /**
     * Set items
     *
     * @param items List
     */
    public void setItems(List<T> items) {
        this.removeAllElements();

        objects = new Vector<>(items);

        if (getSize() > 0) {
            fireIntervalAdded(this, 0, objects.size() - 1);
            setSelectedItem(getElementAt(0));
        }
    }

    public Object getSelectedItem() {
        return selectedObject;
    }

    /**
     * Set the value of the selected item. The selected item may be null.
     *
     * @param anObject The combo box value or null for no selection.
     */
    public void setSelectedItem(Object anObject) {

        if ((selectedObject != null && !selectedObject.equals(anObject)) ||
                selectedObject == null && anObject != null) {
            selectedObject = anObject;
            fireContentsChanged(this, -1, -1);
        }
    }

    public int getSize() {
        return objects.size();
    }

    public T getElementAt(int index) {
        if (index >= 0 && index < objects.size())
            return objects.elementAt(index);
        else
            return null;
    }

    /**
     * Returns the index-position of the specified object in the list.
     *
     * @param anObject item
     * @return an int representing the index position, where 0 is
     * the first position
     */
    public int getIndexOf(Object anObject) {
        return objects.indexOf(anObject);
    }

    public void addElement(T anObject) {
        objects.addElement(anObject);
        fireIntervalAdded(this, objects.size() - 1, objects.size() - 1);
        if (objects.size() == 1 && selectedObject == null && anObject != null) {
            setSelectedItem(anObject);
        }
    }

    public void insertElementAt(T anObject, int index) {
        objects.insertElementAt(anObject, index);
        fireIntervalAdded(this, index, index);
    }

    public void removeElementAt(int index) {
        if (getElementAt(index) == selectedObject) {
            if (index == 0) {
                setSelectedItem(getSize() == 1 ? null : getElementAt(index + 1));
            } else {
                setSelectedItem(getElementAt(index - 1));
            }
        }

        objects.removeElementAt(index);

        fireIntervalRemoved(this, index, index);
    }

    public void removeElement(Object anObject) {
        int index = objects.indexOf(anObject);
        if (index != -1) {
            removeElementAt(index);
        }
    }

    /**
     * Empties the list.
     */
    public void removeAllElements() {
        if (objects.size() > 0) {
            int firstIndex = 0;
            int lastIndex = objects.size() - 1;
            objects.removeAllElements();
            selectedObject = null;
            fireIntervalRemoved(this, firstIndex, lastIndex);
        } else {
            selectedObject = null;
        }
    }

    @Override
    public Optional<T> findFirst(String text, Function<T, String> stringConverter, BiPredicate<String, String> comparator) {
        return getItems()//get items
                .parallelStream()//process parallel
                .filter(item -> comparator.test(text, stringConverter.apply(item)))//compare
                .findFirst();//return Optional
    }

    @Override
    public List<T> findAll(String text, Function<T, String> stringConverter, BiPredicate<String, String> comparator) {
        return getItems()//get items
                .parallelStream()//process parallel
                .filter(item -> comparator.test(text, stringConverter.apply(item)))//compare
                .collect(Collectors.toList());//collect
    }

    public Optional<T> applyFilter(String text, Function<T, String> stringConverter, BiPredicate<String, String> comparator) {
        //text is empty - set originals ant return empty Optional
        if (text.isEmpty()) {
            setItems(originals);
            fireFilterApplied(this);
            return Optional.empty();
        }

        List<T> foundItems = originals.parallelStream()
                .filter(item -> comparator.test(text, stringConverter.apply(item)))
                .collect(Collectors.toList());
        setItems(foundItems);

        fireFilterApplied(this);

        if (getItems().size() == 1) {
            return Optional.of(getElementAt(0));
        } else {
            return Optional.empty();
        }

    }

    /**
     * @param source the <code>ListModel</code> that changed, typically "this"
     * @see EventListenerList
     * @see DefaultListModel
     */
    protected void fireFilterApplied(Object source) {
        Object[] listeners = listenerList.getListenerList();
        EFilterEvent e = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ListDataListener.class) {
                if (e == null) {
                    e = new EFilterEvent(source, EFilterEvent.FILTER_APPLIED, -1, -1);
                }
                ((ListDataListener) listeners[i + 1]).contentsChanged(e);
            }
        }
    }
}
