package studio.visualdust.product.codetomd.gui;

import studio.visualdust.product.codetomd.method.EventRW;
import studio.visualdust.product.codetomd.method.FileWriter;
import studio.visualdust.product.codetomd.method.PathWalker;
import studio.visualdust.product.codetomd.resource.Resource;
import studio.visualdust.product.codetomd.structure.MDStream;
import studio.visualdust.product.gztwigets.GButton;
import studio.visualdust.product.gztwigets.GMessageWindow;
import studio.visualdust.product.gztwigets.GTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Vector;

public class MainFrame extends JFrame {
    static int WIDTH = 600;
    static int HEIGHT = 480;
    static int WIDTH_DIV = 50;
    static int HEIGHT_DIV = 50;
    static int HEIGHT_WIG = 50;

    GTextField pathField = new GTextField("请输入一个目录");
    GTextField outputFileFiled = new GTextField("请填写一个输出文件(带有目录的)");
    JList typeList = new JList();
    JScrollPane typeScollPane = new JScrollPane(typeList);
    GButton typeAddButton = new GButton("添加类型");
    GButton okButton = new GButton("开始");
    GButton exitButton = new GButton("退出");

    Vector<String> typeVector = new Vector<>();

    public MainFrame() {
        MainFrame me = this;
        this.setLayout(null);
        this.setUndecorated(true);
        this.getContentPane().setBackground(new Color(255, 255, 255));
        this.setSize(WIDTH, HEIGHT);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - WIDTH) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - HEIGHT) / 2);

        this.add(pathField);
        pathField.SetSize(new Dimension(WIDTH - 2 * WIDTH_DIV, HEIGHT_WIG));
        pathField.setLocation(WIDTH_DIV, HEIGHT_DIV);

        this.add(outputFileFiled);
        outputFileFiled.SetTipColor(new Color(255, 122, 0));
        outputFileFiled.SetSize(new Dimension(WIDTH - 2 * WIDTH_DIV, HEIGHT_WIG));
        outputFileFiled.setLocation(WIDTH_DIV, HEIGHT_DIV + HEIGHT_WIG + 30);

        this.add(typeScollPane);
        typeScollPane.setSize(WIDTH - 2 * WIDTH_DIV, 2 * HEIGHT_WIG);
        typeScollPane.setLocation(WIDTH_DIV, HEIGHT_DIV + 2 * HEIGHT_WIG + 30 + 30);
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteMenuItem = new JMenuItem("删除");
        deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (typeList.getSelectedIndex() != -1) {
                    typeVector.removeElementAt(typeList.getSelectedIndex());
                    typeList.setListData(typeVector);
                }
            }
        });
        popupMenu.add(deleteMenuItem);
        typeList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.show(typeScollPane, e.getX(), e.getY());
                }
            }
        });

        this.add(typeAddButton);
        typeAddButton.SetSize(new Dimension(WIDTH - 2 * WIDTH_DIV, HEIGHT_WIG / 2));
        typeAddButton.setLocation(WIDTH_DIV, HEIGHT_DIV + 4 * HEIGHT_WIG + 30 + 30);
        typeAddButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GMessageWindow messageWindow = new GMessageWindow(me, 2, "输入类型(例如:\".cpp\")");
                messageWindow.okButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String type = messageWindow.getText();
                        if (!type.equals("") && !isIn(typeVector, type))
                            typeVector.add(type);
                        typeList.setListData(typeVector);
                        messageWindow.setVisible(false);
                    }
                });
            }
        });

        this.add(exitButton);
        exitButton.SetBackColor(new Color(169, 30, 0));
        exitButton.SetForeColor(new Color(255, 255, 255));
        exitButton.SetSize(new Dimension((int) ((WIDTH - 2 * WIDTH_DIV) / 2.0), HEIGHT_WIG));
        exitButton.setLocation(WIDTH_DIV, HEIGHT_DIV + 5 * HEIGHT_WIG + 30 + 30 + 20);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EventRW.Write("User Exited");
                System.exit(0);
            }
        });

        this.add(okButton);
        okButton.SetSize(new Dimension((int) ((WIDTH - 2 * WIDTH_DIV) / 2.0), HEIGHT_WIG));
        okButton.setLocation(exitButton.getLocation().x + exitButton.getWidth(), HEIGHT_DIV + 5 * HEIGHT_WIG + 30 + 30 + 20);
        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                File dictionary = new File(pathField.getText());
                File outputFile = new File(outputFileFiled.getText());
                Vector<String> knownType = new Vector<>();
                for (int i = 0; i < typeVector.size(); i++) {
                    if (isIn(Resource.knownFileType, typeVector.elementAt(i)))
                        knownType.add(Resource.knownCodeType.elementAt(getIndexOf(Resource.knownFileType, typeVector.elementAt(i))));
                    else knownType.add("UnknownType");
                }
                if (dictionary.isDirectory() && knownType.size() != 0) {
                    FileWriter.WriteLineUTF8(outputFile, " ", false);
                    for (int i = 0; i < typeVector.size(); i++) {
                        PathWalker pathWalker = new PathWalker(dictionary, typeVector.elementAt(i));
                        pathWalker.walk();
                        MDStream mdStream = new MDStream(knownType.elementAt(i));
                        mdStream.addFile(pathWalker.getLinedFiles());
                        mdStream.output(outputFile);
                    }
                    FileWriter.WriteLineUTF8(outputFile,"Auto by "+Resource.softName+" , "+Resource.author+"\n",true);
                } else {
                    new GMessageWindow(me,0,"输入有问题");
                }
            }
        });

        this.setVisible(true);
    }

    boolean isIn(Vector<String> vector, String string) {
        for (int i = 0; i < vector.size(); i++) {
            if (string.equals(vector.elementAt(i)))
                return true;
        }
        return false;
    }

    int getIndexOf(Vector<String> vector, String string) {
        for (int i = 0; i < vector.size(); i++)
            if (vector.elementAt(i).equals(string))
                return i;
        return -1;
    }
}
