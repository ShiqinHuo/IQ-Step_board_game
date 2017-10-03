Code Review
Wenjun Yang(u6251843) ‘s code reviewed by Shiqin Huo(u5949730)

Features to mention

•	Appropriate program decomposition
The program is divided into two classes, namely StepGame and PartialSolution.
In StepGame, there are 7 methods in StepGame and 3 methods in PartialSolution.
I think the distribution of methods are proper. In the most method, the structures are well-formed.
It follows the principle that global variable comes first, then constructor and lastly, method.

•	Great Java code conventions
The methods are named according to their functions and the parameters are named by the data type of input and input itself.
As to declaration, the number global variables are declared on separated lines,
constant variables are declared in upper case and local variables are initialized after being declared,
following the rule of variable declaration.

•	Well-documented Code
I noticed that there are block comments, single-line comments for some of methods,
thus, the code are demonstrated clearly in the program.

•	Best features of code
The code try various ways to implement the same task.
One is deep-first search, although it is not efficient enough to pass the test,
another one is backtracking and Alpha – beta pruning.

Bugs or unfinished task to mention
•	getSolution method hasn’t been well-formed.
•	In PartialSolution class, in the method entend(), the question how to append the mask on the board hasn’t been solved.

Improvements to mention
•	Some of the code could be implemented more properly and wisely,
    the programmer should reduce the amount of hard coding.
•	The efficiency of the code could be improved a lot.
•	Some special algorithms should be applied to the task.
•	The structure of class should be well-designed, making the class looks more clearly.
•	The methods could be combined, reducing the number of methods.






