package main;

import java.util.ArrayList;

public class MP03 {
    private String label;
    private ArrayList<State> states;
    private State start;
    
    public MP03(String label) {
        this.label = label;
        this.states = new ArrayList<State>();
    }
    
    public void addState(State state){
        states.add(state);
    }
    
    public void setStart(State start){
        this.start = start;
    }
    
    public State getState(String label){
        for(int i = 0; i < states.size(); i++)
            if(states.get(i).getLabel().equals(label))
                return states.get(i);
        
        return null;
    }
    
    public boolean process(String input){
        State currState = start;
        String inputStr = "#" + input + "#";
        int index = 0;
        boolean isDone = false;
        
        //System.out.println(start.getLabel());
        //start.printTransitions();
        
        while(!isDone){
            State nextState = null;
            if(currState.getDirection() == 1){
                //System.out.println("move right");
                if(index == inputStr.length()-1)
                    inputStr = inputStr + "#";
                
                nextState = currState.getNext(inputStr.charAt(index + 1));
                
                if(nextState != null){
                    //System.out.println(currState.getLabel());
                    //nextState.printTransitions();
                    currState = nextState;
                    index += 1;
                } else {
                    isDone = true;
                }
            } else {
                //System.out.println("move left");
                if(index == 0){
                    inputStr = "#" + inputStr;
                    index = 1;
                }
                
                nextState = currState.getNext(inputStr.charAt(index - 1));
               
                if(nextState != null){
                    //System.out.println(currState.getLabel());
                    //nextState.printTransitions();
                    currState = nextState;
                    index -= 1;
                } else {
                    isDone = true;
                }
            }
        }        
        return currState.getIsFinal();
    }
    
    public void printStates(){
        for(int i = 0; i < states.size(); i++){
            System.out.println("State: " + states.get(i).getLabel());
            states.get(i).printTransitions();
        }
    }
}
