import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

class Mover {

    //FIELDS
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    private String userName;

    // CONSTRUCTOR
    Mover(String userName) {
        if (userName != null) {
            File user =  new File(userName);
            if (user.isDirectory()) {
                this.userName = userName + "/";
            } else {
                System.out.println("INCORRECT USER NAME");
            }
        } else {
            System.out.println("USER NAME IS EMPTY");
        }
    }

    void move(String to, String file) throws Exception {

        if (to.equals("") || file.equals(""))
            throw new NullPointerException("There is nothing to move");

        //CREATING DIRECTORY WHERE TO MOVE
        File directory = new File(userName + to + "/Moved"); //  /Users/max/School/WDZ

        if (directory.mkdir()) {
            System.out.println(ANSI_GREEN + "THE FOLDER Moved HAS BEEN CREATED\n" + ANSI_RESET);
        }

        if (!directory.exists() || !directory.isDirectory()) {
            throw new IsNotADirectoryExeption();
        }

        try {
            File temp = new File(file);
            File toMove = new File(directory + "/" + temp.getName());
            Files.move(
                    Paths.get(file),
                    Paths.get(String.valueOf(toMove)),
                    StandardCopyOption.REPLACE_EXISTING
            );
            System.out.println(ANSI_GREEN + "FILE  "+ toMove.getName() + "  MOVED SUCCESSFULLY" + ANSI_RESET);

        } catch (NoSuchFileException e) {
            throw new NoSuchFileException("FILE " + file + " CANNOT BE MOVED");
        }
    }
}