package by.ese.components.repositories;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryComposition;
import org.springframework.data.repository.core.support.RepositoryFragment;

import javax.persistence.EntityManager;
import java.io.Serializable;

import static org.springframework.data.querydsl.QuerydslUtils.QUERY_DSL_PRESENT;

public class QuerydslPredicateProjectionRepositoryFactory extends JpaRepositoryFactory {

    private final EntityManager entityManager;
    private EntityPathResolver entityPathResolver;

    public QuerydslPredicateProjectionRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
        this.entityPathResolver = SimpleEntityPathResolver.INSTANCE;
    }

    @Override
    protected RepositoryComposition.RepositoryFragments getRepositoryFragments(RepositoryMetadata metadata) {
        RepositoryComposition.RepositoryFragments fragments = super.getRepositoryFragments(metadata);

        boolean isQueryDslRepository = QUERY_DSL_PRESENT
                && QuerydslPredicateProjectionRepository.class.isAssignableFrom(metadata.getRepositoryInterface());

        if (isQueryDslRepository) {

            JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType());

            Object querydslFragment = getTargetRepositoryViaReflection(QuerydslPredicateProjectionRepositoryImpl.class, entityInformation,
                    entityManager, entityPathResolver, null);

            fragments = fragments.append(RepositoryFragment.implemented(querydslFragment));
        }

        return fragments;

    }
}