package com.gitee.starblues.grape.plugin.web;

import com.gitee.starblues.grape.core.plugin.web.WebViewRegister;


/**
 * 插件web界面定义接口
 * @author starBlues
 * @version 1.0
 */
public interface PluginWebInterface {

    /**
     * 配置界面
     * @param register 注册者
     */
    void config(WebViewRegister register);

}
