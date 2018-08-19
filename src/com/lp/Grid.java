package com.lp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.RuntimeException;
public class Grid {

    private int[][] grid;

    private enum ComparisonEnum { INCREASING, DECREASING }

    Grid( int[][] _grid) {
        grid = _grid;
    }

    Grid( int gridSize ) {
        // Create random grid for testing
        gridSize = 4;
        int maxValue = 20;
        grid = new int[gridSize][gridSize];
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                grid[x][y] = ThreadLocalRandom.current().nextInt(1, maxValue + 1);
            }
        }
    }

    public int[][] getGrid( ) {
        return grid;
    }

    public List<Path> getDecreasingPaths( int startX, int startY, boolean allowDiagonal ) {
        return getPaths( startX, startY, allowDiagonal, ComparisonEnum.DECREASING);
    }

    public List<Path> getIncreasingPaths( int startX, int startY, boolean allowDiagonal ) {
        return getPaths( startX, startY, allowDiagonal, ComparisonEnum.INCREASING);
    }

    private boolean compare( int left, int right, ComparisonEnum comparison) {
        switch (comparison) {
            case INCREASING: return (left>right);
            case DECREASING: return (left<right);
            default: throw ( new RuntimeException( "Unknown operator for comparison.") );
        }
    }

    // TODO: add option to disallow paths that cross
    private List<Path> getPaths( int startX, int startY, boolean allowDiagonal, ComparisonEnum comparison ) {

        List<Path> paths = new ArrayList<Path>();
        for ( int x = (startX == 0) ? startX : startX-1; x <= ((startX == grid.length-1) ? startX : startX+1); x++) {
            for ( int y = (startY == 0) ? startY : startY-1; y <= ((startY == grid.length-1) ? startY : startY+1); y++) {
                if (!allowDiagonal && x != startX && y != startY) continue;
                if (compare(grid[x][y], grid[startX][startY], comparison)) {
                    for (Path path : getPaths(x, y, allowDiagonal, comparison)) {
                        path.prepend(startX,startY);
                        paths.add(path);
                    }
                }
            }
        }
        if (paths.isEmpty()) {
            Path path = new Path();
            path.add( startX, startY );
            paths.add(path);
        }
        return paths;
    }

}
