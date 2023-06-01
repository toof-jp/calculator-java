import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.exit(1);
        }
        State s = new State(args[0]);

        /*
        Scanner stdIn = new Scanner(System.in);
        State s = new State(stdIn.next());
        stdIn.close();
         */

        double result = Parser.parse(s);

        if (result == Double.NEGATIVE_INFINITY) {
            System.out.println("-∞");
        } else if (result == Double.POSITIVE_INFINITY) {
            System.out.println("+∞");
        } else {
            System.out.println(result);
        }
    }
}
