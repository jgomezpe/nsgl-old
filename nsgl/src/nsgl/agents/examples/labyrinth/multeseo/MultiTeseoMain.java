/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.agents.examples.labyrinth.multeseo;

import nsgl.agents.Agent;
import nsgl.agents.AgentProgram;
import nsgl.agents.examples.labyrinth.Labyrinth;
import nsgl.agents.examples.labyrinth.LabyrinthDrawer;
import nsgl.agents.examples.labyrinth.teseo.simple.RandomReflexTeseo;
import nsgl.agents.simulate.util.SimpleLanguage;
import nsgl.type.array.Vector;


public class MultiTeseoMain {
  private static SimpleLanguage getLanguage(){
    return  new SimpleLanguage( new String[]{"front", "right", "back", "left", "treasure", "fail",
        "afront", "aright", "aback", "aleft"},
                                   new String[]{"no_op", "die", "advance", "rotate"}
                                   );
  }

  public static void main( String[] argv ){
     AgentProgram[] teseo = new AgentProgram[12];
    int index1 = 0;
    int index2 = 1;
    teseo[index1] = new RandomReflexTeseo( getLanguage() );
    teseo[index2] = new RandomReflexTeseo( getLanguage() );
    
    LabyrinthDrawer.DRAW_AREA_SIZE = 600;
    LabyrinthDrawer.CELL_SIZE = 40;
    Labyrinth.DEFAULT_SIZE = 15;
    
    Agent agent1 = new Agent(teseo[index1]);    
    Agent agent2 = new Agent(teseo[index2]);
    
    //Agent agent3 = new Agent(p3);
    Vector<Agent> agent = new Vector<Agent>();
    agent.add(agent1);
    agent.add(agent2);
//    Agent agent = new Agent( new RandomReflexTeseoAgentProgram( getLanguage() ) );
    MultiAgentLabyrinthMainFrame frame = new MultiAgentLabyrinthMainFrame( agent, getLanguage() );
    frame.setVisible(true); 
  }
}
