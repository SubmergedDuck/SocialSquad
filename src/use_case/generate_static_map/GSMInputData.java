package use_case.generate_static_map;

public class GSMInputData {
    private final String username;
    private final int totalPins;

    public GSMInputData(String username, int totalPins){
        this.username = username;
        this.totalPins = totalPins;
    }
    String getUsername(){return this.username;}
    int getTotalPins(){return this.totalPins;}
}
