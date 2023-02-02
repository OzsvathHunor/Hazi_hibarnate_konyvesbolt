package org.hazi;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        View v = new View();
        try (
                Scanner sc = new Scanner(System.in);
                Controller c = new Controller()
        ) {
            v.controller = c;
            v.mainMenu(sc);
        }
    }
}