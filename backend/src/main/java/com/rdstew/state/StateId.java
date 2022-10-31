package com.rdstew.state;

import org.apache.commons.lang3.RandomStringUtils;

public class StateId {
    private String id;
    public StateId(int length, boolean use_letters, boolean use_numbers){
        this.id = RandomStringUtils.random(length, use_letters, use_numbers);
    }

    public String toString(){
        return this.id;
    }
}
