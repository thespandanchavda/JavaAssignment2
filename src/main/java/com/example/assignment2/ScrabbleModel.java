package com.example.assignment2;

import java.util.HashMap;
import java.util.Map;

public class ScrabbleModel {
    private int[] values;
    private Map<Character, Integer> mapchar;

    public ScrabbleModel() {
        values = new int[]{9, 2, 2, 4, 12, 2, 3, 2, 8, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
        mapchar = new HashMap<>();
    }

    public boolean checkValidWord(String word) {
        int[] temp = values.clone();

        for (int i = 0; i < word.length(); i++) {
            char c = Character.toLowerCase(word.charAt(i));
            if (--temp[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public int calculateWordPoints(String word) {
        int points = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = Character.toLowerCase(word.charAt(i));
            points += getPointValue(c);
        }
        return points;
    }

    private int getPointValue(char c) {
        int index = c - 'a';
        if (index >= 0 && index < values.length) {
            return values[index];
        }
        return 0;
    }
}
