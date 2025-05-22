package org.my.springstart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceB {
    @Autowired
    private ServiceA serviceA;

    public void getById(){
        System.out.println("ServiceB getById");
    }

    public void test(){
        serviceA.add();
    }
}
