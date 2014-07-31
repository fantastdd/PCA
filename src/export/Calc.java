package export;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import essential.Relation;
import essential.CompositionTable;
import essential.MTS;
import essential.SetOperators;
import essential.Constraint;


public class Calc {
	static JFrame jf = new JFrame();
	static JTextField jtf = new JTextField(15);
	static JButton split = new JButton("split");
	static JButton compose = new JButton("compose");
	static JButton reset = new JButton("reset");
	static JTextArea display = new JTextArea(30,30);
public static void main(String args[])
{

	split.addActionListener(new ActionListener(){

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		// TODO Auto-generated method stub
		String relations = jtf.getText();
		String relation;
		Integer rels = 0;
		while(relations.length()!=0)
		{
			
		if(relations.contains(","))
			relation = relations.substring(0, relations.indexOf(","));
		else
			relation = relations;
		
			if (relation.equalsIgnoreCase("DC"))
				rels += Relation.DC.id;
			if (relation.equalsIgnoreCase("EC"))
				rels += Relation.EC.id;
			if (relation.equalsIgnoreCase("PO"))
				rels += Relation.PO.id;
			if (relation.equalsIgnoreCase("NTPP"))
				rels += Relation.NTPP.id;
			if (relation.equalsIgnoreCase("NTPPi"))
				rels += Relation.NTPPi.id;
			if (relation.equalsIgnoreCase("TPP"))
				rels += Relation.TPP.id;
			if (relation.equalsIgnoreCase("TPPi"))
				rels += Relation.TPPi.id;
			if (relation.equalsIgnoreCase("EQ"))
				rels += Relation.EQ.id;
			
			if(!relations.contains(","))
				break;
			
			relations = relations.substring(relations.indexOf(",")+1);
			//System.out.println(relations);
		}
		display.setText(Relation.translateToString(rels));
		display.append(("\nMTS:  "+MTS.BIMTS_H8.contains(rels)));
		Constraint<Integer> constraint = new Constraint<Integer>(rels,1,2);
		for (Integer result: SetOperators.splitToMinmumSubsets(rels,"H8"))
	    	display.append("\n"+ Relation.translateToString(result));
		
	}
	
});
	compose.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			// TODO Auto-generated method stub
			String relations = jtf.getText();
			String relation;
			int origin = 0;
			
			int relLeft = 0;
			int relRight = 0;
			String originRelations = relations.substring(0,relations.indexOf("."));
			String lefRelations = relations.substring(relations.indexOf(".")+1,relations.lastIndexOf("."));
			String rigRelations = relations.substring(relations.lastIndexOf(".")+1);
			while(originRelations.length()!=0 )
			{
			
			if(originRelations.contains(","))
				 relation =originRelations.substring(0, originRelations.indexOf(","));
			else
				 relation =originRelations;
		
			if (relation.equalsIgnoreCase("DC"))
				origin += Relation.DC.id;
			if (relation.equalsIgnoreCase("EC"))
				origin += Relation.EC.id;
			if (relation.equalsIgnoreCase("PO"))
				origin += Relation.PO.id;
			if (relation.equalsIgnoreCase("NTPP"))
				origin += Relation.NTPP.id;
			if (relation.equalsIgnoreCase("NTPPi"))
				origin += Relation.NTPPi.id;
			if (relation.equalsIgnoreCase("TPP"))
				origin += Relation.TPP.id;
			if (relation.equalsIgnoreCase("TPPi"))
				origin += Relation.TPPi.id;
			if (relation.equalsIgnoreCase("EQ"))
				origin += Relation.EQ.id;
				
				if(!originRelations.contains(","))
					break;
				
				originRelations =originRelations.substring(originRelations.indexOf(",")+1);
				//System.out.println(relations);
				
			}
			
		
			
			
			
			while(lefRelations.length()!=0 )
		{
			if(lefRelations.contains(","))
				 relation =lefRelations.substring(0, lefRelations.indexOf(","));
			else
				 relation =lefRelations;

			if (relation.equalsIgnoreCase("DC"))
				relLeft += Relation.DC.id;
			if (relation.equalsIgnoreCase("EC"))
				relLeft += Relation.EC.id;
			if (relation.equalsIgnoreCase("PO"))
				relLeft += Relation.PO.id;
			if (relation.equalsIgnoreCase("NTPP"))
				relLeft += Relation.NTPP.id;
			if (relation.equalsIgnoreCase("NTPPi"))
				relLeft += Relation.NTPPi.id;
			if (relation.equalsIgnoreCase("TPP"))
				relLeft += Relation.TPP.id;
			if (relation.equalsIgnoreCase("TPPi"))
				relLeft += Relation.TPPi.id;
			if (relation.equalsIgnoreCase("EQ"))
				relLeft += Relation.EQ.id;
			
		
				if(!lefRelations.contains(","))
					break;
				
			lefRelations =lefRelations.substring(lefRelations.indexOf(",")+1);
				//System.out.println(relations);
			}
			
			
			
			
			while(  rigRelations.length()!=0)
			{
				
				if (rigRelations.contains(","))
				 relation =  rigRelations.substring(0,rigRelations.indexOf(","));
			else
				relation =  rigRelations;

			if (relation.equalsIgnoreCase("DC"))
				relRight += Relation.DC.id;
			if (relation.equalsIgnoreCase("EC"))
				relRight += Relation.EC.id;
			if (relation.equalsIgnoreCase("PO"))
				relRight += Relation.PO.id;
			if (relation.equalsIgnoreCase("NTPP"))
				relRight += Relation.NTPP.id;
			if (relation.equalsIgnoreCase("NTPPi"))
				relRight += Relation.NTPPi.id;
			if (relation.equalsIgnoreCase("TPP"))
				relRight += Relation.TPP.id;
			if (relation.equalsIgnoreCase("TPPi"))
				relRight += Relation.TPPi.id;
			if (relation.equalsIgnoreCase("EQ"))
				relRight += Relation.EQ.id;
				
				if(! rigRelations.contains(","))
					break;
				
				 rigRelations =  rigRelations.substring(rigRelations.indexOf(",")+1);
				//System.out.println(relations);
			
			}
		
			display.setText("O:  "+ Relation.translateToString(origin));
			display.append("\nL:  "+Relation.translateToString(relLeft));
			display.append("\nR:  "+Relation.translateToString(relRight));
			display.append(("\nResult:  "+Relation.translateToString(CompositionTable.LookUpTable(relLeft, relRight))));
			display.append(("\nResult:  "+Relation.translateToString(SetOperators.intersection(origin, CompositionTable.LookUpTable(relLeft, relRight)))));
			
		}
		
	});
reset.addActionListener(new ActionListener(){

	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		jtf.setText("");
		}
		
	
	
});
jf.setLayout(new FlowLayout());
jf.add(jtf);
jf.add(split);
jf.add(compose);
jf.add(reset);
jf.add(display);
jf.setDefaultCloseOperation(3);
jf.setSize(500,300);
jf.setVisible(true);
}
}
