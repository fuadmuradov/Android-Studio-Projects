package com.fuadmuradov.javalearning;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args){

        System.out.println("hello java");
        System.out.println(5*2);
        String[] my_string = new String[3];
        my_string[0]="Fuad";
        my_string[1]="Murad";
        my_string[2]="Orxan";
        for(int i=0; i<3; i++){
           // System.out.println(my_string[i]);
        }

        HashMap<String, String> myhashmap=new HashMap<>();
        myhashmap.put("name", "Fuad");
        myhashmap.put("surname", "Muradov");
        System.out.println(myhashmap.get("name"));
        System.out.println(myhashmap.get("surname"));






    }
}
