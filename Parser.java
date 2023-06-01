/*
EBNF (ISO/IEC 14977:1996)
```
expression = term , { ( "+" | "-" ) , term }
term = factor , { ( "*" | "/" ) , factor }
factor = number | "(" expression ")"
number = [ "-" ] , { digit }- , [ "." , { digit }- ] ;
digit = "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9" ;
```
*/

public class Parser {

    static double parse(State s){
        double ret = parseExpression(s);
        if (s.peek() != '\0'){
            System.exit(1);
        }
        return ret;
    }

    static double parseExpression(State s) {
        double ret = parseTerm(s);
        while (s.peek() == '+' || s.peek() == '-') {
            char ope = s.peek();
            s.next();
            if (ope == '+') {
                ret += parseTerm(s);
            } else {
                ret -= parseTerm(s);
            }
        }

        return ret;
    }

    static double parseTerm(State s) {
        double ret = parseFactor(s);
        while (s.peek() == '*' || s.peek() == '/') {
            char ope = s.peek();
            s.next();
            if (ope == '*') {
                ret *= parseFactor(s);
            } else {
                ret /= parseFactor(s);
            }
        }

        return ret;
    }

    static double parseFactor(State s) {
        if (s.peek() == '(') {
            s.next(); // read '('
            double ret = parseExpression(s);
            if (s.peek() != ')') {
                System.exit(1);
            }
            s.next(); // read ')'
            return ret;
        }

        double ret = parseNumber(s);

        return ret;
    }

    static double parseNumber(State s) {
        double ret = 0;
        boolean isMinus = false;

        if (s.peek() == '-') {
            isMinus = true;
            s.next();
        }

        if (!isDigit(s.peek())) {
            System.exit(1);
        }

        while (isDigit(s.peek())) {
            ret *= 10;
            ret += (int)s.peek() - '0';
            s.next();
        }

        if (s.peek() == '.') {
            s.next();

            if (!isDigit(s.peek())) {
                System.exit(1);
            }

            int index = -1;
            while (isDigit(s.peek())) {
                ret += ((int)s.peek() - '0') * Math.pow(10, index);
                s.next();
                index--;
            }
        }

        if (isMinus) {
            ret = -ret;
        }

        return ret;
    }

    static boolean isDigit(Character c) {
        return 0x30 <= (int)c && (int)c <= 0x39;
    }
}
