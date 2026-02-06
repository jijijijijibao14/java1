package business;

import java.io.ObjectOutputStream;
import java.io.*;
import java.util.*;
import model.Student;
import tools.Acceptable;
import tools.Inputer;
/**
 *
 * @author LAPTOP
 */
public class Students extends ArrayList<Student>{
    
    private boolean changed = false;
    public boolean isChanged(){
        return changed;
    }
    
    //FUNCTION 1:
    public void saveToFile(String pathObj){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathObj))){
            oos.writeObject(new ArrayList<>(this));
            System.out.println("Registration data has been saved to " + pathObj);
        }catch (Exception e){
            System.out.println("Error saving object file"+ pathObj);
        }
        changed = false;
    }
    
    public void loadFromFile(String path){
        File f = new File(path);
        if (!f.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))){
            this.clear();
            this.addAll((ArrayList<Student>) ois.readObject());
            changed = false;
        }catch (Exception e){
            System.out.println("Error loading data from "+ path);
        }
    }
    
    public void addStudent(Mountains mountains){
        System.out.println("---- NEW REGISTRATION ----");
        String id;
        while (true){
            id = Inputer.inputAndCheck("Student ID (CE/DE/HE/QE/SE + 6 digits): ",Acceptable.STU_ID_VALID);
            if(searchById(id) != null){
                System.out.println("This student ID already exists. Tryu another.");
            }else {
                break;
            }
        }
        String name = Inputer.inputAndCheck("Student name (2-20 chars): ",Acceptable.NAME_VALID);
        String phone = Inputer.inputAndCheck("Phone (10 digits): ",Acceptable.PHONE_VALID);
        String email = Inputer.inputAndCheck("Email: ",Acceptable.EMAIL_VALID);
        
        String mCode;
        while (true){
            mCode = Inputer.getString("Mountain code: ").toUpperCase();
            if (mountains.exists(mCode)) break;
            System.out.println("Mountain code does not exist. Please re-enter.");
        }
        
        double tuitionFee = 6000000;
        if(phone.matches(Acceptable.VIETTEL_VALID) || phone.matches(Acceptable.VNPT_VALID)){
            tuitionFee = tuitionFee * 0.65;
        }
        
        Student s = new Student(id, name, phone, email, mCode, tuitionFee);
        this.add(s);
        changed = true;
        
        System.out.println("New regidtration has been added successfully.");
    }
    
    public void updateStudent(Mountains mountains){
        System.out.println("----UPDATE STUDENT----");
        String id = Inputer.inputAndCheck("Student ID to update: ", Acceptable.STU_ID_VALID);
        Student s = searchById(id);
        if ( s == null){
            System.out.println("This student not registration yet.");
            return;
        }
        System.out.println("Current Student Info: ");
        System.out.println(headerLine());
        System.out.println(s);
        
        System.out.println("Enter new Info: ");
        
        String newName = Inputer.inputAndCheck("New name: ", Acceptable.NAME_VALID);
        String newPhone = Inputer.inputAndCheck("New phone: ", Acceptable.PHONE_VALID);
        String newEmail = Inputer.inputAndCheck("New email: ", Acceptable.EMAIL_VALID);
        
        String newCode;
        while(true){
            newCode = Inputer.getString("New mountain code: ").toUpperCase();
            if (mountains.exists(newCode)) break;
            System.out.println("Mountain code not exists. Please re-enter");
        }
        
        String confirm = Inputer.getString("Confirm update? (Y/N): ");
        if (confirm.equalsIgnoreCase("N")){
            System.out.println("Update Cancelled!");
            return;
        }
        s.setName(newName);
        s.setPhone(newPhone);
        s.setEmail(newEmail);
        s.setMountainCode(newCode);
        
        changed = true;
        System.out.println("Update successfully!");
        
    }
    
    public Student searchById(String id){
        for (Student s : this){
            if (s.getId().equalsIgnoreCase(id)){
                return s;
            }
        }
        return null;
    }
    private String headerLine(){
        return String.format(
                "%-10s %-20s %-12s %-20s %-8s %-10s",
                "ID", "Name", "Phone", "Email", "Peak", "Fee"
        );
    }

    @Override
    public boolean add(Student s) {
        changed = true;
        return super.add(s);
    }
    
    @Override
    public Student remove(int index){
        changed = true;
        return super.remove(index);
    }
    // FUNCTION 3:
    public void displayList(){
        System.out.println("----REGISTRATION LIST----");
        if(this.isEmpty()){
            System.out.println("No students have registered yet.");
            return;
        }
        System.out.println(headerLine());
        for (Student s : this){
            System.out.println(s);
        }
    }
    //FUNCTION 4:
    public void deleteStudent(){
        System.out.println("----DELETE REGISTRATION----");
        String id = Inputer.inputAndCheck("Student Id to delete: ", Acceptable.STU_ID_VALID);
        Student s = searchById(id);
        if (s == null){
            System.out.println("This studnet has not registered yet");
            return;
        }
        System.out.println("Student details: ");
        System.out.println(headerLine());
        System.out.println(s);
        String confirm = Inputer.getString("Are you sure you want to delete? (Y/N): ");
        if (confirm.equalsIgnoreCase("Y")){
            this.remove(s);
            changed = true;
            System.out.println("The registration has been successfully deleted.");
        }else{
            System.out.println("Deletion cancelld.");
        }
    }
    //FUNCTION 5:
    public void searchByName(){
        System.out.println("----SEARCH PARTICIPANTS BY NAME----");
        String key = Inputer.getString("Enter name or part of name: ").toLowerCase();
        List<Student> result = new ArrayList<>();
        for (Student s : this){
            if (s.getName().toLowerCase().contains(key)){
                result.add(s);
            }
        }
        if (result.isEmpty()){
            System.out.println("No one matches the search criterial.");
        }else{
            System.out.println("Matching student: ");
            System.out.println(headerLine());
            for (Student s : result){
                System.out.println(s);
            }
        }
    }
    //FUNCTION 6:
    public void filterByCampus(){
        System.out.println("----FILTER DATA BY CAMPUS----");
        String campus = Inputer.getString("Enter campus code [DE/CE/HE/SE/QE]: ").toUpperCase();
        
        List<Student> result = new ArrayList<>();
        for (Student s : this){
            if (s.getId().toUpperCase().startsWith(campus)){
                result.add(s);
            }
        }
        if (result.isEmpty()){
            System.out.println("No students have registered under this campus.");
        }else{
            System.out.println("Registered Students under campus " + campus + ";");
            System.out.println(headerLine());
            for (Student s : result){
                System.out.println(s);
            }
        }
    }
    public void statisticsByMountain (Mountains mountains){
        System.out.println("---- STATISTICS OF REGISTRATION BY MOUNTAIN ---- ");
        if (this.isEmpty()){
            System.out.println("No registration data to statistics.");
            return;
        }
        Map<String,Integer> countMap = new HashMap<>();
        Map<String,Double> feeMap = new HashMap<>();
        for(Student s : this){
            String code = s.getMountainCode();
            countMap.put(code, countMap.getOrDefault(code, 0) + 1);
            feeMap.put(code, feeMap.getOrDefault(code, 0.0) + s.getTuitionPee());
        }
        System.out.printf("%-10s %-25s %-10s %-15s%n",
                "code","Mountain Name","Count","Total Fee");
        System.out.println("-----------------------------------------------");
        for (String code : countMap.keySet()){
            String name = mountains.getNameByCode(code);
            int count = countMap.get(code);
            double totalFee = feeMap.get(code);
            System.out.printf("%-10s %-25s %-10s %-15.0f%n",
                    code, name, count, totalFee);
        }
    }
    
}
