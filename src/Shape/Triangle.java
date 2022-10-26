package Shape;

import java.awt.geom.GeneralPath;

public class Triangle extends Shape2D {
    public Triangle(Vertex[] vertices) {
        super(vertices);
    }

    public Triangle(Vertex[] vertices, int verticesNum) {
        super(vertices, verticesNum);
    }

    @Override
    public GeneralPath generatePath() {
        GeneralPath path = new GeneralPath();
        if (_verticesNum == 2) {
            path.moveTo(_vertices[0].getX(), _vertices[0].getY());
            path.lineTo(_vertices[1].getX(), _vertices[1].getY());
        }
        if (_verticesNum == 3) {
            path.moveTo(_vertices[0].getX(), _vertices[0].getY());
            path.lineTo(_vertices[1].getX(), _vertices[1].getY());
            path.lineTo(_vertices[2].getX(), _vertices[2].getY());
            path.closePath();
        }
        return path;
    }


    @Override
    public Shape2D cloneShape2D() {
        return new Triangle(_vertices, _verticesNum);
    }
}
