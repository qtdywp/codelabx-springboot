package com.example.demo.model;

import com.example.demo.model.group.GroupA;
import com.example.demo.model.group.GroupB;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

public class Role
{
    @Range(min = 1,max = Integer.MAX_VALUE,message = "角色ID必须大于等于0",groups = {GroupA.class})
    /**角色id*/
    private Integer roleID;

    @NotBlank
    @Length(min = 4,max = 20,message = "角色名长度必须在[4,20]内",groups = {GroupB.class})
    /**角色名*/
    private String roleName;

    @Range(min = 0,max = 100,message = "编号必须是[0,100]",groups={Default.class})
    /**角色编号*/
    private Integer roleNO;

    @Range(min = 0,max = 2,message = "启用状态必须是[0,1]",groups = {GroupB.class})
    /**启用状态 0：未启用；1：已启用*/
    private Integer IsActive;

    public Integer getRoleID()
    {
        return roleID;
    }

    public void setRoleID(Integer roleID)
    {
        this.roleID = roleID;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public Integer getRoleNO()
    {
        return roleNO;
    }

    public void setRoleNO(Integer roleNO)
    {
        this.roleNO = roleNO;
    }

    public Integer getIsActive()
    {
        return IsActive;
    }

    public void setIsActive(Integer isActive)
    {
        IsActive = isActive;
    }
}
