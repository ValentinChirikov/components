/*
 * (C) Copyright 27 нояб. 2018 г. Valentin Chirikov (http://ese.by/) 
 * valc@ese.by +375 44 7629763
 */
package by.ese.components.swing.table;

import by.ese.components.domains.ColumnDescription;

/**
 * Converts domain model to table model. 
 * @author Valentin
 * @param <T> domain model class
 */
public interface DomainModelConverter<T> {
    /**
     * Columns descriptions array
     * @param clazz domain class
     * @return array
     */
    public ColumnDescription[] convert(Class<T> clazz);
}
