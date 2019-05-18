# ToyLanguage

Interpreter for language with simple grammar

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

* __IDENTIFIER__ - ascii-7 string, composed with the 
following regular expression \[a-z,A-Z,_\]* and has non-zero length

* __NUMBER__ - positive integer value, composed with the 
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
* Cll-expression performs call of defined function witt its name and arguments
* All the expressions are evaluated from right to left