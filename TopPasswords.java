/** \file   TopPasswords.java
* \brief     Class that checks if a password is in the list of most common passwords.
* \author    Loucas Mouttotou
* \date      2024-2024
* \bug       No bugs so far
* \copyright University of Nicosia.
*/

import java.io.*;

public class TopPasswords {
    private final String filePath;

    //Constructor.
    public TopPasswords(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Function <code>isCommon<code> Checks if the password exists in the topPasswords file.
     * @param password       The password to check.
     * @return True          If the password is found in the file, otherwise false.
     * @throws IOException   If there is an issue reading the file.
     */
    public boolean isCommon(String password) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}



