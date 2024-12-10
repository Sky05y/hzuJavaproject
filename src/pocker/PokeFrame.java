package pocker;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
/**
 *
 * @author Sky
 */

public class PokeFrame {
    String[] array = new String[52];
    JButton[] button = new JButton[13];
    boolean poker_up= false;
    JButton 当前牌 = new JButton();
    JButton 前一张牌 = new JButton();
    JButton 出牌 = new JButton("出牌");
    boolean[] out = new boolean[13]; // 记录是否出牌
    public static void main(String[] args) {
        PokeFrame pf = new PokeFrame();
        pf.得到牌();
        pf.生成窗口();
    }
    public void 得到牌(){
        Pocker p = new Pocker();
        array = p.获取牌(p);
        p.打印牌(array);
    }
    void 生成窗口(){
        //创建背景面板
        JFrame jf = new JFrame("发牌");
        ImageIcon bg = new ImageIcon("img/bj.jpg");
        JLabel jl = new JLabel(bg);
        Arrays.fill(out, false);
        jl.setBounds(0, 0, 1600, 900);
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jf.add(jl);
        int w = jl.getWidth();
        int h = jl.getHeight();
        // 创建按钮面板
        JLayeredPane jp = jf.getLayeredPane();
        for (int i = 0; i < 13; i++) {
            button[i] = new JButton();
            ImageIcon btn = new ImageIcon("img/"+array[i]);//设置图标
            button[i].setIcon(btn);
            button[i].setBounds(w/2-667 + i * 50, h-300, 134, 201); //设置按钮位置
            jp.add(button[i],Integer.valueOf(i));
            监听器 ls = new 监听器();
            button[i].addActionListener(ls);
        }
        // 添加出牌按钮
        出牌.setBounds(200, 350, 80, 40);
        出牌.setVisible(false);  // 初始时隐藏出牌按钮
        jl.add(出牌);
        出牌.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (当前牌 != null && poker_up) {
                    poker_up = false;
                    int centerX = 800;  // 屏幕中心 X 坐标
                    int centerY = 400;  // 屏幕中心 Y 坐标
                    // 将当前选中的牌移动到屏幕中央
                    if(当前牌!=前一张牌)
                        前一张牌.setVisible(false);
                    当前牌.setBounds(centerX, centerY, 134, 201);
                    
                    // 移动当前牌右边的牌
                    boolean cul = false;
                    for (int i = 0; i < 13; i++) {
                        if (button[i] == 当前牌) {
                            cul = true; //这是确保当前牌左边的不会动
                            out[i] = true;
                        }
                        if (cul && !out[i] ){   //确保这个牌左边的和出的不会动
                            int newX = button[i].getX() - 50;
                            button[i].setBounds(newX, button[i].getY(), 134, 201);
                        }
                    }
                    前一张牌 = 当前牌;
                }
            }
        });
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    class 监听器 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton 目标牌 = (JButton)e.getSource(); // 获取触发事件的按钮
            int idx = 0;
            for(int i=0;i<13;i++)
                if(button[i]==目标牌)
                {
                    idx = i;
                    break;
                }
            if(!poker_up && !out[idx])
                {
                    目标牌.setBounds(目标牌.getX(), 目标牌.getY()-25, 134, 201); // 设置按钮位置
                    poker_up = true;
                    当前牌 = 目标牌;
                    出牌.setVisible(true);
                }
            else if((当前牌==目标牌)&&poker_up&&!out[idx])
                {
                    当前牌.setBounds(当前牌.getX(), 当前牌.getY()+25, 134, 201);
                    poker_up = false;
                    出牌.setVisible(false);  // 隐藏出牌按钮
                }
            else if((当前牌!=目标牌)&&poker_up&&!out[idx])
                {
                    当前牌.setBounds(当前牌.getX(), 当前牌.getY()+25, 134, 201);
                    目标牌.setBounds(目标牌.getX(), 目标牌.getY()-25, 134, 201);//当前牌下来，目标牌上去
                    当前牌 = 目标牌;
                }
        }
    }
}

