README – Map2D Implementation
Overview

In this assignment I implemented a 2D integer map (Map2D) represented by a two-dimensional array.
The map supports drawing operations, BFS-based algorithms, and saving/loading from a file.
A simple GUI was also implemented to display the map.

All implementations strictly follow the given interfaces without any changes.

Map Class

Full implementation of the Map2D interface.
The map is internally represented as a 2D integer array (int[][]), where each cell represents a pixel value.

Constructors & Initialization

Map(int w, int h, int v)
Creates a map of size w × h and initializes all pixels with value v.

Map(int size)
Creates a square map of size size × size initialized with zeros.

Map(int[][] data)
Creates a deep copy of an existing 2D array into the map.

init(int w, int h, int v)
Initializes the map dimensions and fills all cells with value v.

init(int[][] arr)
Initializes the map as a deep copy of the given array.

Basic Map Operations

getMap()
Returns a deep copy of the internal 2D array.

getWidth() / getHeight()
Return the width and height of the map.

getPixel(int x, int y)
Returns the pixel value at (x, y), or -1 if out of bounds.

getPixel(Pixel2D p)
Returns the pixel value at the given Pixel2D.

setPixel(int x, int y, int v)
Sets a pixel value at (x, y). Throws an exception if out of bounds.

setPixel(Pixel2D p, int v)
Sets a pixel value using a Pixel2D object.

isInside(Pixel2D p)
Checks whether a pixel is inside the map boundaries.

sameDimensions(Map2D other)
Checks whether another map has the same dimensions.

Map Arithmetic

addMap2D(Map2D p)
Adds the values of another map to this map, cell by cell.

mul(double scalar)
Multiplies all pixel values by a scalar.

rescale(double sx, double sy)
Rescales the map using nearest-neighbor interpolation.

Drawing Operations

drawCircle(Pixel2D center, double radius, int color)
Draws a filled circle with the given center, radius, and color.

drawLine(Pixel2D p1, Pixel2D p2, int color)
Draws a straight line between two points using a Bresenham-like approach.

drawRect(Pixel2D p1, Pixel2D p2, int color)
Draws a filled rectangle defined by two corner points.

Equality

equals(Object obj)
Performs a deep comparison between two maps (same size and same pixel values).

BFS-Based Algorithms

All path-finding and fill algorithms are implemented using Breadth-First Search (BFS).

fill(Pixel2D start, int newColor, boolean cyclic)
Flood-fill algorithm that fills all connected pixels of the same color starting from start.
Supports optional cyclic (wrap-around) behavior at map edges.
Returns the number of pixels filled.

shortestPath(Pixel2D p1, Pixel2D p2, int obstacleColor, boolean cyclic)
Computes the shortest path between two points using BFS, avoiding obstacles.
Supports cyclic behavior.
Returns an array of Pixel2D representing the path, or null if no path exists.

allDistance(Pixel2D start, int obstacleColor, boolean cyclic)
Computes a distance map where each cell stores the shortest distance from start.
Inaccessible cells are marked with -1.

Index2D Class

Implementation of the Pixel2D interface.

Includes:

Storage of x and y coordinates

Euclidean distance calculation

Equality comparison

String representation

Ex2_GUI

Basic graphical and file interface using StdDraw.

Features:

Visual display of the map

Saving the map to a text file

Loading the map from a text file

Tests
JUnit tests were written for:
Map initialization
Equality checks
Flood fill
Shortest path
Distance map
The GUI was tested manually.

Summary
All required methods were fully implemented
BFS is used consistently for fill and path algorithms
Obstacles are handled using a specific color value
Cyclic (wrap-around) behavior is supported where required
The implementation follows the given interfaces exactly

The GUI works as expected
<img width="779" height="955" alt="צילום מסך 2025-12-25 143731" src="https://github.com/user-attachments/assets/32606baf-c7c9-4508-b7ce-8c9cf9755bf3" />


String representation
