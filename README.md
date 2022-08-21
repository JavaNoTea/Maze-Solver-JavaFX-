# Maze-Solver-JavaFX-
A program that implements JavaFX UI to build mazes with walls and teleporters. Utilizes a breadth-first search algorithm to find the optimal solution to the maze.

![maze1](https://user-images.githubusercontent.com/99133333/185803628-321fcf21-d38a-4d74-8d20-1c1ca7c7b7de.png)


How to use/features-
 
![maze2](https://user-images.githubusercontent.com/99133333/185803853-a00b7eb7-87ef-4473-83a7-6166a9b14c2e.png)
-The top bar contains a variety of tools (start, end, wall, eraser, teleporter). When selected you can place these squares on to the maze map. On this top bar there is also a teleport group text box. When a teleporter is placed it will be assigned to the teleport group currently written in that box. For example, if 'A' is written in the box and two 'A' teleporters are placed then a bridge will be created between those two teleporters on the maze map.
 
![maze3](https://user-images.githubusercontent.com/99133333/185803875-8b96f6d8-4e6a-4b1c-9629-f1bab85bc230.png)
-The maze map is the grid seen below the tool bar. This is where squares can be placed when selected on the tool bar. The size of the grid can also be reset using the grid width and height selectors at the bottom of the window.

-The start/stop button can be found in the bottom right. When activated, the solution to the maze will be shown in yellow on the grid along with a text showing how many steps it took. When stopped, the solution will be erased and the maze map will be reverted back to how it appeared before the maze solver was run.
