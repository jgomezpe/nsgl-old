package nsgl.type.bit.cast;

import nsgl.search.multilevel.CodeDecodeMap;
import nsgl.type.bit.BitArray;
import nsgl.type.bit.Converter;

/**
 * <p>Title:BinaryToIntBitArray</p>
 * <p>Description: Growing function that creates an BitArray of integers from a bit BitArray</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Bit2Int extends CodeDecodeMap<BitArray, int[]> {
	/**
	 * If grayCode is used
	 */
  protected boolean grayCode;
  /**
   * int size
   */
  protected int int_size;

  /**
   * Constructor: Creates an individual with a random genome
   */
  public Bit2Int(int _int_size, boolean _grayCode) {
    grayCode = _grayCode;
    int_size = _int_size;
  }

  /**
   * Constructor: Creates an individual with a random genome
   */
  public Bit2Int(int _int_size) {
    grayCode = false;
    int_size = _int_size;
  }

  /**
   * Generates a thing from the given genome
   * @param genome Genome of the thing to be expressed
   * @return A thing expressed from the genome
   */
  public int[] decode(BitArray genome) {
    return Converter.getIntArray(genome, int_size, grayCode);
  }

  /**
   * Generates a genome from the given thing
   * @param thing A thing expressed from the genome
   * @return Genome of the thing
   */
  public BitArray code(int[] thing) {
    BitArray A = new BitArray(int_size*thing.length, false);
    Converter.setIntArray(A, thing, int_size, grayCode);
    return A;
  }
  
  public int min(){
      if( int_size == 32 ){
          return Integer.MIN_VALUE;
      }else{
          return 0;
      }
  }
  
  public int max(){
      if( int_size >= 31 ){
          return Integer.MAX_VALUE;
      }else{
          return (1 << int_size) - 1;
      }
  } 
  
  public static void main( String[] args ){
      for( int i=1; i<=32; i++ ){
          Bit2Int grow = new Bit2Int(i);
          System.out.println( i + ","+ grow.min() + "," + grow.max() );
      }
  }
}
