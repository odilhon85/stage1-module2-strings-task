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
        StringTokenizer tokens = new StringTokenizer(source);
        List<String> delimArr = new ArrayList<>(delimiters);
        for (int i = 0; i < delimArr.size(); i++) {
            if(tokens.hasMoreElements()){
                tokens.nextToken(delimArr.get(i));
            }
        }
        List<String> list = new ArrayList<>();
        while (tokens.hasMoreTokens()){
            list.add(tokens.nextToken());
        }
        return list;
    }
}
