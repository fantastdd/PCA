package test;

import java.util.ArrayList;

import essential.Relation;
import essential.CompositionTable;
import essential.MTS;
import essential.SetOperators;

public class testBiOperations {
public static void main(String args[])
{
	
    int i = Relation.TPP.id + Relation.TPPi.id; 
    System.out.println(i);
	System.out.println(MTS.BIMTS.contains(i));

     System.out.println(Integer.toBinaryString(1));
	  System.out.println(1<<0); // 2 << 3    data << bits
    int k = 1;
    int t;
    t = k<<1;
    t = k<<1;
  System.out.println(k);
  System.out.println(t);
  
  Integer[][] a= {{1,1},{2,2}};
  ArrayList<Integer[][]> b = new ArrayList<Integer[][]>();
 // b.add(a[0][1]);
  Integer[][] c;
  c = a;
  System.out.println("c "+ c[0][0]);
  a[0][0] = 2;
  System.out.println("c  " +c[0][0]);
  
  int w = 48;
  System.out.println((w>>5^1)%2 ==0);
  System.out.println((w>>7^1)%2 ==0);
  System.out.println(!(((w>>5^1)%2 == 0)&&((w>>7^1)%2 ==0)));
 
  if (!(((w>>5^1)%2 == 0)&&((w>>7^1)%2 ==0)))
  { 
	//  System.out.println("@@@@"+w);
	  if ((w>>5^1)%2 ==0)
    {
	  w = w > 1<<5 ? w^(1<<5):(1<<5)^w;
	//  System.out.println("@@@@"+w);
	  w = w> 1<<7 ? w|(1<<7):(1<<7)|w;
    }
  else
   
    if ((w>>7^1)%2 ==0)
  {
      w = w<<1 > 1<<7 ? w^(1<<7):(1<<7)^w;
   	  w = w<<1 > 1<<5 ? w|(1<<5):(1<<5)|w;
  }
  }	  
  System.out.println("!!!"+w);

  if (!( ((w>>3^1)%2 ==0)&& ((w>>6^1)%2 ==0 )))
  {
	
	 if ((w>>3^1)%2 ==0)
    {
		 w = w > 1<<3 ? w^(1<<3):(1<<3)^w;
		  w = w> 1<<6 ? w|(1<<6):(1<<6)|w;
	  System.out.println(w);
    }
	 else
   
    if ((w>>6^1)%2 ==0)
  {
    	 w = w > 1<<6 ? w^(1<<6):(1<<6)^w;
   	  w = w > 1<<3 ? w|(1<<3):(1<<3)|w;
  
  }
  }	  
  System.out.println(Relation.translateToString(w));
  System.out.println(Relation.translateToString(CompositionTable.LookUpTable(64, 10)));
  System.out.println(Relation.translateToString(SetOperators.inverse(72)));
  System.out.println(Relation.translateToString(CompositionTable.LookUpTable(2, 72)));
  System.out.println(((1<<8)+1));
  int path = (32<<20) + (15<<10) + 12;
  System.out.println(Integer.toBinaryString(((1<<31)|(1023))));
  System.out.println(path & ((1<<31)|(1023))); // 2
  System.out.println((path>>10) & ((1<<31)|(1023))); // 1
  System.out.println((path>>20) & ((1<<31)|(1023))); // 0
  System.out.println(255|2); // 0
  System.out.println(Integer.toBinaryString((12<<8|1)));
  int key = 12<<8|1;
  System.out.println((key>>8)+"  "+Integer.toBinaryString(((key<<24)>>24)));
  int j = key<<24>>24;
  System.out.println(j);
}
}
