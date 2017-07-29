package com.tcl.service;

import com.tcl.model.WebUser;

/**
 * Created by wang on 2017-07-03.
 */
public interface WebUserService {
    WebUser selectById(Long id);

    WebUser selectByUserName(String userName);
}
