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
import obj.Publishers;

public class PublisherManagement extends ArrayList<Publishers> {

    public PublisherManagement() {
    }

    void createPublisher() {
        this.add(inputPub());
        System.out.println("Created!");
    }

    public int findID(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getpublisherID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

    public int findBookPublisherID(String ID) {
        BooksManagement bookMan = new BooksManagement();
        bookMan.readFromBookFile();
        for (int i = 0; i < bookMan.size(); i++) {
            if (bookMan.get(i).getPublisherID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

    Publishers inputPub() {
        Publishers obj = new Publishers();

        System.out.println("Publisher's ID(Pxxxxx): ");
        obj.setpublisherID(Validation.getWithRegex("[P][0-9]{5}"));
        while (findID(obj.getpublisherID()) > -1) {
            System.err.println("Duplicate ID");
            obj.setpublisherID(Validation.getWithRegex("[P][0-9]{5}"));
        }

        System.out.println("Publisher's name: ");
        obj.setPublisherName(Validation.getWithRegex("[A-Z,a-z,0-9, ]{5,30}"));

        System.out.println("Phone number: ");
        obj.setPhoneNum(Validation.getWithRegex("[0-9]{10,12}"));

        return obj;
    }

    void deletePublisher() {
        System.out.println("Enter Publisher's ID to remove:");
        String ID = Validation.getWithRegex("[P][0-9]{5}");
        int target = findID(ID);
        if (target > -1 && findBookPublisherID(ID) == -1) {
            System.out.println("The Publisher will be deleted!");
            if(Validation.getYN()==true){
                this.remove(target);
            System.out.println("Publisher was deleted.");
            }else{
                System.err.println("Publisher was not deleted.");
            }
            
        } else if (target == -1) {
            System.err.println("Publisher’s ID does not exist!");
        } else {
            System.err.println("Publisher’s ID can not delete");
        }
    }

    void saveToFile() {
        try {
            File f = new File(".\\src\\Data\\publisher.dat");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Publishers p : this) {
                bw.write(p.toString());
            }
            System.out.println("Saved!");
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.err.println("Can't save file.");
        }
    }

    void readFromFile() {
        try {
            File f = new File(".\\src\\Data\\publisher.dat");
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
                String ID = info[1].trim();
                String Name = info[2].trim();
                String Phone = info[3].trim();
                if (!(ID.isEmpty())) {
                    this.add(new Publishers(ID, Name, Phone));
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Error input file.");
        }
    }

    void printFromFile() {
        List<Publishers> newList = new ArrayList<Publishers>();
        try {
            File f = new File(".\\src\\Data\\publisher.dat");
            if (!f.exists()) {
                throw new Exception();
            }

            newList.clear();
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String info[] = line.split("[|]");
                String ID = info[1].trim();
                String Name = info[2].trim();
                String Phone = info[3].trim();
                if (!(ID.isEmpty())) {
                    newList.add(new Publishers(ID, Name, Phone));
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.err.println("Error input file.");
        }
        if (this.isEmpty()) {
            System.err.println("Nothing to display!");
        } else {
            Collections.sort(newList, Comparator.comparing(Publishers::getPublisherName));
            for (Publishers p : newList) {
                System.out.print(p);
            }
        }
    }

    void layoutForPublisher() {
        System.out.print("PUBLISHER\n");
        System.out.print("+---------+-----------------+------------------+\n");
        System.out.format("| %-8s| %-16s| %-16s |\n", "ID", "NAME", "PHONE");
        System.out.print("+---------+-----------------+------------------+\n");
        printFromFile();
        System.out.print("+---------+-----------------+------------------+\n");
    }
}
