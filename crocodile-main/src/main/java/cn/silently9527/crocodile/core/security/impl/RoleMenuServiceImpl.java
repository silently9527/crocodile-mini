package com.gitee.starblues.grape.core.security.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gitee.starblues.grape.repository.databases.entity.RoleMenu;
import com.gitee.starblues.grape.repository.databases.mapper.RoleMenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.starblues.grape.core.security.RoleMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * <p>
 * 角色菜单权限表 服务实现类
 * </p>
 *
 * @author starblues
 * @since 2020-12-31
 */
@Service
@AllArgsConstructor
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {


    @Override
    public void removeByMenuId(String menuId) {
        if(StrUtil.isEmpty(menuId)){
            return;
        }
        Wrapper<RoleMenu> wrapper = Wrappers.<RoleMenu>lambdaQuery()
                .eq(RoleMenu::getMenuId, menuId);
        remove(wrapper);
    }

    @Override
    public void removeByMenuId(Collection<String> menuIds) {
        if(menuIds == null || menuIds.isEmpty()){
            return;
        }
        Wrapper<RoleMenu> wrapper = Wrappers.<RoleMenu>lambdaQuery()
                .in(RoleMenu::getMenuId, menuIds);
        remove(wrapper);
    }

    @Override
    public void removeByRoleId(String roleId) {
        if(StrUtil.isEmpty(roleId)){
            return;
        }
        Wrapper<RoleMenu> wrapper = Wrappers.<RoleMenu>lambdaQuery()
                .eq(RoleMenu::getRoleId, roleId);
        remove(wrapper);
    }
}
