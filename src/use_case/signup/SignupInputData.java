package use_case.signup;

public class SignupInputData {
    final private String username;
    final private String realName;

    final private String password;

    final private String repeatPassword;

    final private int age;

    final private char sex;

    public SignupInputData(String username, String realName, String password, String repeatPassword, int age, char sex){
        this.username = username;
        this.realName = realName;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.age = age;
        this.sex = sex;
    }

}
