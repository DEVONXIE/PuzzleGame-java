package com.weiwei.test;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;


public class TestJFrame extends JFrame implements ActionListener {
    JButton jb1 = new JButton("按钮1");
    JButton jb2 = new JButton("按钮2");

    public TestJFrame() {
        setTitle("Test");
        //取消居中
        setLayout(null);
        //设置窗口大小
        setSize(600, 600);
        //设置关闭模式
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置居中
        setLocationRelativeTo(null);
        //置顶
        setAlwaysOnTop(true);

        jb1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("单机");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("按下");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("释放");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("划入");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("划出");
            }
        });

        jb1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("按着不送");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("松开");
            }
        });
        jb2.addActionListener(this);

        //设置按钮位置和大小
        jb1.setBounds(0, 0, 100, 100);
        jb2.setBounds(100, 0, 100, 100);
        //添加按钮到组件
        getContentPane().add(jb1);
        getContentPane().add(jb2);


        //设置可见
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();
        if (ob == jb1) {
            jb1.setSize(200, 200);
        } else if (ob == jb2) {
            Random r = new Random();
            jb2.setBounds(r.nextInt(500), r.nextInt(500), 100, 100);
        }
    }
}
