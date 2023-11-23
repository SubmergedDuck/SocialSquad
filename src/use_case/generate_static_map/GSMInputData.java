package use_case.generate_static_map;

public class GSMInputData {
    private final String username;
    private final int totalPins;
    private final float width;
    private final float height;

    public GSMInputData(String username, int totalPins, float width, float height){
        this.username = username;
        this.totalPins = totalPins;
        this.width = width;
        this.height = height;
    }
    String getUsername(){return this.username;}
    int getTotalPins(){return this.totalPins;}
    float getWidth(){return this.width;}
    float getHeight(){return this.height;}
}
