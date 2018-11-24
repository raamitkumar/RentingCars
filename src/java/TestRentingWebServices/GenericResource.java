/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestRentingWebServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import net.sf.json.JSONObject;

/**
 * REST Web Service
 *
 * @author 1792937
 */
@Path("application")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    Statement stm;
    ResultSet rs;
    int number;

    Connectionclass conclass = new Connectionclass();

    /**
     * Retrieves representation of an instance of
     * TestRentingWebServices.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("Login&{username}&{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("username") String uname, @PathParam("password") String pass) {

        stm = conclass.createConnection();
        JSONObject singledata = new JSONObject();
        try {
            System.out.println("select * from USERS WHERE username=" + uname+" and userpassword="+pass);
            ResultSet rs = stm.executeQuery("select * from USERS WHERE username=" + uname+" and userpassword="+pass);

            String fName, lName, email, contactnumber, userpassword, dateOfbirth, address;

            int user_Id;

           
            
                while (rs.next()) {
                    System.out.println("yaaehhhhhhhhhhhhhhhhhhhhhhn........................................");
                    fName = rs.getString("FIRSTNAME");
                    lName = rs.getString("LASTNAME");
                    email = rs.getString("EMAIL");
                    contactnumber = rs.getString("PHONENUMBER");
                    userpassword = rs.getString("USERPASSWORD");
                    dateOfbirth = rs.getString("DATEOFBIRTH");
                    address = rs.getString("ADDRESS");
                    System.out.println("username is " + fName);

                    singledata.accumulate("Status", "OK");
                    singledata.accumulate("FIRSTNAME", fName);
                    singledata.accumulate("LNAEM", lName);
                    singledata.accumulate("Phone", contactnumber);
                    singledata.accumulate("Active", true);
                    singledata.accumulate("email", email);

                }
           

            rs.close();
            stm.close();

        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return singledata.toString();
    }

}
