package deprecated;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import essential.PCGraph;
import essential.Constraint;
 
public class Fun extends JFrame {
  private PCGraph graph;
  private LinkedList<Constraint> constraints;
  private JPanel jpanel = new JPanel();
 public   Fun(PCGraph graph)
  {
     this.graph = graph;
 	setTitle("Color Presnetation");
	setSize(800, 800);
 	 addWindowListener(new WindowAdapter() {
 	      public void windowClosing(WindowEvent e) {
 	        System.exit(0);
 	      }
 	    });
 	 add(jpanel);
 	setVisible(true);
 	paint(jpanel.getGraphics());
 	jpanel.repaint();
  
  }
 public Fun(PCGraph graph,String title)
 {
    this.graph = graph;
	setTitle(title);
	setSize(800, 800);
	 addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      }
	    });
	 add(jpanel);
	setVisible(true);
	paint(jpanel.getGraphics());
	jpanel.repaint();
 
 }
 public Fun(PCGraph graph,String title,LinkedList<Constraint> constraints)
 {
    this.graph = graph;
    this.constraints = constraints;
	setTitle(title);
	setSize(800, 800);
	 addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      }
	    });
	 add(jpanel);
	setVisible(true);
	paint(jpanel.getGraphics());
	jpanel.repaint();
 
 }
  public  Fun()
  {
	setTitle("DrawRect");
	setSize(800, 800);
	 addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      }
	    });
	 add(jpanel);
	setVisible(true);
	paint(jpanel.getGraphics());
	jpanel.repaint();
  }
  public void paint(Graphics g) {

   for (int i = 0; i<this.graph.getNumOfNodes();i++)
	   for (int j = 0;j<this.graph.getNumOfNodes();j++)
	   {
			Color color;
	/*	if (((i == 5 & j ==23)||(i == 5 & j ==32)||(i == 20 & j ==41)||(i == 30 & j ==41) )&& graph.getNodes()[i][j]!=255)
			 g.setColor(Color.green);*/
		
		//	if((((i==20 & j == 30))|(i==23 & j==32)|(i ==20)|(i==30))&& graph.getNodes()[i][j]!=255)
	//			g.setColor(Color.green);
	//		else
		
		
		if(this.graph.getEdges()[i][j]==255)
		   color = new Color(255<<16|255<<8|255);
		else
		{
			int k = Integer.bitCount(this.graph.getEdges()[i][j]);
		     int l = (8 -k);
			color = new Color(this.graph.getEdges()[i][j]<<16|(this.graph.getEdges()[i][j]/k)<<8|(this.graph.getEdges()[i][j]/l));
		
		}
		   g.setColor(color);
		
		if (this.constraints!=null){
		for (Constraint c:this.constraints)
		{
			if (((Integer)c.getSource()==i)&&((Integer)c.getDestination()==j)&& graph.getEdges()[i][j]!=255)
			{	
			//	System.out.println("i:  "+i+"   j: "+j);
				g.setColor(Color.green);
			}
		}
		}
		    g.fillRect(j*10, 30+i*10, 10, 10);
		      
	   }
	  
   repaint();
 

  }
 
  public static void main(String[] args) {
  
   Fun fun = new Fun();

  }
}