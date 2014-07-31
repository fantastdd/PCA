package test;

import essential.Relation;
import essential.CompositionTable;
import essential.SetOperators;

public class testAssociativity {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	int count = 0;
        for (int i = 1;i<=255;i++)
        {
        	int reli = i;  
        	for (int j = 1;j<=255;j++)
        	  {
        		int relj = j;  
        		for (int k = 1;k<=255;k++)
        		{
        			int relk = k;
        			int relleft = CompositionTable.LookUpTable(CompositionTable.LookUpTable(reli, relj),relk);
        			int relright = CompositionTable.LookUpTable(reli,CompositionTable.LookUpTable(relj, relk));
        			
        			if(relleft != relright)
        				System.out.println("Oops: "+BinaryRelations.translateToString(reli)+" "+BinaryRelations.translateToString(relj)+"   "+BinaryRelations.translateToString(relk));
        			count++;
        		}
        	  }
        }
        System.out.println(count);
        */
		  for (int i = 1;i<=255;i++)
	        {
	        	int reli = i;  
	        	for (int j = 1;j<=255;j++)
	        	  {
	        		int relj = j;  
	        		for (int k = 1;k<=255;k++)
	        		{
	        			int relk = k;
	        			int relleft = CompositionTable.LookUpTable(relj,reli);
	        			int relright = CompositionTable.LookUpTable(relk,reli);
	        			int combine = SetOperators.intersection(relleft,relright);
	        			
	        			if(SetOperators.intersection(relj,relk)!= 0)
	        				
	                  {
	        			int combine2 = CompositionTable.LookUpTable(SetOperators.intersection(relj,relk),reli);
	        			    if(combine != 0 && (combine2 == 0))
	        				    System.out.println("Oops: "+Relation.translateToString(combine)+" "+Relation.translateToString(combine2)+"   "+
	        				    		Relation.translateToString(reli) + "   " +	Relation.translateToString(relj) + "   "+Relation.translateToString(relk));
	        			}
	        			else
	        			{
	        				
	        				if (combine != 0)
	        					   System.out.println("Oops: "+Relation.translateToString(combine)+"   "+
		        				    		Relation.translateToString(reli) + "   " +	Relation.translateToString(relj) + "   "+Relation.translateToString(relk));
	        			}
	        		}
	        	  }
	        }
	}

}
