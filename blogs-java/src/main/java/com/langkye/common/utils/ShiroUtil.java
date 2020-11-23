package com.langkye.common.lang.utils;

import com.langkye.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author langkye
 */
public class ShiroUtil {

    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

}