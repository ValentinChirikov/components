package by.ese.components.repositories;

import com.querydsl.core.types.EntityPath;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;

import java.io.Serializable;

public interface GenericQueryDSLDataRepository<T, Q extends EntityPath<?>, ID extends Serializable>
        extends QuerydslPredicateProjectionRepository<T>, GenericRepository<T, ID>, QuerydslBinderCustomizer<Q> {
}
