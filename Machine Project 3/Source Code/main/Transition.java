package main;

public class Transition {
    private Character symbol;
    private State next;

    public Transition(Character symbol, State next) {
        this.symbol = symbol;
        this.next = next;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public State getNext() {
        return next;
    }

    public void setNext(State next) {
        this.next = next;
    }
}
