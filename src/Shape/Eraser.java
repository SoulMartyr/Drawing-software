package Shape;

import java.awt.Color;
import java.util.Vector;

/**
 * 橡皮擦类
 *
 * @author Liu
 * @date 2022/11/01
 */
public class Eraser extends Brush{
    public Eraser(Vector<Vertex> vertices) {
        super(vertices);
    }


    public Eraser(Vector<Vertex> vertices, int verticesNum, int lineWidth, Color color) {
        super(vertices, verticesNum, lineWidth, color);
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
        return new Eraser(_vertices, _verticesNum, _lineWidth, _drawColor);
    }
}
