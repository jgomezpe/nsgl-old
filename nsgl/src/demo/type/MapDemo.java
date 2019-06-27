package demo.type;

import nsgl.type.keymap.Map;
import nsgl.type.object.Cloneable;

public class MapDemo {

	public static void traversing( Map<Integer> v ){
	      System.out.println("**************TRAVERSING THE MAP****************");
	      System.out.println("**************Using for each****************");
	      for( Integer k:v){
	    	  System.out.print( " " + k );  
	      }
	      System.out.println();
	}
	
	public static void print( Map<Integer> v ){
	      for( Integer k:v) System.out.print( " " + k );
	      System.out.println();
	}
	
	public static void removing( Map<Integer> v ){
	      System.out.println("**************REMOVING FROM A MAP****************");
	      print(v);
	      System.out.println("**************Searching for the element and removing it****************");
	      for( int i=0; i<5; i++ ){
	    	  int k = (int)(Math.random() * 10);
	    	  System.out.print("Removing element:" + k);
	          boolean removed = v.del(k);
	          System.out.println( ":" + removed);
	      }
	      print(v);      
	}
	
	public static void adding( Map<Integer> v ){
	      System.out.println("**************ADDING TO A MAP****************");
	      print(v);
	      for( int i=0; i<5; i++ ){
	    	  int k = (int)(Math.random() * 10);
	    	  System.out.print("Adding element:" + k);
	          boolean added = v.add(k);
	          System.out.println( ":" + added);
	      }
	      print(v);      
	}
	
	public static void consulting( Map<Integer> v ){
	      System.out.println("**************CONSULTING THE MAP****************");
	      print(v);
	      System.out.println("**************Determines if an element belongs to the map****************");	      
	      for( int i=0; i<5; i++ ){
	    	  int d = (int)(Math.random() * 15);
	    	  System.out.print("Element " + d + " at position:");
	          boolean k = v.contains(d);
	          System.out.println( ":" + k);
	      }
	}
	
	public static Map<Integer> creating(){
		System.out.println("**************CREATING A MAP****************");
		return new Map<Integer>();
	}
	
	@SuppressWarnings("unchecked")
	public static Map<Integer> cloning(Map<Integer> v){
		System.out.println("**************CLONING A MAP****************");
		Cloneable c = Cloneable.cast(v);
		Map<Integer> clone = (Map<Integer>)c.clone();
		print(clone);
		return clone;
	} 
	
	public static void main( String[] args ){
		Map<Integer> v = creating();
		adding(v);
		traversing(v);
		Map<Integer> c = cloning(v);
		removing(v);
		consulting(v);
		System.out.println("**************CLONE OF THE ORIGINAL MAP****************");
		print(c);
		System.out.println("**************FINAL MAP****************");
		print(v);
    }  

}
