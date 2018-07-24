package com.peng.BackManagement.module.system.role.service.impl;

import com.peng.BackManagement.module.system.role.constant.UserConstants;
import com.peng.BackManagement.module.system.role.controller.SysRoleController;
import com.peng.BackManagement.module.system.role.dao.SysRole;
import com.peng.BackManagement.module.system.role.dao.SysRoleMenu;
import com.peng.BackManagement.module.system.role.mapper.SysRoleMapper;
import com.peng.BackManagement.module.system.role.mapper.SysRoleMenuMapper;
import com.peng.BackManagement.module.system.role.service.ISysRoleService;
import com.peng.BackManagement.util.stringutil.Convert;
import com.peng.BackManagement.util.stringutil.StringUtils;
import com.peng.BackManagement.util.timeutil.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * @author: peng
 * @time: 2018/7/21
 **/
@Service("sysRoleService")
public class SysRoleServiceImpl implements ISysRoleService {
    private static final Logger log = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 根据条件查询角色数据
     *
     * @param sysRole
     * @return
     */
    @Override
    public List<SysRole> selecttRoleList(SysRole sysRole) {
        if (log.isDebugEnabled()) {
            log.debug("{}",sysRole.toString());
        }
        return sysRoleMapper.selectRoleList(sysRole);
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> selectPrivByUserId(Long userId) {

        List<SysRole> perms = sysRoleMapper.selectRolesByUserId(userId);
        Set<String> peremsSet = new HashSet<>();

        perms.forEach((SysRole sysRole) -> {
            if (StringUtils.isNotNull(sysRole)) {
                peremsSet.addAll(Arrays.asList(sysRole.getRoleKey().trim().split(",")));
            }
        });
        return peremsSet;
    }

    /**
     * 根据用户查询角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> selectRolesByUserId(Long userId) {

        List<SysRole> userRolesList = sysRoleMapper.selectRolesByUserId(userId);
        List<SysRole> allRoleList = sysRoleMapper.selectRoleAll();

        allRoleList.forEach((SysRole role) -> {
            userRolesList.forEach((SysRole roleTmp) -> {
                if (role.getRoleId() == roleTmp.getRoleId()) {
                    role.setFlag(true);
                }
            });
        });

        return allRoleList;
    }

    /**
     * 查询 所有角色
     *
     * @return
     */
    @Override
    public List<SysRole> selectRoleAll() {
        return sysRoleMapper.selectRoleAll();
    }

    /**
     * 通过角色ID查询角色
     *
     * @param roleId
     * @return
     */
    @Override
    public SysRole selectRoleByRoleId(Long roleId) {
        return sysRoleMapper.selectRoleByRoleId(roleId);
    }

    /**
     * 通过角色ID删除角色
     *
     * @param roleId
     * @return
     */
    @Override
    public int deleteRoleByRoleId(Long roleId) {
        return sysRoleMapper.deleteRoleByRoleId(roleId);
    }

    /**
     * 批量删除角色信息
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @Override
    public int batchDeleteRolesByRoleIds(String ids) throws Exception {
        Long[] roleIds = Convert.toLongArray(ids);
        for (Long roleId : roleIds) {
            SysRole sysRole = selectRoleByRoleId(roleId);
            if (countSysRoleByRoleId(roleId) > 0) {
                throw new Exception(String.format("%l$s已分配，不能删除", sysRole.getRoleName()));
            }
        }
        return sysRoleMapper.batchDeleteRolesByRoleIds(ids);
    }

    /**
     * 保存角色信息
     *
     * @param sysRole
     * @return
     */
    @Override
    public int saveRoleInfo(SysRole sysRole) throws ParseException {
        Long roleId = sysRole.getRoleId();
        int insertCount = 0;

        if (StringUtils.isNotNull(roleId)) {
            //sysRole.setUpdateBy();以后添加
            // 修改角色信息
            //sysRoleMapper.up
        } else {
            //sysRole.setCreateBy();
            // 新增角色信息
            sysRole.setCreateTime(TimeUtils.getCurrentDate());

            if (log.isDebugEnabled()) {
                log.debug("角色信息:{}",sysRole.toString());
            }

            insertCount = sysRoleMapper.saveRoleInfo(sysRole);
        }


        //return saveSysRoleMenuInfo(sysRole);
        return insertCount;
    }

    /**
     * 新增角色菜单信息
     *
     * @param sysRole
     * @return
     */
    public int saveSysRoleMenuInfo(SysRole sysRole) {
        int insertCount = 0;
        // 新增用户与角色管理
        List<SysRoleMenu> list = new ArrayList<>();
        for (Long menuId : sysRole.getMenuIds()) {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setRoleId(sysRole.getRoleId());
            roleMenu.setMenuId(menuId);
        }

        if (list.size() > 0) {
            insertCount = sysRoleMenuMapper.batchSaveRoleMenu(list);
        }

        return insertCount;
    }

    /**
     * 校验角色名称是否唯一
     *
     * @param role
     * @return
     */
    @Override
    public String checkRoleNameUnique(SysRole role) {

        if (role.getRoleId() == null) {
            role.setRoleId(-1L);
        }

        Long roleId = role.getRoleId();
        SysRole roleInfo = sysRoleMapper.checkRoleNameUnique(role.getRoleName());
        if (StringUtils.isNotNull(roleInfo) && StringUtils.isNotNull(roleInfo) && roleId.equals(roleInfo.getRoleId())) {
            return UserConstants.ROLE_NAME_NOT_UNIQUE;
        }

        return UserConstants.ROLE_NAME_UNIQUE;
    }

    /**
     * 通过角色ID查询角色使用 的数量
     *
     * @param roleId
     * @return
     */
    @Override
    public int countSysRoleByRoleId(Long roleId) {
        return sysRoleMapper.countSysRoleByRoleId(roleId);
    }
}
