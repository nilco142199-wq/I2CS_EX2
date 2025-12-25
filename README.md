In this assignment I implemented a 2D integer map (Map2D) represented by a two-dimensional array.
The map supports drawing operations, BFS-based algorithms, and saving/loading from a file.
A simple GUI was also implemented to display the map.

Map
Full implementation of the Map2D interface.
Includes:
Map initialization (by size, square, or array)
Get and set pixel values
Boundary checks
Drawing operations: rectangle, line, and circle
Scalar multiplication and rescaling
Flood fill algorithm (with and without cyclic behavior)
Shortest path between two points using BFS
Distance map from a starting point using BFS
Deep equality check (equals)

Index2D
Implementation of the Pixel2D interface.
Includes:
Storing x and y coordinates
Euclidean distance calculation
Equality check
String representation

Ex2_GUI
Basic graphical and file interface using StdDraw.
Includes:
Drawing the map
Saving a map to a text file
Loading a map from a text file

Tests
JUnit tests were written for:
Map initialization
Equality check
Flood fill
Shortest path
Distance map
The GUI was tested manually.

All algorithms are based on BFS
Obstacles are handled using a color value
Cyclic (wrap-around) behavior is supported where required
The implementation follows the given interfaces without changes

Summary
All required methods were implemented, tested, and the GUI works as expected.
Equality check

String representation
