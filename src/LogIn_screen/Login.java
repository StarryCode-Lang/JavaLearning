package LogIn_screen;

import javax.swing.*;
import java.awt.*;

public class Login {
    private JTextField jtf_account;
    private JTextField jtf_password;
    public void showUI() {
        //窗体
        JFrame jf = new JFrame();
        //分辨率
        jf.setSize(425, 550);
        jf.setTitle("登录界面");
        //居中
        jf.setLocationRelativeTo(null);
        //退出进程
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //流式布局管理器
        jf.setLayout(new FlowLayout());

        //图片+标签(第一行)
        ImageIcon im = new ImageIcon("D:\\Desktop\\20230516\\Java_Learning\\src\\LonIn_screen1.jpg");
        JLabel jl = new JLabel(im);
        jf.add(jl);

        // 创建账号和输入框(第二行)
        JPanel jp_account = new JPanel(new FlowLayout());
        //账号输入提示
        JLabel user_a = new JLabel("账号:");
        jp_account.add(user_a);
        //账号输入框
        jtf_account = new JTextField();
        jtf_account.setPreferredSize(new Dimension(360, 30));
        jp_account.add(jtf_account);
        jf.add(jp_account);

        //创建密码输入框(第三行)
        JPanel jp_password = new JPanel(new FlowLayout());
        //密码输入提示
        JLabel user_p = new JLabel("密码:");
        jp_password.add(user_p);
        //密码输入框
        jtf_password = new JTextField();
        jtf_password.setPreferredSize(new Dimension(360, 30));
        jp_password.add(jtf_password);
        jf.add(jp_password);

        //登录按钮(第四行)
        JButton jbu_login = new JButton("登录");
        jf.add(jbu_login);

        //注册按钮(第五行)
        JButton jbu_register = new JButton("注册");
        jf.add(jbu_register);

        //设置可见
        jf.setVisible(true);

        //登录监听器
        ButtonListener listener = new ButtonListener(this);
        jbu_login.addActionListener(listener);

        ButtonListener_register listener_register = new ButtonListener_register();
        jbu_register.addActionListener(listener_register);

    }
    public JTextField getAccountField() {
        return jtf_account;
    }
    public JTextField getPasswordField() {
        return jtf_password;
    }
    public static void main (String[] args){
        Login lo =new Login();
        lo.showUI();
    }
}
