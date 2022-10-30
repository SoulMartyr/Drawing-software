package Shape;

import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.util.Vector;

public class Quad extends Shape2D{
    public Quad(Vector<Vertex> vertices) {
        super(vertices);
    }

    public Quad(Vector<Vertex> vertices, int verticesNum, int lineWidth, Color color) {
        super(vertices, verticesNum, lineWidth, color);
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
        return new Quad(_vertices, _verticesNum, _lineWidth, _color);
    }
}
