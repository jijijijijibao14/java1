package business;

import model.Department;
import java.io.*;
import java.util.*;
import tools.Acceptable;
import tools.Inputer;
import business.Employees;
import java.lang.reflect.Array;
/**
 *
 * @author LAPTOP
 */
public class Departments extends ArrayList<Department>{
    public Departments(String path){
        load(path);
    }
    private void load(String path){
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = br.readLine())!= null){
                if(line.trim().isEmpty()) continue;
                String[] t = line.split(",");
                if (t.length >= 3){
                    this.add(new Department(t[0].trim(),t[1].trim(),t[2].trim()));
                }
            }
        }catch(Exception e){
                System.out.println("Cannot load department form" + path);
        }
    }
    
    public void saveToFileD(String pathTxt){
        try(PrintWriter pw = new PrintWriter(new FileWriter(pathTxt))){
            for(Department d : this){
                pw.println(d.getDepartmentId()+","+ d.getDepartmetName()+","+ d.getLocation());
            }
            System.out.println("Department data has been saved to"+ pathTxt);
            changed = false;
        }catch(Exception e){
            System.out.println("Cannot save Department data to"+ pathTxt);
        }
    }
        
    public void loadFromFileD(String path){
        File f = new File(path);
        if(!f.exists()) return;
        
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            this.clear();
            String line;
            while((line = br.readLine()) != null){
                if(line.trim().isEmpty()) continue;
                
                String[] t = line.split(",");
                if(t.length >= 3){
                    this.add(new Department(t[0].trim(),t[1].trim(),t[2].trim()));
                }
            }
            changed = false;
        }catch(Exception e){
            System.out.println("Cannot load Department data from" + path);
        }
    }
    
    
    public boolean exists (String code){
        return this.stream().anyMatch(d -> d.getDepartmentId().equalsIgnoreCase(code));
    }
    public String getNameByCode(String code){
        for(Department d : this){
            if(d.getDepartmentId().equalsIgnoreCase(code)){
                return d.getDepartmetName();
            }
        }
        return "Unknown";
    }
    
    
    private boolean changed = false;
    public boolean isChaged(){
        return changed;
    }
    
    public Department searchById(String id){
        for(Department d : this){
            if(d.getDepartmentId().equalsIgnoreCase(id)){
                return d;
            }
        }
        return null;
    }
    
    private String headerLine(){
        return String.format(
         "%-10s %-25s %-10s",
         "D-id", "Name", "Location");
    }
    
    @Override
    public boolean add(Department d){
        changed = true;
        return super.add(d);
    }  
    
    @Override
    public Department remove(int index){
        changed = true;
        return super.remove(index);
    }
    
    public void departmentDisplay(){
        System.out.println("----DEPARTMENT LIST----");
        if(this.isEmpty()){
            System.out.println("No department here");
            return;
        }
        System.out.println(headerLine());
        for(Department d : this){
            System.out.println(d);
        }
    }
    
    public void addDepartment(Employees employees){
        System.out.println("----NEW DEPARTMENT----");
        String id;
        while(true){
            id = Inputer.inputAndCheck("Department ID (D + 2 digit): ",Acceptable.DP_ID_VALID).toUpperCase();
            if(searchById(id)!= null){
                System.out.println("This Department ID already exists. Pls try another!");
            }else{
                break;
            }
        }
        String name = Inputer.inputAndCheck("Department name(must not be empty): ", Acceptable.NAME_VALID);
        String location = Inputer.inputAndCheck("Location(must not be empty): ",Acceptable.LOCAL_VALID);
        
        String mCode;
        while (true){
            mCode = Inputer.getString("Employee code: ").toUpperCase();
            if (employees.exists(mCode)) break;
            System.out.println("Employee code does not exist. Please re-enter.");
        } 
        
        Department d = new Department(id, name, location);
        this.add(d);
        changed = true;
        
        System.out.println("New department added!");
    }
    
    public void searchDeById(){
        System.out.println("----SEARCH DEPARTMENT BY ID----");
        String key = Inputer.getString("Enter Department ID: ").toUpperCase();
        List<Department> result = new ArrayList<>();
        for (Department d : this){
            if(d.getDepartmentId().toUpperCase().contains(key)){
                result.add(d);
            }
        }
        if(result.isEmpty()){
            System.out.println("this ID does not exists!");
        }else{
            System.out.println("Info: ");
            System.out.println(headerLine());
            for (Department d : result){
                System.out.println(d);
            }
        }
    }
    
    public void updateDepartment(Departments departments){
        System.out.println("----UPDATE DEPARTMENT BY ID----");
        String id = Inputer.inputAndCheck("Department ID to update: ", Acceptable.DP_ID_VALID);
        Department d = searchById(id);
        if (d == null){
            System.out.println("this depaertment does not exists.");
            return;
        }
        System.out.println("Current Department Info: ");
        System.out.println(headerLine());
        System.out.println(d);
        System.out.println("Enter new Info: ");
        
        String newName = Inputer.inputAndCheck("New Name: ", Acceptable.NAME_VALID);
        String newLocation = Inputer.inputAndCheck("New Location: ", Acceptable.LOCAL_VALID);
        
        String confirm = Inputer.getString("Confirm update? (Y/N): ");
        if(confirm.equalsIgnoreCase("N")){
            System.out.println("Update Cancelled!");
            return;
        }
        
        d.setDepartmetName(newName);
        d.setLocation(newLocation);
        
        changed = true;
        System.out.println("Update Successfully!");
    }
    
    public void searchDeByFloor(){
        System.out.println("----SEARCH DEPARTMENT BY SPECIFIC LOCATION----");
        String floor = Inputer.getString("Enter Specific Location: ");
        List<Department> result = new ArrayList<>();
        for (Department d : this){
            if(d.getLocation().contains(floor)){
                result.add(d);
            }
        }
        if(result.isEmpty()){
            System.out.println("this Department does not exists!");
        }else{
            System.out.println("Info: ");
            System.out.println(headerLine());
            for (Department d : result){
                System.out.println(d);
            }
        }
    }
    
    
}
