package com.example.ecommerce.Models.Utilities;

public class Validator {

    public static boolean isEmail(String phrase){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (phrase.matches(emailPattern) && phrase.length() > 0){
            return true;
        }else{
            return false;
        }
    }


    public static String isValidPassword(String phrase){
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,}";
        if(phrase.length()>8){
            if(phrase.matches(pattern)){
                return "okay";
            }else {
                return "Invalid Password";
            }
        }else {
            return "Length must be more than 8 characters";
        }
    }


}
