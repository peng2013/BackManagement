package com.peng.BackManagement.module.system.role.controller;

import com.peng.BackManagement.module.system.role.dao.SysRole;
import com.peng.BackManagement.module.system.role.service.ISysRoleService;
import com.peng.BackManagement.util.resutil.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


/**
 * 角色信息接口
 *
 * @author: peng
 * @time: 2018/7/21
 **/
@Api(description = "角色信息接口")
@RestController
@RequestMapping("/SysRole")
public class SysRoleController {
    private static final Logger log = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private ISysRoleService sysRoleService;

    @ApiOperation(value = "查询角色", notes = "根据信息查询角色列表")
    @RequestMapping(value = "/getRoleList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<List> getRoleListByInfo(HttpServletRequest request, @RequestBody SysRole sysRole) {

        try {
            List<SysRole> resultList = sysRoleService.selecttRoleList(sysRole);
            return ResponseData.succcess(resultList, "查询成功");
        } catch (Exception e) {
            return ResponseData.error(null, "查询失败");
        }

    }

    @ApiOperation(value = "新增角色", notes = "新增角色")
    @RequestMapping(value = "/saveRole", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseData<Map> saveRoleInfo(HttpServletRequest request, @RequestBody SysRole sysRole) {
        int insertCount = 0;


        try {
            insertCount = sysRoleService.saveRoleInfo(sysRole);
        } catch (Exception e) {
            return ResponseData.error(null, "插入异常!");
        }

        return ResponseData.succcess(null, "成功插入" + insertCount + "条数据!");
    }
}