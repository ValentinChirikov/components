/*
 * (C) Copyright 27 нояб. 2018 г. Valentin Chirikov (http://ese.by/) 
 * valc@ese.by +375 44 7629763
 */
package by.ese.components.swing.table;

import by.ese.components.domains.ColumnDescription;
import by.ese.components.utils.ClassUtils;
import com.google.common.primitives.Primitives;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Model converter introspects domain model properties and fills column description
 * @author Valentin
 */
public class IntrospectorModelConverter implements DomainModelConverter {

    @Override
    public ColumnDescription[] convert(Class clazz) {
        List<ColumnDescription> columnDescriptions = new LinkedList<>();
        try {
            if(clazz.isPrimitive() || clazz == String.class || Primitives.isWrapperType(clazz)) {
                columnDescriptions.add(new ColumnDescription(clazz.getSimpleName(), clazz.getSimpleName()));
            } else {
                for (PropertyDescriptor pd : Introspector.getBeanInfo(clazz).getPropertyDescriptors()) {
                    if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
                        columnDescriptions.add(new ColumnDescription(pd.getName(), pd.getDisplayName().equals(pd.getName())?ClassUtils.nicify(pd.getName()):pd.getDisplayName()));
                    }
                }
            }
        } catch (IntrospectionException ex) {
            Logger.getLogger(IntrospectorModelConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return columnDescriptions.toArray(new ColumnDescription[0]);
    }
    
}
