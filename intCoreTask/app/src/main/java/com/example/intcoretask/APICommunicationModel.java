package com.example.intcoretask;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;
public class APICommunicationModel
{
    private String linkSignIn;
    private String linkSignUp;
    private String linkGetProfile;
    private String api_token;
    private String phone;

    public APICommunicationModel()
    {
        linkSignIn = "https://internship-api-v0.7.intcore.net/api/v1/user/auth/signin";
        linkSignUp = "https://internship-api-v0.7.intcore.net/api/v1/user/auth/signup";
        linkGetProfile = "https://internship-api-v0.7.intcore.net/api/v1/user/auth/get-profile";
        api_token = "";
    }

    public ArrayList<String> apiSignUp(String name,String phone, String password, String email) throws JSONException, ProtocolException, MalformedURLException, IOException
    {
        String link = linkSignUp +"?name=" + name + "&phone=" + phone + "&password=" + password + "&email=" + email;
        URL obj = new URL(link);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("x-www-form-urlencoded", "application/json");
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        ArrayList<String> errors = new ArrayList<String>();
        int status = con.getResponseCode();
        String statusMessage = con.getResponseMessage();
        if (status > 299) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                JSONObject jsonobject = new JSONObject(response.toString());
                JSONArray arr = jsonobject.getJSONArray("errors");              //Getting the list of messages out to be accessible in an array.
                JSONObject jsonobject2 = arr.getJSONObject(0);
                String error = jsonobject2.getString("message");
                errors.add(error);
            }
            con.disconnect();
        }
        return errors;
    }
    public String apiSignIn(String name, String password) throws MalformedURLException, ProtocolException, IOException, JSONException
    {
        String link = linkSignIn +"?name=" + name + "&password=" + password;
        URL obj = new URL(link);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        String status = con.getResponseMessage();
        if (status!="OK") {
            ArrayList<String> errors = new ArrayList<String>();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject jsonobject = new JSONObject(response.toString());
            JSONArray arr = jsonobject.getJSONArray("errors");              //Getting the list of messages out to be accessible in an array.
            JSONObject jsonobject2 = arr.getJSONObject(0);
            String error = jsonobject2.getString("message");

            con.disconnect();
            return error;
        } else {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            JSONObject jsonobject = new JSONObject(response.toString());
            api_token = jsonobject.getJSONObject("user").getString("api_token");

            con.disconnect();
            return "";
        }


    }
    public void setapi_token(String s)
    {
        api_token = s;
    }
    public String[] apiGetProfile() throws MalformedURLException, ProtocolException, JSONException, IOException
    {
        String[] information = new String[3];
        String link = linkGetProfile + "?api_token=" + api_token;
        URL obj = new URL(link);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject jsonobject = new JSONObject(response.toString());
        information[0] = jsonobject.getJSONObject("user").getString("name");
        information[1] = jsonobject.getJSONObject("user").getString("email");
        information[2] = jsonobject.getJSONObject("user").getString("phone");

        con.disconnect();
        return information;
    }


}