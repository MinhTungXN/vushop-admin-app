package com.vu.entity;

import com.vu.constant.EntityConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = EntityConstant.USERS_TABLE, 
		schema = EntityConstant.PUBLIC_SCHEMA,
		uniqueConstraints = {@UniqueConstraint(name = EntityConstant.USERS_UK,
        columnNames = {EntityConstant.EMAIL_COLUMN,
                        EntityConstant.PHONE_COLUMN})})
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = EntityConstant.ID_COLUMN, nullable = false)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = EntityConstant.USERS_ROLES_TABLE,
		    joinColumns = @JoinColumn(name = EntityConstant.USER_ID_COLUMN),
		    inverseJoinColumns = @JoinColumn(name = EntityConstant.ROLE_ID_COLUMN))
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private Set<Role> roles = new HashSet<Role>();

    @NotBlank
    @Column(name = EntityConstant.FULLNAME_COLUMN, length = 200, nullable = false)
    private String fullname;
    
    @NotBlank
    @Column(name = EntityConstant.USERNAME_COLUMN, length = 200, nullable = false)
    private String username;
    
    @NotBlank
    @Column(name = EntityConstant.PASSWORD_COLUMN, length = 255, nullable = false)
    private String password;

    @NotBlank
    @Column(name = EntityConstant.EMAIL_COLUMN, length = 50, nullable = false)
    private String email;

    @NotBlank
    @Column(name = EntityConstant.ADDRESS_COLUMN, length = 255, nullable = false)
    private String address;

    @NotBlank
    @Column(name = EntityConstant.PHONE_COLUMN, length = 12, nullable = false)
    private String phone;

    @NotBlank
    @Column(name = EntityConstant.AVATAR_COLUMN,
            columnDefinition = EntityConstant.TEXT_COLUMN_DEFINITION, nullable = true)
    private String avatar;
    
    @Column(name = EntityConstant.ACTIVATED_COLUMN, nullable = true)
    private Boolean activated;
    
    @Column(name = EntityConstant.REMEMBER_TOKEN_COLUMN, length = 255, nullable = true)
    private String rememberToken;

    public User() {
    }

	public User(Long id, @NotBlank String fullname, @NotBlank String username, @NotBlank String password,
			@NotBlank String email, @NotBlank String address, @NotBlank String phone, @NotBlank String avatar,
			@NotBlank Boolean activated, @NotBlank String rememberToken) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.avatar = avatar;
		this.activated = activated;
		this.rememberToken = rememberToken;
	}

	public User(@NotBlank String fullname, @NotBlank String username, @NotBlank String password,
			@NotBlank String email, @NotBlank String address, @NotBlank String phone, @NotBlank String avatar,
			@NotBlank Boolean activated, @NotBlank String rememberToken) {
		super();
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.avatar = avatar;
		this.activated = activated;
		this.rememberToken = rememberToken;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public String getRememberToken() {
		return rememberToken;
	}

	public void setRememberToken(String rememberToken) {
		this.rememberToken = rememberToken;
	}

}
