package interface_adapter.generate_static_map;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GenerateStaticMapViewModel extends ViewModel {
    private GenerateStaticMapState state = new GenerateStaticMapState();

    private final PropertyChangeSupport observable = new PropertyChangeSupport(this);

    public GenerateStaticMapViewModel(){
        super("generate static map");
    }

    public void setState(GenerateStaticMapState state){
        this.state = state;
    }

    public GenerateStaticMapState getState(){return state;}
    @Override
    public void firePropertyChanged() {
        observable.firePropertyChange("generate static map",null,this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
