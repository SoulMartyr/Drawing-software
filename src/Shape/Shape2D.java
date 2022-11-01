package Shape;

import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.util.Vector;

/**
 * 图形基类
 *
 * @author Liu
 * @date 2022/11/01
 */
public class Shape2D {
    /**
     * 顶点数组
     */
    protected Vector<Vertex> _vertices;
    /**
     * 已绘制顶点数
     */
    protected int _verticesNum;
    /**
     * 线宽
     */
    protected int _lineWidth;
    /**
     * 绘制颜色、填充颜色
     */
    protected Color _drawColor, _fillColor;
    /**
     * 是否填充绘制
     */
    protected boolean _isFill;

    /**
     * 无参构造
     */
    public Shape2D() {
        this(null, 0);
    }

    /**
     * 单参构造
     *<p>一般用于绘制开始时的初始化
     * @param vertices 顶点数组
     */
    public Shape2D(Vector<Vertex> vertices) {
        this(vertices, 0);
    }

    /**
     * 双参构造
     *<p>一般用于绘制简单图形或暂时使用的图形时的初始化
     * @param vertices    顶点数组
     * @param verticesNum 已绘制顶点数
     */
    public Shape2D(Vector<Vertex> vertices, int verticesNum) {
        this(vertices, verticesNum, 4, Color.BLACK, false, Color.WHITE);
    }

    /**
     * 四参构造
     *<p>一般用于不需要填充图形的初始化或深拷贝
     * @param vertices    顶点数组
     * @param verticesNum 已绘制顶点数
     * @param lineWidth   线宽
     * @param drawColor   绘制颜色
     */
    public Shape2D(Vector<Vertex> vertices, int verticesNum, int lineWidth, Color drawColor) {
        this(vertices, verticesNum, lineWidth, drawColor, false, Color.WHITE);
    }

    /**
     * 六参构造
     *<p>一般用于复杂图形的初始化与深拷贝
     * @param vertices    顶点数组
     * @param verticesNum 已绘制顶点数
     * @param lineWidth   线宽
     * @param drawColor   绘制颜色
     * @param isFill      是否填充绘制
     * @param fillColor   填充颜色
     */
    public Shape2D(Vector<Vertex> vertices, int verticesNum, int lineWidth, Color drawColor, boolean isFill, Color fillColor) {
        _vertices = new Vector<>(vertices);
        _verticesNum = verticesNum;
        _lineWidth = lineWidth;
        _drawColor = drawColor;
        _isFill = isFill;
        _fillColor = fillColor;
    }

    /**
     * 生成路径
     * <br> 通过GeneralPath实现多态的绘制
     * @return {@link GeneralPath}
     */
    public GeneralPath generatePath() {
        return null;
    }

    /**
     * 添加顶点
     *
     * @param x x坐标
     * @param y y坐标
     */
    public void addVertex(int x, int y) {
        _verticesNum += 1;
        _vertices.add(new Vertex(x, y));
    }

    /**
     * 改变顶点
     *
     * @param index 索引
     * @param x     x坐标
     * @param y     y坐标
     */
    public void changeVertex(int index, int x, int y) {
        _vertices.get(index).setXY(x, y);
    }

    /**
     * 获取已绘制顶点数
     *
     * @return int 已绘制顶点数
     */
    public int getVerticesNum() {
        return _verticesNum;
    }

    /**
     * 设置线宽
     *
     * @param lineWidth 线宽
     */
    public void setLineWidth(int lineWidth) {
        _lineWidth = lineWidth;
    }

    /**
     * 获取线宽
     *
     * @return int 线宽
     */
    public int getLineWidth() {
        return _lineWidth;
    }

    /**
     * 设置绘制颜色
     *
     * @param color 绘制颜色
     */
    public void setDrawColor(Color color) {
        _drawColor = color;
    }

    /**
     * 获取绘制颜色
     *
     * @return {@link Color}
     */
    public Color getDrawColor() {
        return _drawColor;
    }

    /**
     * 设置是否填充绘制
     *
     * @param isFill 是否填充绘制
     */
    public void setFill(boolean isFill) {
        _isFill = isFill;
    }

    /**
     * 判断是否填充绘制
     *
     * @return boolean 是否填充绘制
     */
    public boolean isFill() {
        return _isFill;
    }

    /**
     * 设置填充颜色
     *
     * @param color 填充颜色
     */
    public void setFillColor(Color color) {
        _fillColor = color;
    }

    /**
     * 获取填充颜色
     *
     * @return {@link Color} 填充颜色
     */
    public Color getFillColor() {
        return _fillColor;
    }

    /**
     * 矫正顶点
     * <br>用于shift键被按下时确保当前绘制直线水平或垂直
     */
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

    /**
     * 深拷贝
     *
     * @return {@link Shape2D}
     */
    public Shape2D cloneShape2D() {
        return new Shape2D(_vertices, _verticesNum);
    }

    /**
     * 重置
     */
    public void clear() {
        _vertices = new Vector<>(_verticesNum);
        _verticesNum = 0;
    }


}
