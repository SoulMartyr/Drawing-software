package UI;

import java.util.HashMap;
import java.util.Map;

public enum Function {
    Brush(100), Line(2), Curve(3), Triangle(3),RightTriangle(3),Rectangle(4),
    RoundedRectangle(5), Circle(2), Polygon(5), Eraser(100), LineWidth(0), ColorChooser(0), ColorViewer(0);
    private int _verticesNum;

    Function(int num) {
        _verticesNum = num;
    }

    public int getVerticesNum() {
        return _verticesNum;
    }

    public void setVerticesNum(int num) {
        _verticesNum = num;
    }

}
