package Shape;

import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

public class Circle extends Shape2D {
    public Circle(Vertex[] vertices) {
        super(vertices);
    }

    public Circle(Vertex[] vertices, int verticesNum) {
        super(vertices, verticesNum);
    }

    @Override
    public GeneralPath generatePath() {
        Ellipse2D circle = new Ellipse2D.Double();
        if (_verticesNum == 2) {
            double r = Vertex.calDistance(_vertices[0], _vertices[1]);
            double x = _vertices[0].getX() - r;
            double y = _vertices[0].getY() - r;
            circle.setFrame(x, y, 2 * r, 2 * r);
        }

        return new GeneralPath(circle);
    }

    @Override
    public Shape2D cloneShape2D() {
        return new Circle(_vertices, _verticesNum);
    }


}
