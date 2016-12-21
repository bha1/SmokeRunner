package com.webapp.sort.facade;

public class HeapSort {

	private int total;
	
	private void swap(@SuppressWarnings("rawtypes") Comparable[] arr, int a, int b){
		@SuppressWarnings("rawtypes")
		Comparable temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	@SuppressWarnings("unchecked")
	private void heapify(@SuppressWarnings("rawtypes") Comparable[] arr, int i){
		int lft = i*2;
		int rght = lft+1;
		int grt = i;
		
		if(lft <= total && arr[lft].compareTo(arr[grt]) > 0 ){
			grt = lft;
		}
		if(rght <= total && arr[rght].compareTo(arr[grt]) > 0 ){
			grt = rght;
		}
		
		if(grt != i){
			swap(arr,i,grt);
			heapify(arr, grt);
		}
	}
	
	private void sort(@SuppressWarnings("rawtypes") Comparable[] arr){
		total = arr.length-1;
		for(int i = total/2; i >= 0; i--){
			heapify(arr,i);
			System.out.println(java.util.Arrays.toString(arr));
		}
		for(int i = total; i > 0; i--){
			swap(arr,0,i);
			total--;
			heapify(arr,0);
		}
	}
 public static void main(String[] args) {
	 HeapSort heapSort = new HeapSort();
		Integer[] arr =  new Integer[] { 3,6,  2, 1, 5, 4,7 };
		System.out.println(java.util.Arrays.toString(arr));
		heapSort.sort(arr);
		System.out.println(java.util.Arrays.toString(arr));
}		

}
