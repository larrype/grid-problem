package com.lp;

/**
 * Created by p41159 on 8/19/2018.
 */
public class Main {
    public static void main(String[] args) {

        Grid grid = new Grid(4);
        GridView gridView = new GridView( grid );
        gridView.showGrid( );

        System.out.println( "The descreasing paths from point 1,2 including diagnonals are:");
        gridView.showPaths( grid.getDecreasingPaths(1,2, true) );

        System.out.println( "The descreasing paths from point 1,2 excluding diagnonals are:");
        gridView.showPaths( grid.getDecreasingPaths(1,2, false) );

        System.out.println( "The increasing paths from point 1,2 including diagnonals are:");
        gridView.showPaths( grid.getIncreasingPaths(1,2, true) );

        System.out.println( "The increasing paths from point 1,2 excluding diagnonals are:");
        gridView.showPaths( grid.getIncreasingPaths(1,2, false) );

        System.out.println( "The descreasing paths from point 0,0 including diagnonals are:");
        gridView.showPaths( grid.getDecreasingPaths(0,0, true));

        System.out.println( "The descreasing paths from point 3,3 including diagnonals are:");
        gridView.showPaths( grid.getDecreasingPaths(3,3, true));
    }
}
