import java.io.File;
import java.nio.file.Paths;
import java.util.Objects;

class Lister {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    private String [] allFiles = new String [1];
    private String userName;
    private int numberOfFilesInStartFolders = 0;

    Lister (String userName) {
        if (userName != null) {
            this.userName = userName + "/";
        } else System.out.println("ERROR!!! USER NAME IS EMPTY");
    }

    private String [] increaseArraySize(String [] input) {
        String [] output = new String[input.length + 1];
        System.arraycopy(input, 0, output, 0, input.length);
        return output;
    }

    String [] findAllFiles(String [] startingDirectories) {

        for (String startingDirectory : startingDirectories) {
            try {
                getFilesFromDirectory(startingDirectory);
            } catch (NullPointerException e) {
                System.out.println(ANSI_RED + "CANNOT GET FILES FROM " + userName + startingDirectory + ANSI_RESET);
            }


        }
        return allFiles;
    }

    private void getFilesFromDirectory(String directory) throws NullPointerException{

        String path = userName + directory;
            int numberOfFiles = Objects.requireNonNull(new File(path).list()).length; // DEFINING NUMBER OF ITERATIONS
            String [] filesInTheFolder = new File(path).list();
            if (filesInTheFolder == null) return;
            for (int i = 0; i < numberOfFiles; i++) {
                File file = new File(path + "/" + filesInTheFolder[i]); // CREATING A NEW FILE IN JAVA
                if (allFiles[allFiles.length - 1] != null) allFiles = increaseArraySize(allFiles); // CHECKING OF THE ARRAY IS FULL
                allFiles[numberOfFilesInStartFolders++] = String.valueOf(Paths.get(String.valueOf(file)));
                if (file.isDirectory()) {
                    getFilesFromDirectory(directory + "/" + filesInTheFolder[i]);
                }

            }
    }

    void printAllFiles() {

        String [] names = new String[allFiles.length];
        for (int i = 0; i < allFiles.length; i++) {
            String name = "";
            int j = allFiles[i].length() - 1;
            while (allFiles[i].charAt(j) != '/') {
                name = allFiles[i].charAt(j) + name;
                j--;
            }
            names[i] = name;
        }

        for (int i = 0; i < names.length; i++) {
            if (i%2 == 0) System.out.println(ANSI_BLUE + names[i] + ANSI_RESET);
            else System.out.println(ANSI_PURPLE + names[i] + ANSI_RESET);
        }
        System.out.println(ANSI_YELLOW + "\nNUMBER OF FILES IN DEFINED FOLDERS: " + numberOfFilesInStartFolders + ANSI_RESET);
    }

    void printAllReferences() {
        for (int i = 0; i < allFiles.length; i++) {
            if (i%2 == 0) System.out.println(ANSI_BLUE + allFiles[i] + ANSI_RESET);
            else System.out.println(ANSI_PURPLE + allFiles[i] + ANSI_RESET);
        }
        System.out.println(ANSI_YELLOW + "\nNUMBER OF FILES IN DEFINED FOLDERS: " + numberOfFilesInStartFolders + ANSI_RESET);
    }
}