package Shape;

import java.awt.geom.GeneralPath;

public class Line extends Shape2D {
    public Line(Vertex[] vertices) {
        super(vertices);
    }

    public Line(Vertex[] vertices, int verticesNum) {
        super(vertices, verticesNum);
    }

    @Override
    public GeneralPath generatePath() {
        GeneralPath path = new GeneralPath();
        if (_verticesNum == 2) {
            path.moveTo(_vertices[0].getX(), _vertices[0].getY());
            path.lineTo(_vertices[1].getX(), _vertices[1].getY());
        }
        return path;
    }

    @Override
    public Shape2D cloneShape2D() {
        return new Line(_vertices, _verticesNum);
    }
}
