package com.lsm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestMain {

	public static void main(String[] args) {

		/*
		 * int[] arr = { 1, 2, 3, 1, 2, 5, 6, 7, 2, 7, 7, 1 }; Set<Integer> set = new
		 * HashSet<>();
		 */
		/*
		 * for(int n:arr) { 
		 * if(!set.add(n)) {
		 * System.out.println("duplicate elements-"+n); } }
		 */

		/*
		 * for(int i=0;i<arr.length;i++) { 
		 * for(int j=i+1;j<arr.length;j++) {
		 * if(arr[i]==arr[j]) { 
		 * System.out.println(arr[j]); 
		 * } } }
		 */

		// Reverse a String

		// String str = "apple";
		 
		/* 
		 * String revStr = "";
		 * 
		 * for (int i = str.length() - 1; i >= 0; i--) {
		 * 
		 * revStr += str.charAt(i); } System.out.println(revStr);
		 */
		
		/*
		 * StringBuffer sb=new StringBuffer(str); sb.reverse(); String revStr=new
		 * String(sb); System.out.println(revStr);
		 */
		
		 
		/*
		 * String st="good morning"; String str = st.replaceAll("morning", "evening");
		 * System.out.println(str);
		 */
		 
		 //SORTING AN ARRAY
		
		int[] arr= {5,7,4,2,9,1,3};
		
		int temp;
		for(int i=0;i<arr.length;i++) {
			for(int j=i+1;j<arr.length;j++) {
				if(arr[i]>arr[j]) {
					temp=arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
					
				}
			}
		}
		String[] array= {"a","b","c"};
		
		String s=String.join(",", array);
		System.out.println(s);
		

	}
}
















