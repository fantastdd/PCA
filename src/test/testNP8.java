package test;

import java.util.Iterator;
import java.util.LinkedHashSet;

import deprecated.ReRelation;

import essential.CompositionTable;
import essential.MTS;
import essential.SetOperators;

public class testNP8 {
public static void main(String args[])
{
double count = 0;
LinkedHashSet NP8 = SetOperators.buildPowerSet();

NP8.removeAll(MTS.getMTS("H8"));	
System.out.println(NP8.size());
Iterator iter0 = NP8.iterator();
double counter[] = new double[108];
int index = 0;
while(iter0.hasNext())
{	
	LinkedHashSet<ReRelation> rel0 = (LinkedHashSet<ReRelation>) iter0.next();
    Iterator iter1 = NP8.iterator();
    counter[index] = count;
    while (iter1.hasNext())
    	{
    	LinkedHashSet<ReRelation> rel1 = (LinkedHashSet<ReRelation>) iter1.next();
    	 Iterator iter2 = NP8.iterator();
    	
    	  while (iter2.hasNext())
    	  {
    		   
    		    LinkedHashSet<ReRelation> rel2 = (LinkedHashSet<ReRelation>) iter2.next();
    			if (
    			!(CompositionTable.lookUpTable(rel0, rel1)).containsAll(rel2)||
    			!(CompositionTable.lookUpTable(	(LinkedHashSet<ReRelation>) SetOperators.inverse(rel2),
    					rel0).containsAll(SetOperators.inverse(rel1)))||
    					
    	    	!(CompositionTable.lookUpTable(	rel2,(LinkedHashSet<ReRelation>) SetOperators.inverse(rel1)
    	    					).containsAll(rel0))
    	    		)
    					
    			{
    				count ++;
    				System.out.println(count +"  o :"+rel0+" relL  "+ rel1 + "relR  "+rel2);
    				System.out.println("--------------");
    			
    			}
    	  
    	  }
    	
    	}
    counter[index] = count - counter[index];
    index++;
}
System.out.println(count);
System.out.println(count/Math.pow(NP8.size(),3));
for (int i = 0;i<counter.length;i++)
System.out.println("Counter:   " + counter[i]);








}
}
