package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private String currentString;
    private List<String> printedStrings = new LinkedList<>();

    @Override
    public String getNextString() {
        return this.currentString;
    }
    @Override
    public void setNextString(String nextString) {
        if(nextString != null) {
            this.currentString = nextString;
            printedStrings.add(nextString);
        }
        else {
            throw new IllegalArgumentException("empty strings aren't accepted");
        }
    }
    @Override
    public List<String> getPrintedStrings() {
        return this.printedStrings;
    }
    @Override
    public void printCurrentString() {
        if(this.currentString != null) {
            System.out.println(this.currentString);
        }
        else {
            throw new IllegalStateException("current string isn't setted");
        }
    }


}
