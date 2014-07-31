package tool;

import java.util.HashMap;

import essential.Relation;
import essential.CompositionTable;

public class write_to_table {
public static void main(String args[])
{
	
	HashMap<Integer,Integer> totaltable = CompositionTable.getBiTotalTable();
	System.out.println(totaltable.size());
	for (Integer key:totaltable.keySet())
	{
		int LH = (key >> 8);
		int RH = key - (LH << 8);
		System.out.println(" Relation:   "+Relation.translateToString(LH) + " COMPOSE WITH  Relation: " + Relation.translateToString(RH) + " = " + 
		Relation.translateToString(totaltable.get(key)));
		
		
	
	}





}
}
