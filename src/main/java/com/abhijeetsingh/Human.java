package com.abhijeetsingh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Human {

//    @Autowired
//    @Qualifier("organObjectOne")
    private Organ organ;

//    @Autowired
//    @Qualifier("jobObject")
    private Job job;

    public Human(){};

    public Human(Organ organ, Job job) {
        this.organ = organ;
        this.job = job;
    }


    @Autowired
    @Qualifier("organObjectOne")
    public void setOrgan(Organ organ) {
        this.organ = organ;
    }

    @Autowired
    public void setJob(Job job) {
        this.job = job;
    }

    public void isAlive() {
        organ.working();
        job.job();
    }

}
