/** \file   randomPassword.java
* \brief     Class that generates a random password.
* \author    Loucas Mouttotou
* \date      2024-2024
* \bug       No bugs so far
* \copyright University of Nicosia.
*/

import java.io.IOException;
import java.util.Random;

public class randomPassword {
    private final TopPasswords common;

    //Constructor for randomPassword.
    public randomPassword(TopPasswords common) {
        this.common = common;
    }

    /**
     * Function <code>generatePassword<code> Generates a random password based on the given length and requirements, as asked in (b).
     * <BR>
     * @param len  The desired length of the password.
     * @return The generated password as a String.
     */
    public String generatePassword(int len) {
    	//Password requirements.
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String num = "0123456789";
        String specialChars = "<>,.?/}]{[]+=-!~@#$%^&*()";
        String combination = upper + lower + num + specialChars;

        //Makes sure length is at least 12.
        if (len < 12) {
            System.out.println("Password length must be at least 12");
            len = 12;
        }

        String password;
        Random random = new Random();
        do {
            char[] passwordArray = new char[len];

            //At least one character from each requirement category.
            passwordArray[0] = upper.charAt(random.nextInt(upper.length()));
            passwordArray[1] = lower.charAt(random.nextInt(lower.length()));
            passwordArray[2] = num.charAt(random.nextInt(num.length()));
            passwordArray[3] = specialChars.charAt(random.nextInt(specialChars.length()));

            //Fill the remaining positions with random characters from the combination.
            for (int i = 4; i < len; i++) {
                passwordArray[i] = combination.charAt(random.nextInt(combination.length()));
            }

            //Shuffle the password array for true randomness.
            for (int i = 0; i < passwordArray.length; i++) {
                int randomIndex = random.nextInt(passwordArray.length);
                char temp = passwordArray[i];
                passwordArray[i] = passwordArray[randomIndex];
                passwordArray[randomIndex] = temp;
            }

            password = new String(passwordArray);
        } while (isCommon(password));

        return password;
    }

    /**
     * Function <code>isCommon<code> Checks if the generated password is Common.
     * <BR>
     * @param password  The password to check.
     * @return          True if the password is Common, otherwise false.
     */
    private boolean isCommon(String password) {
        try {
            return common.isCommon(password);
        } catch (IOException e) {
            System.out.println("Error checking password against blacklist: " + e.getMessage());
            return false;
        }
    }
}
