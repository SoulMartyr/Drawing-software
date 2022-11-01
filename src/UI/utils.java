package UI;

import Shape.*;

import java.util.Vector;

/**
 * 组件类
 * <p>统一管理涉及绘制时监听器的Switch判断
 * @author Liu
 * @date 2022/11/01
 */
public class utils {
    /**
     * ActionPerformed Switch
     *
     * @param vertices 顶点数组
     * @param func     当前绘制图形
     * @return {@link Shape2D}
     */
    public static Shape2D ActionSwitch(Vector<Vertex> vertices, Function func) {
        switch (func) {
            case Line -> {
                return new Line(vertices);
            }
            case Triangle -> {
                return new Triangle(vertices);
            }
            case RightTriangle -> {
                return new RightTriangle(vertices);
            }
            case Rectangle -> {
                return new Rectangle(vertices);
            }
            case Circle -> {
                return new Circle(vertices);
            }
            case RoundedRectangle -> {
                return new RoundedRectangle(vertices);
            }
            case Quad -> {
                return new Quad(vertices);
            }
            case Polygon -> {
                return new Polygon(vertices, Function.Polygon.getVerticesNum());
            }
            case Brush -> {
                return new Brush(vertices);
            }
            case Eraser -> {
                return new Eraser(vertices);
            }
        }
        return null;
    }

    /**
     * MousePressed Switch
     *
     * @param shape2D    图形基类
     * @param func       当前绘制图形
     * @param shape2DVec 记录已绘制图形的数组
     * @param x          x坐标
     * @param y          y坐标
     */
    public static void PressedSwitch(Shape2D shape2D, Function func, Vector<Shape2D> shape2DVec, int x, int y) {
        switch (func) {
            case Line, Circle -> {
                shape2D.addVertex(x, y);
                shape2D.addVertex(x, y);
            }
            case Triangle, RightTriangle, Quad -> {
                if (shape2D.getVerticesNum() == 0) {
                    shape2D.addVertex(x, y);
                } else {
                    shape2DVec.remove(shape2DVec.size() - 1);
                }
                shape2D.addVertex(x, y);
            }
            case Rectangle -> {
                if (shape2D.getVerticesNum() != 0) {
                    shape2DVec.remove(shape2DVec.size() - 1);
                }
                shape2D.addVertex(x, y);
                shape2D.addVertex(x, y);
            }
            case RoundedRectangle -> {
                if (shape2D.getVerticesNum() == 0) {
                    shape2D.addVertex(x, y);
                    shape2D.addVertex(x, y);
                } else {
                    shape2DVec.remove(shape2DVec.size() - 1);
                    if (shape2D.getVerticesNum() == 2) {
                        shape2D.addVertex(x, y);
                        shape2D.addVertex(x, y);
                    } else {
                        shape2D.addVertex(x, y);
                    }
                }
            }
            case Polygon -> {
                if (shape2D.getVerticesNum() == 0) {
                    shape2D.addVertex(x, y);
                }
                shape2D.addVertex(x, y);
            }
            case Brush, Eraser -> {
                shape2D.addVertex(x, y);
            }
        }
    }

    /**
     * MouseReleased Switch
     *
     * @param shape2D    图形基类
     * @param func       当前绘制图形
     * @param shape2DVec 记录已绘制图形的数组
     * @param x          x坐标
     * @param y          y坐标
     */
    public static void ReleasedSwitch(Shape2D shape2D, Function func, Vector<Shape2D> shape2DVec, int x, int y) {
        switch (func) {
            case Line, Circle, Brush, Eraser -> {
                shape2DVec.add(shape2D.cloneShape2D());
                shape2D.clear();
            }
            case Triangle, RightTriangle, Quad -> {
                if (shape2D.getVerticesNum() == 2) {
                    shape2DVec.add(shape2D.cloneShape2D());
                }
                if (shape2D.getVerticesNum() == 3) {
                    shape2DVec.add(shape2D.cloneShape2D());
                    shape2D.clear();
                }
            }
            case Rectangle -> {
                if (shape2D.getVerticesNum() == 2) {
                    shape2DVec.add(shape2D.cloneShape2D());
                }
                if (shape2D.getVerticesNum() == 4) {
                    shape2DVec.add(shape2D.cloneShape2D());
                    shape2D.clear();
                }
            }
            case RoundedRectangle -> {
                if (shape2D.getVerticesNum() == 2) {
                    shape2DVec.add(shape2D.cloneShape2D());
                }
                if (shape2D.getVerticesNum() == 4) {
                    shape2DVec.add(shape2D.cloneShape2D());
                }
                if (shape2D.getVerticesNum() == 5) {
                    shape2DVec.add(shape2D.cloneShape2D());
                    shape2D.clear();
                }
            }
            case Polygon -> {
                if (shape2D.getVerticesNum() == Function.Polygon.getVerticesNum()) {
                    shape2DVec.add(shape2D.cloneShape2D());
                    shape2D.clear();
                }
            }
        }

    }


    /**
     * MouseDragged Switch
     *
     * @param shape2D    图形基类
     * @param func       当前绘制图形
     * @param x          x坐标
     * @param y          y坐标
     */
    public static void DraggedSwitch(Shape2D shape2D, Function func, int x, int y) {
        switch (func) {
            case Line, Circle -> {
                shape2D.changeVertex(1, x, y);
            }
            case Triangle, RightTriangle, Quad -> {
                if (shape2D.getVerticesNum() == 2)
                    shape2D.changeVertex(1, x, y);
                if (shape2D.getVerticesNum() == 3)
                    shape2D.changeVertex(2, x, y);
            }
            case Rectangle -> {
                if (shape2D.getVerticesNum() == 2)
                    shape2D.changeVertex(1, x, y);
                if (shape2D.getVerticesNum() == 4)
                    shape2D.changeVertex(2, x, y);
            }
            case RoundedRectangle -> {
                if (shape2D.getVerticesNum() == 2)
                    shape2D.changeVertex(1, x, y);
                if (shape2D.getVerticesNum() == 4)
                    shape2D.changeVertex(2, x, y);
                if (shape2D.getVerticesNum() == 5)
                    shape2D.changeVertex(4, x, y);
            }
            case Polygon -> {
                shape2D.changeVertex(shape2D.getVerticesNum() - 1, x, y);
            }
            case Brush, Eraser -> {
                shape2D.addVertex(x, y);
            }
        }
    }
}
