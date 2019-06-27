package demo.random;

import nsgl.random.Shuffle;

public class ShuffleDemo {
	public static void int_array(){
		System.out.println("**********Shuffling an int array************");
		int[] x = new int[100];
		for( int i=0; i<x.length; i++) x[i] = i;
		@SuppressWarnings("rawtypes")
		Shuffle s = new Shuffle();
		s.apply(x);
		for( int i=0; i<x.length; i++) System.out.println(x[i]);		
	}
	
	public static void string_array(){
		System.out.println("**********Shuffling a String array************");
		String[] x = new String[]{"one", "two", "three", "four", "five"};
		Shuffle<String> s = new Shuffle<String>();
		s.apply(x);
		for( int i=0; i<x.length; i++) System.out.println(x[i]);		
	}
	
	public static void main( String[] args ){
		int_array();  // Shuffling an int array
		string_array();  //shuffling an String array
	}
}