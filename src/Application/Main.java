package Application;

import Errors.LangException;
import Runtime.Interpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final String EXIT = "__exit__";
        final Scanner in = new Scanner(System.in);
        final PrintStream out = System.out;

        out.println("+--------------------------------------------+");
        out.println("|            FLanguage interpreter           |");
        out.println("+--------------------------------------------+");
        out.println("| Enter file name with program to get result |");
        out.println("| of its execution. Enter '__exit__' to exit |");
        out.println("| the console application.                   |");
        out.println("+--------------------------------------------+");

        while (true) {
            out.print("\nEnter file name with program: ");

            final String file = in.next();
            if (file.equals(EXIT)) {
                return;
            }

            try {
                final Scanner input = new Scanner(new File(file));
                final StringBuilder builder = new StringBuilder();
                while (input.hasNextLine()) {
                    builder.append(input.nextLine());
                    if (input.hasNextLine()) builder.append("\n");
                }
                String source = builder.toString();
                out.println(source + "\n");

                Interpreter interpreter = new Interpreter(source);
                try {
                    interpreter.prepare();
                    interpreter.run();
                    int result = interpreter.getResult();
                    out.println("Output: " + result);
                } catch (LangException e) {
                    out.println(e.getMessage());
                }
            } catch (FileNotFoundException e) {
                out.println("File not found");
            }
        }
    }

}
