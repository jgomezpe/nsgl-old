package nsgl.random;

import nsgl.random.raw.RawGenerator;

public abstract class InverseGenerator<T> extends UsesRawGenerator implements RandGenerator<T> {
	public InverseGenerator(){}
	
	public InverseGenerator( RawGenerator g ){ super(g); }
	
    /**
     * Returns a random double number
     * @param x Inverse value (cumulative probability)
     * @return A random double number
     */
    public abstract T next(double x);

    /**
     * Returns a random double number
     * @return A random double number
     */
    @Override
    public T next(){ return next(raw().next()); }
}