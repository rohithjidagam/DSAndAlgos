package com.google;

import java.util.ArrayList;
import java.util.List;

public class PrettyJson {

    public static void main(String[] args) {

        String s = "{A:\"B\",C:{D:\"E\",F:{G:\"H\",I:\"J\"}}}";

        PrettyJson p = new PrettyJson();
        List<String> res = p.prettyJSON(s);
        for (String st : res) {
            System.out.println(st);
        }
    }

    public List<String> prettyJSON(String s) {

        ArrayList<String> result = new ArrayList<String>();
        int tabCounter = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
            case '{':
            case '[':
                if (sb.length() > 0) {
                    result.add(sb.toString());
                    sb = new StringBuilder();
                }
                for (int j = 0; j < tabCounter; j++) {
                    sb.append('\t');
                }
                sb.append(c);
                result.add(sb.toString());
                sb = new StringBuilder();
                tabCounter++;
                break;

            case ']':
            case '}':
                if (sb.length() > 0) {
                    result.add(sb.toString());
                    sb = new StringBuilder();
                }
                tabCounter--;
                for (int j = 0; j < tabCounter; j++) {
                    sb.append('\t');
                }
                sb.append(c);
                break;

            case ',':
                sb.append(c);
                result.add(sb.toString());
                sb = new StringBuilder();
                break;

            default:
                if (sb.length() == 0) {
                    for (int j = 0; j < tabCounter; j++) {
                        sb.append('\t');
                    }
                }
                sb.append(c);
            }
        }

        if (sb.length() > 0) {
            result.add(sb.toString());
        }

        return result;
    }

}
