package queue;

public class Test {
    public static void main(String[] args) {
        Test t = new Test();
        t.Menu();
    }

    public void Menu() {
        System.out.println("\n");
        System.out.println("\t\t\t\t====================队列进出=====================");
        System.out.println("\t\t\t\t||                                            ||");
        System.out.println("\t\t\t\t||          1>. 录入商品库存信\t\t\t\t||");
        System.out.println("\t\t\t\t||          2>. 查找某个商品的库存量            ||");
        System.out.println("\t\t\t\t||          3>. 商品出库记录                   ||");
        System.out.println("\t\t\t\t||          0>. 退出管理系统                   ||");
        System.out.println("\t\t\t\t||                                            ||");
        System.out.println("\t\t\t\t=================================================");
        System.out.println("\t\t\t\t输入选项，按回车进入选项:                           ");
    }

}