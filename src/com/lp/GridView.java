package com.lp;

import java.util.List;
import com.lp.Grid;
import com.lp.Coordinate;
import com.lp.Path;

/**
 * Created by p41159 on 8/19/2018.
 */
public class GridView {

    private Grid grid;

    GridView( Grid _grid ) { grid = _grid; };

    public void showPath( Path path, int [][]g ) {
        String separator = "";
        for (Coordinate c : path.getPath()) {
            System.out.printf("%s(%d,%d)", separator, c.x, c.y);
            separator = ",";
        }
        separator = " : ";
        for (Coordinate c : path.getPath()) {
            System.out.printf("%s%d", separator, g[c.x][c.y]);
            separator = ",";
        }
        System.out.printf("%n");
    }

    public void showPaths(List<Path> paths) {
        int[][] g = grid.getGrid();
        for (Path path : paths) {
            showPath(path, g);
        }
    }

    public void showGrid() {
        int[][] g = grid.getGrid();
        System.out.printf("     ");
        for (int y = 0; y < g.length; y++) {
            System.out.printf("y%d ", y);
        }
        System.out.println();
        System.out.println("     ------------");
        for (int x = 0; x < g.length; x++) {
            System.out.printf("x%d |", x);
            for (int y = 0; y < g.length; y++) {
                System.out.printf(" %2d", g[x][y]);
            }
            System.out.printf("%n");
        }
    }
}
