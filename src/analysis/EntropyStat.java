package analysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import tool.GQRGraphReader;
import essential.PCGraph;

public class EntropyStat {
	public static LinkedList<EntropyConstraint> averageCons = new LinkedList<EntropyConstraint>();
	public static LinkedList<EntropyConstraint> encons = new LinkedList<EntropyConstraint>();
	public static int total;
	public static int top;
	public static void printStat(PCGraph graph)
	{

		for (int i = 0; i < graph.getNumOfNodes()-1;i++)
			for (int j = i+1; j <graph.getNumOfNodes();j++)
			{   
			  if (graph.getEdges()[i][j] != 255) {
				int entropy = 0;
				entropy = Entropy.entropy(graph, i, j);
				EntropyConstraint en = new EntropyConstraint(i,j,entropy);
				Entropy.entropy.add(en);
				System.out.print(i + "  "+ j + "  "+ entropy + "   " + Integer.bitCount(graph.getEdges()[i][j]) +" ");
				System.out.println("  "+0);
			  }
			}
	}
		public static void analysisEntropy()
		{
			total = 0;
			top = 0;
			int i = Entropy.entropy.get(0).getI(); 
			int j = Entropy.entropy.get(0).getJ();
			int average = Entropy.entropy.get(0).getEntropy();
			int count = 1;
			for(EntropyConstraint index: Entropy.entropy)
			{
			  	top = (top > index.getEntropy()) ? top : index.getEntropy();
				if (index.getI() == i & index.getJ() == j)
			  		 continue;
			 	if (index.getI() == i || index.getJ() == i)
			 	{
			 		average += index.getEntropy();
			 		
			 		count++;
			 	}
			 	else
			 	{
			 		System.out.println("Average Entropy for variable "+i+" is " + average/count);
			 		total += ( average/count );
			 		averageCons.add(new EntropyConstraint(i,j,average/count));
			 		average = 0;
			 		count = 0;
			 		i = index.getI();
			 		j = index.getJ();
			 		average += index.getEntropy();
			 		count++;
			 		
			 	}
				
			}
			total = total / averageCons.size();
			System.out.println("Total Average:  "+total);
			System.out.println("Top :  "+top);
		}
		public static LinkedList<EntropyConstraint> getCandidnates(PCGraph graph,boolean total_sort,double threshold)
		{
		 
			for (EntropyConstraint aver:averageCons)
			{
				for(EntropyConstraint current:Entropy.entropy)
				{
				//	System.out.println("current:"+current.getEntropy());
				//	System.out.println("aver:"+aver.getEntropy());
				//  System.out.println("!"+Math.abs(current.getEntropy() - aver.getEntropy()));
					
					//  && (Math.abs(current.getEntropy() - aver.getEntropy())<=4)
					if(aver.getI() == current.getI()&& aver.getEntropy() > (total - 1) && aver.getEntropy() < top) //  
					{
						int count = 0;
						Loop: for (int i = 0; i < graph.getNumOfNodes()-1;i++)
							for (int j = i+1; j <graph.getNumOfNodes();j++)
							{
								if(graph.getEdges()[i][j] != 255)
								{ 
									count ++ ;
								
								}
								if(i == current.getI() && j == current.getJ())
								{
									//---- First Filtered --------------------------------------------
									System.out.println("#"+count+"-N60-D14-L4: 1" + "  "+i+"   "+j);
									encons.add(new EntropyConstraint(i,j,current.getEntropy()));
									break Loop;
								}
							
							}
						
					}
				}
			}
			 System.out.println("------------------ Preprocess End ---------------------");
             LinkedList<EntropyConstraint> result =  new LinkedList<EntropyConstraint>();
             
			 result = filter(total_sort,threshold);
		
		 	// return encons;
			 return result;
		}
		public static void printCandidates(PCGraph graph, LinkedList<EntropyConstraint> candidnates, HashMap<EntropyConstraint,Integer> failureEdges
										  ,  HashMap<EntropyConstraint,Integer> lastconfilicts)
		{
			 
			 int count;
		 	 for (EntropyConstraint ec:candidnates)
		 	 {
		 		 count = 0;
		 		 Loop: for (int i = 0; i < graph.getNumOfNodes()-1;i++)
						for (int j = i+1; j <graph.getNumOfNodes();j++)
						{
							if(graph.getEdges()[i][j] != 255)
							{ 
								count ++ ;
							
							}
							if(i == ec.getI() && j == ec.getJ())
							{
								//---- First Filtered --------------------------------------------
								if(lastconfilicts.get(ec) == null)
									lastconfilicts.put(ec, 0);
								if (failureEdges.get(ec) == null)
									failureEdges.put(ec, 0);
								System.out.println("#"+count+"-tuple" + "  "+i+"   "+j + "   A "+ec.getEntropy()
										+" F " + failureEdges.get(ec) + " C " + lastconfilicts.get(ec) + "  Total  " + (failureEdges.get(ec) + lastconfilicts.get(ec)) );
					
								break Loop;
							}
						
						}
		 		 
		 		 
		 	 }
		 	 
		}

		/**Get non-redunant variables, at least appears one time but no more than 2.
		 * */
		private static LinkedList<EntropyConstraint> filter (boolean total_sort,double threshold)
		{
			 // Intger a = variable   Integer b = number of b's appearance in result
			 HashMap<Integer,Integer> candidnates = new HashMap<Integer,Integer>();
			 int maximumAppearance = 0;
			 ArrayList<Integer> theMaximumVar = new ArrayList<Integer>(); // May be two ..... revise later
			 
			 candidnates = preprocess(4);
		
			 HashMap<Integer, LinkedList<EntropyConstraint>> resulttable = new  HashMap<Integer, LinkedList<EntropyConstraint>>();
			 for (Integer var:candidnates.keySet())
			 {
				 if (candidnates.get(var) > maximumAppearance)
					 {
					   maximumAppearance = candidnates.get(var);   
					   theMaximumVar.add(var); 
					 }
				
				 for (EntropyConstraint ec: encons)
				 {
			
					 if ((ec.getI()==var && candidnates.containsKey(ec.getJ())) ||
					     (ec.getJ()==var && candidnates.containsKey(ec.getI())))
					 {
					//	 System.out.println("var   "+var);
					//	 System.out.println("ec.getI()   "+ ec.getI());
					//	 System.out.println("ec.getJ()   "+ ec.getJ());
					//	 if (ec.getI() == 7 && ec.getJ() == 196)
				    //			  System.out.println("Detect:  " +  (candidnates.get(ec.getI())+ candidnates.get(ec.getJ())));
					     EntropyConstraint econ = new EntropyConstraint(ec.getI(),ec.getJ(),
	                             (candidnates.get(ec.getI())+ candidnates.get(ec.getJ())) // appearance, NOT ENTROPY
	                            );
						 if(resulttable.containsKey(var))
						 {
				
							 resulttable.get(var).add(econ);
						 }
					     else
					     {
					    	 LinkedList<EntropyConstraint> temp = new LinkedList<EntropyConstraint>();
					    	 
					    	 temp.add(econ);
					    	 resulttable.put(var,temp);
					     }
					 }
				 }
			 }
			 
			 //------ Only pick up one constraint that contains a variable with highest appearance
			 for (Integer key:resulttable.keySet())
			 {
				 int max = 0;
				 EntropyConstraint selectedCons = new EntropyConstraint();
				 for (EntropyConstraint current:resulttable.get(key))
				 {
					 if (current.getEntropy() >= max)
					 {
						 max = current.getEntropy();
						 selectedCons = current;
					
					 }
				 }
				 resulttable.get(key).clear();
				 resulttable.get(key).add(selectedCons);
			 }
			 // ------- get second final result -------------------------
			 LinkedList<EntropyConstraint> result = new LinkedList<EntropyConstraint>();
			 for (Integer key:resulttable.keySet())
			 {
				result.add(resulttable.get(key).get(0));
				
			 }
			 
			 //------- Final result Sort according to highest appearance, make the variable appear most times top 
			 LinkedList<EntropyConstraint> others = new LinkedList<EntropyConstraint>();
			 LinkedList<EntropyConstraint> top = new LinkedList<EntropyConstraint>();
			 LinkedList<EntropyConstraint> tops = new LinkedList<EntropyConstraint>();
			 LinkedList<Integer> atTop = new LinkedList<Integer>();
			 LinkedList<EntropyConstraint> finalResult = new LinkedList<EntropyConstraint>();
		
			 for (int i = theMaximumVar.size() - 1; i >= 0 ; i-- )
			 {
			     
			
				 if (candidnates.get(theMaximumVar.get(i)) == maximumAppearance)
				 {
				
					 for (Integer key:resulttable.keySet())
					 {
						 if(resulttable.get(key).get(0).getI() == theMaximumVar.get(i) ||
						    resulttable.get(key).get(0).getJ() ==  theMaximumVar.get(i) )
						 { 
							 if(!top.contains(resulttable.get(key).get(0)))
							 top.add(resulttable.get(key).get(0)); }
					 }
					 Collections.sort(top);
					 tops.addAll(top);
					 atTop.add(theMaximumVar.get(i));
	    		}
			 }

			 for (Integer key:resulttable.keySet())
			 {
				 if(key==53)
				 {		
					 System.out.println("Detect " + resulttable.get(key).get(0));
				        System.out.println( !tops.contains(resulttable.get(key).get(0)));
				      for (EntropyConstraint tar: tops)
				      {
				    	  if(tar.equals(resulttable.get(key).get(0)))
				    		  System.out.println("Detect " + tar);
				      }
				 
				 }
				 if(!atTop.contains(resulttable.get(key).get(0).getI()) &&
						 !atTop.contains(resulttable.get(key).get(0).getJ())   )
					 if(!others.contains(resulttable.get(key).get(0)) && !tops.contains(resulttable.get(key).get(0)))
					 {    
						 
						 others.add(resulttable.get(key).get(0));
					 
					 }
			 }
			 Collections.sort(others);
     		 finalResult.addAll(others);
			 finalResult.addAll(tops);
		//	 	 Collections.sort(result);
			 if (total_sort)
             	 Collections.sort(finalResult);
			 //---------- Only Retain top percentage -----------------------
			 int returnSize = (int) (finalResult.size() * threshold);
			 Iterator it = finalResult.iterator();
			 int removeSize = finalResult.size() - returnSize;
			 while (it.hasNext() && removeSize > 0)
			 {
				 EntropyConstraint constraint = (EntropyConstraint) it.next();
				 it.remove();
				 removeSize--;
			 }
			 
			 
			 return finalResult;
			 
			 
			 
			 
			 
			 
		}
		/** get the variables in first-filtered list ,T	hose variables which appear less than num will be filtered 
		 * 
		 * */
        private static HashMap<Integer,Integer> preprocess(int num)
        {
        	HashMap<Integer,Integer> temp = new HashMap<Integer,Integer>();
    
        	for (EntropyConstraint ec : encons)
        	{
        	/*	if(ec.getI() == 6 || ec.getJ() ==6)
        			System.out.println("*********** Find Six *********");*/
        		if (temp.containsKey(ec.getI()))
        			temp.put(ec.getI(),(1 + temp.get(ec.getI())));
        		else
        			temp.put(ec.getI(), 1);
        		
          		if (temp.containsKey(ec.getJ()))
        			temp.put(ec.getJ(),(1 + temp.get(ec.getJ())));
          		else
          			temp.put(ec.getJ(), 1);
        	}
        	
        	HashMap<Integer,Integer> result = new HashMap<Integer,Integer>();
        	for (Integer index:temp.keySet())
        	{
        		int numOfAppearance = temp.get(index);
        	
        		if(!(numOfAppearance < num))
        		{	
        			result.put(index,numOfAppearance);
        	        // System.out.println("Index  "+ index + "  " + numOfAppearance );	
        		}
        	
        	}
        	return result;
        }

	public static void main(String args[]) throws IOException
	{
		//Graph graph = ReadGraphFromGQR.readGraphFromGQR("./JAVAGNI/H_Jochen_325.csp");
//	Graph graph = GQRReader.readGraphFromGQR("./JAVAGNI/H_FIRST_200_20_4_PC.csp");
	//	Graph graph = GQRReader.readGraphFromGQR("/home/users/xiaoyuge/Emp/GNI/Test_Graph.csp");
	PCGraph graph = GQRGraphReader.readGraphFromGQR("/home/users/xiaoyuge/Emp/GNI/H_40th_220_20_4_PC.csp");
//	Graph graph = GQRReader.readGraphFromGQR("./JAVAGNI/H_SEVENTH_200_20_4_PC.csp");
//    	Graph graph = GQRReader.readGraphFromGQR("./JAVAGNI/H_FOURTH_200_20_4_PC.csp");
//		Graph graph = GQRReader.readGraphFromGQR("./JAVAGNI/H_SEVENTH_200_20_4_PC.csp");
	//   Graph graph = GQRReader.readGraphFromGQR("./JAVAGNI/H_EIGHTH_200_20_4_PC.csp");
	//	 Graph graph = GQRReader.readGraphFromGQR("./JAVAGNI/H_23th_200_20_4_PC.csp");
	//	Graph graph = GQRReader.readGraphFromGQR("/home/users/xiaoyuge/Emp/GNI/H_33th_220_20_4.csp");
//		Graph graph = GQRReader.readGraphFromGQR("./JAVAGNI/H_TWLTH_200_20_4_PC.csp");
//		Graph graph = GQRReader.readGraphFromGQR("./JAVAGNI/H_13th_200_20_4_PC.csp");
	//	Graph graph = GQRReader.readGraphFromGQR("./JAVAGNI/H_FIRST_256_20_4_PC.csp");
	//	Graph graph = GQRReader.readGraphFromGQR("./JAVAGNI/H_TENTH_200_20_4_PC.csp");
//		Graph graph = GQRReader.readGraphFromGQR("./JAVAGNI/H_FIFTH_200_20_4_PC.csp");
//		Graph graph = GQRReader.readGraphFromGQR("./JAVAGNI/H_ELEVENTH_200_20_4_PC.csp");
		//	Graph graph = GQRReader.readGraphFromGQR("./JAVAGNI/H_NEW_60_14_4_1_PC.csp");
	//	Graph graph = GQRReader.readGraphFromGQR("./JAVAGNI/H_NEW_60_14_4_20_PC.csp");
	//	Graph graph = GQRReader.readGraphFromGQR("./JAVAGNI/H_NEW_60_14_4_55_PC.csp");
	//	Graph graph = GQRReader.readGraphFromGQR("./JAVAGNI/H_NEW_60_14_4_3_PC.csp");
	//	Graph graph = GQRReader.readGraphFromGQR("./JAVAGNI/H_NEW_60_14_4_14_PC.csp");
		//Graph graph = ReadGraphFromGQR.readGraphFromGQR("./JAVAGNI/H_NEW_NORMAL.csp");
	    graph.setName("Oringial");
	 //   EntropyStat.printStat(graph); 
	    Entropy.calEntropy(graph);
	    EntropyStat.analysisEntropy();
       LinkedList<EntropyConstraint>  candidnates = EntropyStat.getCandidnates(graph,true,0.15);
  //   LinkedList<EntropyConstraint>  candidnates = EntropyStat.getCandidnates(graph,false,0.15);
	    System.out.println("--------------------- Count Last Conflict --------------------------------");
	   // LastConflictCount.countLastConflict("./JAVAGNI/H_8th_200_20_14_S");
	   // LastConflictCount.countLastConflict("./JAVAGNI/H_10th_200_20_14_S");
	    HashMap<EntropyConstraint, Integer> lastconfilicts =  LastConflictCount.countLastConflict("/home/users/xiaoyuge/Emp/GNI/H_11th_200_20_4_PC_S");
	    System.out.println("--------------------- Count Failed Edges --------------------------------");
	    HashMap<EntropyConstraint, Integer> failureEdges = LastConflictCount.countFailureEdges("/home/users/xiaoyuge/Emp/GNI/H_11th_200_20_4_PC_S");
	    
	    EntropyStat.printCandidates(graph, candidnates, failureEdges, lastconfilicts);
	}
}