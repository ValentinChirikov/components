package by.ese.components.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class QuerydslPredicateProjectionRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable> extends JpaRepositoryFactoryBean<T, S, ID> {
    public QuerydslPredicateProjectionRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new QuerydslPredicateProjectionRepositoryFactory(entityManager);
    }
}