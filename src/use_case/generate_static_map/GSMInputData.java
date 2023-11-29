package use_case.generate_static_map;

public class GSMInputData {
    private final String username;
    private final int totalPins;
    private final int width;
    private final int height;

    public GSMInputData(String username, int totalPins, int width, int height){
        this.username = username;
        this.totalPins = totalPins;
        this.width = width;
        this.height = height;
    }
    String getUsername(){return this.username;}
    int getTotalPins(){return this.totalPins;}
    int getWidth(){return this.width;}
    int getHeight(){return this.height;}
}
