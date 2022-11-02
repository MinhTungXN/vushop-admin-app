package com.vu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vu.constant.ParameterConstant;
import com.vu.constant.QueryConstant;
import com.vu.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query(
        nativeQuery = true,
        value = QueryConstant.FIND_ROLE_NAMES_BY_USER_ID
    )
	List<String> findRoleNamesByUserId(@Param(ParameterConstant.USER_ID_PARAMETER) Long userId);
	
	@Query(
        nativeQuery = true,
        value = QueryConstant.FIND_ROLES_BY_USER_ID
    )
	List<Role> findRolesByUserId(@Param(ParameterConstant.USER_ID_PARAMETER) Long userId);
}
