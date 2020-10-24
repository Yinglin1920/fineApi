package com.example.demo.common;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/16 15:44
 * @desc
 */
public class CurrentUser {

    public static AuthorizedUser currentUser;


    /**
     *
     * @return
     */
    public static AuthorizedUser currentUser() {

        // 仅测试时使用,不带token的话则为默认的
        if(ObjectHelper.isEmpty(currentUser)){
            AuthorizedUser authorizedUser = new AuthorizedUser();
            authorizedUser.setId(1L);
            authorizedUser.setUserName("test");
            authorizedUser.setDeptCode("TEST");
            authorizedUser.setDeptName("测试");
            return authorizedUser;
        }
        return  currentUser;
    }

    /**
     * 获取用户部门权限
     *
     * @return set集合
     */
    public static Set<String> getDeptCodes() {
        return Optional.ofNullable(currentUser())
                .map(AuthorizedUser::getDeptCodes)
                .filter(i -> i.size() > 0)
                .orElse(new HashSet<>(Collections.singletonList("")));
    }

    /**
     * 获取用户的菜单权限
     *
     * @return set集合
     */
    public static Set<String> getMenuCodes() {
        return Optional.ofNullable(currentUser())
                .map(AuthorizedUser::getMenuCodes)
                .filter(i -> i.size() > 0)
                .orElse(new HashSet<>(Collections.singletonList("")));
    }

    /**
     * 获取用户的角色
     *
     * @return set集合
     */
    public static Set<Long> getRoleIds() {
        return Optional.ofNullable(currentUser())
                .map(AuthorizedUser::getRoleIds)
                .filter(i -> i.size() > 0)
                .orElse(new HashSet<>(Collections.singletonList(-1L)));
    }

//    /**
//     * 获取用户的审批权限
//     *
//     * @return set集合
//     */
//    public static Set<Integer> getApplyFlowIds() {
//        return Optional.ofNullable(currentUser())
//                .map(AuthorizedUser::getApplyFlowIds)
//                .filter(i -> i.size() > 0)
//                .orElse(new HashSet<>(Collections.singletonList(-1)));
//    }

//    /**
//     * 获取用户的审批权限
//     *
//     * @return set集合
//     */
//    public static Set<Integer> getTbFlowIds() {
//        return Optional.ofNullable(currentUser())
//                .map(AuthorizedUser::getTbFlowIds)
//                .filter(i -> i.size() > 0)
//                .orElse(new HashSet<>(Collections.singletonList(-1)));
//    }
//
//    /**
//     * 获取用户的审批权限
//     *
//     * @return set集合
//     */
//    public static Set<Integer> getOpporFlowIds() {
//        return Optional.ofNullable(currentUser())
//                .map(AuthorizedUser::getOpporFlowIds)
//                .filter(i -> i.size() > 0)
//                .orElse(new HashSet<>(Collections.singletonList(-1)));
//    }
//
//    /**
//     * 是否有审核一键审核的权限
//     * @return
//     */
//    public static boolean getIsDataApplyCheck(){
//        return Optional.ofNullable(currentUser())
//                .map(AuthorizedUser::isDataApplyCheck)
//                .orElse(false);
//    }

    /**
     * 获取用户的id
     *
     * @return set集合
     */
    public static Long getId() {
        return Optional.ofNullable(currentUser())
                .map(AuthorizedUser::getId)
                .orElse(0L);
    }

    /**
     * 获取用户的姓名
     *
     * @return 姓名
     */
    public static String getUserName() {
        return Optional.ofNullable(currentUser())
                .map(AuthorizedUser::getUserName)
                .orElse("");
    }

    /**
     * 获取用户的登录名
     *
     * @return 登录名
     */
    public static String getLoginName() {
        return Optional.ofNullable(currentUser())
                .map(AuthorizedUser::getLoginName)
                .orElse("");
    }

    /**
     * 获取用户的部门编码
     *
     * @return 部门编码
     */
    public static String getDeptCode() {
        return Optional.ofNullable(currentUser())
                .map(AuthorizedUser::getDeptCode)
                .orElse("");
    }

    /**
     * 获取用户的部门名称
     *
     * @return 部门名称
     */
    public static String getDeptName() {
        return Optional.ofNullable(currentUser())
                .map(AuthorizedUser::getDeptName)
                .orElse("");
    }

    /**
     * 验证当前用户是否有部门权限
     *
     * @param deptCode 部门编码
     * @return 是否有权限
     */
    public static boolean validDept(String deptCode) {
        return Optional.of(getDeptCodes())
                .filter(set -> set.size() > 0)
                .map(set -> set.contains(deptCode))
                .orElse(false);
    }
}
