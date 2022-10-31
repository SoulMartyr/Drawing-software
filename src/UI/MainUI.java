package UI;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferStrategy;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import Shape.*;

/**
 * @author LiuJiayuan
 * @version 1.0
 */
public class MainUI extends JFrame {
    private JMenuBar _menubar;
    private JMenu _menuFile, _menuOperate;
    private JMenuItem _menuOpen, _menuSave, _menuCancel, _menuRedo, _menuBgColor;
    private Vector<JMenuItem> _menuItemVec;

    private JToolBar _toolBar;
    private JButton _btnLine, _btnQuad, _btnTriangle, _btnRightTriangle, _btnRectangle, _btnRoundedRectangle,
            _btnCircle, _btnPolygon, _btnBrush, _btnEraser, _btnDrawColorChooser, _drawColorViewer, _btnFillColorChooser, _btnLineWidth, _fillColorViewer;
    private JCheckBox _btnFill;
    private Vector<JButton> _toolBtnVec;

    private Container _container;
    private JPanel _canvasPanel;
    private Canvas _canvas;

    private Function _func;
    private Vector<Vertex> _vertices;
    private BufferStrategy _strategy;
    private Shape2D _shape2D;
    private Vector<Shape2D> _shape2DVec;
    private int _lineWidth;
    private boolean _isFill;
    private Color _cvColor, _bgColor, _fillColor;
    private JButton _preBtn;
    private int _vecIndex;

    /**
     * 初始化窗口与控件
     */
    public MainUI() {
        super("Painting");
        InitSizeAndPos();
        InitMenuBar();
        InitToolBar();
        InitCanvas();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        InitMenuListener();
        InitBtnListener();
        InitCanvasListener();
    }

    /**
     * 依据屏幕大小初始化窗口大小<br>
     * 窗口位于屏幕中心且长宽均为屏幕长宽的0.75
     */
    private void InitSizeAndPos() {
        Toolkit toolKit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolKit.getScreenSize();
        double width = screenDimension.getWidth();
        double height = screenDimension.getHeight();
        setSize((int) width * 3 / 4, (int) height * 3 / 4);
        setLocationRelativeTo(null);
    }

    /**
     * 初始化菜单栏
     */
    private void InitMenuBar() {
        _menuFile = new JMenu("File");
        _menuOperate = new JMenu("Operate");
        _menuOpen = new JMenuItem("Open");
        _menuSave = new JMenuItem("Save");
        _menuCancel = new JMenuItem("Cancel");
        _menuRedo = new JMenuItem("Redo");
        _menuBgColor = new JMenuItem("Background Color");

        _menuFile.add(_menuOpen);
        _menuFile.add(_menuSave);
        _menuOperate.add(_menuCancel);
        _menuOperate.add(_menuRedo);
        _menuOperate.add(_menuBgColor);

        _menuItemVec = new Vector<>();
        _menuItemVec.add(_menuOpen);
        _menuItemVec.add(_menuSave);
        _menuItemVec.add(_menuCancel);
        _menuItemVec.add(_menuRedo);
        _menuItemVec.add(_menuBgColor);

        _menubar = new JMenuBar();
        _menubar.add(_menuFile);
        _menubar.add(_menuOperate);

        setJMenuBar(_menubar);
    }

    private void InitToolBar() {
        String[] _toolBtnStr = {"Brush", "Line", "Quad", "Triangle", "RightTriangle", "Rectangle", "RoundedRectangle",
                "Circle", "Polygon", "Eraser", "LineWidth", "DrawColorChooser","DrawColorViewer","FillColorChooser","FillColorViewer"};

        _toolBtnVec = new Vector<>();
        //绘图类
        _btnBrush = new JButton();
        _toolBtnVec.add(_btnBrush);
        _btnBrush.setToolTipText("Draw free.");
        _btnLine = new JButton();
        _toolBtnVec.add(_btnLine);
        _btnLine.setToolTipText("Draw Line.");
        _btnQuad = new JButton();
        _toolBtnVec.add(_btnQuad);
        _btnQuad.setToolTipText("Draw Quadratic Curve.");
        _btnTriangle = new JButton();
        _toolBtnVec.add(_btnTriangle);
        _btnTriangle.setToolTipText("Draw Triangle.");
        _btnRightTriangle = new JButton();
        _toolBtnVec.add(_btnRightTriangle);
        _btnRightTriangle.setToolTipText("Draw RightTriangle.");
        _btnRectangle = new JButton();
        _toolBtnVec.add(_btnRectangle);
        _btnRectangle.setToolTipText("Draw Rectangle.");
        _btnRoundedRectangle = new JButton();
        _toolBtnVec.add(_btnRoundedRectangle);
        _btnRoundedRectangle.setToolTipText("Draw RoundedRectangle.");
        _btnCircle = new JButton();
        _toolBtnVec.add(_btnCircle);
        _btnCircle.setToolTipText("Draw Circle.");
        _btnPolygon = new JButton();
        _toolBtnVec.add(_btnPolygon);
        _btnCircle.setToolTipText("Draw Polygon.Right click to set vertices number.");
        //操作类
        _btnEraser = new JButton();
        _toolBtnVec.add(_btnEraser);
        _btnEraser.setToolTipText("Eraser.");
        _btnLineWidth = new JButton();
        _toolBtnVec.add(_btnLineWidth);
        _btnLineWidth.setToolTipText("Set Line Width.");
        //颜色类
        _btnDrawColorChooser = new JButton();
        _toolBtnVec.add(_btnDrawColorChooser);
        _btnDrawColorChooser.setToolTipText("Set color of draw.");
        _drawColorViewer = new JButton();
        _toolBtnVec.add(_drawColorViewer);
        _btnFillColorChooser = new JButton();
        _toolBtnVec.add(_btnFillColorChooser);
        _fillColorViewer = new JButton();
        _toolBtnVec.add(_fillColorViewer);
        _btnFillColorChooser.setToolTipText("Set color of fill.");

        _btnFill = new JCheckBox("Fill");
        _btnFill.setSize(32,32);
        _btnFill.setFont(new Font("宋体", 0, 16));

        for (int i = 0; i < _toolBtnVec.size(); i++) {
            JButton btn = _toolBtnVec.get(i);
            String functionName = Function.values()[i].toString();
            if (functionName.equals("DrawColorViewer") || functionName.equals("FillColorViewer")) {
                btn.setText("     ");
                btn.setFont(new Font("宋体", 1, 30));
                btn.setOpaque(true);
                btn.setEnabled(false);
                btn.setBackground(Color.BLACK);
            } else {
                btn.setText(functionName);
                btn.setFont(new Font("宋体", 1, 0));
                btn.setIcon(new ImageIcon(MainUI.class.getResource("/Icon/" + _toolBtnStr[i] + ".png")));
            }
            btn.setFocusPainted(false);
        }


        _toolBar = new JToolBar("ToolBar");

        for (int i = 0; i < _toolBtnVec.size(); i++) {
            JButton btn = _toolBtnVec.get(i);
            String functionName = Function.values()[i].toString();
            switch (functionName) {
                case "Brush" -> {
                    _toolBar.add(new JLabel("Draw"));
                    _toolBar.addSeparator();
                }
                case "Eraser" -> {
                    _toolBar.addSeparator();
                    _toolBar.add(new JLabel("Tool"));
                    _toolBar.addSeparator();
                }
                case "DrawColorChooser" -> {
                    _toolBar.addSeparator();
                    _toolBar.add(new JLabel("Current Draw Color"));
                    _toolBar.addSeparator();
                }
                case "FillColorChooser" ->{
                    _toolBar.addSeparator();
                    _toolBar.add(_btnFill);
                    _toolBar.addSeparator();
                    _toolBar.add(new JLabel("Current Fill Color"));
                    _toolBar.addSeparator();
                }
            }
            _toolBar.add(btn);
        }

        _container = getContentPane();
        _container.setLayout(new BorderLayout());
        _container.add(_toolBar, BorderLayout.NORTH);

    }

    private void InitCanvas() {
        _canvasPanel = new JPanel();
        _canvasPanel.setLayout(new GridLayout());
        _canvas = new Canvas() {
            @Override
            public void paint(Graphics g) {
                g.clearRect(0, 0, _canvas.getWidth(), _canvas.getHeight());
                Graphics2D g2D = (Graphics2D) g;
                for (int i = 0; i < _vecIndex; i++) {
                    Shape2D shape2D = _shape2DVec.get(i);
                    PaintShape2D(g2D, shape2D);
                }
            }
        };
        _canvas.setBackground(Color.WHITE);
        _canvas.setSize(new Dimension(this.getWidth(), this.getHeight()));
        _canvasPanel.add(_canvas);
        _container.add(_canvasPanel);

        _shape2DVec = new Vector<>();
        _func = Function.Line;
        _preBtn = _btnLine;

        _lineWidth = 4;
        _cvColor = Color.BLACK;
        _bgColor = Color.WHITE;
        _isFill = false;
        _fillColor = Color.BLACK;

        _vecIndex = 0;


    }

    private void InitMenuListener() {
        for (JMenuItem jMenuItem : _menuItemVec) {
            jMenuItem.addActionListener(new MenuItemListener());
        }
    }

    private void InitBtnListener() {
        for (JButton btn : _toolBtnVec) {
            btn.addActionListener(new BtnListener());
        }
        _btnFill.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                _isFill = !_isFill;
            }
        });
    }

    private void InitCanvasListener() {
        _canvas.createBufferStrategy(2);
        _strategy = _canvas.getBufferStrategy();
        _canvas.addMouseListener(new CanvasListener());
        _canvas.addMouseMotionListener(new CanvasListener());
        _btnLine.doClick();
        _btnLine.setEnabled(false);
    }

    private void PaintShape2D(Graphics2D graphics2D, Shape2D shape2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(shape2D.GetDrawColor());
        graphics2D.setStroke(new BasicStroke(shape2D.GetLineWidth()));
        GeneralPath path = shape2D.generatePath();
        graphics2D.draw(path);
        if(shape2D.isFill()){
            graphics2D.setColor(shape2D.GetFillColor());
            graphics2D.fill(path);
        }
    }

    private class MenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            switch (str) {
                case "Open" -> {
                    JFileChooser fileChooser = new JFileChooser("D:\\");
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Png Img (*.png)", "png"));
                    int result = fileChooser.showOpenDialog(MainUI.this);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        try {
                            BufferedImage image = ImageIO.read(file);//获取图片
                            Graphics g = _canvas.getGraphics();
                            g.drawImage(image, 0, 0, _canvas.getWidth(), _canvas.getHeight(), MainUI.this);
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }

                }
                case "Save" -> {
                    BufferedImage img = new BufferedImage(
                            _canvas.getWidth(), _canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2D = img.createGraphics();
                    g2D.setColor(_bgColor);
                    g2D.fillRect(0, 0, _canvas.getWidth(), _canvas.getHeight());
                    for (Shape2D shape2D : _shape2DVec) {
                        PaintShape2D(g2D, shape2D);
                    }

                    JFileChooser fileChooser = new JFileChooser("D:\\");
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Png Img (*.png)", "png"));
                    int result = fileChooser.showSaveDialog(MainUI.this);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        if (!file.getAbsolutePath().endsWith(".png"))
                            file = new File(file.getAbsolutePath() + ".png");
                        try {
                            ImageIO.write(img, "png", file);
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }
                }
                case "Cancel" -> {
                    try {
                        _vecIndex -= 1;
                        _canvas.paint(_canvas.getGraphics());
                    } catch (IndexOutOfBoundsException iobe) {
                        _vecIndex += 1;
                        iobe.printStackTrace();
                    }
                }
                case "Redo" -> {
                    try {
                        _vecIndex += 1;
                        _canvas.paint(_canvas.getGraphics());
                    } catch (IndexOutOfBoundsException iobe) {
                        _vecIndex -= 1;
                        iobe.printStackTrace();
                    }
                }
                case"Background Color" ->{
                    Color color = JColorChooser.showDialog(MainUI.this, "Select a color", _cvColor);

                    _bgColor = color;
                    _canvas.setBackground(color);
                }

            }
        }
    }

    private class BtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            switch (str) {
                case "Brush", "Line", "Quad", "Triangle", "RightTriangle", "Rectangle", "RoundedRectangle",
                        "Circle", "Polygon", "Eraser" -> {

                    _func = Function.valueOf(e.getActionCommand());
                    _vertices = new Vector<>(_func.getVerticesNum());
                    _shape2D = utils.ActionSwitch(_vertices, _func);

                    JButton lastBtn = (JButton) e.getSource();
                    lastBtn.setEnabled(false);
                    _preBtn.setEnabled(true);

                    _preBtn = lastBtn;
                }
                case "LineWidth" -> {
                    String strLineWidth = JOptionPane.showInputDialog(MainUI.this, "LineWidth",
                            "Input LineWidth", JOptionPane.QUESTION_MESSAGE);
                    try {
                        _lineWidth = Integer.parseInt(strLineWidth);
                        _shape2D.SetLineWidth(_lineWidth);
                    } catch (NullPointerException | NumberFormatException ne) {
                        ne.printStackTrace();
                    }
                }
                case "DrawColorChooser" -> {
                    Color color = JColorChooser.showDialog(null, "Select a color", _cvColor);

                    _cvColor = color;
                    _drawColorViewer.setBackground(color);
                    _shape2D.SetDrawColor(_cvColor);
                }
                case "FillColorChooser" -> {
                    Color color = JColorChooser.showDialog(null, "Select a color", _cvColor);

                    _fillColor = color;
                    _fillColorViewer.setBackground(color);
                }
            }
        }
    }

    private class CanvasListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == 3 && _func == Function.Polygon) {
                String strVerticesNum = JOptionPane.showInputDialog(MainUI.this, "Vertices Num(default:5)",
                        "Input VerticesNum", JOptionPane.QUESTION_MESSAGE);
                try {
                    Function.Polygon.setVerticesNum(Integer.parseInt(strVerticesNum));
                    _shape2D = utils.ActionSwitch(_vertices, _func);
                } catch (NullPointerException | NumberFormatException ne) {
                    ne.printStackTrace();
                }
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getButton() != 1) return;
            super.mousePressed(e);
            try {
                int count = _shape2DVec.size() - _vecIndex;
                if (count > 0) {
                    for (int i = 0; i < count; i++) {
                        _shape2DVec.remove(_shape2DVec.size() - 1);
                    }
                    _shape2D.clear();
                }

                _shape2D.SetLineWidth(_lineWidth);
                _shape2D.SetFill(_isFill);
                _shape2D.SetFillColor(_fillColor);

                utils.PressedSwitch(_shape2D, _func, _shape2DVec, e.getX(), e.getY());

                if (_func == Function.Eraser)
                    _shape2D.SetDrawColor(_bgColor);
                else
                    _shape2D.SetDrawColor(_cvColor);

            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }


        @Override
        public void mouseReleased(MouseEvent e) {
            if(e.getButton() != 1) return;
            super.mouseReleased(e);
            try {
                utils.ReleasedSwitch(_shape2D, _func, _shape2DVec, e.getX(), e.getY());
                _vecIndex = _shape2DVec.size();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            try {
                do {
                    do {
                        Graphics2D graphics = (Graphics2D) _strategy.getDrawGraphics();

                        graphics.clearRect(0, 0, _canvas.getWidth(), _canvas.getHeight());
                        for (Shape2D shape2D : _shape2DVec) {
                            PaintShape2D(graphics, shape2D);
                        }

                        utils.DraggedSwitch(_shape2D, _func, e.getX(), e.getY());
                        PaintShape2D(graphics, _shape2D);

                        graphics.dispose();

                    } while (_strategy.contentsRestored());
                    _strategy.show();

                } while (_strategy.contentsLost());
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
    }


}
