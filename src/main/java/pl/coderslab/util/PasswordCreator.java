package pl.coderslab.util;

import java.util.Random;

public class PasswordCreator {

    private Random rand = new Random();

    private String passChars = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";

    public String createPass() {

        String userPass = "";

        for(int i = 0; i < 8; i ++) {
            int n = rand.nextInt(62);
            char oneChar = passChars.charAt(n);
            userPass += oneChar;
        }

        return userPass;
    }
}
