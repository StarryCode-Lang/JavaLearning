package fighting;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("欢迎来到王者荣耀!");
        String heroP1_Name, heroP2_Name;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入第一个英雄的名字:");
        heroP1_Name = scanner.nextLine();
        System.out.println("请输入第二个英雄的名字:");
        heroP2_Name = scanner.nextLine();
        hero heroP1 = new hero(heroP1_Name);
        hero heroP2 = new hero(heroP2_Name);
        int round = 1;
        while (!heroP1.isDead() && !heroP2.isDead() && round<=10) {
            System.out.println("Round " + round + ":");
            heroP1.attack(heroP2);
            heroP2.attack(heroP1);
            System.out.println(heroP1.getName() + " HP: " + heroP1.getHP());
            System.out.println(heroP2.getName() + " HP: " + heroP2.getHP());
            round++;
        }
        if (heroP1.isDead() || (heroP1.getHP() < heroP2.getHP())) {
            System.out.println(heroP2.getName() + " 获胜了！");
        }else if (heroP2.isDead() || (heroP1.getHP() > heroP2.getHP())) {
            System.out.println(heroP1.getName() + " 获胜了！");
        }else{
            System.out.println("本局游戏平局！");
        }
    }
}
