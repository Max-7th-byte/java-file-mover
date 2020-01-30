class Seeker extends Lister{

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    private String [] allFilesNeeded;
    private String[] startingFolders;
    private int numberOfFiles;

    Seeker (String userName, String [] whereToLook) {
        super(userName);
        if (whereToLook != null) {
            startingFolders = new String[whereToLook.length];
            startingFolders = whereToLook;
        } else System.out.println("INCORRECT DATA!!! NO FOLDERS TO LOOK AT");
    }

    private String [] increaseArraySize(String [] input) {
        String [] output = new String[input.length + 1];
        System.arraycopy(input, 0, output, 0, input.length);
        return output;
    }

    String [] lookForFilesWithNames(String [] tags) {

        if (tags == null) {
            System.out.println("CANNOT LOOK FOR EMPTY STRING");
            return null;
        }
        String[] allFiles = findAllFiles(startingFolders);
        allFilesNeeded = new String[1];
        int files = 0;
        try {
            for (int i = 0; i <allFiles.length; i++) {
                for (int j = 0; j < tags.length; j++) {
                    if (allFiles[i].contains(tags[j])) {
                        boolean notThere = true; //CHECKING IF IT IS NOT A DOUBLE
                        for (int k = 0; k < allFilesNeeded.length; k++) {
                            if (allFiles[i].equals(allFilesNeeded[k])) {
                                notThere = false;
                                break;
                            }
                        }
                        if (notThere) {
                            if (allFilesNeeded[allFilesNeeded.length - 1] != null)
                                allFilesNeeded = increaseArraySize(allFilesNeeded);
                            allFilesNeeded[files++] = allFiles[i];
                            numberOfFiles++;
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("INVALID DATA!!!");
        }

        return allFilesNeeded;
    }

    void printAllFilesNeeded() {
        if (allFilesNeeded[0] == null) return;
        for (int i = 0; i < allFilesNeeded.length; i++) {
            if (i%2 == 0) System.out.println(ANSI_BLUE + allFilesNeeded[i] + ANSI_RESET);
            else System.out.println(ANSI_PURPLE + allFilesNeeded[i] + ANSI_RESET);
        }
        System.out.println(ANSI_YELLOW + "\nNUMBER OF FILES TO TRANSFER: " + numberOfFiles + "\n------------------------" + ANSI_RESET);
    }
}