package LogIn_screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ButtonListener implements ActionListener {
    private final Login login;
    public ButtonListener(Login login) {
        this.login = login;
    }
    //事件处理方法
    public void actionPerformed(ActionEvent e){
        System.out.println("点击按钮登录！");
        //检查账号密码
        JTextField passwordField = login.getPasswordField();
        JTextField accountField = login.getAccountField();
        String account = accountField.getText();
        String password = passwordField.getText();
        if (checkLogin(account, password)){
            System.out.println("登录成功!");
            //显示登录新的界面
            JFrame jf01 = new JFrame();
            jf01.setSize(425, 550);
            jf01.setTitle("登录成功");
            jf01.setLocationRelativeTo(null);
            jf01.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf01.setLayout(new FlowLayout());
            ImageIcon im01 = new ImageIcon("D:\\Desktop\\编程文件\\LogIn_screen\\1.jpg");
            JLabel jl01 = new JLabel(im01);
            jf01.add(jl01);
            JPanel jp_account01 = new JPanel(new FlowLayout());
            //账号输入提示
            JLabel user_a01 = new JLabel("登录成功");
            jp_account01.add(user_a01);
            jf01.add(jp_account01);
            jf01.setVisible(true);
        }else{
            System.out.println("账号或密码错误");
            accountField.setText("");
            passwordField.setText("");
        }
    }
    private boolean checkLogin(String account, String password) {
        return Objects.equals(account, "Rainbow") && Objects.equals(password, "123456");
    }
}
