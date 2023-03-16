package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email() {
    }

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character

        if(this.password.equals(oldPassword)) {
            //check new password
            if(newPassword.length() >= 8 && isValid(newPassword)) {
                this.password = newPassword;
            }
        }
    }
    boolean isValid(String password) {
        boolean upper = false, lower = false, digit = false, specialChar = false;
        for(int i=0; i<password.length(); i++) {
            int c = password.charAt(i);

            if(c >= 65 && c <= 90) {
                upper = true;
            }
            else if (c >= 97 && c <= 122) {
                lower = true;
            }
            else if (c >=48 && c <= 57) {
                digit = true;
            }
            else if (c == 64 || (c >= 33 && c <= 47)) {// @ = 64
                specialChar =true;
            }
        }

        return upper && lower && digit && specialChar;
    }
}
