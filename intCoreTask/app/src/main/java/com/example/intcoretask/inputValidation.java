package com.example.intcoretask;

import java.util.ArrayList;
public class inputValidation {
    public String check_phone(String phone)
    {
        ArrayList<String> al = new ArrayList<String>();
        al.add("0100");
        al.add("0122");
        al.add("0109");
        al.add("0106");
        al.add("0111");

        String message = "";
        if(phone.length()==11)
        {
            if(al.contains(phone.substring(0, 4)))
            {
                message = "";
            }
            else
            {
                message = "phone must start with 0100,0122,0109,0106,0111";
            }
        }
        else
        {
            message = "Phone must be 11 numbers";
        }
        return message;
    }

    public String check_name(String name)
    {
        String message= "";
        if(name.length() < 3)
            message = "Name length >= 3";

        return message;
    }

    public String check_email(String email)
    {
        String message = "";
        if(email.substring(email.length()-4, email.length()-1).toLowerCase() == "com" && email.contains("@"))
        {
            message = "";
        }
        else
        {
            message = "Mail must be '@'" + "_.com";
        }
        return message;
    }

}
