package com.gitee.starblues.grape.rest.security;

import com.gitee.starblues.grape.core.security.MenuService;
import com.gitee.starblues.grape.core.security.UserService;
import com.gitee.starblues.grape.core.security.model.AuthUserInfo;
import com.gitee.starblues.grape.rest.common.BaseResource;
import com.gitee.starblues.grape.rest.common.Result;
import com.gitee.starblues.grape.rest.common.enums.ApiEnum;
import com.gitee.starblues.grape.rest.security.model.vo.NavigationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.gitee.starblues.grape.rest.common.Result.*;
import static com.gitee.starblues.grape.rest.common.ResultUtils.errorLog;

/**
 * 授权相关接口
 * @author starBlues
 * @version 1.0
 * @since 2020-07-24
 */
@RestController
@RequestMapping(BaseResource.API + "user")
@Api(tags = "用户授权接口")
@AllArgsConstructor
@Slf4j
public class SecurityResource extends BaseResource {

    private final UserService userService;
    private final MenuService menuService;

    @GetMapping("/info")
    @ApiOperation("获取当前用户信息")
    public Result<AuthUserInfo> getUserInfo(){
        try {
            AuthUserInfo authUserInfo = userService.getAuthUserInfo();
            return success(ApiEnum.GET_SUCCESS, authUserInfo);
        } catch (Exception e) {
            errorLog(log, e, "获取用户信息失败. {}", e.getMessage());
            return failure(ApiEnum.GET_ERROR, "获取用户信息失败", e);
        }
    }

    @GetMapping("/nav")
    @ApiOperation("获取当前用户角色的菜单")
    public Result<NavigationVo> getCurrentUserNavInfo(){
        return success(ApiEnum.GET_SUCCESS, menuService.getCurrentUserNav());
    }

    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public Result<String> logout(){
        return response(ApiEnum.OPERATE_SUCCESS);
    }


}
