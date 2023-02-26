package com.portalprojects.entity;

import com.portalprojects.entity.base.PrimaryEntity;
import com.portalprojects.infrastructure.constant.EntityProperties;
import com.portalprojects.infrastructure.constant.MucDoUuTien;
import com.portalprojects.infrastructure.constant.TrangThaiDauViec;
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
@Table(name = "dau_viec")
public class DauViec extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_CODE, nullable = false)
    private String maDauViec;

    @Column(length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String tenDauViec;

    @Column(length = EntityProperties.LENGTH_DESCRIPTION)
    @Nationalized
    private String moTa;

    @Column(nullable = false)
    private Long deadLine;

    @Column(nullable = false)
    private Long ngayHoanThanh;

    @Column(nullable = false)
    private MucDoUuTien mucDoUuTien;

    private Short tienDo;

    @Column(length = EntityProperties.LENGTH_DESCRIPTION)
    @Nationalized
    private String ghiChu;

    @Column(nullable = false)
    private TrangThaiDauViec trangThaiDauViec;
}
