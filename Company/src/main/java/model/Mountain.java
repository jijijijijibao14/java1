package model;

import java.io.Serializable;

/**
 *
 * @author LAPTOP
 */
public class Mountain implements Serializable{
    private String Code;
    private String name;
    private String province;
    private String description;
    
    public Mountain ( String Code, String name, String province, String desciption) {
        this.Code = Code;
        this.name = name;
        this.province = province;
        this.description = description;
        
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Mountain{" + "Code=" + Code + ", name=" + name + ", province=" + province + ", description=" + description + '}';
    }
    
}