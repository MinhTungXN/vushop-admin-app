package com.vu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.vu.constant.EntityConstant;

@Entity
@Table(name = EntityConstant.ROLES_TABLE, 
		schema = EntityConstant.PUBLIC_SCHEMA)
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = EntityConstant.ID_COLUMN, nullable = false)
	private Long id;
	
	@NotBlank
    @Column(name = EntityConstant.NAME_COLUMN, length = 50, nullable = false)
    private String name;
	
	@NotBlank
	@Column(name = EntityConstant.DESC_COLUMN, length = 100, nullable = false)
	private String desc;
	
	public Role() {
	}
	
	public Role(Long id, @NotBlank String name, @NotBlank String desc) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
