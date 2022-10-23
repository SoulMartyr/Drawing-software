package UI;

import Shape.Line;
import Shape.Shape2D;
import Shape.Vertex;

import java.util.Vector;

public class utils {
    public static void PressedSwitch(Vertex[] vertices, Function func, int x, int y) {
        switch (func) {
            case Line -> {
                vertices[0] = new Vertex(x, y);
            }
            case Triangle -> {
            }
        }
    }

    public static void ReleasedSwitch(Vertex[] vertices, Function func, Vector<Shape2D> shape2DVec) {
        switch (func) {
            case Line -> {
                shape2DVec.add(new Line(vertices));
            }
            case Triangle -> {
            }
        }
    }

    public static Shape2D DraggedSwitch(Vertex[] vertices, Function func, Vector<Shape2D> shape2DVec, int x, int y) {
        Shape2D shape2D = new Shape2D();
        switch (func) {
            case Line -> {
                vertices[1] = new Vertex(x, y);
                shape2D = new Line(vertices);
            }
            case Triangle -> {
            }
        }
        return shape2D;
    }
}
