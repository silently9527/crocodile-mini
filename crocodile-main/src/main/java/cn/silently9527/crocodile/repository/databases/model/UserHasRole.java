package com.gitee.starblues.grape.repository.databases.model;

import com.gitee.starblues.grape.repository.databases.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author starBlues
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserHasRole extends User {

    private String roleNames;


}
