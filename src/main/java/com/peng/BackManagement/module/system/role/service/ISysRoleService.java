package com.peng.BackManagement.module.system.role.service;


import com.peng.BackManagement.module.system.role.dao.SysRole;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

/**
 * 角色业务层
 *
 * @author: peng
 * @time: 2018/7/21
 **/
public interface ISysRoleService {

    /**
     * 根据条件查询角色数据
     *
     * @param sysRole
     * @return
     */
    List<SysRole> selecttRoleList(SysRole sysRole);

    /**
     * 根据用户ID查询权限列表
     *
     * @param userId
     * @return
     */
    Set<String> selectPrivByUserId(Long userId);

    /**
     * 根据用户Id查询角色
     *
     * @param userId
     * @return
     */
    List<SysRole> selectRolesByUserId(Long userId);

    /**
     * 查询所有角色
     *
     * @return
     */
    List<SysRole> selectRoleAll();

    /**
     * 通过角色Id查询角色
     *
     * @param roleId
     * @return
     */
    SysRole selectRoleByRoleId(Long roleId);

    /**
     * 通过角色ID删除角色
     *
     * @param roleId
     * @return
     */
    int deleteRoleByRoleId(Long roleId);

    /**
     * 批量删除角色
     *
     * @param ids
     * @return
     */
    int batchDeleteRolesByRoleIds(String ids) throws Exception;

    /**
     * 保存角色信息
     *
     * @param sysRole
     * @return
     */
    int saveRoleInfo(SysRole sysRole) throws ParseException;

    /**
     * 校验角色名称是否唯一
     *
     * @param role
     * @return
     */
    String checkRoleNameUnique(SysRole role);

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId
     * @return
     */
    int countSysRoleByRoleId(Long roleId);
}
