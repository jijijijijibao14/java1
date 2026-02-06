package tools;

/**
 *
 * @author LAPTOP
 */
public interface Acceptable {
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