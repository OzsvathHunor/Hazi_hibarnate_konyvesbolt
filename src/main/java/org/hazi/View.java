package org.hazi;

import java.util.Scanner;

public class View {

    Controller controller;
    void mainMenu(Scanner sc) {
        String option = "ghyt";
        do {
            switch (option) {
                case "i" -> {
                    controller.initAll();
                }
                case "b" -> {
                    String optionb = "ghyt";
                    do {
                        switch (optionb) {
                            case "lb" -> {
                                controller.listBook();
                            }
                            case "ba" -> {
                                controller.addBook();
                            }
                            case "bm" -> {
                                controller.updateBook();
                            }
                            case "t" -> {
                                controller.serachBookTitle();
                            }
                            case "a" -> {
                                controller.serachBookAuthor();
                            }
                            case "i" -> {
                                controller.serachBookISBN();
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
                            case "ab" -> {
                                controller.listAuthor();
                            }
                            case "aa" -> {
                                controller.addAuthor();
                            }
                            case "am" -> {
                                controller.updateAuthor();
                            }
                            case "ad" -> {
                                controller.deleteAuthor();
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
                                controller.addStore();
                            }
                            case "sm" -> {
                                controller.updateStore();
                            }
                            default -> {
                                if (!options.equalsIgnoreCase("ghyt")) {
                                    System.out.println("Nem ismert opcio...");
                                }
                            }
                        }
                        bookMenu();
                        System.out.println("Mit lepsz?");
                    } while (!"q".equalsIgnoreCase(options = sc.nextLine()));
                }
                default -> {
                    if (!option.equalsIgnoreCase("ghyt")) {
                        System.out.println("Nem ismert opcio...");
                    }
                }
            }

            printMenu();
            System.out.println("Mit lepsz?");
        } while (!"q".equalsIgnoreCase(option = sc.nextLine()));
    }

    private void printMenu() {
        System.out.println("=".repeat(30));
//        System.out.println("\tInit books - (i)");
        System.out.println("\tBook menu - (b)");
        System.out.println("\tAuthor menu - (l)");
        System.out.println("\tStore menu - (c)");
//        System.out.println("\tTorol Pizza - (d)");
//        System.out.println("\tOrszag - tt - (ttcar)");
//        System.out.println("\tOrszag - tt - EG - (ttcareg)");
        System.out.println("\tKilepes - (q)");
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
        System.out.println("\tKilepes - (q)");
        System.out.println("=".repeat(30));
    }

    private void authorMenu(){
        System.out.println("=".repeat(30));
        System.out.println("\tList authors - (la)");
        System.out.println("\tAuthor added - (aa)");
        System.out.println("\tAuthor modify - (am)");
        System.out.println("\tAuthor delete - (ad)");
        System.out.println("\tKilepes - (q)");
        System.out.println("=".repeat(30));
    }

    private void storeMenu(){
        System.out.println("=".repeat(30));
        System.out.println("\tList stores - (ls)");
        System.out.println("\tStore added - (sa)");
        System.out.println("\tStore modify - (sm)");
        System.out.println("\tKilepes - (q)");
        System.out.println("=".repeat(30));
    }
}
