package Shape;

import java.awt.geom.GeneralPath;

public class Line extends Shape2D {
    public Line(Vertex[] vertices){
        super(vertices);
    }
    public GeneralPath generatePath(){
        GeneralPath path =new GeneralPath();
        path.moveTo(_vertices[0].getX(),_vertices[0].getY());
        path.lineTo(_vertices[1].getX(),_vertices[1].getY());
        return path;
    }
}
