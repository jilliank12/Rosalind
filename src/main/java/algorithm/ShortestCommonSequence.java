package algorithm;

import java.io.*;
import java.util.*;

/**
 * Created by Jilliankim on 4/7/16.
 */
public class ShortestCommonSequence {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        boolean first = true;

        try (Scanner sc = new Scanner(new File("/Users/Jilliankim/Desktop/25Dataset.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.charAt(0) == '>') {
                    if (first)
                        first = false;
                    else
                        System.out.println();
                    System.out.printf("%s: ", line.substring(1));
                } else {
                    System.out.print(line);
                }
            }
        }
        System.out.println();


    }

    private void createSuperString(Set<String> substrings) {
        int totalStrings = substrings.size();
        String[] match = new String[totalStrings];
        int i = 0;

        for(String superString : substrings) {
            Set<String> temp = new HashSet<String>(substrings);
            String maxSuperString = superString;
            while (temp.size() > 1) {

                String subString = "";
                String nextMaxSuperString = maxSuperString;

                for (String nextString : temp) {
                    if (!nextString.equals(nextMaxSuperString)) {
                        String superTemp = getSuperString(maxSuperString, nextString);
                        if (nextMaxSuperString.equals(maxSuperString) || nextMaxSuperString.length() > superTemp.length()) {
                            nextMaxSuperString = superTemp;
                            subString = nextString;
                        }
                    }
                }

                temp.remove(maxSuperString);
                temp.remove(subString);
                maxSuperString = nextMaxSuperString;
                temp.add(maxSuperString);
            }

            match[i] = maxSuperString;
            i++;
        }
        String bestString = match[0];

        for (i = 1; i < match.length; i++) {
            if (bestString.length() > match[i].length()) {
                bestString = match[i];
            }
        }

        System.out.println("Shortest Common Superstring : " + bestString);
        System.out.println("length is: " + bestString.length());
    }

    private String getSuperString(String superString, String someString) {
        String result = superString;
        int endIndex = someString.length() - 1;

        while (endIndex > 0 && !superString.endsWith(someString.substring(0, endIndex))) {
            endIndex--;
        }

        if (endIndex > 0) result += someString;
        else result += someString;

        return result;
    }


}
