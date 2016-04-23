package hackathon.com.sansad.models.mp;

/**
 * Created by utk994 on 23-Apr-16.
 */
public class MpData {
    private String debates;

    private String constituency;

    private String score;

    private String state;

    private String questions;

    private String house;

    private String party;

    private String mp_id;

    private String education_qualification;

    private String id;

    private String private_bills;

    private String first_name;

    private String age;

    private String elected;

    private String last_name;

    private String gender;

    private String attendance_percentage;

    private String education_details;

    private String in_office;

    private String imgurl;

    public String getDebates ()
    {
        return debates;
    }

    public void setDebates (String debates)
    {
        this.debates = debates;
    }

    public String getConstituency ()
    {
        return constituency;
    }

    public void setConstituency (String constituency)
    {
        this.constituency = constituency;
    }

    public String getScore ()
    {
        return score;
    }

    public void setScore (String score)
    {
        this.score = score;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getQuestions ()
    {
        return questions;
    }

    public void setQuestions (String questions)
    {
        this.questions = questions;
    }

    public String getHouse ()
    {
        return house;
    }

    public void setHouse (String house)
    {
        this.house = house;
    }

    public String getParty ()
    {
        return party;
    }

    public void setParty (String party)
    {
        this.party = party;
    }

    public String getMp_id ()
    {
        return mp_id;
    }

    public void setMp_id (String mp_id)
    {
        this.mp_id = mp_id;
    }

    public String getEducation_qualification ()
    {
        return education_qualification;
    }

    public void setEducation_qualification (String education_qualification)
    {
        this.education_qualification = education_qualification;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPrivate_bills ()
    {
        return private_bills;
    }

    public void setPrivate_bills (String private_bills)
    {
        this.private_bills = private_bills;
    }

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    public String getAge ()
    {
        return age;
    }

    public void setAge (String age)
    {
        this.age = age;
    }

    public String getElected ()
    {
        return elected;
    }

    public void setElected (String elected)
    {
        this.elected = elected;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }

    public String getAttendance_percentage ()
    {
        return attendance_percentage;
    }

    public void setAttendance_percentage (String attendance_percentage)
    {
        this.attendance_percentage = attendance_percentage;
    }

    public String getEducation_details ()
    {
        return education_details;
    }

    public void setEducation_details (String education_details)
    {
        this.education_details = education_details;
    }

    public String getIn_office ()
    {
        return in_office;
    }

    public void setIn_office (String in_office)
    {
        this.in_office = in_office;
    }

    public String getImgurl ()
    {
        return imgurl;
    }

    public void setImgurl (String imgurl)
    {
        this.imgurl = imgurl;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [debates = "+debates+", constituency = "+constituency+", score = "+score+", state = "+state+", questions = "+questions+", house = "+house+", party = "+party+", mp_id = "+mp_id+", education_qualification = "+education_qualification+", id = "+id+", private_bills = "+private_bills+", first_name = "+first_name+", age = "+age+", elected = "+elected+", last_name = "+last_name+", gender = "+gender+", attendance_percentage = "+attendance_percentage+", education_details = "+education_details+", in_office = "+in_office+", imgurl = "+imgurl+"]";
    }
}
