package Management;

public class Funct {

    public int menuMain() {
        Runtime.getRuntime().gc();
        int choice;
        System.out.println("+----------------------------------+");
        System.out.println("|       BOOK STORE MANAGEMENT      |");
        System.out.println("+----------------------------------+");
        System.out.format("|%-34s|\n", "Choose funct:");
        System.out.format("|%-34s|\n", "1.Publishers management");
        System.out.format("|%-34s|\n", "2.Books management:");
        System.out.format("|%-34s|\n", "3.Exit program!");
        System.out.println("+----------------------------------+");

        while (true) {
            pubList.clear();
            bookList.clear();
            choice = Validation.getInt(1, 3);
            switch (choice) {
                case 1:
                    pubList.readFromFile();
                    publisherMenu();
                    pubList.clear();
                    return 0;
                case 2:
                    bookList.readFromBookFile();
                    bookMenu();
                    bookList.clear();
                    return 0;
                case 3:
                    pubList.clear();
                    bookList.clear();
                    System.out.println("+----------------------+");
                    System.out.println("| Thank you for using! |");
                    System.out.println("+----------------------+");
                    return 0;
            }
        }
    }

    PublisherManagement pubList = new PublisherManagement();

    private int publisherMenu() {
        Runtime.getRuntime().gc();
        int choice;
        System.out.println("PUBLISHERS MANAGEMENT");
        System.out.println("+--------------------------------------------+");
        System.out.format("|%-44s|\n", "1.1. Create a Publisher");
        System.out.format("|%-44s|\n", "1.2. Delete the Publisher");
        System.out.format("|%-44s|\n", "1.3. Save the Publishers list to file");
        System.out.format("|%-44s|\n", "1.4. Print the Publisher lisy from the file.");
        System.out.println("+--------------------------------------------+");
        System.out.println("|      REMEMBER TO SAVE BEFORE GO BACK       |");
        System.out.println("|       Press 0 to go back main menu!        |");
        System.out.println("+--------------------------------------------+");

        while (true) {
            choice = Validation.getInt(0, 4);
            switch (choice) {
                case 1:
                    pubList.createPublisher();
                    if (Validation.getYN() == true) {
                        return publisherMenu();
                    } else {
                        return menuMain();
                    }
                case 2:
                    pubList.deletePublisher();
                    return publisherMenu();
                case 3:
                    pubList.saveToFile();
                    if (Validation.getYN() == true) {
                        return publisherMenu();
                    } else {
                        return menuMain();
                    }
                case 4:
                    pubList.layoutForPublisher();
                    if (Validation.getYN() == true) {
                        return publisherMenu();
                    } else {
                        return menuMain();
                    }
                case 0:
                    return menuMain();

            }
        }
    }

    BooksManagement bookList = new BooksManagement();

    private int bookMenu() {
        Runtime.getRuntime().gc();
        int choice;
        System.out.println("BOOKS MANAGEMENT");
        System.out.println("+--------------------------------------------+");
        System.out.format("|%-44s|\n", "2.1. Create a Book");
        System.out.format("|%-44s|\n", "2.2. Search the Book");
        System.out.format("|%-44s|\n", "2.3. Update a Book");
        System.out.format("|%-44s|\n", "2.4. Delete the Book");
        System.out.format("|%-44s|\n", "2.5. Save the Books list to file.");
        System.out.format("|%-44s|\n", "2.6. Print the Books list from the file.");
        System.out.println("+--------------------------------------------+");
        System.out.println("|      REMEMBER TO SAVE BEFORE GO BACK       |");
        System.out.println("|       Press 0 to go back main menu!        |");
        System.out.println("+--------------------------------------------+");

        while (true) {
            choice = Validation.getInt(0, 6);
            switch (choice) {
                case 1:
                    bookList.createBook();
                    if (Validation.getYN() == true) {
                        return bookMenu();
                    } else {
                        return menuMain();
                    }
                case 2:
                    bookList.sreachTheBook();
                    return bookMenu();
                case 3:
                    bookList.updateBook();
                    return bookMenu();
                case 4:
                    bookList.deleteBook();
                    return bookMenu();
                case 5:
                    bookList.saveToFile();
                    if (Validation.getYN() == true) {
                        return bookMenu();
                    } else {
                        return menuMain();
                    }
                case 6:
                    bookList.layoutForBook();
                    if (Validation.getYN() == true) {
                        return bookMenu();
                    } else {
                        return menuMain();
                    }
                case 0:
                    return menuMain();
            }
        }
    }
}
