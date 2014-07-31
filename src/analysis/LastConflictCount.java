package analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class LastConflictCount {
     public static HashMap<EntropyConstraint,Integer> failureEdges = new HashMap<EntropyConstraint,Integer>();
     public static HashMap<EntropyConstraint,Integer> lastConflicts = new HashMap<EntropyConstraint,Integer>();
     public static HashMap<EntropyConstraint, Integer> countLastConflict(String filename) throws IOException
     {
    	 lastConflicts.clear();
    	 File file = new File(filename);
         FileReader fr = new FileReader(file);
         BufferedReader bf = new BufferedReader(fr);
         while(bf.ready())
         {
        	 String line = bf.readLine();
        	 if (line.contains("Real Last Conflict:"))
        	 {
        		 String I = (line.substring(line.indexOf(":")+1,line.indexOf(",")));
        		 String J = line.substring(line.indexOf(",")+1);
        		 if ( I.equals("18446744073709551615") && J.equals("18446744073709551615"))
        			 continue;
                 int i = Integer.parseInt(I);
                 int j = Integer.parseInt(J);
        		 EntropyConstraint ec = new EntropyConstraint(i,j,0);
        		 if (lastConflicts.containsKey(ec))
        			 lastConflicts.put(ec,(lastConflicts.get(ec)+1));
        		 else 
        			 lastConflicts.put(ec,1);        	 
        	}
        	 
         }
         bf.close();
         fr.close();
         for(EntropyConstraint ec: lastConflicts.keySet())
         {
        	 System.out.println(ec.getI()+"    "+ec.getJ() + ":        " + lastConflicts.get(ec));
         }
		return lastConflicts;
     
     
     
     
     
     }
     public static HashMap<EntropyConstraint, Integer> countFailureEdges(String filename) throws IOException
     {
    	 failureEdges.clear();
    	 File file = new File(filename);
         FileReader fr = new FileReader(file);
         BufferedReader bf = new BufferedReader(fr);
         while(bf.ready())
         {
        	
        	 String line = bf.readLine();
        	 if (line.contains("Queue Report Failure:"))
        	 {

        		 String I = (line.substring(line.indexOf(":")+1,line.indexOf(",")));
        		 String J = line.substring(line.indexOf(",")+1);
        		 if ( I.equals("18446744073709551615") && J.equals("18446744073709551615"))
        			 continue;
                 int i = Integer.parseInt(I);
                 int j = Integer.parseInt(J);
        		 EntropyConstraint ec = new EntropyConstraint(i,j,0);
        		 if (failureEdges.containsKey(ec))
        			 failureEdges.put(ec,(failureEdges.get(ec)+1));
        		 else 
        			 failureEdges.put(ec,1);        	 
        	}
        	 
         }
         bf.close();
         fr.close();
     
         for(EntropyConstraint ec: failureEdges.keySet())
         {
        	 System.out.println(ec.getI()+"    "+ec.getJ() + ":        " + failureEdges.get(ec));
         }
     
         return failureEdges;
     
     
     
     }
     
     
     
}
