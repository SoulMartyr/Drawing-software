package UI;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能枚举
 * 
 * @author Liu
 * @date 2022/11/01
 */
public enum Function {
    Brush(100), Line(2), Quad(3), Triangle(3), RightTriangle(3), Rectangle(4),
    RoundedRectangle(5), Circle(2), Polygon(5), Eraser(100), LineWidth(0),
    DrawColorChooser(0), DrawColorViewer(0), FillColorChooser(0),FillColorViewer(0);

    /**
     * 顶点数
     */
    private int _verticesNum;

    /**
     * 函数
     *
     * @param num 顶点数
     */
    Function(int num) {
        _verticesNum = num;
    }

    /**
     * 获取顶点数
     *
     * @return int 顶点数
     */
    public int getVerticesNum() {
        return _verticesNum;
    }

    /**
     * 设置顶点数
     *
     * @param num 顶点数
     */
    public void setVerticesNum(int num) {
        _verticesNum = num;
    }

}
