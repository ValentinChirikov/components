package by.ese.components.repositories;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SuppressWarnings("unused")
public interface QuerydslPredicateProjectionRepository<T> {
    <Projection> Page<Projection> findAll(Predicate predicate, Pageable pageable, FactoryExpression<Projection> select);

    <Projection> List<Projection> findAll(Predicate predicate, Sort sort, FactoryExpression<Projection> select);

    <Projection> List<Projection> findAll(Predicate predicate, FactoryExpression<Projection> select);

    <Projection> List<Projection> findAll(Predicate predicate, FactoryExpression<Projection> select, Expression<?>[] groupBy);

    <Projection> List<Projection> findAll(Predicate predicate, FactoryExpression<Projection> select, OrderSpecifier<? extends Comparable>[] orderBy);

    <Projection> List<Projection> findAll(Predicate predicate, FactoryExpression<Projection> select, Expression<?>[] groupBy, OrderSpecifier<? extends Comparable>[] orderBy);

    <Projection> List<Projection> findAll(Predicate predicate, FactoryExpression<Projection> select, Expression<?> groupBy, OrderSpecifier<? extends Comparable>[] orderBy);

    <Projection> List<Projection> findAll(Predicate predicate, FactoryExpression<Projection> select, Expression<?>[] groupBy, OrderSpecifier<? extends Comparable> orderBy);

    <Projection> List<Projection> findAll(Predicate predicate, FactoryExpression<Projection> select, Expression<?> groupBy);

    <Projection> List<Projection> findAll(Predicate predicate, FactoryExpression<Projection> select, OrderSpecifier<? extends Comparable> orderBy);

    <Projection> List<Projection> findAll(Predicate predicate, FactoryExpression<Projection> select, Expression<?> groupBy, OrderSpecifier<? extends Comparable> orderBy);
}