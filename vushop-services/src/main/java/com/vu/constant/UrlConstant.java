package com.vu.constant;

public class UrlConstant {

	// -- Home URLs
    public static final String DEFAULT_URL = "/";
    public static final String HOME_URL = "/home";
    
    // -- Common Resource URLs
    public static final String CSS_FILES_PATH_URL = "/css/**";
    public static final String JS_FILES_PATH_URL = "/js/**";
    
    // -- User URLs
    public static final String USER_API_URL = "/api/user";
    public static final String USERS_PATH_URL = "/user/**";
    public static final String USER_LIST_URL = "/user/list";
    public static final String USER_DETAIL_URL = "/user/detail";
    public static final String USER_ADD_URL = "/user/add";
    public static final String USER_EDIT_URL = "/user/edit";
    public static final String USER_REMOVE_URL = "/user/remove";
    public static final String REDIRECT_USER_LIST_URL = "redirect:/user/list";
    
    // -- Role URLs
    public static final String ROLES_PATH_URL = "/role/**";
    
    // -- Security Check URLs
    public static final String LOGIN_URL = "/login";
    public static final String LOGOUT_URL = "/logout";
    public static final String SECURITY_CHECK_URL = "/j_spring_security_check";
    public static final String LOGIN_FAILURE_URL = "/login?error=true";
    public static final String LOGOUT_SUCCESS_URL = "/login?logout=true";
    public static final String REDIRECT_LOGIN_URL = "redirect:/login";
    
    // -- Error URLs
    public static final String ACCESS_DENIED_URL = "/access-denied";
    public static final String NOT_FOUND_URL = "/not-found";
    
    // -- Product URLs
    public static final String PRODUCT_API_URL = "/api/product";
    
    public static final String NEW_ARRIVAL_PRODUCTS_URL = "/new-arrivals";
    public static final String HOT_SALE_PRODUCTS_URL = "/hot-sales";
    public static final String BEST_SELLER_PRODUCTS_URL = "best-sellers";

    public static final String PRODUCT_LIST_URL = "/list";
    public static final String PRODUCT_DETAILS_URL = "/detail";
    public static final String PRODUCT_ADD_URL = "/add";
    public static final String PRODUCT_EDIT_URL = "/edit/{id}";
    public static final String PRODUCT_REMOVE_URL = "/remove/{id}";
}
