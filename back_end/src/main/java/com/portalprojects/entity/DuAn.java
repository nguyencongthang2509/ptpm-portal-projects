package com.portalprojects.entity;

import com.portalprojects.entity.base.PrimaryEntity;
import com.portalprojects.infrastructure.constant.EntityProperties;
import com.portalprojects.infrastructure.constant.TrangThaiDuAn;
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
@Table(name = "du_an")
public class DuAn extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_CODE, nullable = false)
    private String maDuAn;

    @Column(length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String tenDuAn;

    @Column(length = EntityProperties.LENGTH_DESCRIPTION)
    @Nationalized
    private String taiNguyen;

    @Column(length = EntityProperties.LENGTH_DESCRIPTION)
    @Nationalized
    private String moTa;

    @Column(nullable = false)
    private Long ngayBatDau;

    @Column(nullable = false)
    private Long ngayKetThuc;

    private Short tienDo;

    @Column(nullable = false)
    private TrangThaiDuAn trangThai;

}
