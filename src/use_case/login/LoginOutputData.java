package use_case.login;

public class LoginOutputData{
    private final String username;

    private final String[] userCoordinates;

    public LoginOutputData(String username, String[] userCoordinates){
        this.userCoordinates = userCoordinates;
        this.username=username;
    }
    public String getUsername(){
        return username;
    }

    public String[] getUserCoordinates(){return userCoordinates;}

}
