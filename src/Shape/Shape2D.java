package Shape;

import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.util.Vector;

public class Shape2D {
    protected Vector<Vertex> _vertices;
    protected int _verticesNum;
    protected int _lineWidth;
    protected Color _color;

    public Shape2D() {
        this(null, 0);
    }

    public Shape2D(Vector<Vertex> vertices) {
        this(vertices, 0);
    }

    public Shape2D(Vector<Vertex> vertices, int verticesNum) {
        this(vertices, verticesNum, 4, Color.BLACK);
    }

    public Shape2D(Vector<Vertex> vertices, int verticesNum, int lineWidth, Color color) {
        _vertices = new Vector<>(vertices);
        _verticesNum = verticesNum;
        _lineWidth = lineWidth;
        _color = color;
    }

    public GeneralPath generatePath() {
        return null;
    }

    public void AddVertex(int x, int y) {
        _verticesNum += 1;
        _vertices.add(new Vertex(x, y));
    }

    public void ChangeVertex(int index, int x, int y) {
        _vertices.set(index, new Vertex(x, y));
    }

    public int GetVerticesNum() {
        return _verticesNum;
    }

    public void SetLineWidth(int lineWidth) {
        _lineWidth = lineWidth;
    }

    public int GetLineWidth() {
        return _lineWidth;
    }

    public void SetColor(Color color) {
        _color = color;
    }

    public Color GetColor() {
        return _color;
    }

    public Shape2D cloneShape2D() {
        return new Shape2D(_vertices, _verticesNum);
    }

    public void clear() {
        _vertices = new Vector<>(_verticesNum);
        _verticesNum = 0;
    }


}
