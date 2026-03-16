package business;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import model.Department;
import model.Employee;
import tools.Acceptable;
import tools.Inputer;
/**
 *
 * @author LAPTOP
 */
public class Employees extends ArrayList<Employee>{
    public Employees (String path){
        load(path);
    }
    private void load(String path){
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = br.readLine())!= null){
                if(line.trim().isEmpty()) continue;
                String[] t = line.split(",");
                if (t.length >= 5){
                    this.add(new Employee(t[0].trim(),t[1].trim(),t[2].trim(),LocalDate.parse(t[3].trim()),Double.parseDouble(t[4].trim())));
                }
            }
        }catch(Exception e){
                System.out.println("Cannot load department form" + path);
        }
    }
    
    public boolean exists (String code){
        return this.stream().anyMatch(e -> e.getEmployeeId().equalsIgnoreCase(code));
    }
    public String getNameByCode(String code){
        for(Employee e : this){
            if(e.getEmployeeId().equalsIgnoreCase(code)){
                return e.getName();
            }
        }
        return "Unknown";
    }
    
    private boolean changed = false;
    public boolean isChaged(){
        return changed;
    }
    
    public void saveToFileE(String pathTxt){
        try(PrintWriter pw = new PrintWriter(new FileWriter(pathTxt))){
            for(Employee e : this){
                pw.println(e.getEmployeeId()+","+ e.getDepartmentId()+","+ e.getName()+","+ e.getDoB()+","+ e.getSalary());
            }
            System.out.println("Employee data has been saved to"+ pathTxt);
            changed = false;
        }catch(Exception e){
            System.out.println("Cannot save Employee data to"+ pathTxt);
        }
    }
        
    public void loadFromFileE(String path){
        File f = new File(path);
        if(!f.exists()) return;
        
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            this.clear();
            String line;
            while((line = br.readLine()) != null){
                if(line.trim().isEmpty()) continue;
                
                String[] t = line.split(",");
                if(t.length >= 5){
                    this.add(new Employee(t[0].trim(),t[1].trim(),t[2].trim(),LocalDate.parse(t[3].trim()),Double.parseDouble(t[4].trim())));
                }
            }
            changed = false;
        }catch(Exception e){
            System.out.println("Cannot load Employee data from" + path);
        }
    }
    
    public Employee searchById(String id){
        for(Employee e : this){
            if(e.getEmployeeId().equalsIgnoreCase(id)){
                return e;
            }
        }
        return null;
    }
    
    private String headerLine(){
        return String.format(
         "%-10s %-10s %-20s %-12s %10s%n",
         "E-id", "D-id", "Name", "DoB", "Salary");
    }
    
    @Override
    public boolean add(Employee e){
        changed = true;
        return super.add(e);
    }    
    @Override
    public Employee remove(int index){
        changed = true;
        return super.remove(index);
    }
    
    public void EmployeeDisplay(){
        System.out.println("----EMPLOYEE LIST----");
        if(this.isEmpty()){
            System.out.println("No Employee here");
            return;
        }
        System.out.println(headerLine());
        for(Employee e : this){
            System.out.println(e);
        }
    }
    
    public void addEmployee(Departments departments){
        System.out.println("----NEW EMPLOYEE----");
        String id;
        while (true){
            id = Inputer.inputAndCheck("Employee ID(E + 3 digit): ", Acceptable.EP_ID_VALID).toUpperCase();
            if (searchById(id) != null){
                System.out.println("This EmployeeID already exists. Try another!");
            }else{
                break;
            }
        }
        String name = Inputer.inputAndCheck("Employee name: ",Acceptable.NAME_VALID);
        LocalDate DoB = LocalDate.parse(Inputer.inputAndCheck("Day of Birth (YYYY-MM-DD): ",Acceptable.BIRTHDATE_VALID));
        double salary = Double.parseDouble(Inputer.inputAndCheck("Salary: ", Acceptable.SALARY_VALID)); 
        String mCode;
        while (true){
            mCode = Inputer.getString("Department code: ").toUpperCase();
            if(departments.exists(mCode)) break;
            System.out.println("Department code does not exists. Try again.");
        }
        
        Employee e = new Employee(id, mCode, name, DoB, salary);
        this.add(e);
        changed = true;
        
        System.out.println("New Employee added!");
    }
    
    public void deleteEmployee(){
        System.out.println("----REMOVE EMPLOYEE----");
        String id = Inputer.inputAndCheck("Enter Employee ID to delete: ", Acceptable.EP_ID_VALID);
        Employee e = searchById(id);
        if( e == null){
            System.out.println("This employee does not exist!");
        }
        System.out.println("Employee details: ");
        System.out.println(headerLine());
        System.out.println(e);
        String confirm = Inputer.getString("Are you sure to delete this employee? (Y/N): ");
        if(confirm.equalsIgnoreCase("Y")){
            this.remove(e);
            changed = true;
            System.out.println("Delete successfully!");
        }else{
            System.out.println("Cancelled deletion!");
        }
    }
    
    public void updateEmployee(){
        System.out.println("----UPDATE EMPLOYEE----");
        String id = Inputer.inputAndCheck("Employee ID to update: ", Acceptable.EP_ID_VALID);
        Employee e = searchById(id);
        if (e == null){
            System.out.println("This Employee does not exists! Try another.");
            return;
        }
        System.out.println("Current Employee Info: ");
        System.out.println(headerLine());
        System.out.println(e);
        System.out.println("Enter new Info: ");
        
        String newName = Inputer.inputAndCheck("New Employee name: ", Acceptable.NAME_VALID);
        LocalDate newDoB = LocalDate.parse(Inputer.inputAndCheck("Day of Birth (YYYY-MM-DD): ",Acceptable.BIRTHDATE_VALID));
        double newSalary = Double.parseDouble(Inputer.inputAndCheck("Salary: ", Acceptable.SALARY_VALID));
        
        String confirm = Inputer.getString("Comfirm update? (Y/N): ");
        if(confirm.equalsIgnoreCase("N")){
            System.out.println("Update Cancelled!");
            return;
        }
        e.setName(newName);
        e.setDoB(newDoB);
        e.setSalary(newSalary);
        
        changed = true;
        System.out.println("Update Successfully!");
    }
    
    public void EmployeeByDepartment(){
        System.out.println("----FILTER EMPLOYEE BY DEPARTMENT----");
        String id = Inputer.getString("Enter Department code [D]: ").toUpperCase();
        
        List<Employee> result = new ArrayList<>();
        for (Employee e : this){
            if(e.getDepartmentId().toUpperCase().startsWith(id)){
                result.add(e);
            }
        }
        if(result.isEmpty()){
            System.out.println("No employees in this department.");
        }else{
            System.out.println("Employee under Department"+ id +";");
            System.out.println(headerLine());
            for(Employee e : result){
                System.out.println(e);
            }
        }
    }
    
    public void searchByName(){
        System.out.println("----SEARCH EMPLOYEE BY NAME----");
        String name = Inputer.getString("Enter Employee's name: ").toLowerCase();
        List<Employee> result = new ArrayList<>();
        for (Employee e : this){
            if(e.getName().toLowerCase().contains(name)){
                result.add(e);
            }
        }
        if(result.isEmpty()){
            System.out.println("This Emloyee does not exists.");
        }else{
            System.out.println("Employees details: ");
            System.out.println(headerLine());
            for(Employee e : result){
                System.out.println(e);
            }
        }
    }
    public void sortBySalary(){
        System.out.println("----SORT EMPLOYEE BY SALARY----");
        for (int i = 0; i < this.size(); i++){
            int min = i;
            for (int j = i + 1; j < this.size();j++){
                if (this.get(j).getSalary() < this.get(min).getSalary()){
                    min = j;
                }
            }
            Employee temp = this.get(i);
            this.set(i, this.get(min));
            this.set(min, temp);
        }
        for(Employee e : this){
            System.out.println(e);
        }
    }       
}
