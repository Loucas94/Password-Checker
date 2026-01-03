/** \file   PasswordCheck.java
* \brief     Class that checks the complexity of a password.
* \author    Loucas Mouttotou
* \date      2024-2024
* \bug       No bugs so far
* \copyright University of Nicosia.
*/

import java.util.regex.Pattern;

class PasswordCheck {
	//Password requirements.
    private final int minLength = 12;
    private final Pattern uppercase = Pattern.compile("[A-Z]");
    private final Pattern lowercase = Pattern.compile("[a-z]");
    private final Pattern digits = Pattern.compile("[0-9]");
    private final Pattern specialCharacters = Pattern.compile("[^a-zA-Z0-9]");

    /**
     * Function <code>checkPasswordStrength<code> Checks the strength of a password and provides feedback.
     * <BR>
     * @param password   The password to check.
     * @return           Appropriate message containing the password strength and feedback.
     */
    public String checkPasswordStrength(String password) {
        StringBuilder feedback = new StringBuilder();

        if (password.length() < minLength) {
            feedback.append("Password should be at least ").append(minLength).append(" characters long.\n");
        }

        if (!uppercase.matcher(password).find()) {
            feedback.append("Password should contain at least one uppercase letter.\n");
        }

        if (!lowercase.matcher(password).find()) {
            feedback.append("Password should contain at least one lowercase letter.\n");
        }

        if (!digits.matcher(password).find()) {
            feedback.append("Password should contain at least one digit.\n");
        }

        if (!specialCharacters.matcher(password).find()) {
            feedback.append("Password should contain at least one special character.\n");
        }

        String strength;
        if (feedback.length() == 0) {
            strength = "Strong";
        }
        else {
            strength = "Weak";
        }

        return "Password Complexity: " + strength + "\n" + feedback.toString();
        

    }
}
