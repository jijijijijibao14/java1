package tools;

/**
 *
 * @author LAPTOP
 */
public interface Acceptable {
<<<<<<< HEAD
    String EP_ID_VALID = "^[Ee]\\d{3}$";
    String DP_ID_VALID = "^[Dd]\\d{2}$";
    String NAME_VALID = "^.{2,20}$";
    String LOCAL_VALID = "(?i)^floor\\s?\\d{1,2}$";
    String BIRTHDATE_VALID = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    public static final String SALARY_VALID = "^\\d+(\\.\\d+)?$";
    
    static boolean isValid(String data, String pattern) {
        return data.matches(pattern);
    }
}
=======
    String STU_ID_VALID = "^[CcDdHhSsQq][Ee]\\d{6}$";
    String NAME_VALID = "^.{2,20}$";
    String PHONE_VALID = "^\\d{10}$";
    String EMAIL_VALID = "^[\\w.-]+@[\\w.-]+\\.\\w+$";

    // Viettel / VNPT để giảm 35%
    String VIETTEL_VALID = "^(086|096|097|098|03)\\d{7}$";
    String VNPT_VALID = "^(081|082|083|084|085|088|091|094)\\d{7}$";

    static boolean isValid(String data, String pattern) {
        return data.matches(pattern);
    }
}  
>>>>>>> e47424b4bf77ba2cc84c22eee1baff5136ff4cd1
