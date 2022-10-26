package UI;

import Shape.*;

import java.util.Vector;

public class utils {
    public static Shape2D ActionSwitch(Vertex[] vertices, Function func) {
        switch (func) {
            case Line -> {
                return new Line(vertices);
            }
            case Triangle -> {
                return new Triangle(vertices);
            }
            case Rectangle -> {
                return new Rectangle(vertices);
            }
        }
        return null;
    }

    public static void PressedSwitch(Shape2D shape2D, Function func, int x, int y) {
        switch (func) {
            case Line -> {
                shape2D.SetVertex(0, x, y);
                shape2D.SetVertex(1, x, y);
            }
            case Triangle -> {
                if (shape2D.GetVerticesNum() == 0) {
                    shape2D.SetVertex(0, x, y);
                    shape2D.SetVertex(1, x, y);
                } else {
                    shape2D.SetVertex(2, x, y);
                }
            }
            case Rectangle -> {
                if (shape2D.GetVerticesNum() == 0) {
                    shape2D.SetVertex(0, x, y);
                    shape2D.SetVertex(1, x, y);
                } else {
                    shape2D.SetVertex(2, x, y);
                    shape2D.SetVertex(3, 0, 0);
                }
            }
        }
    }

    public static void ReleasedSwitch(Shape2D shape2D, Function func, Vector<Shape2D> shape2DVec, int x, int y) {
        switch (func) {
            case Line -> {
                shape2D.ChangeVertex(1, x, y);
                shape2DVec.add(shape2D.cloneShape2D());
                shape2D.clear();
            }
            case Triangle -> {
                if (shape2D.GetVerticesNum() == 2) {
                    shape2D.ChangeVertex(1, x, y);
                    shape2DVec.add(shape2D.cloneShape2D());
                }
                if (shape2D.GetVerticesNum() == 3) {
                    shape2D.ChangeVertex(2, x, y);
                    shape2DVec.remove(shape2DVec.size() - 1);
                    shape2DVec.add(shape2D.cloneShape2D());
                    shape2D.clear();
                }
            }
            case Rectangle -> {
                if (shape2D.GetVerticesNum() == 2) {
                        shape2D.ChangeVertex(1, x, y);
                        shape2DVec.add(shape2D.cloneShape2D());

                    }
                    if (shape2D.GetVerticesNum() == 4) {
                        shape2D.ChangeVertex(2, x, y);
                    shape2DVec.remove(shape2DVec.size() - 1);
                    shape2DVec.add(shape2D.cloneShape2D());
                    shape2D.clear();
                }
            }
        }

    }


    public static void DraggedSwitch(Shape2D shape2D, Function func, int x, int y) {
        switch (func) {
            case Line -> {
                shape2D.ChangeVertex(1, x, y);
            }
            case Triangle -> {
                if (shape2D.GetVerticesNum() == 2)
                    shape2D.ChangeVertex(1, x, y);
                if (shape2D.GetVerticesNum() == 3)
                    shape2D.ChangeVertex(2, x, y);
            }
            case Rectangle -> {
                if (shape2D.GetVerticesNum() == 2)
                    shape2D.ChangeVertex(1, x, y);
                if (shape2D.GetVerticesNum() == 4)
                    shape2D.ChangeVertex(2, x, y);
            }
        }
    }
}
