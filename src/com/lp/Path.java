package com.lp;

import java.util.ArrayList;
import java.util.List;
import com.lp.Coordinate;

/**
 * Created by p41159 on 8/19/2018.
 */
public class Path {
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
}
