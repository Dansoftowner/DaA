package playground;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import datastructures.Stack;

public class Expression {

    public static void main(String[] args) throws IOException {
        var inputReader = new BufferedReader(new InputStreamReader(System.in));
        var userInput = inputReader.readLine();

        var exp = new Expression(userInput);
        System.out.println("Is balanced: " + exp.isBalanced());
    }

    private static final char OPENING_CURLY_BRACKET = '{';
    private static final char OPENING_SQUARE_BRACKET = '[';
    private static final char OPENING_ANGLE_BRACKET = '<';
    private static final char OPENING_PARENTHESES = '(';

    private static final char CLOSING_CURLY_BRACKET = '}';
    private static final char CLOSING_SQUARE_BRACKET = ']';
    private static final char CLOSING_ANGLE_BRACKET = '>';
    private static final char CLOSING_PARENTHESES = ')';

    private static final Map<Character, Character> BRACKET_PAIRS =
            Map.of(
                    OPENING_CURLY_BRACKET, CLOSING_CURLY_BRACKET,
                    OPENING_SQUARE_BRACKET, CLOSING_SQUARE_BRACKET,
                    OPENING_ANGLE_BRACKET, CLOSING_ANGLE_BRACKET,
                    OPENING_PARENTHESES, CLOSING_PARENTHESES
            );

    private final String expression;

    public Expression(String expression) {
        this.expression = expression;
    }


    public boolean isBalanced() {
        //Evaluate
        Stack<Character> openingTags = new Stack<>();

        for (char ch : expression.toCharArray()) {
            if (isOpeningBracket(ch))
                openingTags.push(ch);
            else if (isClosingBracket(ch)) {
                if (openingTags.isEmpty()) return false;

                char lastOpeningTag = openingTags.pop();
                if (!areBracketPairs(lastOpeningTag, ch)) return false;
            }
        }

        return openingTags.isEmpty();
    }

    private boolean isOpeningBracket(char ch) {
        return BRACKET_PAIRS.containsKey(ch);
    }

    private boolean isClosingBracket(char ch) {
        return BRACKET_PAIRS.containsValue(ch);
    }

    private boolean areBracketPairs(char left, char right) {
        return BRACKET_PAIRS.get(left) == right;
    }

}
