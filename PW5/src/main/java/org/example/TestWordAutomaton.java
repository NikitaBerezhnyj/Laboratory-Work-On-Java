package org.example;

import java.util.Objects;

public class TestWordAutomaton {

    public String recognize(String input) {
        Objects.requireNonNull(input, "Input must not be null");

        int state = 0;

        for (char ch : input.toCharArray()) {
            switch (state) {
                case 0:
                    if (ch == 'T') {
                        state = 1;
                    }
                    break;

                case 1:
                    if (ch == 'E') {
                        state = 2;
                    } else if (ch == 'T') {
                        state = 1;
                    } else {
                        state = 0;
                    }
                    break;

                case 2:
                    if (ch == 'S') {
                        state = 3;
                    } else if (ch == 'T') {
                        state = 1;
                    } else {
                        state = 0;
                    }
                    break;

                case 3:
                    if (ch == 'T') {
                        return "F";
                    } else {
                        state = 0;
                    }
                    break;

                default:
                    throw new IllegalStateException(
                            "Unexpected state: " + state
                    );
            }
        }

        return String.valueOf(state);
    }
}