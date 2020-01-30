import java.nio.file.NoSuchFileException;

public class Main {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public static void main(String[] args) {

        // TO DEFINE BY A USER
        String userName = "/Users/max";
        String[] folders = {"Desktop"};
        Seeker seeker_to_show = new Seeker(userName, folders);
        String[][] tags = {
                {"Marketing", "marketing", "management", "Management", "Manager", "manager", "Organization", "organization", "Company", "company", "Strategy", "strategy"},// TAGS FOR MANAGEMENT, THE LAST ELEMENT SHOWS THE DIRECTION OF MOVING
                {"_HW", "Derivative", "derivative", "function", "Function", "Variable", "variable", "limit", "Limit", "Series", "series", "Sequences", "Integral", "Integrals"} // HOMEWORKS FOLDER
        };

        String[] tagNames = {"MARKETING AND MANAGEMENT FILES", "MATH HOMEWORKS"};
        String[] referencesToMove = {"School/WDZ", "School/AM/Homeworks"}; // YOU HAVE TO DEFINE REFERENCED STARTING AFTER YOUR USER NAME WITHOUT BACKSLASH
        //

        // LISTING ALL THE FILES
        System.out.println(ANSI_YELLOW + "\nLIST OF THE FILES IN DEFINED FOLDERS:\n" + ANSI_RESET);
        seeker_to_show.findAllFiles(folders);
        seeker_to_show.printAllFiles();
        System.out.println("\n-----------------------------------------\n\n");
        //

        try {

            if (tags.length != tagNames.length || tags.length != referencesToMove.length || tagNames.length != referencesToMove.length)
            throw new DoesNotEqualExeption();

            for (int i = 0; i < tags.length; i++) {

                try {

                    System.out.println(ANSI_YELLOW + "------------------------\n" + tagNames[i] + ":" + ANSI_RESET);

                    Seeker seeker = new Seeker(userName, folders);
                    String[] files = seeker.lookForFilesWithNames(tags[i]);
                    seeker.printAllFilesNeeded();
                    System.out.println(ANSI_GREEN + "\nTRANSFERRING...\n" + ANSI_RESET);
                    for (int j = 0; j < files.length; j++) {
                        Mover m = new Mover(userName);
                        m.move(referencesToMove[i], files[j]);
                    }
                    System.out.println(ANSI_GREEN + "\nDONE\n" + ANSI_RESET);

                } catch (NullPointerException e) {
                    System.out.println(ANSI_RED + "THERE IS NO FILES TO TRANSFER\n" + ANSI_RESET);
                } catch (MovingExeption | IsNotADirectoryExeption e) {
                    e.printStackTrace();
                } catch (NoSuchFileException e) {
                    e.getMessage();
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getMessage();
                }
            }

        } catch (DoesNotEqualExeption e) {
            e.printStackTrace();
            System.out.println("\nSIZES OF DEFINED ARRAYS ARE:\ntags length = " + tags.length +
                    "\ntagNames length = " + tagNames.length + "\nreferencesToMove length = " + referencesToMove.length);
        }
    }
}