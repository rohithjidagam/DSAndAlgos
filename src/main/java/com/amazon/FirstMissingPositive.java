package com.amazon;

public class FirstMissingPositive {

	public static void main(String[] args) {

		int arr[] = { 0, 1, -3, -8, 3, 5, 10, 2, -10, -20 };
		
		int n = arr.length;
		int j = 0;
		for (int i = 0; i < n; i++) {
			if(arr[i] <= 0){
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				j++;
			}
		}
		
		int[] nums = new int[n-j];
		for(int i=j,k=0;i<n;i++){
			nums[k++] = arr[i];
		}
		
		for (int i = 0; i < nums.length; i++) {
			int val = Math.abs(nums[i]) - 1;
			if(val < nums.length && nums[val] > 0)
				nums[val] = -nums[val];
		}
		
		for (int i = 0; i < nums.length; i++) {
			if(nums[i] > 0){
				System.out.println(i+1);
				break;
			}
				
		}
	}

}
