package Shape;

import java.awt.geom.GeneralPath;
import java.util.Vector;

public class QuadCurve extends Shape2D{
    public QuadCurve(Vector<Vertex> vertices) {
        super(vertices);
    }

    public QuadCurve(Vector<Vertex> vertices, int verticesNum) {
        super(vertices, verticesNum);
    }
    @Override
    public GeneralPath generatePath() {
        GeneralPath path = new GeneralPath();
        if (_verticesNum == 2) {
            path.moveTo(_vertices.get(0).getX(), _vertices.get(0).getY());
            path.lineTo(_vertices.get(1).getX(), _vertices.get(1).getY());
        }
        if (_verticesNum == 3){
            path.moveTo(_vertices.get(0).getX(), _vertices.get(0).getY());
            path.quadTo(_vertices.get(1).getX(), _vertices.get(1).getY(),_vertices.get(2).getX(), _vertices.get(2).getY());
        }
        return path;
    }
    @Override
    public Shape2D cloneShape2D() {
        return new QuadCurve(_vertices, _verticesNum);
    }
}
