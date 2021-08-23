# Tic-Tac-Toe with AI

## About
Everybody remembers this paper-and-pencil game from childhood: Tic-Tac-Toe, also known as Noughts and crosses or Xs and Os. A single mistake usually costs you the game, but thankfully it is simple enough that most players discover the best strategy quickly. Let’s program Tic-Tac-Toe and get playing!
## Learning outcomes
After finishing this project, you'll get to know a lot about planning and developing a complex program from scratch, using classes and methods, handling errors, and processing user input. You will also learn to use OOP (Object-Oriented Programming) in the process.

## Description
In this project, you'll write a game called Tic-Tac-Toe that you can play against your computer. The computer will have three levels of difficulty — easy, medium, and hard.

The top-left cell will have the coordinates (1, 1) and the bottom-right cell will have the coordinates (3, 3), as shown in this table:

(1, 1) (1, 2) (1, 3) \n
(2, 1) (2, 2) (2, 3) \n
(3, 1) (3, 2) (3, 3) \n

The program should ask the user to enter the coordinates of the cell where they want to make a move.

Keep in mind that the first coordinate goes from left to right, and the second coordinate goes from top to bottom. Also, notice that coordinates start with 1 and can be 1, 2, or 3.

But what if the user attempts to enter invalid coordinates? This could happen if they try to enter letters or symbols instead of numbers, or the coordinates of an already occupied cell. Your program needs to prevent these things from happening by checking the user's input and catching possible exceptions.

In easy mode the computer makes random moves. In medium mode the computer is looking to block any chances to win or try to win if he has already 2 in line. Unlike medium, when the AI is playing at hard level, it doesn't just look one move ahead to see an immediate win or prevent an immediate loss. At this level, it can look two moves ahead, three moves ahead, and even further. It can calculate all possible moves that might be played during the game, and choose the best one based on the assumption that its opponent will also play perfectly. So, it doesn't rely on the mistakes of its opponent and plays the game without fault from start to finish regardless of the opponent's skill!

The algorithm that implements this is called minimax. It's a brute force algorithm that maximizes the value of the AI's position and minimizes the worth of its opponent's. Minimax is not just for Tic-Tac-Toe. You can use it with any other game where two players make alternate moves, such as chess.

## Example
The example below shows how your program should work.
The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

```
Input command: > start hard user
Making move level "hard"

   |   |  
---+---+---
 X |   |  
---+---+---
   |   |  

Enter the coordinates: > 2 2

   |   |  
---+---+---
 X | O |  
---+---+---
   |   |  

Making move level "hard"

   | X | 
---+---+---
 X | O |  
---+---+---
   |   |  

Enter the coordinates: > 3 2

   | X | 
---+---+---
 X | O |  
---+---+---
   | O |  

Making move level "hard"

 X | X | 
---+---+---
 X | O |  
---+---+---
   | O |  

Enter the coordinates: > 3 1

 X | X | 
---+---+---
 X | O |  
---+---+---
 O | O |  

Making move level "hard"

 X | X | X
---+---+---
 X | O |  
---+---+---
 O | O |  

X wins

Input command: > exit
```
