import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        int[] result = twoSum(new int[]{3,2,4},6);


        System.out.println(result[0]+","+result[1]);
    }


    public static int[] twoSum(int[] nums, int target) {


        int sum = 0;
        int[] result = new int[2];
        for(int i = 0;i<nums.length;i++){
            for(int j = i+1;j<nums.length;j++){

                sum = nums[i]+nums[j];
                System.out.println(nums[i]+","+nums[j]);
                System.out.println(sum);
                if(sum == target){
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }
}
