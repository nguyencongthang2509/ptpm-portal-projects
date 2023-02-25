package com.portalprojects.entity;

import com.portalprojects.entity.base.PrimaryEntity;
import com.portalprojects.infrastructure.constant.EntityProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Nationalized;

/**
 * @author thangncph26123
 */
@Entity
@Table(name = "nhan")
@Data
@ToString
public class Nhan extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_CODE, nullable = false)
    private String ma;

    @Column(length = EntityProperties.LENGTH_NAME_SHORT)
    @Nationalized
    private String ten;
}
