package test;

import java.util.LinkedHashSet;
import java.util.LinkedList;

import essential.CompositionTable;
import essential.MTS;
import essential.SetOperators;

public class testUpdatesProbability {
 public static void main(String args[])
 {
	 double count = 0;
	 double total = 0;
	 LinkedHashSet<Integer> NP8 = new LinkedHashSet<Integer>();
	 NP8 = SetOperators.buildPowerSetBinary();
	 NP8.removeAll(MTS.BIMTS_H8);
	 for (Integer con1 : NP8)
	 {
		LinkedList<Integer> splitset = SetOperators.splitToMinmumSubsets(con1, "H8");
		 //LinkedList<Integer> splitset = SetOperators.splitToAtomicLabel(con1);
	   for (Integer s1 : splitset)
		 for (Integer con2: NP8)
		     for (Integer con3: NP8)
		     {
		    	 int newcon2 = CompositionTable.LookUpTable(s1, con3);
		    	 int newcon3 = CompositionTable.LookUpTable(s1, con2);
		    	 if ((newcon2 < con2) || (newcon3 < con3))
                       count ++;  
		         total ++ ;
		     }
		 
	 }
	 
	 count =  count/2;
	 total = total/2;
	 System.out.println((double)(count/total));
	 
 }
}
