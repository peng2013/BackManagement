package com.peng.BackManagement.module.system.role.mapper;


import com.peng.BackManagement.module.system.role.dao.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface SysRoleMapper {
    /**
     * 根据条件查询角色数据
     *
     * @param sysRole
     * @return
     */
    List<SysRole> selectRoleList(SysRole sysRole);

    /**
     * 根据用户ID查询权限列表
     *
     * @param userId
     * @return
     */
    Set<SysRole> selectPrivByUserId(Long userId);

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
    int batchDeleteRolesByRoleIds(String ids);

    /**
     * 保存角色信息
     *
     * @param sysRole
     * @return
     */
    int saveRoleInfo(SysRole sysRole);

    /**
     * 校验角色名称是否唯一
     *
     * @param roleName
     * @return
     */
    SysRole checkRoleNameUnique(String roleName);

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId
     * @return
     */
    int countSysRoleByRoleId(Long roleId);
}