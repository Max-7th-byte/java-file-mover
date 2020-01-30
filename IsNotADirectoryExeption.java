class IsNotADirectoryExeption extends Exception {
    IsNotADirectoryExeption() {
        super("The file is not a directory");
    }
}
