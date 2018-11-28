package csc366;

import csc366.world.Shape;

import java.util.ArrayDeque;
import java.util.Optional;

/**
 * Recursive Descent Parser for the following Grammar:
 * S -> Command | Question
 * Command -> Action Object (Command)
 * Question -> AV Object MV (Object) "?"
 * Object -> (DA) (Adjective) Shape
 * Action -> "pick up" | "move" | "put down" | "put" | "drop" | "on"
 * AV -> "what is" | "can" | "which"
 * MV -> "supported by" | "support" | "is sitting on" | "is"
 * Adjective -> "red" | "green" | "blue"
 * DA -> "a" | "the"
 * Shape -> "block" | "pyramid" | "it"
 */
public class Parser {

    private int linePtr;
    private Symbol[] line;
    private Symbol symbol;
    private boolean isErrorPrinted;

    private enum Symbol {
        PICK_UP, MOVE, PUT_DOWN, PUT, DROP, ON, WHAT_IS, CAN, WHICH, SUPPORTED_BY, SUPPORT, IS_SITTING_ON, IS,
        RED, GREEN, BLUE, A, THE, BLOCK, PYRAMID, IT, QUESTION_MARK
    }

    /**
     * Parse input line into symbols, and hand over to initial production S for recursive parse.
     * @param lineStr Input line
     * @return Optional containing block action representing the input or nothing
     */
    public Optional<BlockAction> parse(String lineStr) {
        String[] tokens = lineStr.strip().split(" ");
        ArrayDeque<Symbol> lineList = new ArrayDeque<>();
        // If the last token ends with a question mark, remove it and explicitly insert it into the symbol array
        String lastToken = tokens[tokens.length-1];
        if (lastToken.endsWith("?")) {
            lineList.add(Symbol.QUESTION_MARK);
            tokens[tokens.length - 1] = lastToken.substring(0, lastToken.length() - 1);
        }
        for (int i = tokens.length-1; i >= 0; i--) {
            String currToken = tokens[i];
            switch (currToken) {
                case "up":
                    if (tokens[i-1].equalsIgnoreCase("pick")) {
                        lineList.addFirst(Symbol.PICK_UP);
                        i--;
                    }
                    break;
                case "down":
                    if (tokens[i-1].equalsIgnoreCase("put")) {
                        lineList.addFirst(Symbol.PUT_DOWN);
                        i--;
                    }
                    break;
                case "is":
                    if (tokens[i-1].equalsIgnoreCase("what")) {
                        lineList.addFirst(Symbol.WHAT_IS);
                        i--;
                    } else {
                        lineList.addFirst(Symbol.IS);
                    }
                    break;
                case "by":
                    if (tokens[i-1].equalsIgnoreCase("supported")) {
                        lineList.addFirst(Symbol.SUPPORTED_BY);
                        i--;
                    }
                    break;
                case "on":
                    if (tokens[i-1].equalsIgnoreCase("sitting")
                            && tokens[i-2].equalsIgnoreCase("is")) {
                        lineList.addFirst(Symbol.IS_SITTING_ON);
                        i -= 2;
                    }
                    break;
                default:
                    try {
                        lineList.addFirst(Symbol.valueOf(currToken.toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        // symbol does not exist
                        printError();
                        return Optional.empty();
                    }
            }
        }
        // init class variables for this parse
        line = new Symbol[lineList.size()];
        lineList.toArray(line);
        linePtr = 0;
        isErrorPrinted = false;
        // perform actual parse
        nextSymbol();
        BlockAction action = s();
        return !isErrorPrinted ? Optional.of(action) : Optional.empty();
    }

    /**
     * Print the error message signifying to the user an unsuccessful parse.
     */
    private void printError() {
        if (!isErrorPrinted) {
            System.out.println("I don't understand.");
            isErrorPrinted = true;
        }
    }

    /**
     * Set value of local variable symbol to the next symbol in the line.
     */
    private void nextSymbol() {
        if (linePtr < line.length) {
            symbol = line[linePtr++];
        }
    }

    /**
     * Accept Symbol s if it is the expected next symbol.
     * @param s symbol that was seen
     * @return true if seen symbol matches expected symbol
     */
    private boolean accept(Symbol s) {
        if (s == symbol) {
            nextSymbol();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the current symbol is an action.
     * Action -> "pick up" | "move" | "put down" | "put" | "drop" | "on"
     * @return true if the current symbol is an action
     */
    private boolean symbolIsAction() {
        return symbol == Symbol.PICK_UP || symbol == Symbol.MOVE || symbol == Symbol.PUT_DOWN ||
                symbol == Symbol.PUT || symbol == Symbol.DROP || symbol == Symbol.ON;
    }

    /**
     * Check if the current symbol is a definite article.
     * DA -> "a" | "the"
     * @return true if the current symbol is a definite article
     */
    private boolean symbolIsDA() {
        return symbol == Symbol.A || symbol == Symbol.THE;
    }

    /**
     * Check if the current symbol is an adjective.
     * Adjective -> "red" | "green" | "blue"
     * @return true if the current symbol is an adjective
     */
    private boolean symbolIsAdjective() {
        return symbol == Symbol.RED || symbol == Symbol.GREEN || symbol == Symbol.BLUE;
    }

    /**
     * S -> Question | Command
     * @return A BlockAction representing the input
     */
    private BlockAction s() {
        if (symbolIsAction()) {
            return command();
        } else {
            return question();
        }
    }

    /**
     * Question -> AV Object MV (Object) "?"
     * @return A QuestionAction representing the input
     */
    private QuestionAction question() {
        QuestionAction action = new QuestionAction();
        action.setAv(av());
        action.setObject1(object());
        action.setMv(mv());
        if (symbolIsDA()) {
            action.setObject2(object());
        }
        if (!accept(Symbol.QUESTION_MARK)) {
            printError();
        }
        return action;
    }

    /**
     * Command -> Action Object (Command)
     * @return A CommandAction representing the input
     */
    private BlockAction command() {
        CommandAction action = new CommandAction();
        action.setAction(action());
        action.setObject(object());
        if (symbolIsAction()) {
            action.setCommand(command());
        }
        return action;
    }

    /**
     * Object -> DA (Adjective) Shape
     * @return ObjectNode representing the object specified by the user
     */
    private ObjectNode object() {
        Optional<Shape.Color> color = Optional.empty();
        Shape.Type shape;
        if (symbolIsDA()) {
            da();
        }
        if (symbolIsAdjective()) {
            color = Optional.ofNullable(adjective());
        }
        shape = shape();
        return color.map(color1 -> new ObjectNode(shape, color1)).orElseGet(() -> new ObjectNode(shape));
    }

    /**
     * DA -> "a" | "the"
     */
    private void da() {
        if (!accept(Symbol.A) && !accept(Symbol.THE)) {
            printError();
        }
    }


    /**
     * Action -> "pick up" | "move" | "put down" | "put" | "drop" | "on"
     * @return The type of action that should be performed
     */
    private CommandAction.Action action() {
        if (accept(Symbol.PICK_UP) || accept(Symbol.MOVE)) {
            return CommandAction.Action.PICK_UP;
        } else if (accept(Symbol.PUT_DOWN) || accept(Symbol.DROP) || accept(Symbol.PUT) || accept(Symbol.ON)) {
            return CommandAction.Action.PUT_DOWN;
        } else {
            printError();
            return null;
        }
    }

    /**
     * AV -> "what is" | "can" | "which"
     * @return Auxiliary Verb from the input
     */
    private QuestionAction.AV av() {
        if (accept(Symbol.WHAT_IS)) {
            return QuestionAction.AV.WHAT_IS;
        } else if (accept(Symbol.CAN)) {
            return QuestionAction.AV.CAN;
        } else if (accept(Symbol.WHICH)) {
            return QuestionAction.AV.WHICH;
        } else {
            printError();
            return null;
        }
    }

    /**
     * MV -> "supported by" | "support" | "is sitting on" | "is"
     * @return Main Verb from the input
     */
    private QuestionAction.MV mv() {
        /*
        if (!(accept(Symbol.SUPPORTED_BY) || accept(Symbol.SUPPORT) || accept(Symbol.IS_SITTING_ON) ||
                accept(Symbol.IS))) {
            printError();
        }
        */
        if (accept(Symbol.SUPPORTED_BY)) {
            return QuestionAction.MV.SUPPORTED_BY;
        } else if (accept(Symbol.SUPPORT)) {
            return QuestionAction.MV.SUPPORT;
        } else if (accept(Symbol.IS_SITTING_ON)) {
            return QuestionAction.MV.IS_SITTING_ON;
        } else {
            printError();
            return null;
        }
    }

    /**
     * Adjective -> "red" | "green" | "blue"
     * @return The specified color of the object
     */
    private Shape.Color adjective() {
        if (accept(Symbol.RED)) {
            return Shape.Color.RED;
        } else if (accept(Symbol.BLUE)) {
            return Shape.Color.BLUE;
        } else if (accept(Symbol.GREEN)) {
            return Shape.Color.GREEN;
        } else {
            printError();
            return null;
        }
    }

   /**
    * Shape -> "block" | "pyramid" | "it"
    * @return The specified shape of the object
    */
   private Shape.Type shape() {
       if (accept(Symbol.BLOCK)) {
           return Shape.Type.BLOCK;
       } else if (accept(Symbol.PYRAMID)) {
           return Shape.Type.PYRAMID;
       } else if (accept(Symbol.IT)) {
           return Shape.Type.IT;
       } else {
           printError();
           return null;
       }
   }

}
