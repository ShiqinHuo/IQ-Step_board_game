Code Review
Xiangyi Luo(u6162693) ‘s code reviewed by Wenjun Yang(u6251843)

Features to mention:

•	Awesome design at early stage.

•	Proper naming style: The method and variable names directly tells what they are and is friendly for review.

•	Great structured classes: The Pieces class store eight pieces and their eight rotations in array properly
    with some small but useful methods. The Alphabet pieces encode the 50 positions and tell those are pegs.

•	Well-commented and coding style: State the input and output clearly and describe how the method working in detail,
    leading the code easy to comprehensible. Also, divide complex task into smaller task using divide and conquer concept.

•	Well-chosen data structure to store information about pieces and positions, which shorten the time to get all correct orders.

•	Perfect constraint to determinate whether pieces are in correct order.

•	Some code is reused frequently as tools when writing bigger task in StepGame class.

Bugs to improve:

•	Pass test perfectly and do not cause time out error.

Suggestions to improve:

•	Redundant codes for matrix operation (in task 5 and task6), can combine the similar code.

•	In recursive method, considering the time and space cost carefully.

•	May try topological sorting to reduce the searching tree nodes.

•	The terminal condition could be simplied.

•	Optimization of the scanning process for the board: may not store all the positions.
