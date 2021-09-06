package com.gitee.starblues.grape.rest;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gitee.starblues.grape.core.security.LoginLogService;
import com.gitee.starblues.grape.repository.databases.entity.LoginLog;
import com.gitee.starblues.grape.rest.common.BaseResource;
import com.gitee.starblues.grape.rest.common.Result;
import com.gitee.starblues.grape.rest.common.enums.ApiEnum;
import com.gitee.starblues.grape.rest.model.param.loginlog.LoginLogPageParam;
import com.gitee.starblues.grape.utils.AuthUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import static com.gitee.starblues.grape.rest.common.Result.*;
/**
 * 登录日志接口配置
 * @author starBlues
 * @version 1.0
 */
@RestController
@RequestMapping(BaseResource.API + "login-log")
@Api(tags = "登录日志接口")
@AllArgsConstructor
@Slf4j
public class LoginLogResource extends BaseResource {

    private final LoginLogService loginLogService;


    @GetMapping()
    @PreAuthorize("@auth.permission('loginLog:query')")
    @ApiOperation("分页查询登录日志")
    public Result<IPage<LoginLog>> pageQuery(@Valid LoginLogPageParam param) {
        LambdaQueryWrapper<LoginLog> wrapper = Wrappers.<LoginLog>lambdaQuery();
        if(!StrUtil.isEmpty(param.getUsername())){
            wrapper.like(LoginLog::getUsername, param.getUsername());
        }
        if(param.getLoginResult() != null){
            wrapper.like(LoginLog::getLoginResult, param.getLoginResult());
        }
        wrapper.orderByDesc(LoginLog::getGmtLoginTime);
        Page<LoginLog> page = new Page<>(param.getCurrentPage(), param.getPageSize());
        page = loginLogService.page(page, wrapper);
        return success(ApiEnum.GET_SUCCESS, page);
    }


    @GetMapping("/current")
    @ApiOperation("分页查询当前登录用户的登录日志")
    public Result<Page<LoginLog>> pageQueryByCurrentUser(@Valid LoginLogPageParam param) {
        String currentUsername = AuthUtils.getCurrentUsername();
        if(StrUtil.isEmpty(currentUsername)){
            return failure("没有发现当前认证的用户");
        }
        LambdaQueryWrapper<LoginLog> wrapper = Wrappers.<LoginLog>lambdaQuery();
        wrapper.eq(LoginLog::getUsername, currentUsername);
        wrapper.orderByDesc(LoginLog::getGmtLoginTime);
        Page<LoginLog> page = new Page<>(param.getCurrentPage(), param.getPageSize());
        page = loginLogService.page(page, wrapper);
        return success(ApiEnum.GET_SUCCESS, page);
    }

}
