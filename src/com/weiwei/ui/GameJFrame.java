package com.weiwei.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    int[][] data = new int[4][4];
    int x, y;
    int[][] originalData = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
    int step = 0; //步数计数器
    String path = "image\\animal\\animal3\\";

    //创建运动，动物，美女三个菜单条目给更换图片
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportItem = new JMenuItem("运动");
    JMenuItem hotGirlItem = new JMenuItem("美女");

    //创建菜单条目
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reloginItem = new JMenuItem("重新登陆");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem accountItem = new JMenuItem("我");

    //所有与游戏主界面有关的逻辑写在这里
    public GameJFrame() {
        //初始化界面
        initJFrame();

        //初始化菜单栏
        initJMenuBar();

        //初始化数据
        initData();

        //初始化图片
        initImage();

        setVisible(true);

    }

    private void initData() {
        //清空步数
        step = 0;
        int[] tmpData = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            int tmp;
            int index = random.nextInt(tmpData.length);
            tmp = tmpData[index];
            tmpData[index] = tmpData[i];
            tmpData[i] = tmp;

        }
        for (int i = 0; i < 16; i++) {
            if (tmpData[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = tmpData[i];
        }

    }

    private void initImage() {
        //清空界面
        getContentPane().removeAll();

        //增加计数器并添加到界面内
        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50, 30, 100, 20);
        getContentPane().add(stepCount);

        //判断是否胜利，如果胜利则加载胜利图片
        if (isWin()) {
            JLabel winJLabel = new JLabel(new ImageIcon("image\\win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            getContentPane().add(winJLabel);
        }
        /*
        //创建Icon对象
        ImageIcon icon = new ImageIcon("D:\\Code\\Java\\PuzzleGame\\image\\animal\\animal3\\1.jpg");

        //创建Jlable对象来看管理icon
        JLabel jLabel = new JLabel(icon);

        //设置图片的宽高以及坐标，左上为（0，0）
        jLabel.setBounds(0,0,105,105);

        //将图片添加到contentPane
        getContentPane().add(jLabel);

        //添加到JFrame
        add(jLabel);
        */

        //优化后代码
        /*
        int tmp = 1;
        for(int cnt2 = 0,y = 0;cnt2 < 4;y += 105,cnt2++) {
            for (int cnt1 = 0,x = 0;cnt1 < 4 ; x += 105,cnt1++) {
                Icon icon = new ImageIcon("D:\\Code\\Java\\PuzzleGame\\image\\animal\\animal3\\"+ tmp + ".jpg");
                tmp++;
                JLabel jLabel = new JLabel(icon);
                jLabel.setBounds(x,y,105,105);
                getContentPane().add(jLabel);
            }
        }
        */

        //添加随机数来打乱图片
        /*
        HashSet<Integer> set = new HashSet<>();
        for (int cnt2 = 0, y = 0; cnt2 < 4; y += 105, cnt2++) {
            for (int cnt1 = 0, x = 0; cnt1 < 4; x += 105, cnt1++) {
                int tmp = 0;
                while (true) {
                    Random r = new Random();
                    tmp = r.nextInt(16);
                    if (!set.contains(tmp)) {
                        set.add(tmp);
                        break;
                    }
                }
                Icon icon = new ImageIcon("D:\\Code\\Java\\PuzzleGame\\image\\animal\\animal3\\" + tmp + ".jpg");
                JLabel jLabel = new JLabel(icon);
                jLabel.setBounds(x, y, 105, 105);
                getContentPane().add(jLabel);
            }
        }
        */

        //优化后代码，将二重循环变成一重循环
        /*
        HashSet<Integer> set = new HashSet<>();
        for (int cnt2 = 0, dis = 0; cnt2 < 16; dis += 105, cnt2++) {

            int tmp = 0;
            while (true) {
                Random r = new Random();
                tmp = r.nextInt(16);
                if (!set.contains(tmp)) {
                    set.add(tmp);
                    break;
                }
            }
            Icon icon = new ImageIcon("D:\\Code\\Java\\PuzzleGame\\image\\animal\\animal3\\" + tmp + ".jpg");
            JLabel jLabel = new JLabel(icon);
            jLabel.setBounds(dis % 420, 105 * (dis / 420), 105, 105);
            getContentPane().add(jLabel);

        }
        */


        //再版代码，使用数据集合来初始化图片，而不是直接用变量
        for (int cnt = 0, dis = 0; cnt < 16; dis += 105, cnt++) {
            Icon icon = new ImageIcon(path + data[cnt / 4][cnt % 4] + ".jpg");
            JLabel jLabel = new JLabel(icon);
            jLabel.setBounds(dis % 420 + 83, 105 * (dis / 420) + 134, 105, 105);
            jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
            getContentPane().add(jLabel);
        }

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
        background.setBounds(40, 40, 508, 560);
        getContentPane().add(background);

        //刷新界面
        getContentPane().repaint();
    }

    private void initJMenuBar() {
        //初始化菜单
        //创建菜单栏
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单对象
        JMenu functionMenu = new JMenu("功能");
        JMenu aboutMenu = new JMenu("关于");

        //创建更换图片的菜单
        JMenu changeImage = new JMenu("更换图片");

        //添加菜单对象
        jMenuBar.add(functionMenu);
        jMenuBar.add(aboutMenu);

        //将更换图片的菜单添加入功能菜单中
        functionMenu.add(changeImage);

        //添加菜单条目
        functionMenu.add(replayItem);
        functionMenu.add(reloginItem);
        functionMenu.add(closeItem);

        //将更换图片的三个按钮添加入更换图片
        changeImage.add(animalItem);
        changeImage.add(sportItem);
        changeImage.add(hotGirlItem);

        //添加关于我们菜单条目
        aboutMenu.add(accountItem);


        //为菜单条目绑定事件监听
        replayItem.addActionListener(this);
        reloginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        //为更换图片的三个菜单条目添加事件监听
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);
        hotGirlItem.addActionListener(this);


        //添加菜单栏
        setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        setSize(603, 680);

        //设置页面标题
        setTitle("VV拼图");

        //设置页面置顶
        setAlwaysOnTop(true);

        //设置页面居中
        setLocationRelativeTo(null);

        //取消窗口的自动居中
        setLayout(null);

        //添加键盘监听
        addKeyListener(this);

        //设置关闭模式
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //如果判赢，禁止进行任何操作
        if (isWin()) {
            return;
        }

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            //按A查看完整图片

            //把页面内容全部删除
            getContentPane().removeAll();
            //加载完整图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            getContentPane().add(all);

            //添加背景图
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40, 40, 508, 560);
            getContentPane().add(background);

            //刷新界面
            getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //如果判赢，直接返回，不能进行任何操作
        if (isWin()) {
            return;
        }

        //游戏逻辑
        //将空白方块在data中的坐标与周围方块替换
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            if (x == 3) {
                return;
            }
            System.out.println("UP");
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            step++;//计数器更新
        } else if (code == KeyEvent.VK_DOWN) {
            if (x == 0) {
                return;
            }
            System.out.println("DOWN");
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            step++;//计数器更新
        } else if (code == KeyEvent.VK_LEFT) {
            if (y == 3) {
                return;
            }
            System.out.println("LEFT");
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            step++;//计数器更新
        } else if (code == KeyEvent.VK_RIGHT) {
            if (y == 0) {
                return;
            }
            System.out.println("RIGHT");
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            step++;//计数器更新

        } else if (code == KeyEvent.VK_W) {
            data = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
            x = 3;
            y = 3;
        }


        //重新载入图片
        initImage();

    }

    public boolean isWin() {
        return Arrays.deepEquals(data, originalData);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == replayItem) {
            System.out.println("重新游戏");
            //清空步数
            step = 0;
            //初始化数据
            initData();
            //初始化图像
            initImage();
        } else if (obj == reloginItem) {
            System.out.println("重新登录");
            //关闭游戏窗口
            dispose();
            //创建登陆界面
            new LoginJFrame();
        } else if (obj == closeItem) {
            System.out.println("关闭游戏");
            System.exit(0);
        } else if (obj == accountItem) {
            System.out.println("关于我们");
            //创建弹窗
            JDialog jd = new JDialog();
            //设置弹窗大小
            jd.setBounds(0, 0, 255, 255);
            //创建标签管理图片
            JLabel jb = new JLabel(new ImageIcon("image\\damie.jpg"));
            //设置尺寸
            jb.setBounds(0, 0, 99, 100);
            //将标签添加入弹窗中
            jd.getContentPane().add(jb);
            //设置弹窗置顶
            jd.setAlwaysOnTop(true);
            //设置弹窗居中
            jd.setLocationRelativeTo(null);
            //设置其可见
            jd.setVisible(true);
        } else if (obj == animalItem) {
            System.out.println("换成动物");
            //添加随机数来做到随机图片
            Random r = new Random();
            int tmp = r.nextInt(8) + 1;
            //修改图片路径
            path = "image\\animal\\animal" + tmp + "\\";
            //刷新数据
            initData();
            //刷新图片
            initImage();
        } else if (obj == hotGirlItem) {
            System.out.println("换成妹子");
            Random r = new Random();
            int tmp = r.nextInt(13) + 1;
            path = "image\\girl\\girl" + tmp + "\\";
            initData();
            initImage();
        } else if (obj == sportItem) {
            System.out.println("换成运动图片");
            Random r = new Random();
            int tmp = r.nextInt(10) + 1;
            path = "image\\sport\\sport" + tmp + "\\";
            initData();
            initImage();
        }
    }
}
