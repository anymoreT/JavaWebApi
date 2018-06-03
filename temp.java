package com.auto;

public class temp {
	public void sort(int[] intList){
		int temp = 0;
		for (int i=0; i < intList.length; i++){
			for(int j=0; j< intList.length-1 - i; j++){
				if (intList[j] < intList[j+1]){
					temp = intList[j] ;
					 intList[j] = intList[j+1];
					 intList[j+1] = temp;
				}
				
			}
		}
		for (int i=0; i < intList.length; i++){
			System.out.println( intList[i]);
		}
	}
	
	public static void Main(String[] args) {
		int[] intList = {1,9,0,20,-1,90,3};
		new temp().sort(intList);
		
	}

}
