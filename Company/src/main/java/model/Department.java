package model;

import java.io.Serializable;

/**
 *
 * @author LAPTOP
 */
public class Department{
    private String departmentId;
    private String departmetName;
    private String Location;

    public Department(String departmentId, String departmetName, String Location) {
        this.departmentId = departmentId;
        this.departmetName = departmetName;
        this.Location = Location;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmetName() {
        return departmetName;
    }

    public void setDepartmetName(String departmetName) {
        this.departmetName = departmetName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-25s %-10s", departmentId, departmetName, Location);
    }
    
}
