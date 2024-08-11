package com.assessment.comsc.role;

/**
 * Definition of Role.
 * There 5 members exist in our system.
 */
public enum Role {

    MODULE_LEADER(0, "ModuleLeader", "http://localhost:8080/admin/sideNav"),
    PS_TEAM(1, "PSTeam", "http://localhost:8080/tracking"),
    INTERNAL_MONITOR(2, "InternalMonitor", "/guest"),
    EXTERNAL_MONITOR(3, "ExternalMonitor", "/guest"),
    PANEL_MONITOR(4, "PanelMonitor", "/guest");


    //Map each role in the code using numbers
    private Integer roleCode;
    //real name
    private String roleName;
    //after logging in , system will redirect to this url depend on different role.
    private String url;

    public static String getUrlByRoleCode(Integer roleCode, long id) {
        for (Role role : values()) {
            if (role.getRoleCode().equals(roleCode)) {
                if (roleCode == 1 || roleCode == 0) {
                    return role.getUrl() + "?userId=" + id;
                }
                return role.getUrl();
            }
        }
        return "";
    }


    Role(Integer roleCode, String roleName, String url) {
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.url = url;
    }


    public Integer getRoleCode() {
        return roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getUrl() {
        return url;
    }

}
