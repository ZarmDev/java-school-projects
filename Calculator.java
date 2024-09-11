import java.util.Scanner;
import java.util.ArrayList; // import the ArrayList class

public class Calculator {
    // Final variables - also why does it need static it broke my progrma without
    // static :/
    final static char[] OPERATIONS = { '+', '-', '*', '/', '%' };

    // From
    // https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
    static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static void print(Object contents) {
        System.out.print(contents);
    }

    static void println(Object contents) {
        System.out.print(contents + "\n");
    }

    static boolean isOperation(char character) {
        for (char c : OPERATIONS) {
            if (c == character) {
                return true;
            }
        }
        return false;
    }

    static void throwErr() {
        println("Your expression is not valid.");
    }

    public static void main(String[] args) {
        println("Welcome to the calculator. Note: Does not follow PEMDAS.");
        Scanner readIn = new Scanner(System.in);
        println("Enter your expression: ");

        String expression = readIn.nextLine();
        // https://www.geeksforgeeks.org/how-to-remove-all-white-spaces-from-a-string-in-java/
        String newExpression = expression.replaceAll("\\s", "");
        // for (int i = 0; i < expression.length(); i++) {
        // Remove all spaces in the input
        // https://www.geeksforgeeks.org/how-to-convert-char-to-string-in-java/
        // if (!(String.valueOf(expression.charAt(i)) == "")) {
        // newExpression += Character.toString(expression.charAt(i));
        // }
        //
        // }
        int i = 0;
        // Expect a operation after each number
        boolean done = false;
        // Why is it called tokenType? Because it makes me look smarter
        String tokenType;
        tokenType = "number";
        // https://stackoverflow.com/questions/2369967/how-can-i-check-whether-an-array-is-null-empty
        // Just add values in advance because otherwise we can't just set any item
        // (I just found out after that it's fixed size like java are you kidding me)
        // String[] numbers = new String[100];
        ArrayList<String> numbers = new ArrayList<String>(); // Create an ArrayList object
        ArrayList<Character> operations = new ArrayList<Character>(); // Create an ArrayList object
        char currentLetter = newExpression.charAt(0);

        while (!done) {
            if (i == 0 || tokenType == "number") {
                // Expect a number as first character
                String number = "";
                try {
                    currentLetter = newExpression.charAt(i);
                    while (Character.isDigit(currentLetter)) {
                        currentLetter = newExpression.charAt(i);
                        if (!Character.isDigit(currentLetter)) {
                            i++;
                            break;
                        }
                        // println(currentLetter);
                        number += currentLetter;
                        i++;
                    }
                } catch (Exception e) {
                    // Probably out of bounds so just add the currentLetter and get out of there!
                    String newNumber = number;
                    numbers.add(newNumber);
                    break;
                }
                // if (!Character.isDigit(currentLetter)) {
                // println("Error 1");
                // println("Letter: " + currentLetter);
                // print("i: ");
                // println(i);
                // throwErr();
                // return;
                // }
                tokenType = "operation";
                numbers.add(number);
            } else if (tokenType == "operation") {
                tokenType = "number";
                operations.add(newExpression.charAt(i - 1));
                // if ((i == newExpression.length() - 1)) {
                // println("break!");
                // break;
                // }
                // No need to increment because the number statement already did that.
                // i++;
                // print("test");
                // println(newExpression.charAt(i - 1));
                // Expect a operation
                // if (!isOperation(newExpression.charAt(i + 1))) {
                // print("2");
                // throwErr();
                // return;
                // }
            }
            if (i > newExpression.length()) {
                // println("break!");
                break;
            }
        }
        // If numbers == 2 then operations == 1
        // If numbers == 3 then operations == 2
        // If numbers == 4, then operations == 3
        // println("DONE!");
        for (int z = 0; z < numbers.size(); z++) {
            println(numbers.get(z));
            // Just ignore errors because numbers.size() != operations.size()
            try {
                print("operation: ");
                println(operations.get(z));
            } catch (Exception e) {
                //
            }
        }
        double sum = 0;
        try {
            for (int t = 0; t < operations.size(); t++) {
                // https://stackoverflow.com/questions/5769669/convert-string-to-double-in-java
                double firstNum = Double.parseDouble(numbers.get(t));
                double secondNum = Double.parseDouble(numbers.get(t + 1));
                char currentOperation = operations.get(t);
                if (t == 0) {
                    if (currentOperation == '+') {
                        // https://stackoverflow.com/questions/19987628/conversion-char-to-double
                        // double d = (double) (firstNum - '0');
                        // double d2 = (double) (secondNum - '0');
                        double result = firstNum + secondNum;
                        sum += result;
                    } else if (currentOperation == '*') {
                        double result = firstNum * secondNum;
                        sum += result;
                    } else if (currentOperation == '/') {
                        double result = firstNum / secondNum;
                        sum += result;
                    } else if (currentOperation == '%') {
                        double result = firstNum % secondNum;
                        sum = result;
                    }
                } else {
                    if (currentOperation == '+') {
                        // https://stackoverflow.com/questions/19987628/conversion-char-to-double
                        // double d = (double) (firstNum - '0');
                        // double d2 = (double) (secondNum - '0');
                        sum += secondNum;
                    } else if (currentOperation == '*') {
                        sum *= secondNum;
                    } else if (currentOperation == '/') {
                        sum /= secondNum;
                    } else if (currentOperation == '%') {
                        sum %= secondNum;
                    }
                }
            }
        } catch (Exception e) {
            // Do nothing
            println(e);
        }
        println(sum);
        readIn.close();
    }
}