package main;

import java.util.ArrayList;

public class State {
    private String label;
    private ArrayList<Transition> transitions;
    private int direction;
    private boolean isFinal;

    public State(String label) {
        this.label = label;
        this.direction = 1;
        this.transitions = new ArrayList<Transition>();
        this.isFinal = false;
    }
    
    public void addTransition(Character symbol, State next) {
        this.transitions.add(new Transition(symbol, next));
    }
    
    public State getNext(Character symbol){      
        for(int i = 0; i < transitions.size(); i++)
            if(transitions.get(i).getSymbol().equals(symbol))
                return transitions.get(i).getNext();
        
        return null;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }
    
    public void printTransitions(){
        for(int i = 0; i <  transitions.size(); i++){
            System.out.print("[" + this.label + ": ");
            System.out.print(transitions.get(i).getSymbol() + ", ");
            System.out.println(transitions.get(i).getNext().getLabel() + "]");
        }
    }
}
