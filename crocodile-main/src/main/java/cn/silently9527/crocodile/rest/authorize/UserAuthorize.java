package cn.silently9527.crocodile.rest.authorize;

/**
 * 用户授权定义
 * @author starBlues
 * @version 1.0
 */
public final class UserAuthorize {

    private UserAuthorize(){}

    /**
     * 添加用户
     */
    public static final String QUERY = "hasAnyAuthority('user:query', '*:*')";


}
