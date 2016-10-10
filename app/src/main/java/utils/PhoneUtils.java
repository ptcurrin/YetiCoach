package utils;

/**
 * Created by Patrick on 9/26/2016.
 */

public class PhoneUtils {

    public static String formatPhoneNumber(String rawNumber) {

        StringBuilder rNumber = new StringBuilder("");



        char[] chars = rawNumber.toCharArray();
        for (int i = 0; i < rawNumber.length(); i++) {
            if (i == 0) {
                rNumber.append("(");
                rNumber.append(rawNumber.charAt(i));
            }
            else if (i == 3) {
                rNumber.append(")");
                rNumber.append(rawNumber.charAt(i));
            }
            else if (i == 6) {
                rNumber.append("-");
                rNumber.append(rawNumber.charAt(i));
            }
            else {
                rNumber.append(chars[i]);
            }
        }
        return rNumber.toString();
    }

}
