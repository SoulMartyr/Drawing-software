package Shape;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.Vector;

public class Circle extends Shape2D {
    public Circle(Vector<Vertex> vertices) {super(vertices); }

    public Circle(Vector<Vertex> vertices, int verticesNum, int lineWidth, Color color) {
        super(vertices, verticesNum, lineWidth, color);
    }
    @Override
    public GeneralPath generatePath() {
        Ellipse2D circle = new Ellipse2D.Double();
        if (_verticesNum == 2) {
            double r = Vertex.calDistance(_vertices.get(0), _vertices.get(1));
            double x = _vertices.get(0).getX() - r;
            double y = _vertices.get(0).getY() - r;
            circle.setFrame(x, y, 2 * r, 2 * r);
        }

        return new GeneralPath(circle);
    }

    @Override
    public Shape2D cloneShape2D() {
        return new Circle(_vertices, _verticesNum, _lineWidth, _color);
    }


}
