package hackathon.com.sansad;

/**
 * Created by utk994 on 01-Nov-15.
 */


public class mp {
    public Integer id;
    public Integer age;
    public String attendance;
    public String constituency;
    public Integer debates;
    public String education_details;
    public String education_qualifications;
    public String elected;
    public String first_name;
    public String house;
    public String last_name;
    public String  in_office;
    public String mp_id;
    public String party;
    public Integer questions;
    public Integer private_bills;
    public String state;
    public String gender;
    public String score;







    public mp(){

    }
    public mp(Integer id, Integer age,String attendance,String constituency,Integer debates ,String education_details,
              String education_qualifications,String elected,
              String first_name,String gender,String house,String in_office,String last_name,String mp_id,
              String party,Integer private_bills,Integer questions,String state,String score ) {
        this.id = id;
        this.gender=gender;
        this.age = age;
        this.attendance = attendance;
        this.constituency = constituency;
        this.debates = debates;
        this.education_details = education_details;
        this.education_qualifications = education_qualifications;
        this.first_name = first_name;
        this.last_name = last_name;
        this.elected = elected;
        this.in_office = in_office;
        this.mp_id = mp_id;
        this.house=house;
        this.party=party;
        this.questions=questions;
        this.private_bills=private_bills;
        this.state=state;
        this.score=score;
    }
    Integer getId(){
        return this.id;
    }

    String getScore(){return this.score;}


    Integer getPrivate_bills(){
        return this.private_bills;
    }
    Integer getAge(){
        return this.age;
    }
   String getAttendance(){
        return this.attendance;
    }
    Integer getDebates(){
        return this.debates;
    }
    Integer getQuestions(){
        return this.questions;
    }
    String getFirst_name() {
        return this.first_name;
    }
    String getConstituency() {
        return this.constituency;
    }
    String getgender(){
        return  this.gender;
    }

    String getLast_name() {
        return this.last_name;
    }

    String getParty() {
        return this.party;
    }

    String getHouse() {
        return this.house;
    }

    String getEducation_details(){
        return this.education_details;
    }

    String geteducation_qualifications() {
        return this.education_qualifications;
    }

    String getState() {
        return this.state;
    }
    String getElected() {
        return this.elected;
    }
  String getIn_office(){
        return this.in_office;
    }
    String getMp_id() {
        return this.mp_id;
    }

    void setId(Integer id){
        this.id=id;
    }
    void setAge(Integer age){
        this.age=age;
    }
    void setAttendance(String attendance){
        this.attendance=attendance;
    }
    void setPrivate_bills(Integer private_bills){
        this.private_bills=private_bills;
    }
    void setDebates(Integer debates){
        this.debates=debates;
    }
    void setFirst_name(String first_name){
        this.first_name=first_name;
    }

    void setLast_name(String last_name){
        this.first_name=last_name;
    }

    void setParty(String party){
        this.first_name=party;
    }

    void setEducation_details(String education_details){
        this.education_details=education_details;
    }

    void seteducation_qualifications(String education_qualifications){
        this.education_qualifications=education_qualifications;
    }
    void setHouse(String house){
        this.house=house;
    }
    void setElected(String elected) {
        this.elected = elected;
    }
    void setIn_office(String  in_office) {
        this.in_office=in_office;
    }
    void setConstituency(String constituency) {
        this.constituency = constituency;
    }
    void setMp_id(String mp_id){
        this.mp_id=mp_id;
    }
    void setQuestions(Integer questions){
        this.questions=questions;
    }
    void setState(String state) {
        this.state=state;
    }
    void setgender(String gender){
        this.gender=gender;
    }
  void setScore(String score){
        this.score=score;
    }
}