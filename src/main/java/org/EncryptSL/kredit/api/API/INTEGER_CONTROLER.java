package org.EncryptSL.kredit.api.API;

public class INTEGER_CONTROLER {

    public boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
