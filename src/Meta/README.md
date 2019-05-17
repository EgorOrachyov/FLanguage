# Toy language

## Grammar rules

The following lines of code provides exhaustive grammar description
for the language used to create a simple high level calculator:

```
operation    = "+" | "-" | "*" | "/" | "%" | ">" | "<" | "="

bin-expr     = "(" expr operation expr ")"
arg-list     = expr | expr "," arg-list
call-expr    = IDENTIFIER "(" arg-list ")"
if-expr      = "[" expr "]" "?" "(" expr ")" ":" "(" expr ")"

expr         = IDENTIFIER
             | NUMBER
             | bin-expr
             | call-expr
             | if-expr

param-list   = IDENTIFIER | IDENTIFIER "," param-list

fun-def      = IDENTIFIER "(" param-list ")" "=" "{" expr "}"
fun-def-list = ""
             | fun-def EOL
             | fun-def EOL fun-def-list

program      = fun-def-list expr

```

* __IDENTIFIER__ - ascii-7 string, composed with the 
following regular expression \[a-z,A-Z,_\]* and has non-zero length

* __NUMBER__ - integer value, composed with the 
following regular expression \[-,''\]\[a-z,A-Z,_\]*

Examples for identifier and number strings:

```
identifiers: aaaa, _aas_, ___SS

not identifiers: aaa34a, _aas_1, ds(dsd)ds

numbers: 1112, -12312, -0000

not numbers: +432, -223-, 139d

```

## Semantic rules

