package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    public String getNextString();

    public void setNextString(String nextString);

    public List<String> getPrintedStrings();

    public void printCurrentString();

}
