package com.example.intcoretask;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

public class User
{
    public static APICommunicationModel publicObject;

    private String name;
    private String password;
    private String phone;
    private String email;

    public void set_name(String n)
    {
        name = n;
    }
    public void set_password(String p)
    {
        password = p;
    }
    public void set_phone(String pn)
    {
        phone = pn;
    }
    public void set_email(String e)
    {
        email = e;
    }

    public String get_name()
    {
        return name;
    }
    public String get_password()
    {
        return password;
    }
    public String get_phoneNumber()
    {
        return phone;
    }
    public String get_email()
    {
        return email;
    }

    public void getProfile()
    {
        String[] information= null;
        try {
            if(publicObject == null)
                publicObject = new APICommunicationModel();

            information = publicObject.apiGetProfile();
        } catch (ProtocolException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

        name = information[0];
        phone = information[1];
        password =information[2];
        email = information[3];
    }

    public String signIn()
    {
        if(publicObject == null)
            publicObject = new APICommunicationModel();

        String message = "";
        try {
            message = publicObject.apiSignIn(name, password);
        } catch (ProtocolException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(message.length()==0)
            return "Signing in..";
        else
            return message;

    }

    public ArrayList<String> signUp()
    {
        if(publicObject == null)
            publicObject = new APICommunicationModel();
        ArrayList<String> al = null;
        try {
            al = publicObject.apiSignUp(name, password, phone, email);
        } catch (JSONException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

        return al;
    }
}