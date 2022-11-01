package Shape;

import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.util.Vector;

public class Shape2D {
    protected Vector<Vertex> _vertices;
    protected int _verticesNum;
    protected int _lineWidth;
    protected Color _drawColor, _fillColor;
    protected boolean _isFill;

    public Shape2D() {
        this(null, 0);
    }

    public Shape2D(Vector<Vertex> vertices) {
        this(vertices, 0);
    }

    public Shape2D(Vector<Vertex> vertices, int verticesNum) {
        this(vertices, verticesNum, 4, Color.BLACK, false, Color.WHITE);
    }

    public Shape2D(Vector<Vertex> vertices, int verticesNum, int lineWidth, Color drawColor) {
        this(vertices, verticesNum, lineWidth, drawColor, false, Color.WHITE);
    }

    public Shape2D(Vector<Vertex> vertices, int verticesNum, int lineWidth, Color drawColor, boolean isFill, Color fillColor) {
        _vertices = new Vector<>(vertices);
        _verticesNum = verticesNum;
        _lineWidth = lineWidth;
        _drawColor = drawColor;
        _isFill = isFill;
        _fillColor = fillColor;
    }

    public GeneralPath generatePath() {
        return null;
    }

    public void addVertex(int x, int y) {
        _verticesNum += 1;
        _vertices.add(new Vertex(x, y));
    }

    public void changeVertex(int index, int x, int y) {
        _vertices.get(index).setXY(x, y);
    }

    public int getVerticesNum() {
        return _verticesNum;
    }

    public void setLineWidth(int lineWidth) {
        _lineWidth = lineWidth;
    }

    public int getLineWidth() {
        return _lineWidth;
    }

    public void setDrawColor(Color color) {
        _drawColor = color;
    }

    public Color getDrawColor() {
        return _drawColor;
    }

    public void setFill(boolean isFill) {
        _isFill = isFill;
    }

    public boolean isFill() {
        return _isFill;
    }

    public void setFillColor(Color color) {
        _fillColor = color;
    }

    public Color getFillColor() {
        return _fillColor;
    }

    public void correctVertex() {
        int index = _verticesNum - 1;
        int x1 = _vertices.get(index - 1).getX();
        int y1 = _vertices.get(index - 1).getY();
        int x2 = _vertices.get(index).getX();
        int y2 = _vertices.get(index).getY();
        if (Math.abs(x1 - x2) < Math.abs(y1 - y2)) {
            _vertices.get(index).setX(x1);
        } else {
            _vertices.get(index).setY(y1);
        }

    }

    public Shape2D cloneShape2D() {
        return new Shape2D(_vertices, _verticesNum);
    }

    public void clear() {
        _vertices = new Vector<>(_verticesNum);
        _verticesNum = 0;
    }


}
