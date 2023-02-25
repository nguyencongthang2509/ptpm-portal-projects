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
@Table(name = "nhan_dau_viec")
@Data
@ToString
public class NhanDauViec extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_ID)
    private String nhanId;

    @Column(length = EntityProperties.LENGTH_ID)
    private String dauViecId;
}
