package cn.silently9527.crocodile.core.security.model;

import cn.silently9527.crocodile.repository.databases.entity.Menu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 菜单树
 * @author starBlues
 * @version 1.0
 * @since 2020-12-31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuTree extends Menu {

    private List<MenuTree> children;

}
