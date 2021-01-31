package com.chao.pictureview;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class PictureView {
    //创建Frame对象
    private Frame frame=new Frame();
    //创建菜单条
    private MenuBar menuBar=new MenuBar();
    //创建文件
    private Menu menu=new Menu("文件");
    //创建打开与保存
    private MenuItem openMenu=new MenuItem("打开");
    private MenuItem seveMenu=new MenuItem("保存");

    //创建BufferedImage对象
    private BufferedImage image;
    //创建画布
    private class Mycavas extends Canvas{
        @Override
        public void paint(Graphics g) {
            g.drawImage(image,0,0,null);
        }
    }
    Mycavas mycavas=new Mycavas();

    //组装界面
    private void init() {
        //组装菜单界面
        openMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileDialog fileDialog=new FileDialog(frame,"打开",FileDialog.LOAD);
                fileDialog.setVisible(true);
                String directory = fileDialog.getDirectory();
                String file = fileDialog.getFile();

                try {
                    image= ImageIO.read(new File(directory,file));
                    mycavas.repaint();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        seveMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileDialog fileDialog=new FileDialog(frame,"保存",FileDialog.SAVE);
                fileDialog.setVisible(true);
                String directory = fileDialog.getDirectory();
                String file = fileDialog.getFile();
                try {
                 ImageIO.write(image,"JPEG",new File(directory,file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        menu.add(openMenu);
        menu.add(seveMenu);
        menuBar.add(menu);
        frame.setMenuBar(menuBar);

        //放入画布
        mycavas.setPreferredSize(new Dimension(500,500));
        frame.add(mycavas);

        //适配
        frame.pack();
        frame.setVisible(true);
        frame.setLocation(500,500);
        //关闭窗口
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    public static void main(String[] args) {
        new PictureView().init();
    }

}
