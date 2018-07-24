package com.peng.BackManagement.module.system.role.mapper;

import com.peng.BackManagement.module.system.role.dao.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMenuMapper {
    /**
     * 通过角色ID删除角色和菜单关联
     *
     * @param roleId
     * @return
     */
    int deleteSysRoleMenuByRoleId(Long roleId);

    /**
     * 批量删除角色菜单关联信息
     *
     * @param ids
     * @return
     */
    int batchDeleteRoleMenu(String ids);

    /**
     * 查询菜单使用数量
     *
     * @param menuId
     * @return
     */
    int selectCountRoleMenuByMenuId(Long menuId);

    /**
     * 批量新增角色菜单信息
     *
     * @param roleMenuList
     * @return
     */
    int batchSaveRoleMenu(List<SysRoleMenu> roleMenuList);
}