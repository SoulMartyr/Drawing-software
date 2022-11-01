package Shape;

import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.util.Vector;

/**
 * 二次曲线类
 *
 * @author Liu
 * @date 2022/11/01
 */
public class Quad extends Shape2D{
    public Quad(Vector<Vertex> vertices) {
        super(vertices);
    }

    public Quad(Vector<Vertex> vertices, int verticesNum, int lineWidth, Color drawColor,boolean isFill,Color fillColor) {
        super(vertices, verticesNum, lineWidth, drawColor, isFill, fillColor);
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
        return new Quad(_vertices, _verticesNum, _lineWidth, _drawColor,_isFill,_fillColor);
    }
}
