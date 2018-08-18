package com.lp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Grid {

    private int[][] grid;

    private class Coordinate {
        int x;
        int y;
        Coordinate( int _x, int _y ) {
            x = _x;
            y = _y;
        }
    }

    Grid( int[][] _grid) {
        grid = _grid;
    }

    private class Path {
        ArrayList<Coordinate> path;
        Path() {
            path = new ArrayList<Coordinate> ();
        }
        public void add( int x, int y ) {
            path.add(new Coordinate(x,y));
        }
        public void prepend( int x, int y ) {
            path.add(0, new Coordinate(x,y));
        }
        public void add( Coordinate c ) {
            path.add(c);
        }
        public List<Coordinate> getPath() {
            return path;
        }
        public void showPath( ) {
            String separator = "";
            for (Coordinate c : path) {
                System.out.printf( "%s(%d,%d)",separator,c.x,c.y);
                separator = ",";
            }
            separator = " : ";
            for (Coordinate c : path) {
                System.out.printf( "%s%d",separator,grid[c.x][c.y]);
                separator = ",";
            }
            System.out.printf("%n");
        }
    }

    // TODO: add option to disallow paths that cross
    public List<Path> getDecreasingPaths( int startX, int startY, boolean allowDiagonal ) {

        List<Path> paths = new ArrayList<Path>();
        for ( int x = (startX == 0) ? startX : startX-1; x <= ((startX == grid.length-1) ? startX : startX+1); x++) {
            for ( int y = (startY == 0) ? startY : startY-1; y <= ((startY == grid.length-1) ? startY : startY+1); y++) {
                if (!allowDiagonal && x != startX && y != startY) continue;
                if (grid[x][y] < grid[startX][startY]) {
                    for (Path path : getDecreasingPaths(x, y, allowDiagonal)) {
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

    public void showPaths( List<Path> paths ) {
        for (Path path : paths) {
            path.showPath( );
        }
    }

    public void showGrid( ) {
        System.out.printf("     ");
        for (int y = 0; y < grid.length; y++) {
            System.out.printf("y%d ",y);
        }
        System.out.println();
        System.out.println( "     ------------");
        for (int x = 0; x < grid.length; x++) {
            System.out.printf("x%d |",x);
            for (int y = 0; y < grid.length; y++) {
                System.out.printf( " %2d", grid[x][y] );
            }
            System.out.printf("%n");
        }
    }
    public static void main(String[] args) {

        // Create random grid for testing
        int gridSize = 4;
        int maxValue = 20;
	    int[][] g = new int[gridSize][gridSize];
        for (int x = 0; x < g.length; x++) {
            for (int y = 0; y < g.length; y++) {
                g[x][y] = ThreadLocalRandom.current().nextInt(1, maxValue + 1);
            }
        }

        Grid grid = new Grid(g);
        grid.showGrid( );
        System.out.println( "The descreasing paths from point 1,2 including diagnonals are:");
        grid.showPaths( grid.getDecreasingPaths(1,2, true) );

        System.out.println( "The descreasing paths from point 1,2 excluding diagnonals are:");
        grid.showPaths( grid.getDecreasingPaths(1,2, false) );

        System.out.println( "The descreasing paths from point 0,0 including diagnonals are:");
        grid.showPaths( grid.getDecreasingPaths(0,0, true));

        System.out.println( "The descreasing paths from point 3,3 including diagnonals are:");
        grid.showPaths( grid.getDecreasingPaths(3,3, true));
    }
}
