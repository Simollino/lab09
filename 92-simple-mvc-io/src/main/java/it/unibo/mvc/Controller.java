package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private File currentFile;
    private static final String PATH = System.getProperty("user.home") + File.separator + "output.txt";

    public Controller() {
        this.currentFile = new File(PATH);
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }

    public File getCurrentFile() {
        return this.currentFile;
    }

    public String getCurrentFilePath() {
        return this.currentFile.getAbsolutePath();
    }

    public void save(String s) throws IOException {
        try (PrintStream ps = new PrintStream(this.currentFile.getAbsolutePath(), StandardCharsets.UTF_8)) {
            ps.println(s);
        }
    }

}
