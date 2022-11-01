package Shape;

import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.util.Vector;

/**
 * 直线类
 *
 * @author Liu
 * @date 2022/11/01
 */
public class Line extends Shape2D {
    public Line(Vector<Vertex> vertices) {
        super(vertices);
    }

    public Line(Vector<Vertex> vertices, int verticesNum) {
        super(vertices, verticesNum);
    }

    public Line(Vector<Vertex> vertices, int verticesNum, int lineWidth, Color drawColor) {
        super(vertices, verticesNum, lineWidth, drawColor);
    }

    @Override
    public GeneralPath generatePath() {
        GeneralPath path = new GeneralPath();
        if (_verticesNum == 2) {
            path.moveTo(_vertices.get(0).getX(), _vertices.get(0).getY());
            path.lineTo(_vertices.get(1).getX(), _vertices.get(1).getY());
        }
        return path;
    }

    /**
     * 是否需要填充绘制
     * <p>不需要填充绘制，恒返回false
     * @return false
     */
    @Override
    public boolean isFill(){
        return false;
    }

    @Override
    public Shape2D cloneShape2D() {
        return new Line(_vertices, _verticesNum,_lineWidth,_drawColor);
    }


}
