package by.ese.components.repositories;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by Valentin on 07.04.16.
 *
 * @param <T>  - Entity class
 * @param <ID> - Entity ID class
 */
public interface GenericRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T> {

    @Override
    List<T> findAll(Predicate predicate);

    @Override
    List<T> findAll(Predicate predicate, Sort sort);

    List<T> findByIdIn(Collection<T> ids);

}
