package MainProgram;

import Management.Funct;

public class Main {
    public static void main(String[] args) {
        Runtime.getRuntime().gc();
        Funct fc = new Funct();
        fc.menuMain();
    }
}
