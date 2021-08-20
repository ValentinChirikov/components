package by.ese.components.swing.test;

import org.hamcrest.Description;
import org.mockito.ArgumentMatcher;

import java.beans.PropertyChangeEvent;

import static org.hamcrest.CoreMatchers.is;

@SuppressWarnings("nls")
public class PropertyChangeEventMatcher extends ArgumentMatcher<PropertyChangeEvent> {
    private final String propertyName;
    private final Object oldValue;
    private final Object newValue;
    
    public PropertyChangeEventMatcher(String propertyName, Object oldValue, Object newValue) {
        this.propertyName = propertyName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
    
    @Override
    public boolean matches(Object argument) {
        if (argument instanceof PropertyChangeEvent) {
            PropertyChangeEvent pce = (PropertyChangeEvent) argument;
            
            boolean result = propertyName.equals(pce.getPropertyName());
            result &= oldValue == null || pce.getOldValue() == null || is(oldValue).matches(pce.getOldValue()); 
            result &= newValue == null || pce.getNewValue() == null || is(newValue).matches(pce.getNewValue());
            
            return result;
        }
        
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void describeTo(Description description) {
        super.describeTo(description);
        description.appendText(" " + propertyName);
    }

}
