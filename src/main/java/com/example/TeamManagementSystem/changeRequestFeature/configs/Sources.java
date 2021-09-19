package com.example.TeamManagementSystem.changeRequestFeature.configs;

import com.example.TeamManagementSystem.changeRequestFeature.domain.entityMarker.ChangeRequestEntityMarker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;


public class Sources<ID extends Number> {

    private Map<Class<?>, JpaRepository<? extends ChangeRequestEntityMarker, ? extends Number>> repositories;
    private Map<Class<?>, Map<String, Supplier<?>>> repositoryOperations;
    private final EntityManager entityManager;

    private String approverRole;

    public Sources(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    {
        repositories = new HashMap<>();
        repositoryOperations = new HashMap<>();
    }

    public <T extends JpaRepository<D, ?>, D> void addOperation(Class<D> domainClass, Map<String, Supplier<?>> repositoryOperations) {
        this.repositoryOperations.put(domainClass, repositoryOperations);
    }

    public <T extends JpaRepository<D, ?>, D> void addRepository(Class<D> domainClass, JpaRepository<? extends ChangeRequestEntityMarker, ? extends Number> repository) {
        this.repositories.put(domainClass, repository);
    }

    public <T extends ChangeRequestEntityMarker> JpaRepository<T, ID> loadRepository(Class<?> domainClass) {
        return (JpaRepository<T, ID>) this.repositories.get(domainClass);
    }

    public <E> E findCollectionOfElements(String operationQualifier, List<Class<E>> domainClass) {
        Supplier<E> operation = (Supplier<E>) this.repositoryOperations.get(domainClass).get(operationQualifier);
        return operation.get();
    }

    public <E> E findCollectionOfElements(String operationQualifier, Class<E> domainClass) {
        Supplier<E> operation = (Supplier<E>) this.repositoryOperations.get(domainClass).get(operationQualifier);
        return operation.get();
    }

    public <E extends ChangeRequestEntityMarker> E executeRepositoryFunction(Class<E> domainClass, Function<SimpleJpaRepository<ChangeRequestEntityMarker, ? extends Number>, ChangeRequestEntityMarker> function) {
        return (E) function.apply((SimpleJpaRepository<ChangeRequestEntityMarker, ? extends Number>) loadRepository(domainClass));
    }


    public void setApproverRole(String approverRole) {
        this.approverRole = approverRole;
    }

    public String getApproverRole() {
        return approverRole;
    }
}
