package com.portalprojects.entity;

import com.portalprojects.infrastructure.constant.EntityProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

/**
 * @author thangncph26123
 */

@Entity
@Table(name = "thanh_vien_du_an")
@Data
@ToString
public class ThanhVienDuAn {

    @Id
    @Column(length = EntityProperties.LENGTH_ID, updatable = false)
    private String id;

    @Column(length = EntityProperties.LENGTH_ID)
    private String thanhVienId;

    @Column(length = EntityProperties.LENGTH_ID)
    private String duAnId;
}
