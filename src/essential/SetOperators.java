package essential;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import deprecated.ReConstraint;
import deprecated.ReRelation;


public class SetOperators {
private static LinkedList<LinkedList<LinkedHashSet<ReRelation>>> splitCandidnate;
private static LinkedList<LinkedList<Integer>> bisplitCandidnate;


public static int union(int a, int b)
{
    
	return a+b;
}



public static int intersection(int a, int b)
{
   return a&b;	


}
public static  LinkedList<ReConstraint<Integer>> splitToMinmumSubsets(ReConstraint<Integer> a,String MTStype){
	
	HashMap<Integer, LinkedList<LinkedHashSet<ReRelation>>> CurrentMTS;
	if(MTStype.equalsIgnoreCase("H8"))
		CurrentMTS = MTS.getMTSMAP(MTStype);
	else
		CurrentMTS = MTS.MTSMAP;
			
	LinkedHashSet<ReRelation> relation = a.getRelations();
	LinkedList<LinkedHashSet<ReRelation>> resultRelation = new LinkedList<LinkedHashSet<ReRelation>>();
	LinkedList<ReConstraint<Integer>> result = new LinkedList<ReConstraint<Integer>>();
	int size = relation.size();
    splitCandidnate	= new LinkedList<LinkedList<LinkedHashSet<ReRelation>>>();
	for (int i = size;i>0;i--)
	{
     	splitCandidnate.add(CurrentMTS.get(i));
	}
//	currentProcessingGroup.add(splitCandidnate.pop());
  //  if(splitsSuccess(relation,resultRelation))
   // 	return null;
	resultRelation = splitsSuccess(relation,resultRelation);
	for (LinkedHashSet<ReRelation> set:resultRelation)
	{
		ReConstraint<Integer> constraint = new ReConstraint<Integer>(set,a.getSource(),a.getDestination());
	    result.add(constraint);
	}
	return result;
}
public static  LinkedList<Constraint<Integer>> splitToMinmumSubsets(Constraint<Integer> a,String MTStype){
	
	HashMap<Integer, LinkedList<Integer>> CurrentMTS;
	if(MTStype.equalsIgnoreCase("H8"))
		CurrentMTS = MTS.getBIMTSMAP(MTStype);
	else
		CurrentMTS = MTS.BIMTSMAP;
			
	int relation = a.getRelations();
	LinkedList<Integer> resultRelation = new LinkedList<Integer>();
	LinkedList<Constraint<Integer>> result = new LinkedList<Constraint<Integer>>();
	
	int size = Integer.bitCount(relation);
    bisplitCandidnate	= new LinkedList<LinkedList<Integer>>();
	for (int i = size;i>0;i--)
	{
     	bisplitCandidnate.add(CurrentMTS.get(i));
	}
//	currentProcessingGroup.add(splitCandidnate.pop());
  //  if(splitsSuccess(relation,resultRelation))
   // 	return null;
	resultRelation = splitsSuccess(relation,resultRelation);
	for (Integer set:resultRelation)
	{
		Constraint<Integer> constraint = new Constraint<Integer>(set,a.getSource(),a.getDestination());
	    result.add(constraint);
	}
	return result;
}
public static  LinkedList<Integer> splitToMinmumSubsets(int a,String MTStype){
	
	HashMap<Integer, LinkedList<Integer>> CurrentMTS;
	if(MTStype.equalsIgnoreCase("H8"))
		CurrentMTS = MTS.getBIMTSMAP(MTStype);
	else
		CurrentMTS = MTS.BIMTSMAP;
			
	int relation = a;
	LinkedList<Integer> resultRelation = new LinkedList<Integer>();
	
	
	int size = Integer.bitCount(relation);
    bisplitCandidnate	= new LinkedList<LinkedList<Integer>>();
	for (int i = size;i>0;i--)
	{
     	bisplitCandidnate.add(CurrentMTS.get(i));
	}
//	currentProcessingGroup.add(splitCandidnate.pop());
  //  if(splitsSuccess(relation,resultRelation))
   // 	return null;
	
	resultRelation = splitsSuccess(relation,resultRelation);
	 LinkedList<Integer> result = new LinkedList<Integer>();
	//------ Filter Replicated atoms ---- e.g. {TPP,NTPP,DC} {TPPI,NTPPI,DC,} REMOVE DC from either set 
	int rsize = resultRelation.size();
	for (int i = 0; i < rsize;i++)
	{
		int current = resultRelation.get(i);
		for (int j = i + 1; j < rsize; j++)
		{
			int next = resultRelation.get(j);
			if (SetOperators.intersection(current,next) != 0)
			    {
			    	   // System.out.println("current " +BinaryRelations.translateToString(current));	
				     //   System.out.println("next    "+ BinaryRelations.translateToString(next));
				      //  System.out.println("current intersect next  " + 
				     //                       BinaryRelations.translateToString(SetOperators.intersection(current,next)));
				     //   System.out.println( "current XOR next:  " + BinaryRelations.translateToString(current^next));
			        	current = current & (current^next);
			       
			    }
			
		}
		if (current != 0)
		result.add(current);
	}
	
	return result;
}

public static  LinkedList<Integer> splitToAtomicLabel(int a){
	

	LinkedList<Integer> resultRelation = new LinkedList<Integer>();
    String binary = Integer.toBinaryString(a);
    for (int j = 0; j < binary.length();j++)
    {     
		 char rs = binary.charAt(j);
		 if(rs == '1')
			resultRelation.add((1<<binary.length()-1-j));
	}
			   
	return resultRelation;
}









private static LinkedList<LinkedHashSet<ReRelation>>  splitsSuccess(LinkedHashSet<ReRelation> rel,LinkedList<LinkedHashSet<ReRelation>> resultRelation)
{
for(LinkedHashSet<ReRelation> currentSet:splitCandidnate.pop())
{
	// System.out.println("------"+currentSet);
	if (rel.containsAll(currentSet)&& checkSubSet(resultRelation,currentSet)){
	// System.out.println(currentSet);
	 resultRelation.add(currentSet);}
}

if (checkSplitCompleted(rel,resultRelation))
	 return resultRelation;
else
{
	 return splitsSuccess(rel,resultRelation);
}
}


private static LinkedList<Integer>  splitsSuccess(Integer rel,LinkedList<Integer> resultRelation)
{
	
for(Integer currentSet:bisplitCandidnate.pop())
{
	/*System.out.println("Relation: "+BinaryRelations.translateToString(currentSet));
	System.out.println("rel: "+rel);
	System.out.println("currentSet: "+currentSet);
	System.out.println("currentSet rel: "+(rel^currentSet));
	System.out.println((rel^currentSet) == currentSet);*/
	// System.out.println("------"+currentSet);
	if (((rel&currentSet) == currentSet)&& checkSubSet(resultRelation,currentSet)){
	// System.out.println(currentSet);
	 resultRelation.add(currentSet);}
}

if (checkSplitCompleted(rel,resultRelation))
	 return resultRelation;
else
{
	 return splitsSuccess(rel,resultRelation);
}
}
// judge whether b is a subset of any element in the list of a
private static boolean checkSubSet(LinkedList<LinkedHashSet<ReRelation>> result,LinkedHashSet<ReRelation> set)
{
    for (LinkedHashSet<ReRelation> check:result)
    {
    	if(check.containsAll(set))
    		return false;
    }	
    return true;


}

//judge whether b is a subset of any element in the list of a
private static boolean checkSubSet(LinkedList<Integer> result,int set)
{
 for (Integer check:result)
 {
 	if((check&set) == set)
 		return false;
 }	
 return true;


}


private static boolean checkSplitCompleted(LinkedHashSet<ReRelation> rel,LinkedList<LinkedHashSet<ReRelation>> resultRelation)
{
	
	LinkedHashSet<ReRelation> checkRel = new LinkedHashSet<ReRelation>();
	for(LinkedHashSet currentSet:resultRelation)
	{
	 checkRel.addAll(currentSet);
	}
	
	if (checkRel.equals(rel))
		return true;
	else
		return false;




}
private static boolean checkSplitCompleted(Integer rel,LinkedList<Integer> resultRelation)
{
	

	int check = 0; 
	for(Integer currentSet:resultRelation)
	{
	 check =  check | currentSet;
	}
	
	if (check == rel)
		return true;
	else
		return false;




}


public static int inverse(int w)
{
 
	  if (!(((w>>5^1)%2 == 0)&&((w>>7^1)%2 ==0)))
	  { 
		
		  if ((w>>5^1)%2 ==0)
	    {
		  w = w > 1<<5 ? w^(1<<5):(1<<5)^w;
		
		  w = w> 1<<7 ? w|(1<<7):(1<<7)|w;
	    }
	    else
	   
	     if ((w>>7^1)%2 ==0)
	    {
	      w = w<<1 > 1<<7 ? w^(1<<7):(1<<7)^w;
	   	  w = w<<1 > 1<<5 ? w|(1<<5):(1<<5)|w;
	     }
	  }	  
	 

	  if (!( ((w>>3^1)%2 ==0)&& ((w>>6^1)%2 ==0 )))
	  {
		
		 if ((w>>3^1)%2 ==0)
	    {
			 w = w > 1<<3 ? w^(1<<3):(1<<3)^w;
			  w = w> 1<<6 ? w|(1<<6):(1<<6)|w;
		
	    }
		 else
	   
	    if ((w>>6^1)%2 ==0)
	  {
	    	 w = w > 1<<6 ? w^(1<<6):(1<<6)^w;
	   	  w = w > 1<<3 ? w|(1<<3):(1<<3)|w;
	  
	  }
	  }	  
	  return w;
}
public static Collection<ReRelation> inverse(Collection<ReRelation> a)
{
LinkedHashSet<ReRelation> b = new LinkedHashSet<ReRelation>();	
for (ReRelation rel:a)
{
 
 switch (rel){
 case DC:rel = ReRelation.DC; break;
 case EC:rel = ReRelation.EC; break;
 case PO:rel = ReRelation.PO; break;
 case TPP:rel = ReRelation.TPPi; break;
 case NTPP:rel = ReRelation.NTPPi;break;
 case TPPi:rel = ReRelation.TPP;break;
 case NTPPi:rel = ReRelation.NTPP;break;
 case EQ: rel = ReRelation.EQ;break;
 }
 
 b.add(rel);
 
}
return b;
}
/**
 * 
 * @return power set of P8 without empty subset. 255. 
 */
public static LinkedHashSet<LinkedHashSet<ReRelation>> buildPowerSet() 
{
	ReRelation brel[] = { ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.NTPP,ReRelation.TPP,ReRelation.NTPPi,ReRelation.TPPi,ReRelation.EQ };	
	LinkedHashSet<LinkedHashSet<ReRelation>> hashSet = new LinkedHashSet<LinkedHashSet<ReRelation>>();
	  int len = brel.length;
      int elements = (int) Math.pow(2, len);
    for (int i = 0; i < elements; i++) {
          String str = Integer.toBinaryString(i);
          int value = str.length();
          String pset = str;
          for (int k = value; k < len; k++) {
                  pset = "0" + pset;
          }
          LinkedHashSet<ReRelation> set = new LinkedHashSet<ReRelation>();
          for (int j = 0; j < pset.length(); j++) {
                  if (pset.charAt(j) == '1')
                          set.add(brel[j]);
          }
          hashSet.add(set);
  }
    // remove the empty sub-set
    hashSet.remove(new LinkedHashSet<ReRelation>());
return hashSet;
}
public static LinkedHashSet<Integer> buildPowerSetBinary()
{
	LinkedHashSet<Integer> powerset = new LinkedHashSet<Integer>();
	for (int i = 1;i<255;i++)
   {
		powerset.add(i);
   }
   return powerset;

}


public static void main(String args[])
{
	
/*	
	LinkedHashSet<Relation> rels = new LinkedHashSet<Relation>();
	rels.add(Relation.TPP);
//	rels.add(Relation.NTPPi);
//	rels.add(Relation.EQ);
	rels.add(Relation.TPPi);
	Constraint<Integer> a = new Constraint(rels,1,2);
	LinkedList<Constraint<Integer>> sc = SetOperators.splitToMinmumSubsets(a,"H8");
    for (Constraint j:sc)
    	System.out.println(j);*/
   int i = 255;
   int j = 32;
   System.out.println(Relation.translateToString(SetOperators.intersection(i,j)));

   System.out.println(Relation.translateToString(SetOperators.inverse(32)));



}
}
