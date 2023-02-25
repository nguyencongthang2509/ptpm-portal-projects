package com.portalprojects.entity;

import com.portalprojects.entity.base.PrimaryEntity;
import com.portalprojects.infrastructure.constant.EntityProperties;
import com.portalprojects.infrastructure.constant.TrangThaiGiaiDoan;
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
@Data
@ToString
@Table(name = "giai_doan")
public class GiaiDoan extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_CODE, nullable = false)
    private String maGiaiDoan;

    @Column(length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String tenGiaiDoan;

    @Column(length = EntityProperties.LENGTH_DESCRIPTION)
    @Nationalized
    private String moTa;

    @Column(nullable = false)
    private Long ngayBatDau;

    @Column(nullable = false)
    private Long ngayKetThuc;

    private Short tienDo;

    @Column(length = EntityProperties.LENGTH_DESCRIPTION)
    @Nationalized
    private String mucTieu;

    @Column(nullable = false)
    private TrangThaiGiaiDoan trangThaiGiaiDoan;
}
