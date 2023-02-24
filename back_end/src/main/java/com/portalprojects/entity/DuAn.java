package com.portalprojects.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author thangncph26123
 */

@Entity
@Data
@ToString
@Table(name = "du_an")
public class DuAn implements Serializable {

    @Id
    @Column(length = 36, updatable = false)
    private String id;

    @Column(name = "ten_du_an")
    private String tenDuAn;
}
