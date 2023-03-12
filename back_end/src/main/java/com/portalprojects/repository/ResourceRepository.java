package com.portalprojects.repository;

import com.portalprojects.entity.Resource;
import com.portalprojects.infrastructure.projection.SimpleEntityProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author thangncph26123
 */
@Repository(ResourceRepository.NAME)
public interface ResourceRepository extends JpaRepository<Resource, String> {

    public static final String NAME = "BaseResourceRepository";

    @Query(value = """
            SELECT id, name FROM resource
            """, nativeQuery = true)
    List<SimpleEntityProj> findAllSimpleEntity();
}
