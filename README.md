# FLanguage

Functional integer expression base simple language with java based interpreter
with basic error tracking and without byte-code generation or optimizations.

## Features

The following list of features describes language and interpreter core
mechanics and main design goals:

* Integer 32-bit calculations (in Java style)
* Basic set of operations
* If-statements support
* Function definition support
* Recursion
* Variables scope

## Grammar rules

The following lines of code provides exhaustive grammar description
for the language used to create a simple high level calculator:

```
operation    = "+" | "-" | "*" | "/" | "%" | ">" | "<" | "="

bin-expr     = "(" expr operation expr ")"
arg-list     = expr | expr "," arg-list
call-expr    = IDENTIFIER "(" arg-list ")"
if-expr      = "[" expr "]" "?" "{" expr "}" ":" "{" expr "}"

expr         = IDENTIFIER
             | NUMBER
             | -NUMBER
             | bin-expr
             | call-expr
             | if-expr

param-list   = IDENTIFIER | IDENTIFIER "," param-list

fun-def      = IDENTIFIER "(" param-list ")" "=" "{" expr "}"
fun-def-list = fun-def EOL
             | fun-def EOL fun-def-list

program      = fun-def-list expr | expr

```

* _IDENTIFIER_ - ascii-7 string, composed with the 
following regular expression \[a-z,A-Z,_\]* and has non-zero length
* _NUMBER_ - positive integer value, composed with the 
following regular expression \[0-9\]* and has non-zero length

Examples for identifier and number strings:

```
identifiers: aaaa, _aas_, ___SS
not identifiers: aaa34a, _aas_1, ds(dsd)ds

numbers: 1112, -12312, -0000
not numbers: +432, -223-, 139d

```

## Semantic rules

The following rules defines the way, how the program and its data will be 
actually executed and interpreted in runtime:

* All variables have 32-bit integer type
* All evaluations do not lead to overflow
* All operations are equivalent to Java operations
* Compare operations returns 1 or 0, whether the condition is true of false
* If-expression executes its true branch whether the condition does not equal to
0, otherwise it executes its false branch
* Call-expression performs call of defined function witt its name and arguments
* All the expressions are evaluated from right to left

## Error handling

There is an exhaustive list off all the error messages, which could be 
generated in time of lexing, parsing or interpreting:

* SYNTAX ERROR - program does not satisfy grammar rules
* PARAMETER NOT FOUND - used identifier out of the scope of undefined in the function
* FUNCTION NOT FOUND - an attempt to call undefined function
* ARGUMENT NUMBER MISMATCH - an attempt to call function with invalid num of arguments 
* RUNTIME ERROR - an error in time of execution
* FUNCTION REDEFINITION - an attempt to define function with already used identifier
* PARAM REDEFINITION - an attempt to use identifier already used in param list of the function

## Program example

The following programs show how the language actually looks like with 
results of the interpreting of this programs:

```java
(2+2)

Output: 4
```

```java
g(x)={(f(x)+f((x/2)))}
f(x)={[(x>1)]?{(f((x-1))+f((x-2)))}:{x}}
g(10)

Output: 60 
```

```java
s(x)={(x*x)}
f(x,y,z)={((s(x)+s(y))+s(z))}
g(x,y,z)={((x+y)+z)}
k(x,y,z,w)={(((x*y)*z)*w)}
f(1,g(2,3,4),k(5,6,7,0))

Output: 90
```

## About

This project was created and developed by [Egor Orachyov](https://github.com/EgorOrachyov)
as a part of the JetBrains Summer Internship 2019 project: Code Insight For R Language Support (IntelliJ platform)
