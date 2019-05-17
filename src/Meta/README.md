# Toy language grammar

The following lines of code provides exhaustive grammar description
for the language used to create a simple high level calculator:

```haskell
operation    = "+" | "-" | "*" | "/" | "%" | ">" | "<" | "="

bin-expr     = "(" expr operation expr ")"
arg-list     = expr | expr "," arg-list
call-expr    = IDENTIFIER "(" <argument-list> ")"
if-expr      = "[" expr "]" "?" "(" expr ")" ":" "(" expr ")"

expr         = IDENTIFIER
             | NUMBER
             | bin-expr
             | if-expr
             | call-expr

param-list   = IDENTIFIER | IDENTIFIER "," param-list

fun-def      = IDENTIFIER "(" param-list ")" "=" "{" expr "}"
fun-def-list = ""
             | fun-def EOL
             | fun-def EOLf fun-def-list

program      = fun-def-list expr

```

* __IDENTIFIER__ - ascii-7 string, composed with the 
following regular expression \[a-z,A-Z,_\]* and has non-zero length

* __NUMBER__ - integer value, composed with the 
following regular expression \[-,''\]\[a-z,A-Z,_\]*

Examples for identifier and number strings:

```python
identifiers:

aaaa
_aas_
Bss_AZZ
____

nod identifiers:

aaa34a
_aas_1
$Bss_AZZ
____

numbers:
1112
-12312
-0000
24324

not numbers:
+432
-223-
----1
00ad00

```