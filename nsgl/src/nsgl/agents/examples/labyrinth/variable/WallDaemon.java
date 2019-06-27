package nsgl.agents.examples.labyrinth.variable;
import nsgl.agents.Action;
import nsgl.agents.AgentProgram;
import nsgl.agents.Percept;
import nsgl.random.UsesRawGenerator;

public class WallDaemon extends UsesRawGenerator implements AgentProgram{
	protected double probability;
	public WallDaemon( double p ){ probability = p;	}
	
	@Override
	public Action compute(Percept p){
		if( raw().bool(probability) ){ return new Action("change_walls"); }
		else return new Action("no_op");
	}

	@Override
	public void init(){}
}