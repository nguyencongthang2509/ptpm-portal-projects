package com.portalprojects.repository;

import com.portalprojects.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author thangncph26123
 */
@Repository(LabelRepository.NAME)
public interface LabelRepository  extends JpaRepository<Label, String> {

    public static final String NAME = "BaseLabelRepository";
}
