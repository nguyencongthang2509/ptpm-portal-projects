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
@Table(name = "giai_doan_dau_viec")
@Data
@ToString
public class GiaiDoanDauViec extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_ID)
    private String giaiDoanId;

    @Column(length = EntityProperties.LENGTH_ID)
    private String dauViecId;
}
