import java.util.Scanner;

public class demo {

    public static void main(String[] args) {
        int[] nums = {2, 2, 2, 3, 3, 3};
        int[] com = new int[1000];
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            cnt = 0;
            for (int j = i; j < nums.length - i; j++) {
                if (nums[j] == nums[i]) {
                    cnt++;
                }
                com[nums[i]] = cnt;
                System.out.print(com[nums[i]] + " " + cnt + " ");
            }
        }
        int max = 0;
        for (int i = 0; i < 10; i++) {
            if (com[i] > max) {
                max = com[i];
                System.out.println("最大:" + i + " " + max);
            }
        }
//        for (int i = 0; i < 10; i++) {
//            if (com[i] == max) {
//                System.out.println(nums[i] + " " + max);
//            }
//        }
    }

}
