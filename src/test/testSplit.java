package test;

import java.util.ArrayList;

import essential.Relation;
import essential.SetOperators;

public class testSplit {
public static void main(String args[])
{
	
int i = Relation.TPPi.id + Relation.NTPP.id + Relation.EQ.id;
System.out.println(SetOperators.splitToMinmumSubsets(i, "H8").size());
for (Integer truth:SetOperators.splitToMinmumSubsets(i, "H8"))
{

	 System.out.println(Relation.translateToString(truth));



}

ArrayList<Integer> list = new ArrayList<Integer>();
list.add(0,0);
list.add(0,1);
System.out.println(list.get(0));


}
}
