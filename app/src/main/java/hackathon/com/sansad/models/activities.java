package hackathon.com.sansad.models;

/**
 * Created by utk994 on 28-Feb-16.
 */
public class activities {
    public String text;
    public Integer type;


    public activities(String text1,Integer type1 ){

        text=text1;
        type=type1;

    }
    public String getText1(){
        return text;
    }
    public void setText1(String text)
    {
        this.text=text;
    }

    public void setType1(Integer type)
    {
        this.type=type;
    }

    public Integer getType1(){
        return type;
    }
    

}


