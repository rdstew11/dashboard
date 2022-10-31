package com.rdstew.state;

public class StateIdBuilder {
    private int length;
    private boolean use_numbers;
    private boolean use_letters;

    public StateId buildStateId(){
        return new StateId(length, use_letters, use_numbers);
    }

    public StateIdBuilder length(int length){
        this.length = length;
        return this;
    }

    public StateIdBuilder use_letters(boolean val){
        this.use_letters = val;
        return this;
    }

    public StateIdBuilder use_numbers(boolean val){
        this.use_numbers = val;
        return this;
    }
}
