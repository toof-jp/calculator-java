# calculator-java
簡易的な計算機の実装  
以下のEBNF (ISO/IEC 14977:1996)で表される文法の計算式を計算し，計算結果を出力する  
```
expression = term , { ( "+" | "-" ) , term }
term = factor , { ( "*" | "/" ) , factor }
factor = number | "(" expression ")"
number = [ "-" ] , { digit }- , [ "." , { digit }- ] ;
digit = "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9" ;
```
