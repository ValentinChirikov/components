package by.ese.components.repositories;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.List;

@SuppressWarnings({"Duplicates", "unused"})
public class QuerydslPredicateProjectionRepositoryImpl<T> extends QuerydslJpaPredicateExecutor<T> implements QuerydslPredicateProjectionRepository<T> {
    private final Querydsl querydsl;


    @SuppressWarnings("unused")
    public QuerydslPredicateProjectionRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager, EntityPathResolver resolver, CrudMethodMetadata metadata) {
        super(entityInformation, entityManager, resolver, metadata);
        EntityPath<T> path = resolver.createPath(entityInformation.getJavaType());
        PathBuilder<T> builder = new PathBuilder<>(path.getType(), path.getMetadata());
        this.querydsl = new Querydsl(entityManager, builder);
    }

    @Override
    public <Projection> Page<Projection> findAll(Predicate predicate, Pageable pageable, FactoryExpression<Projection> select) {
        JPQLQuery<Projection> query = createQuery(predicate).select(select);
        querydsl.applyPagination(pageable, query);
        querydsl.applySorting(pageable.getSort(), query);

        QueryResults<Projection> queryResults = query.fetchResults();
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    @Override
    public <Projection> List<Projection> findAll(Predicate predicate, FactoryExpression<Projection> select) {
        return createQuery(predicate).select(select).fetch();
    }

    @Override
    public <Projection> List<Projection> findAll(Predicate predicate, Sort sort, FactoryExpression<Projection> select) {
        JPQLQuery<Projection> query = createQuery(predicate).select(select);
        querydsl.applySorting(sort, query);

        return query.fetch();
    }

    @Override
    public <Projection> List<Projection> findAll(Predicate predicate, FactoryExpression<Projection> select, Expression<?>[] groupBy, OrderSpecifier<? extends Comparable>[] orderBy) {
        Assert.notNull(select, "Selection parameter must be specified");
        Assert.notNull(groupBy, "GroupBy parameter must be specified");
        Assert.notNull(orderBy, "OrderBy parameter must be specified");

        return createQuery(predicate).select(select).groupBy(groupBy).orderBy(orderBy).fetch();
    }

    @Override
    public <Projection> List<Projection> findAll(Predicate predicate, FactoryExpression<Projection> select, Expression<?>[] groupBy) {
        Assert.notNull(select, "Selection parameter must be specified");
        Assert.notNull(groupBy, "GroupBy parameter must be specified");

        return createQuery(predicate).select(select).groupBy(groupBy).fetch();
    }

    @Override
    public <Projection> List<Projection> findAll(Predicate predicate, FactoryExpression<Projection> select, OrderSpecifier<? extends Comparable>[] orderBy) {
        Assert.notNull(select, "Selection parameter must be specified");
        Assert.notNull(orderBy, "OrderBy parameter must be specified");

        return createQuery(predicate).select(select).orderBy(orderBy).fetch();
    }

    @Override
    public <Projection> List<Projection> findAll(Predicate predicate, FactoryExpression<Projection> select, Expression<?> groupBy) {
        Assert.notNull(select, "Selection parameter must be specified");
        Assert.notNull(groupBy, "GroupBy parameter must be specified");

        return createQuery(predicate).select(select).groupBy(groupBy).fetch();
    }

    @Override
    public <Projection> List<Projection> findAll(Predicate predicate, FactoryExpression<Projection> select, OrderSpecifier<? extends Comparable> orderBy) {
        Assert.notNull(select, "Selection parameter must be specified");
        Assert.notNull(orderBy, "OrderBy parameter must be specified");

        return createQuery(predicate).select(select).orderBy(orderBy).fetch();
    }

    @Override
    public <Projection> List<Projection> findAll(Predicate predicate, FactoryExpression<Projection> select, Expression<?> groupBy, OrderSpecifier<? extends Comparable>[] orderBy) {
        Assert.notNull(select, "Selection parameter must be specified");
        Assert.notNull(groupBy, "GroupBy parameter must be specified");
        Assert.notNull(orderBy, "OrderBy parameter must be specified");

        return createQuery(predicate).select(select).groupBy(groupBy).orderBy(orderBy).fetch();
    }

    @Override
    public <Projection> List<Projection> findAll(Predicate predicate, FactoryExpression<Projection> select, Expression<?>[] groupBy, OrderSpecifier<? extends Comparable> orderBy) {
        Assert.notNull(select, "Selection parameter must be specified");
        Assert.notNull(groupBy, "GroupBy parameter must be specified");
        Assert.notNull(orderBy, "OrderBy parameter must be specified");

        return createQuery(predicate).select(select).groupBy(groupBy).orderBy(orderBy).fetch();
    }

    @Override
    public <Projection> List<Projection> findAll(Predicate predicate, FactoryExpression<Projection> select, Expression<?> groupBy, OrderSpecifier<? extends Comparable> orderBy) {
        Assert.notNull(select, "Selection parameter must be specified");
        Assert.notNull(groupBy, "GroupBy parameter must be specified");
        Assert.notNull(orderBy, "OrderBy parameter must be specified");

        return createQuery(predicate).select(select).groupBy(groupBy).orderBy(orderBy).fetch();
    }
}