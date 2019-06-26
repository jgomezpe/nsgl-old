package nsgl.random;

import nsgl.random.raw.RawGenerator;

public class UsesRawGenerator {
	protected RawGenerator g=null;
	
	public UsesRawGenerator(){}
	
	public UsesRawGenerator( RawGenerator g ){ this.g= g; }
	
	public RawGenerator raw(){ return (g==null)?RawGenerator.cast(this):g; }
	
	public void setRaw( RawGenerator g ){ this.g = g; }
}