package Shape;

import UI.Function;

import java.awt.geom.GeneralPath;
import java.util.Vector;

public class Polygon extends Shape2D {
    public Polygon(Vector<Vertex> vertices) {
        super(vertices);
    }

    public Polygon(Vector<Vertex> vertices, int verticesNum){
        super(vertices, verticesNum);
    }

    @Override
    public GeneralPath generatePath() {
        GeneralPath path = new GeneralPath();
        path.moveTo(_vertices.get(0).getX(), _vertices.get(0).getY());
        for (int i = 1; i < _verticesNum; i++) {
            path.lineTo(_vertices.get(i).getX(), _vertices.get(i).getY());
        }
        if(_verticesNum == Function.Polygon.getVerticesNum())
            path.closePath();
        return path;
    }
    @Override
    public Shape2D cloneShape2D() {
        return new Polygon(_vertices, _verticesNum);
    }
}
