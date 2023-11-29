package use_case.signup;

/*

CommonUser(username, password, joinedEvents, createdEvents, age, sex, userid,
                contact, location);
 */

public class SignupInputData {
    final private String username;
    final private String realName;
    final private String password;
    final private String repeatPassword;
    final private String age;
    final private String sex;

    final private String contact;

    public SignupInputData(String username, String realName, String password, String repeatPassword, String age, String sex, String contact){
        //Strings are inputted in the textbox.
        this.username = username;
        this.realName = realName;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.age = age;
        this.sex = sex;
        this.contact = contact;
    }

    String getUsername(){return username;}

    String getRealName(){return realName;}

    String getPassword(){return password;}

    String getRepeatPassword(){return repeatPassword;}

    Integer getAge() throws NumberFormatException{
        //In case the user inputs an invalid input for age.
        try {
            return Integer.parseInt(age);
        } catch (Exception NumberFormatException) {
            return -1;
        }
    }
    String getAGE(){return age;}

    String getSex(){return sex;}

    String getContact(){return contact;}

    boolean inputsEmpty(){
        return (username != "") && (realName != "") && (password != "") &&
                (repeatPassword != "") && (age != "") && (sex != "");
    }
}
