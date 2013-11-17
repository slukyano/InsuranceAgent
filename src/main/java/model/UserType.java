package model;

public enum UserType {
    ADMIN,
    MANAGER,
    AGENT,
    LEGAL,
    NATURAL,
    UNAUTHORIZED;

    public static UserType fromRoleName(String roleName) {
        if (roleName.compareToIgnoreCase("insurance_admins") == 0)
            return UserType.ADMIN;
        else if (roleName.compareToIgnoreCase("insurance_managers") == 0)
            return UserType.MANAGER;
        else if (roleName.compareToIgnoreCase("insurance_agents") == 0)
            return UserType.AGENT;
        else if (roleName.compareToIgnoreCase("insurance_legals") == 0)
            return UserType.LEGAL;
        else if (roleName.compareToIgnoreCase("insurance_naturals") == 0)
            return UserType.NATURAL;
            //TODO do we need unauthorized?
        else
            return UserType.UNAUTHORIZED;

    }
}
