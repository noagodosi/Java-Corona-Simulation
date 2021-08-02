package IO;

import java.util.ArrayList;
import java.util.List;

public class CareTakerLogFile {

    public List<Memento> statesList=new ArrayList<>();

    public void addMemento(Memento memento){
        statesList.add(memento);
    }
    public Memento getMemento(int index){
        return statesList.get(index);
    }
}
