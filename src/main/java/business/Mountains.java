package business;

/**
 *
 * @author LAPTOP
 */
import model.Mountain;
import java.io.*;
import java.util.*;

public class Mountains extends ArrayList<Mountain>{
    public Mountains(String path){
        load(path);
    }
    private void load(String path){
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = br.readLine())!= null){
                if (line.trim().isEmpty()) continue;
                String[] t = line.split(",");
                if(t.length >= 4){
                    this.add(new Mountain(t[0].trim(),t[1].trim(),t[2].trim(),t[3].trim()));
                }
            }
        }catch (Exception e){
            System.out.println("Cannot load mountains from" + path);
        }
    }
    public boolean exists(String code){
        return this.stream().anyMatch(m -> m.getCode().equalsIgnoreCase(code));
    }
    public String getNameByCode(String code){
        for (Mountain m : this){
            if (m.getCode().equalsIgnoreCase(code)){
                return m.getName();
            }
        }
        return "Unknown";
    }
}
