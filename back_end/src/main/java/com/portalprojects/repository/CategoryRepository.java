package com.portalprojects.repository;

import com.portalprojects.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author thangncph26123
 */
@Repository(CategoryRepository.NAME)
public interface CategoryRepository extends JpaRepository<Category, String> {

    public static final String NAME = "BaseCategoryRepository";
}
