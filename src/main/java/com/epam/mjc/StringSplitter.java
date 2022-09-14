package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {

        List<String> list = new ArrayList<>();
        List<String> delimArr = new ArrayList<>(delimiters);
        StringBuilder delim= new StringBuilder();
        for (String s:delimArr) {
            delim.append(s);
        }
        StringTokenizer tokens = new StringTokenizer(source, delim.toString());
        while (tokens.hasMoreTokens()){
            list.add(tokens.nextToken());
        }
        return list;
    }
}
