package nsgl.webserver;

public class Session {
	protected String id;
	protected long time;
	protected long timeout;
	public Session( String id, long time, long timeout ){
		this.id = id;
		this.time = time;
		this.timeout = timeout;
	}
	
	public String id(){ return id; }
	
	public void setTime( long time ){ this.time = time; }
	
	public boolean expired(long currentTime){ return currentTime-time >= timeout; }
}
