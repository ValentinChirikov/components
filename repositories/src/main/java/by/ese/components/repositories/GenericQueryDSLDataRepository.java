/*
 * (C) Copyright 8 нояб. 2018 г. Valentin Chirikov (http://ese.by/)
 * valc@ese.by +375 44 7629763
 */
package by.ese.components.repositories;

import com.querydsl.core.types.EntityPath;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;

import java.io.Serializable;

public interface GenericQueryDSLDataRepository<T, Q extends EntityPath<?>, ID extends Serializable>
        extends QuerydslPredicateProjectionRepository<T>, GenericRepository<T, ID>, QuerydslBinderCustomizer<Q> {
}
