package use_case.generate_static_map;

public class GSMInputData {
    private final String[] coordinates;
    private final int totalPins;
    private final int width;
    private final int height;

    public GSMInputData(String[] coordinates, int totalPins, int width, int height){
        this.coordinates = coordinates;
        this.totalPins = totalPins;
        this.width = width;
        this.height = height;
    }
    String[] getCoordinates(){return this.coordinates;}
    int getTotalPins(){return this.totalPins;}
    int getWidth(){return this.width;}
    int getHeight(){return this.height;}
}