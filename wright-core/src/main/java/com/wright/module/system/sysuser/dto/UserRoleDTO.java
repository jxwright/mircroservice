package com.wright.module.system.sysuser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force=true)
@AllArgsConstructor
@Builder
public class UserRoleDTO {

	private Integer userId;
    private String userName;
    private Integer roleId;
    private String roleName;
}
