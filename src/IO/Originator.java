package IO;

public class Originator {

    private String state;

    public void setState(String state){
        this.state=state;
    }

    public String getState(){
        return this.state;
    }

    public Memento createMemento(){
        return new Memento(state);
    }

    public void setMemento(Memento memento){
        this.state=memento.getState();
    }
}
