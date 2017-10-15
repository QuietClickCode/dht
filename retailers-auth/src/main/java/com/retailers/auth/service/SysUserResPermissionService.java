package com.retailers.auth.service;

/**
 * 用户资源权限管理
 * @author zhongp
 * @version 1.0.1
 */
public interface SysUserResPermissionService {
    /**
     * 用户权限取得
     * @param userId
     */
    public void loadUserResPermission(Long userId);

    /**
     * 重新加载当前登陆用户权限
     */
    public void loadUserResPermission();
}
