import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Testing {
  public static void main(String[] args) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(1);
    queue.add(2);
    queue.add(3);
    System.out.println(queue.remove());
    System.out.println(queue.remove());

    int [] aaa = new int[5];
    aaa[0]= 100;
    aaa[1]= 0;
    Arrays.sort(aaa);
    System.out.println(aaa[0]+ " " + aaa[4]);
  }

  public int longestOnes(int[] nums, int k) {
    Queue<Integer> queue = new LinkedList<>();
    int count = 0;
    int maxCount = Integer.MIN_VALUE;

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 1) {
        count++;
      } else if (queue.size() <= k) {
        count++;
        queue.add(i);
      } else {
        int leftmost = queue.remove();
        maxCount = Integer.max(maxCount, count);
        count = i - leftmost;
        queue.add(i);
      }
    }

    return Integer.max(maxCount, count);
  }
}
