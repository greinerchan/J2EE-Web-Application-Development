package edu.cmu.cs.webapp.addrbook.formbean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.Field;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;
import org.formbeanfactory.Optional;

public class EntryForm extends FormBean {
    private String id;
    
    private String additional;
    private String address;
    private String birthday;
    private String cellPhone;
    private String city;
    private String country;
    private String digest;
    private String email;
    private String fax;
    private String firstNames;
    private String homePhone;
    private String lastName;
    private String receivedCards;
    private String sentCards;
    private String spouseBirthday;
    private String spouseCell;
    private String spouseEmail;
    private String spouseFirst;
    private String spouseLast;
    private String spouseWork;
    private String state;
    private String workPhone;
    private String zip;
    
    private java.sql.Date birthdayDate;
    private java.sql.Date spouseBirthdayDate;

    public EntryForm() {
        super();
    }

    public EntryForm(HttpServletRequest request) {
        super(request);
    }
    
    public String getId()                { return id;             }
    public String getDigest()            { return digest;         }
    
    public String getAdditional()        { return additional;     }
    public String getAddress()           { return address;        }
    public String getBirthday()          { return birthday;       }
    public String getCellPhone()         { return cellPhone;      }
    public String getCity()              { return city;           }
    public String getCountry()           { return country;        }
    public String getEmail()             { return email;          }
    public String getFax()               { return fax;            }
    public String getFirstNames()        { return firstNames;     }
    public String getHomePhone()         { return homePhone;      }
    public String getLastName()          { return lastName;       }
    public String getReceivedCards()     { return receivedCards;  }
    public String getSentCards()         { return sentCards;      }
    public String getSpouseBirthday()    { return spouseBirthday; }
    public String getSpouseCell()        { return spouseCell;     }
    public String getSpouseEmail()       { return spouseEmail;    }
    public String getSpouseFirst()       { return spouseFirst;    }
    public String getSpouseLast()        { return spouseLast;     }
    public String getSpouseWork()        { return spouseWork;     }
    public String getState()             { return state;          }
    public String getWorkPhone()         { return workPhone;      }
    public String getZip()               { return zip;            }
    
    public int getIdNum() {
        return Integer.parseInt(id);
    }

    public java.sql.Date getBirthdayAsDate()       { return birthdayDate;       }
    public java.sql.Date getSpouseBirthdayAsDate() { return spouseBirthdayDate; }

    @InputType("hidden")
    public void setId(String s)             { id             = s; }
    @Optional
    @InputType("hidden")
    public void setDigest(String s)         { digest         = s; }

    // Required fields
    public void setFirstNames(String s)     { firstNames     = sanitize(s).trim(); }
    public void setLastName(String s)       { lastName       = sanitize(s).trim(); }

    @Optional
    public void setAdditional(String s)     { additional     = sanitize(s).trim(); }
    @Optional
    public void setAddress(String s)        { address        = sanitize(s).trim(); }
    @Optional
    public void setBirthday(String s)       { birthday       = sanitize(s).trim(); }
    @Optional
    public void setCellPhone(String s)      { cellPhone      = sanitize(s).trim(); }
    @Optional
    public void setCity(String s)           { city           = sanitize(s).trim(); }
    @Optional
    public void setCountry(String s)        { country        = sanitize(s).trim(); }
    @Optional
    public void setEmail(String s)          { email          = sanitize(s).trim(); }
    @Optional
    public void setFax(String s)            { fax            = sanitize(s).trim(); }
    @Optional
    public void setHomePhone(String s)      { homePhone      = sanitize(s).trim(); }
    @Optional
    public void setReceivedCards(String s)  { receivedCards  = sanitize(s).trim(); }
    @Optional
    public void setSentCards(String s)      { sentCards      = sanitize(s).trim(); }
    @Optional
    public void setSpouseBirthday(String s) { spouseBirthday = sanitize(s).trim(); }
    @Optional
    public void setSpouseCell(String s)     { spouseCell     = sanitize(s).trim(); }
    @Optional
    public void setSpouseEmail(String s)    { spouseEmail    = sanitize(s).trim(); }
    @Optional
    public void setSpouseFirst(String s)    { spouseFirst    = sanitize(s).trim(); }
    @Optional
    public void setSpouseLast(String s)     { spouseLast     = sanitize(s).trim(); }
    @Optional
    public void setSpouseWork(String s)     { spouseWork     = sanitize(s).trim(); }
    @Optional
    public void setState(String s)          { state          = sanitize(s).trim(); }
    @Optional
    public void setWorkPhone(String s)      { workPhone      = sanitize(s).trim(); }
    @Optional
    public void setZip(String s)            { zip            = sanitize(s).trim(); }

	public void validate() {
	    super.validate();
	    
	    for (Field f : getAllFields()) {
	        if (f.getValue() == null) {
	            addFieldError(f.getName(), "Field not sent");
	        }
	    }
	    
        if (spouseLast != null && spouseLast.length() > 0 &&
                (spouseFirst == null || spouseFirst.length() == 0)) {
		    addFieldError("spouseFirst", "Field needed");
		}

		if (spouseFirst != null && spouseFirst.length() > 0 &&
		        (spouseLast == null || spouseLast.length() == 0)) {
            addFieldError("spouseLast", "Field needed");
		}
		
		try {
		    if (id != null && id.length() > 0) {
		        int idNum = Integer.parseInt(id);
		        if (idNum > 0 && digest.length() == 0) {
		            addFormError("Missing hidden digest field.");
		        }
		    }
		} catch (NumberFormatException e) {
	          addFormError("Id is not an integer");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		
		try {
			if (birthday != null && birthday.length() > 0) {
				Date d = sdf.parse(birthday);
				birthdayDate = new java.sql.Date(d.getTime());
			}
		} catch (ParseException e) {
			addFieldError("birthday", "Invalid (use mm/dd/yyyy)");
		}
		
		try {
			if (spouseBirthday != null && spouseBirthday.length() > 0) {
				Date d = sdf.parse(spouseBirthday);
				spouseBirthdayDate = new java.sql.Date(d.getTime());
			}
		} catch (ParseException e) {
            addFieldError("spouseBirthday", "Invalid (use mm/dd/yyyy)");
		}
	}
}
