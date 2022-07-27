# MatrixCalculator

| Key           | Value                                         |
|---------------|-----------------------------------------------|
| Project Title | MatrixCalculator                              |
| Author        | Jan-Luca Körner                               |
| City          | Düsseldorf                                    |
| Country       | Germany                                       |
| Video         | [My Video Description](https://www.google.de) |

### Short introduction:

My final project for the cs50x course delivers a calculator for matrices and vectors.

### Possible Operations:

Possible arithmetic operations of the matrix and vector calculator are:
* Matrix addition,
* Matrix subtraction,
* Matrix multiplication,
* Matrix inversion,
* Matrix inverse multiplication,
* Matrix potency,
* Solve of linear system of equations,
* Vector addition,
* Vector subtraction and
* Vector scalar multiplication.

These arithmetic operations are possible for every matrix dimension,
except if the arithmetic operation calculates at the datatype bounds of a `java.math.BigDecimal`.

### How the Matrix Calculator works:

1. At the start of the java program you will see a frame with many buttons,
   where you can select your desired arithmetic operation.
2. By pressing the load from csv button, you have the opportunity to select a csv file which is seperated by semicolon
and which contains a matrix. The selected csv file will automatically be loaded in the inline field next to the button.
3. Another opportunity is to press the open edit matrix button. Then you can insert a new matrix or edit a csv loaded matrix.
4. If the inline field next to the open matrix button does not contain data, you will also have the opportunity to
insert a custom matrix with the transferred row and column count. Therefor you only have to press the open edit matrix button.

### Used Technologies:

* `java` as programming language for all arithmetic operations.
* `java.swing` as graphical user interface extension for Java.
* `java.math.BigDecimal` as datatype for each represented value.
