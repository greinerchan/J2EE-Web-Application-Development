package xc4.hw4.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("email")
public class User {
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public String getPassword()        { return password; }
    public String getFirstName()        { return firstName; }
    public String getLastName()			{ return lastName;}
    public String getEmail()            { return email;}

    public void setPassword(String s)  { password = s;    }
    public void setFirstName(String s)  { firstName = s;    }
    public void setEmail(String s)     { email = s;    }
    public void setLastName(String s)  { lastName = s;    }
}
