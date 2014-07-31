package analysis;

import essential.Relation;
import essential.PCGraph;
import essential.SetOperators;

public class StructureDifference {
public static void printDifference(PCGraph a,PCGraph b)

{
	boolean sign = false;
	for(int i = 0;i<a.getNumOfNodes()-1;i++)
		for(int j = i+1;j<a.getNumOfNodes();j++)
		{
			if(a.getEdges()[i][j]!=b.getEdges()[i][j] && a.edges[i][j] != 255)
			{
				System.out.println(a.getName()+"   "+i+" "+j+"  "+Relation.translateToString(a.getEdges()[i][j]));
				
				System.out.println(b.getName()+"   "+i+" "+j+"  "+Relation.translateToString(b.getEdges()[i][j]));
				sign = true;
			}
			
		}
    if(!sign)
    	System.out.println("No Difference");

}
/**
 * Graph a: original graph. 
 * Compare each constraint in a to b 
 * 
 * **/
public static void properSubSet(PCGraph a, PCGraph b)
{
	boolean sign = false;
	for(int i = 0;i < a.getNumOfNodes()-1;i++)
		for(int j = i+1;j<a.getNumOfNodes();j++)
		{
			if(a.getEdges()[i][j]!=255 & (a.getEdges()[i][j]!=b.getEdges()[i][j]))
			{
				System.out.println(a.getName()+"   "+i+" "+j+"  "+Relation.translateToString(a.getEdges()[i][j]));
				
				System.out.println(b.getName()+"   "+i+" "+j+"  "+Relation.translateToString(b.getEdges()[i][j]));
				sign = true;
			}
			
		}
    if(!sign)
    	System.out.println(a.getName()+"  is a sub graph of  "+b.getName());


}
/**
 * Graph a: PC(OG) 
 * Graph b: PC(Replace(PC(OG)))
 * Graph c: The one includes all non-redundancy relations
 * **/
public static void shootNonRedundancy(PCGraph a,PCGraph b,PCGraph c)

{
	for(int i = 0;i<a.getNumOfNodes()-1;i++)
		for(int j = i+1;j<a.getNumOfNodes();j++)
		{
			if(Integer.bitCount(a.getEdges()[i][j]) < Integer.bitCount(b.getEdges()[i][j]))
			{
				c.getEdges()[i][j] =  a.getEdges()[i][j];
				c.getEdges()[j][i] =  a.getEdges()[j][i];
			}
			
		}


}
/**
 * @param og: original graph 
 * @param sg: the one is a solution of MTS(PC(OG))
 * @return similarity between og and sg
 * **/
 public static double similarity(PCGraph og,PCGraph sg)
 {
	 double count = 0;
	 double hit = 0;
	 for (int i =0;i < og.getNumOfNodes();i++)
		 for (int j = i+1;j < og.getNumOfNodes();j++)
		 {
			 // only compare relations in og
			 if (og.getEdges()[i][j]!=255)
			 {
				 count ++;
				 if(SetOperators.intersection(og.getEdges()[i][j], sg.getEdges()[i][j])!=0)
					 hit++;
				 else
				 {
					 System.out.println(og.getName()+" i: "+i+"  "+"j "+j+"  " +Relation.translateToString(og.getEdges()[i][j]));
					 System.out.println(sg.getName()+" i: "+i+"  "+"j "+j+"  " +Relation.translateToString(sg.getEdges()[i][j]));
				 }
			 }
			 
			 
			 
		 }
	 
	 return hit/count;
	 
	 
	 
	 
 }
}
