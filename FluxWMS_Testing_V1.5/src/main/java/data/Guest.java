package data;

import java.util.List;
import java.util.Scanner;

import static data.UserRepository.*;
import static data.UserRepository.getUserEmployeeObject;


public class Guest extends User {


    public Guest(String userName) {
        super(userName);
    }

    @Override
    public boolean authenticate() {
        return false;
    }

    @Override
    public void greet() {
        super.greet();
    }

    @Override
    public void bye(List<String> SESSION_ACTIONS) {
        System.out.printf("\nThank you for your visit, %s!\n", this.name);    }

    public void changeUser(){
        super.changeUser();
    }
}
