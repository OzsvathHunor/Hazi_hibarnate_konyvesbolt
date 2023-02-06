package org.hazi;

import java.sql.Date;
import java.util.Scanner;

public class View {

    Controller controller;
    void mainMenu(Scanner sc) {
        String option = "ghyt";
        do {
            switch (option) {
//                case "i" -> {
//                    controller.initAll();
//                }
                case "b" -> {
                    String optionb = "ghyt";
                    do {
                        switch (optionb) {
                            case "lb" -> {
                                controller.listBook();
                            }
                            case "ba" -> {
                                System.out.print("Add meg a konyv cimet: ");
                                String title = sc.nextLine();
                                System.out.print("Add meg a konyv ISBN-jet: ");
                                String isbn = sc.nextLine();
                                System.out.print("Add meg a konyv kiadasanak idejet (ev-honap-nap): ");
                                Date dop = Date.valueOf(sc.nextLine());
                                System.out.print("Add meg a konyv irojat: ");
                                String author = sc.nextLine();
                                controller.addBook(title, isbn, dop, author);
                            }
                            case "bm" -> {
                                System.out.print("Add meg a konyv Id-jat amit modositani szeretnel: ");
                                Long id = Long.parseLong(sc.nextLine());
                                System.out.print("Add meg a konyv uj cimet: ");
                                String title = sc.nextLine();
                                System.out.print("Add meg a konyv uj ISBN-jet: ");
                                String isbn = sc.nextLine();
                                System.out.print("Add meg a konyv uj kiadasanak idejet (ev-honap-nap): ");
                                Date dop = Date.valueOf(sc.nextLine());
                                controller.updateBook(id, title, isbn, dop);
                            }
                            case "t" -> {
                                System.out.print("Add meg a keresett konyv cimet: ");
                                String title = sc.nextLine();
                                controller.serachBookTitle(title);
                            }
                            case "a" -> {
                                System.out.print("Add meg a keresett konyv irojat: ");
                                String name = sc.nextLine();
                                controller.serachBookAuthor(name);
                            }
                            case "i" -> {
                                System.out.print("Add meg a keresett konyv isbn-jet: ");
                                String isbn = sc.nextLine();
                                controller.serachBookISBN(isbn);
                            }
                            default -> {
                                if (!optionb.equalsIgnoreCase("ghyt")) {
                                    System.out.println("Nem ismert opcio...");
                                }
                            }
                        }
                        bookMenu();
                        System.out.println("Mit lepsz?");
                    } while (!"q".equalsIgnoreCase(optionb = sc.nextLine()));
                }
                case "a" -> {
                    String optiona = "ghyt";
                    do {
                        switch (optiona) {
                            case "la" -> {
                                controller.listAuthor();
                            }
                            case "aa" -> {
                                System.out.print("Add meg az iro nevet: ");
                                String name = sc.nextLine();
                                System.out.print("Add meg az iro szuletesi evet: ");
                                String dob = sc.nextLine();
                                controller.addAuthor(name, dob);
                            }
                            case "am" -> {
                                System.out.print("Add meg a modositani kivant iro Id-jat: ");
                                Long id = Long.parseLong(sc.nextLine());
                                System.out.print("Add meg az iro uj nevet: ");
                                String name = sc.nextLine();
                                System.out.print("Add meg az iro uj szuletesi evet: ");
                                String dob = sc.nextLine();
                                controller.updateAuthor(id, name, dob);
                            }
                            case "ad" -> {
                                System.out.print("Add meg a torolni kivant iro Id-jat: ");
                                Long id = Long.parseLong(sc.nextLine());
                                controller.deleteAuthor(id);
                            }
                            default -> {
                                if (!optiona.equalsIgnoreCase("ghyt")) {
                                    System.out.println("Nem ismert opcio...");
                                }
                            }
                        }
                        authorMenu();
                        System.out.println("Mit lepsz?");
                    } while (!"q".equalsIgnoreCase(optiona = sc.nextLine()));
                }
                case "s" -> {
                    String options = "ghyt";
                    do {
                        switch (options) {
                            case "ls" -> {
                                controller.listStore();
                            }
                            case "sa" -> {
                                System.out.print("Add meg a bolt cimet: ");
                                String address = sc.nextLine();
                                System.out.print("Add meg az bolttulajdonos nevet: ");
                                String owner = sc.nextLine();
                                controller.addStore(address, owner);
                            }
                            case "sm" -> {
                                System.out.print("Add meg a modositani kivant bolt Id-jat: ");
                                Long id = Long.parseLong(sc.nextLine());
                                System.out.print("Add meg a bolt uj cimet: ");
                                String address = sc.nextLine();
                                System.out.print("Add meg az uj bolttulajdonos nevet: ");
                                String owner = sc.nextLine();
                                controller.updateStore(id, address, owner);
                            }
                            default -> {
                                if (!options.equalsIgnoreCase("ghyt")) {
                                    System.out.println("Nem ismert opcio...");
                                }
                            }
                        }
                        storeMenu();
                        System.out.println("Mit lepsz?");
                    } while (!"q".equalsIgnoreCase(options = sc.nextLine()));
                }
                case "st" -> {
                    String optionst = "ghyt";
                    do {
                        switch (optionst) {
                            case "ls" -> {
                                controller.listBookStore();
                            }
                            case "lf" -> {
                                controller.listBookStoreLessFive();
                            }
                            default -> {
                                if (!optionst.equalsIgnoreCase("ghyt")) {
                                    System.out.println("Nem ismert opcio...");
                                }
                            }
                        }
                        storageMenu();
                        System.out.println("Mit lepsz?");
                    } while (!"q".equalsIgnoreCase(optionst = sc.nextLine()));
                }
                default -> {
                    if (!option.equalsIgnoreCase("ghyt")) {
                        System.out.println("Nem ismert opcio...");
                    }
                }
            }
            controller.limit3();
            printMenu();
            System.out.println("Mit lepsz?");
        } while (!"q".equalsIgnoreCase(option = sc.nextLine()));
    }

    private void printMenu() {
        System.out.println("=".repeat(30));
//        System.out.println("\tInit books - (i)");
        System.out.println("\tBook menu - (b)");
        System.out.println("\tAuthor menu - (a)");
        System.out.println("\tStore menu - (s)");
        System.out.println("\tStorage menu - (st)");
        System.out.println("\tKilepes - (q)\n");
        System.out.println("=".repeat(30));
    }

    private void bookMenu(){
        System.out.println("=".repeat(30));
        System.out.println("\tList books - (lb)");
        System.out.println("\tBook added - (ba)");
        System.out.println("\tBook modify - (bm)");
        System.out.println("\tSearch by title - (t)");
        System.out.println("\tSearch by author - (a)");
        System.out.println("\tSearch by ISBN - (i)");
        System.out.println("\tVissza - (q)");
        System.out.println("=".repeat(30));
    }

    private void authorMenu(){
        System.out.println("=".repeat(30));
        System.out.println("\tList authors - (la)");
        System.out.println("\tAuthor added - (aa)");
        System.out.println("\tAuthor modify - (am)");
        System.out.println("\tAuthor delete - (ad)");
        System.out.println("\tVissza - (q)");
        System.out.println("=".repeat(30));
    }

    private void storeMenu(){
        System.out.println("=".repeat(30));
        System.out.println("\tList stores - (ls)");
        System.out.println("\tStore added - (sa)");
        System.out.println("\tStore modify - (sm)");
        System.out.println("\tVissza - (q)");
        System.out.println("=".repeat(30));
    }

    private void storageMenu(){
        System.out.println("=".repeat(30));
        System.out.println("\tList Book - Store - (ls)");
        System.out.println("\tList Book - Store (less than 5 piece) - (lf)");
        System.out.println("\tVissza - (q)");
        System.out.println("=".repeat(30));
    }

}
