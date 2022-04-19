/**
 * @author ljjtpcn
 */
public class SortUtil {
    public static void main(String[] args) {
        int[] arr = {10000, 25, 42, 165, 2};

        sleepSort(arr);
    }

    /**
     * 睡眠排序大法
     * @date [2022/4/8 下午6:34]
     */
    private static void sleepSort(int[] arr) {
        for (int i : arr) {
            new Thread(() -> {
                try {
                    Thread.sleep(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }).start();
        }
    }
}
