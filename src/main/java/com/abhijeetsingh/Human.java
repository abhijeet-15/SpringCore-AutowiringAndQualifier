package com.abhijeetsingh;

public class Human {

    private Organ organ;

    public Human(){};

    public Human(Organ organ) {
        this.organ = organ;
    }

    public void setOrgan(Organ organ) {
        this.organ = organ;
    }

    public void isAlive() {
        organ.working();
    }

}
