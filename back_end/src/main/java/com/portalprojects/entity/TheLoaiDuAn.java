package com.portalprojects.entity;

import com.portalprojects.entity.base.PrimaryEntity;
import com.portalprojects.infrastructure.constant.EntityProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;

/**
 * @author thangncph26123
 */
@Entity
@Table(name = "the_loai_du_an")
@Data
@ToString
public class TheLoaiDuAn extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_ID)
    private String duAnId;

    @Column(length = EntityProperties.LENGTH_ID)
    private String theLoaiId;
}
