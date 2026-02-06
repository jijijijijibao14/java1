package model;

import java.io.Serializable;

/**
 *
 * @author LAPTOP
 */
public class Student implements Serializable{
    private String id;
    private String name;
    private String phone;
    private String email;
    private String mountainCode;
    private double tuitionPee;
    
    public Student(String id, String name,String phone, String email, String mountainCode, double tuitionFee) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.mountainCode = mountainCode;
        this.tuitionPee = tuitionFee;
        
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public double getTuitionPee() {
        return tuitionPee;
    }

    public void setTuitionPee(double tuitionPee) {
        this.tuitionPee = tuitionPee;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-25s | %-12s | %-30s | %-8s | %,12.0f VND",
        id, name, phone, email, mountainCode, tuitionPee);
    }

    
    
}

