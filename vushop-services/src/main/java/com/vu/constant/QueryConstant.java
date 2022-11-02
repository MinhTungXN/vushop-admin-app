package com.vu.constant;

public class QueryConstant {

	public static final String FIND_ROLE_NAMES_BY_USER_ID = 
			"select r.name from public.roles r " + 
				   "inner join public.users_roles ur on ur.role_id = r.id " +
            "where ur.user_id = (:userId);";
	
	public static final String FIND_ROLES_BY_USER_ID = 
			"select * from public.roles r " + 
				   "inner join public.users_roles ur on ur.role_id = r.id " +
            "where ur.user_id = (:userId);";
	
	public static final String FIND_USER_BY_USERNAME_OR_EMAIL = 
			"select * from public.users u " + 
			"where u.username = (:usernameOrEmail) " + 
			"or u.email = (:usernameOrEmail);";
	
	public static final String FIND_ALL_USERS_WITH_FULLNAME = 
			"select * " + 
			"from public.users u " + 
			"where u.fullname like (:fullname);";
	
	public static final String FIND_RANDOM_PRODUCTS = 
			"select * " + 
			"from public.products p " + 
				  "order by random() limit 6;";
	
	public static final String FIND_PRODUCTS_BY_NAME_CONTAINS_KEYWORD = 
			"select * " + 
			"from public.products p " + 
		    "where p.name like (:keyword);";
}
