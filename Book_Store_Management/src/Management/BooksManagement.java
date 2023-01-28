package Management;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import obj.Books;

public class BooksManagement extends ArrayList<Books> {

    public BooksManagement() {
    }

    void createBook() {
        this.add(inputBook());
        System.out.println("Created!");
    }

    int findBookID(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBookID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

    int findPubID(String ID) {
        PublisherManagement pubMan = new PublisherManagement();
        pubMan.readFromFile();
        for (int i = 0; i < pubMan.size(); i++) {
            if (pubMan.get(i).getpublisherID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

    int findAndPrintPubID(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getpublisherID().equals(ID)) {
                printFromList(i);
            }
        }
        return -1;
    }

    public int findBookName(String name) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBookName().toLowerCase().equals(name.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }

    Books inputBook() {
        Books obj = new Books();
        System.out.println("Publisher's ID(Pxxxxx): ");
        obj.setPublisherID(Validation.getWithRegex("[P,p][0-9]{5}").toUpperCase());
        findBookID(obj.getPublisherID());
        while (findPubID(obj.getpublisherID()) == -1) {
            System.err.println("Publisher’s Id is not found");
            obj.setPublisherID(Validation.getWithRegex("[P,p][0-9]{5}").toUpperCase());
        }

        System.out.println("Book's ID(Bxxxxx): ");
        obj.setBookID(Validation.getWithRegex("[B,b][0-9]{5}").toUpperCase());
        while (findBookID(obj.getBookID()) > -1) {
            System.err.println("Duplicate ID");
            obj.setBookID(Validation.getWithRegex("[B,b][0-9]{5}").toUpperCase());
        }

        System.out.println("Book's name: ");
        obj.setBookName(Validation.getWithRegex("[A-Z,a-z,0-9, ]{5,30}"));

        System.out.println("Book's price: ");
        obj.setBookPrice(Validation.getReal(0));

        System.out.println("Book's quantity: ");
        obj.setQuantity(Validation.getInt(1, Integer.MAX_VALUE));

        System.out.println("Status(A/NA): ");
        obj.setStatus(Validation.getStatus());

        return obj;
    }
    
    Books inputToUpdateBook(String bookID) {
        Books obj = new Books();
        System.out.println("Publisher's ID(Pxxxxx): ");
        obj.setPublisherID(Validation.getWithRegex("[P,p][0-9]{5}").toUpperCase());
        findBookID(obj.getPublisherID());
        while (findPubID(obj.getpublisherID()) == -1) {
            System.err.println("Publisher’s Id is not found");
            obj.setPublisherID(Validation.getWithRegex("[P,p][0-9]{5}").toUpperCase());
        }
        
        obj.setBookID(bookID);

        System.out.println("Book's name: ");
        obj.setBookName(Validation.getWithRegex("[A-Z,a-z,0-9, ]{5,30}"));

        System.out.println("Book's price: ");
        obj.setBookPrice(Validation.getReal(0));

        System.out.println("Book's quantity: ");
        obj.setQuantity(Validation.getInt(1, Integer.MAX_VALUE));

        System.out.println("Status(A/NA): ");
        obj.setStatus(Validation.getStatus());

        return obj;
    }

    void printFromList(int index) {
        if (this.isEmpty()) {
            System.err.println("Have no any Book!");
        } else {
            System.out.print("+----------------+------------------+------------------+----------------+------------------+------------------+----------------+\n");
            System.out.print(this.get(index));
            System.out.print("+----------------+------------------+------------------+----------------+------------------+------------------+----------------+\n");

        }
    }

    int sreachTheBook() {
        int choice;
        System.out.println("+----------------------------------+");
        System.out.format("|%-34s|\n", "1.Enter Book's Name:");
        System.out.format("|%-34s|\n", "2.Enter Publisher’s Id:");
        System.out.format("|%-34s|\n", "3.Go back");
        System.out.println("+----------------------------------+");

        while (true) {
            choice = Validation.getInt(1, 3);
            switch (choice) {
                case 1:
                    System.out.println("Enter Book's Name: ");
                    printFromList(findBookName(Validation.getWithRegex("[A-Z,a-z,0-9, ]{5,30}")));
                    if (Validation.getYN() == true) {
                        return sreachTheBook();
                    } else {
                        return 0;
                    }
                case 2:
                    System.out.println("Enter Publisher's ID: ");
                    findAndPrintPubID(Validation.getWithRegex("[P,p][0-9]{5}").toUpperCase());
                    if (Validation.getYN() == true) {
                        return sreachTheBook();
                    } else {
                        return 0;
                    }
                case 3:
                    return 0;
            }
        }
    }

    void readFromBookFile() {
        try {
            File f = new File(".\\src\\Data\\book.dat");
            if (!f.exists()) {
                return;
            }
            this.clear();
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String info[] = line.split("[|]");
                String bookID = info[1].trim();
                String bookName = info[2].trim();
                double bookPrice = Double.parseDouble(info[3]);
                int quantity = Integer.valueOf(info[4].trim());
                double subTotal = Double.parseDouble(info[5]);
                String pubName = info[6].trim();
                String status = info[7].trim();
                if (!(bookID.isEmpty())) {
                    this.add(new Books(bookID, bookName, bookPrice, quantity, subTotal, pubName, status));
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Error input file.");
        }
    }

    void saveToFile() {
        try {
            File f = new File(".\\src\\Data\\book.dat");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Books p : this) {
                bw.write(p.toString());
            }
            System.out.println("Saved!");
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.err.println("Can't save file.");
        }
    }

    void printFromFile() {
        List<Books> newList = new ArrayList<Books>();
        try {
            File f = new File(".\\src\\Data\\book.dat");
            if (!f.exists()) {
                throw new Exception();
            }
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String info[] = line.split("[|]");
                String bookID = info[1].trim();
                String bookName = info[2].trim();
                double bookPrice = Double.parseDouble(info[3]);
                int quantity = Integer.valueOf(info[4].trim());
                double subTotal = Double.parseDouble(info[5]);
                String pubName = info[6].trim();
                String status = info[7].trim();
                if (!(bookID.isEmpty())) {
                    newList.add(new Books(bookID, bookName, bookPrice, quantity, subTotal, pubName, status));
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.err.println("Error input file.");
            return;
        }
        if (newList.isEmpty()) {
            System.err.println("Nothing to display!");
        } else {
            Collections.sort(newList, Comparator.comparingInt(Books::getQuantity).reversed());
            for (Books p : newList) {
                System.out.print(p);
            }
            newList.clear();
        }
    }

    public void updateBook() {
        System.out.println("Enter Book's ID to update:");
        String ID = Validation.getWithRegex("[B,b][0-9]{5}").toUpperCase();
        int target = findBookID(ID);
        if (target > -1) {
             Books input = this.inputToUpdateBook(ID);
             System.out.println("The Book will be update!");
             if(Validation.getYN()==true){
                 this.set(target, input);
            System.out.println("Updated success!");
             }else{
                 input = null;
                 System.err.println("Updated fail!");
             }
        } else {
            System.out.println("Book’s Name does not exist");
        }
    }

    void deleteBook() {
        System.out.println("Enter the Book’s ID: ");
        String ID = Validation.getWithRegex("[B,b][0-9]{5}").toUpperCase();
        int target = findBookID(ID);
        if (target > -1) {
            System.out.println("The Book will be delete!");
            if (Validation.getYN() == true) {
                this.remove(target);
                System.out.println("Book was deleted.");
            } 
            else {
                System.err.println("The Book was not deleted.");
            }
        } else {
            System.err.println("Book’s Name does not exist!");
        }
    }

    void layoutForBook() {
        System.out.print("BOOK\n");
        System.out.print("+----------------+------------------+------------------+----------------+------------------+------------------+----------------+\n");
        System.out.format("| %-14s | %-16s | %-16s | %-14s | %-16s | %-16s | %-14s |\n", "Book's ID", "Book's Name", "Price", "Quantity", "Subtotal", "Publisher's ID", "Status");
        System.out.print("+----------------+------------------+------------------+----------------+------------------+------------------+----------------+\n");
        printFromFile();
        System.out.print("+----------------+------------------+------------------+----------------+------------------+------------------+----------------+\n");
    }
}
