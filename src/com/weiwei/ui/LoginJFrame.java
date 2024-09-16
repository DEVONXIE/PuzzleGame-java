package com.weiwei.ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements MouseListener {
    //创建一个静态集合来存储账号密码
    static ArrayList<User> users = new ArrayList<>();

    static {
        users.add(new User("Devon", "123456"));
    }

    JTextField accountField = new JTextField();

    JTextField passwordField = new JTextField();
    JTextField codeField = new JTextField();
    JLabel codeImageLable = new JLabel();
    VerifyCode codeUtil = new VerifyCode();
    String codeStr = codeUtil.getCode();

    JButton login = new JButton();
    JButton register = new JButton();

    //所有与登录有关的逻辑写在这里
    public LoginJFrame() {
        initFrame();

        initView();

    }

    private void verifyLogin() {
        User tmpUser = new User(accountField.getText(), passwordField.getText());
        boolean flag = false;
        System.out.println(tmpUser.userName + tmpUser.password);
        for (User user : users) {
            if (tmpUser.userName.equals(user.userName) && tmpUser.password.equals(user.password) && codeField.getText().equals(codeStr)) {
                dispose();
                flag = true;
                new GameJFrame();
            }
        }
        if (tmpUser.userName.isEmpty() || tmpUser.password.isEmpty()) {
            showJDialog("用户名或密码为空");
        } else if (codeField.getText().isEmpty()) {
            showJDialog("请输入验证码");
        } else if (!codeField.getText().equals(codeStr)) {
            showJDialog("验证码错误");
        } else if (!flag) {
            showJDialog("用户名或密码错误");
        }
    }

    private void initFrame() {
        setSize(488, 430);
        setTitle("登录");
        //取消页面里窗口的自动居中使得setbounds能起效
        setLayout(null);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initView() {
        //添加用户名文字
        JLabel accountLable = new JLabel(new ImageIcon("image\\login\\用户名.png"));
        accountLable.setBounds(116, 135, 47, 17);
        getContentPane().add(accountLable);

        //添加账号输入框
        accountField.setBounds(195, 134, 200, 30);
        getContentPane().add(accountField);

        //添加密码文字
        JLabel passwordLable = new JLabel(new ImageIcon("image\\login\\密码.png"));
        passwordLable.setBounds(130, 195, 32, 16);
        getContentPane().add(passwordLable);

        //添加文本框来输入密码
        passwordField.setBounds(195, 195, 200, 30);
        getContentPane().add(passwordField);

        //验证码提示文字
        JLabel codeLable = new JLabel(new ImageIcon("image\\login\\验证码.png"));
        codeLable.setBounds(133, 256, 50, 30);
        getContentPane().add(codeLable);

        //验证码输入框
        codeField.setBounds(195, 256, 100, 30);
        getContentPane().add(codeField);

        //添加正确验证码提示框
        codeImageLable.setText(codeStr);
        codeImageLable.setBounds(300, 256, 50, 30);
        getContentPane().add(codeImageLable);
        //给验证码提示框添加事件监听，点击就更换
        codeImageLable.addMouseListener(this);

        //添加登录按钮
        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        //去除按钮边框
        login.setBorderPainted(false);
        //去除按钮背景
        login.setContentAreaFilled(false);
        getContentPane().add(login);
        //给登录按钮添加鼠标监听
        login.addMouseListener(this);

        //添加注册按钮
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        //去除按钮边框
        register.setBorderPainted(false);
        //去除按钮背景
        register.setContentAreaFilled(false);
        getContentPane().add(register);
        //给注册按钮添加鼠标监听
        register.addMouseListener(this);

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
        background.setBounds(0, 0, 470, 390);
        getContentPane().add(background);
    }

    private void showJDialog(String content) {
        JDialog jd = new JDialog();
        //设置大小
        jd.setSize(200, 150);
        //设置置顶
        jd.setAlwaysOnTop(true);
        //设置居中
        jd.setLocationRelativeTo(null);
        //使弹窗不关闭就无法进行下面任何操作
        jd.setModal(true);
        //取消页面居中
        jd.setLayout(null);

        //创建Jlable并添加到弹窗
        JLabel label = new JLabel(content);
        label.setBounds(0, 0, 200, 150);
        jd.getContentPane().add(label);

        jd.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == codeImageLable) {
            codeStr = codeUtil.getCode();
            codeImageLable.setText(codeStr);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object source = e.getSource();
        if (source == login) {
            //将图片替换为按下
            login.setIcon(new ImageIcon("image\\login\\登录按下.png"));
        } else if (source == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object source = e.getSource();
        if (source == login) {
            //将图片恢复原状
            login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
            //调用验证函数，将账号密码和验证码都验证一遍。
            verifyLogin();
        } else if (source == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
