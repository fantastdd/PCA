package essential;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import deprecated.ReRelation;


public class MTS implements Serializable{
	
public static LinkedHashSet<LinkedHashSet<ReRelation>> MTS;
private static LinkedHashSet<LinkedHashSet<ReRelation>> MTS_H8;
private static LinkedHashSet<LinkedHashSet<ReRelation>> MTS_C8;
private static LinkedHashSet<LinkedHashSet<ReRelation>> MTS_Q8;


public static  LinkedHashSet<Integer> BIMTS;
public static LinkedHashSet<Integer> BIMTS_H8;
private static LinkedHashSet<Integer> BIMTS_C8;
private static LinkedHashSet<Integer> BIMTS_Q8;

public static LinkedHashSet<LinkedHashSet<ReRelation>> getMTS(String type) {
	
	if(type.equals("H8"))
	return MTS_H8;
if(type.equals("C8"))
	return MTS_C8;
if(type.equals("Q8"))
	return MTS_Q8;
return MTS_H8;
}
public static LinkedHashSet<Integer> getBIMTS(String type) {
	
	if(type.equals("H8"))
	return BIMTS_H8;
if(type.equals("C8"))
	return BIMTS_C8;
if(type.equals("Q8"))
	return BIMTS_Q8;
return BIMTS_H8;
}

public static HashMap<Integer,LinkedList<LinkedHashSet<ReRelation>>> MTSMAP;
private static HashMap<Integer,LinkedList<LinkedHashSet<ReRelation>>> MTSMAP_H8;
private static HashMap<Integer,LinkedList<LinkedHashSet<ReRelation>>> MTSMAP_C8;
private static HashMap<Integer,LinkedList<LinkedHashSet<ReRelation>>> MTSMAP_Q8;
public static HashMap<Integer,LinkedList<Integer>> BIMTSMAP;

private static HashMap<Integer,LinkedList<Integer>> BIMTSMAP_H8;
private static HashMap<Integer,LinkedList<Integer>> BIMTSMAP_C8;
private static HashMap<Integer,LinkedList<Integer>> BIMTSMAP_Q8;

public static HashMap<Integer,LinkedList<LinkedHashSet<ReRelation>>> getMTSMAP(String type) {
if(type.equals("H8"))
	return MTSMAP_H8;
if(type.equals("C8"))
	return MTSMAP_C8;
if(type.equals("Q8"))
	return MTSMAP_Q8;
return MTSMAP_H8;
}

public static HashMap<Integer,LinkedList<Integer>> getBIMTSMAP(String type) {
if(type.equals("H8"))
	return BIMTSMAP_H8;
if(type.equals("C8"))
	return BIMTSMAP_C8;
if(type.equals("Q8"))
	return BIMTSMAP_Q8;
return BIMTSMAP_H8;
}

static 
{
  try {
	loadTable();
} catch (IOException e) {
	BuildTable();
	e.printStackTrace();
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	BuildTable();
	e.printStackTrace();
}
  



   }
   
MTS(){}
private static void BuildTable()
{ 
	  MTS = SetOperators.buildPowerSet();	
	  MTS_H8 = SetOperators.buildPowerSet();	
	  MTS_C8 = SetOperators.buildPowerSet();	
	  MTS_Q8 = SetOperators.buildPowerSet();	
	  //--- just copy the values from MTS, SO DONOT NEED Powerset
	  BIMTS = new LinkedHashSet<Integer>();
	  BIMTS_H8 = new LinkedHashSet<Integer>();
	  BIMTS_C8 = new LinkedHashSet<Integer>();
	  BIMTS_Q8 = new LinkedHashSet<Integer>();
	  
//	  BIMTS = SetOperators.buildPowerSetBinary();	
//	  BIMTS_H8 = SetOperators.buildPowerSetBinary();	
//	  BIMTS_C8 = SetOperators.buildPowerSetBinary();	
//	  BIMTS_Q8 = SetOperators.buildPowerSetBinary();	
	  
LinkedHashSetFacotry.get(0).add(ReRelation.TPP);
LinkedHashSetFacotry.get(0).add(ReRelation.TPPi);

LinkedHashSetFacotry.get(1).add(ReRelation.TPP);
LinkedHashSetFacotry.get(1).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(1).add(ReRelation.EC);

LinkedHashSetFacotry.get(2).add(ReRelation.TPP);
LinkedHashSetFacotry.get(2).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(2).add(ReRelation.DC);

LinkedHashSetFacotry.get(3).add(ReRelation.TPP);
LinkedHashSetFacotry.get(3).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(3).add(ReRelation.EC);
LinkedHashSetFacotry.get(3).add(ReRelation.DC);

LinkedHashSetFacotry.get(4).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(4).add(ReRelation.TPPi);

LinkedHashSetFacotry.get(5).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(5).add(ReRelation.TPPi);  
LinkedHashSetFacotry.get(5).add(ReRelation.DC);  
  
LinkedHashSetFacotry.get(6).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(6).add(ReRelation.TPPi);  
LinkedHashSetFacotry.get(6).add(ReRelation.EC);  
  
LinkedHashSetFacotry.get(7).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(7).add(ReRelation.TPPi);  
LinkedHashSetFacotry.get(7).add(ReRelation.EC);    
LinkedHashSetFacotry.get(7).add(ReRelation.DC);   
  
LinkedHashSetFacotry.get(8).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(8).add(ReRelation.TPPi);  
LinkedHashSetFacotry.get(8).add(ReRelation.TPP);  


LinkedHashSetFacotry.get(9).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(9).add(ReRelation.TPPi);  
LinkedHashSetFacotry.get(9).add(ReRelation.TPP);  
LinkedHashSetFacotry.get(9).add(ReRelation.DC);  

LinkedHashSetFacotry.get(10).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(10).add(ReRelation.TPPi);  
LinkedHashSetFacotry.get(10).add(ReRelation.TPP);  
LinkedHashSetFacotry.get(10).add(ReRelation.EC);  

LinkedHashSetFacotry.get(11).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(11).add(ReRelation.TPPi);  
LinkedHashSetFacotry.get(11).add(ReRelation.TPP);  
LinkedHashSetFacotry.get(11).add(ReRelation.DC);  
LinkedHashSetFacotry.get(11).add(ReRelation.EC);  

LinkedHashSetFacotry.get(12).add(ReRelation.TPP);
LinkedHashSetFacotry.get(12).add(ReRelation.NTPPi);

LinkedHashSetFacotry.get(13).add(ReRelation.TPP);
LinkedHashSetFacotry.get(13).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(13).add(ReRelation.DC);  

LinkedHashSetFacotry.get(14).add(ReRelation.TPP);
LinkedHashSetFacotry.get(14).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(14).add(ReRelation.EC);  

LinkedHashSetFacotry.get(15).add(ReRelation.TPP);
LinkedHashSetFacotry.get(15).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(15).add(ReRelation.DC);  
LinkedHashSetFacotry.get(15).add(ReRelation.EC); 

LinkedHashSetFacotry.get(16).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(16).add(ReRelation.NTPPi);


LinkedHashSetFacotry.get(17).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(17).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(17).add(ReRelation.DC);  

LinkedHashSetFacotry.get(18).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(18).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(18).add(ReRelation.EC); 

LinkedHashSetFacotry.get(19).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(19).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(19).add(ReRelation.EC); 
LinkedHashSetFacotry.get(19).add(ReRelation.DC);  


LinkedHashSetFacotry.get(20).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(20).add(ReRelation.NTPPi);  
LinkedHashSetFacotry.get(20).add(ReRelation.TPP);  


LinkedHashSetFacotry.get(21).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(21).add(ReRelation.NTPPi);  
LinkedHashSetFacotry.get(21).add(ReRelation.TPP);  
LinkedHashSetFacotry.get(21).add(ReRelation.DC);  

LinkedHashSetFacotry.get(22).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(22).add(ReRelation.NTPPi);  
LinkedHashSetFacotry.get(22).add(ReRelation.TPP);  
LinkedHashSetFacotry.get(22).add(ReRelation.EC);  

LinkedHashSetFacotry.get(23).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(23).add(ReRelation.NTPPi);  
LinkedHashSetFacotry.get(23).add(ReRelation.TPP);  
LinkedHashSetFacotry.get(23).add(ReRelation.DC);  
LinkedHashSetFacotry.get(23).add(ReRelation.EC);  


LinkedHashSetFacotry.get(24).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(24).add(ReRelation.NTPPi);  
LinkedHashSetFacotry.get(24).add(ReRelation.TPP);  


LinkedHashSetFacotry.get(25).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(25).add(ReRelation.NTPPi);  
LinkedHashSetFacotry.get(25).add(ReRelation.TPP);  
LinkedHashSetFacotry.get(25).add(ReRelation.DC);  

LinkedHashSetFacotry.get(26).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(26).add(ReRelation.NTPPi);  
LinkedHashSetFacotry.get(26).add(ReRelation.TPP);  
LinkedHashSetFacotry.get(26).add(ReRelation.EC);  

LinkedHashSetFacotry.get(27).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(27).add(ReRelation.NTPPi);  
LinkedHashSetFacotry.get(27).add(ReRelation.TPP);  
LinkedHashSetFacotry.get(27).add(ReRelation.DC);  
LinkedHashSetFacotry.get(27).add(ReRelation.EC);  
  
LinkedHashSetFacotry.get(28).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(28).add(ReRelation.NTPPi);  
LinkedHashSetFacotry.get(28).add(ReRelation.NTPP);  


LinkedHashSetFacotry.get(29).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(29).add(ReRelation.NTPPi);  
LinkedHashSetFacotry.get(29).add(ReRelation.NTPP);  
LinkedHashSetFacotry.get(29).add(ReRelation.DC);  

LinkedHashSetFacotry.get(30).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(30).add(ReRelation.NTPPi);  
LinkedHashSetFacotry.get(30).add(ReRelation.NTPP);  
LinkedHashSetFacotry.get(30).add(ReRelation.EC);  

LinkedHashSetFacotry.get(31).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(31).add(ReRelation.NTPPi);  
LinkedHashSetFacotry.get(31).add(ReRelation.NTPP);  
LinkedHashSetFacotry.get(31).add(ReRelation.DC);  
LinkedHashSetFacotry.get(31).add(ReRelation.EC);  


LinkedHashSetFacotry.get(32).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(32).add(ReRelation.NTPPi);  
LinkedHashSetFacotry.get(32).add(ReRelation.NTPP);  
LinkedHashSetFacotry.get(32).add(ReRelation.TPP);

LinkedHashSetFacotry.get(33).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(33).add(ReRelation.NTPPi);  
LinkedHashSetFacotry.get(33).add(ReRelation.NTPP);  
LinkedHashSetFacotry.get(33).add(ReRelation.DC); 
LinkedHashSetFacotry.get(33).add(ReRelation.TPP);

LinkedHashSetFacotry.get(34).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(34).add(ReRelation.TPP);
LinkedHashSetFacotry.get(34).add(ReRelation.NTPPi);  
LinkedHashSetFacotry.get(34).add(ReRelation.NTPP);  
LinkedHashSetFacotry.get(34).add(ReRelation.EC); 


LinkedHashSetFacotry.get(35).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(35).add(ReRelation.TPP);
LinkedHashSetFacotry.get(35).add(ReRelation.NTPPi);  
LinkedHashSetFacotry.get(35).add(ReRelation.NTPP);  
LinkedHashSetFacotry.get(35).add(ReRelation.DC);  
LinkedHashSetFacotry.get(35).add(ReRelation.EC);  
    
LinkedHashSetFacotry.get(36).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(36).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(36).add(ReRelation.EC); 

LinkedHashSetFacotry.get(37).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(37).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(37).add(ReRelation.EC); 
LinkedHashSetFacotry.get(37).add(ReRelation.DC); 


LinkedHashSetFacotry.get(38).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(38).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(38).add(ReRelation.TPP);  

LinkedHashSetFacotry.get(39).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(39).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(39).add(ReRelation.TPP); 
LinkedHashSetFacotry.get(39).add(ReRelation.DC); 

LinkedHashSetFacotry.get(40).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(40).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(40).add(ReRelation.TPP); 
LinkedHashSetFacotry.get(40).add(ReRelation.EC); 

LinkedHashSetFacotry.get(41).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(41).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(41).add(ReRelation.TPP); 
LinkedHashSetFacotry.get(41).add(ReRelation.DC); 
LinkedHashSetFacotry.get(41).add(ReRelation.EC); 


LinkedHashSetFacotry.get(42).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(42).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(42).add(ReRelation.NTPP);  

LinkedHashSetFacotry.get(43).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(43).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(43).add(ReRelation.NTPP); 
LinkedHashSetFacotry.get(43).add(ReRelation.DC); 

LinkedHashSetFacotry.get(44).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(44).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(44).add(ReRelation.NTPP); 
LinkedHashSetFacotry.get(44).add(ReRelation.EC); 

LinkedHashSetFacotry.get(45).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(45).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(45).add(ReRelation.NTPP); 
LinkedHashSetFacotry.get(45).add(ReRelation.DC); 
LinkedHashSetFacotry.get(45).add(ReRelation.EC); 


LinkedHashSetFacotry.get(46).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(46).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(46).add(ReRelation.TPP);  
LinkedHashSetFacotry.get(46).add(ReRelation.NTPP);  

LinkedHashSetFacotry.get(47).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(47).add(ReRelation.NTPP);  
LinkedHashSetFacotry.get(47).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(47).add(ReRelation.TPP); 
LinkedHashSetFacotry.get(47).add(ReRelation.DC); 

LinkedHashSetFacotry.get(48).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(48).add(ReRelation.NTPP); 
LinkedHashSetFacotry.get(48).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(48).add(ReRelation.TPP); 
LinkedHashSetFacotry.get(48).add(ReRelation.EC); 

LinkedHashSetFacotry.get(49).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(49).add(ReRelation.NTPP);  
LinkedHashSetFacotry.get(49).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(49).add(ReRelation.TPP); 
LinkedHashSetFacotry.get(49).add(ReRelation.DC); 
LinkedHashSetFacotry.get(49).add(ReRelation.EC); 

LinkedHashSetFacotry.get(50).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(50).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(50).add(ReRelation.EC); 

LinkedHashSetFacotry.get(51).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(51).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(51).add(ReRelation.EC); 
LinkedHashSetFacotry.get(51).add(ReRelation.DC);


LinkedHashSetFacotry.get(52).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(52).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(52).add(ReRelation.TPP);  

LinkedHashSetFacotry.get(53).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(53).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(53).add(ReRelation.TPP); 
LinkedHashSetFacotry.get(53).add(ReRelation.DC); 

LinkedHashSetFacotry.get(54).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(54).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(54).add(ReRelation.TPP); 
LinkedHashSetFacotry.get(54).add(ReRelation.EC); 

LinkedHashSetFacotry.get(55).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(55).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(55).add(ReRelation.TPP); 
LinkedHashSetFacotry.get(55).add(ReRelation.DC); 
LinkedHashSetFacotry.get(55).add(ReRelation.EC); 

LinkedHashSetFacotry.get(56).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(56).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(56).add(ReRelation.NTPP);  

LinkedHashSetFacotry.get(57).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(57).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(57).add(ReRelation.NTPP); 
LinkedHashSetFacotry.get(57).add(ReRelation.DC); 

LinkedHashSetFacotry.get(58).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(58).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(58).add(ReRelation.NTPP); 
LinkedHashSetFacotry.get(58).add(ReRelation.EC); 

LinkedHashSetFacotry.get(59).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(59).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(59).add(ReRelation.NTPP); 
LinkedHashSetFacotry.get(59).add(ReRelation.DC); 
LinkedHashSetFacotry.get(59).add(ReRelation.EC); 
  
  
LinkedHashSetFacotry.get(60).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(60).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(60).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(60).add(ReRelation.TPP);  

LinkedHashSetFacotry.get(61).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(61).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(61).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(61).add(ReRelation.TPP); 
LinkedHashSetFacotry.get(61).add(ReRelation.DC); 

LinkedHashSetFacotry.get(62).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(62).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(62).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(62).add(ReRelation.TPP); 
LinkedHashSetFacotry.get(62).add(ReRelation.EC); 

LinkedHashSetFacotry.get(63).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(63).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(63).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(63).add(ReRelation.TPP); 
LinkedHashSetFacotry.get(63).add(ReRelation.DC); 
LinkedHashSetFacotry.get(63).add(ReRelation.EC);   
  
LinkedHashSetFacotry.get(64).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(64).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(64).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(64).add(ReRelation.TPP);  

LinkedHashSetFacotry.get(65).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(65).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(65).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(65).add(ReRelation.TPP); 
LinkedHashSetFacotry.get(65).add(ReRelation.DC); 

LinkedHashSetFacotry.get(66).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(66).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(66).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(66).add(ReRelation.TPP); 
LinkedHashSetFacotry.get(66).add(ReRelation.EC); 

LinkedHashSetFacotry.get(67).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(67).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(67).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(67).add(ReRelation.TPP); 
LinkedHashSetFacotry.get(67).add(ReRelation.DC); 
LinkedHashSetFacotry.get(67).add(ReRelation.EC);   
  
  
LinkedHashSetFacotry.get(68).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(68).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(68).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(68).add(ReRelation.TPPi);  

LinkedHashSetFacotry.get(69).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(69).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(69).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(69).add(ReRelation.TPPi); 
LinkedHashSetFacotry.get(69).add(ReRelation.DC); 

LinkedHashSetFacotry.get(70).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(70).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(70).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(70).add(ReRelation.TPPi); 
LinkedHashSetFacotry.get(70).add(ReRelation.EC); 

LinkedHashSetFacotry.get(71).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(71).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(71).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(71).add(ReRelation.TPPi); 
LinkedHashSetFacotry.get(71).add(ReRelation.DC); 
LinkedHashSetFacotry.get(71).add(ReRelation.EC);   
    

LinkedHashSetFacotry.get(72).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(72).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(72).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(72).add(ReRelation.TPP);  
LinkedHashSetFacotry.get(72).add(ReRelation.TPPi);

LinkedHashSetFacotry.get(73).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(73).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(73).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(73).add(ReRelation.TPP); 
LinkedHashSetFacotry.get(73).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(73).add(ReRelation.DC); 

LinkedHashSetFacotry.get(74).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(74).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(74).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(74).add(ReRelation.TPP); 
LinkedHashSetFacotry.get(74).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(74).add(ReRelation.EC); 

LinkedHashSetFacotry.get(75).add(ReRelation.NTPPi);
LinkedHashSetFacotry.get(75).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(75).add(ReRelation.EQ);  
LinkedHashSetFacotry.get(75).add(ReRelation.TPP); 
LinkedHashSetFacotry.get(75).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(75).add(ReRelation.DC); 
LinkedHashSetFacotry.get(75).add(ReRelation.EC);   

MTS.removeAll(LinkedHashSetFacotry.getHashset());
MTS_H8.removeAll(LinkedHashSetFacotry.getHashset());
MTS_C8.removeAll(LinkedHashSetFacotry.getHashset());
MTS_Q8.removeAll(LinkedHashSetFacotry.getHashset());

LinkedHashSetFacotry.get(76).add(ReRelation.NTPP); 
LinkedHashSetFacotry.get(76).add(ReRelation.EQ); 
MTS_H8.remove(LinkedHashSetFacotry.get(76));

LinkedHashSetFacotry.get(77).add(ReRelation.NTPP); 
LinkedHashSetFacotry.get(77).add(ReRelation.EQ); 
LinkedHashSetFacotry.get(77).add(ReRelation.DC);
MTS_H8.remove(LinkedHashSetFacotry.get(77));


LinkedHashSetFacotry.get(78).add(ReRelation.NTPP); 
LinkedHashSetFacotry.get(78).add(ReRelation.EQ); 
LinkedHashSetFacotry.get(78).add(ReRelation.PO);
MTS_H8.remove(LinkedHashSetFacotry.get(78));

LinkedHashSetFacotry.get(79).add(ReRelation.NTPP); 
LinkedHashSetFacotry.get(79).add(ReRelation.EQ); 
LinkedHashSetFacotry.get(79).add(ReRelation.PO);
LinkedHashSetFacotry.get(79).add(ReRelation.DC);
MTS_H8.remove(LinkedHashSetFacotry.get(79));

LinkedHashSetFacotry.get(80).add(ReRelation.NTPP); 
LinkedHashSetFacotry.get(80).add(ReRelation.EQ); 
LinkedHashSetFacotry.get(80).add(ReRelation.PO);
LinkedHashSetFacotry.get(80).add(ReRelation.EC);
MTS_H8.remove(LinkedHashSetFacotry.get(80));

LinkedHashSetFacotry.get(81).add(ReRelation.NTPP); 
LinkedHashSetFacotry.get(81).add(ReRelation.EQ); 
LinkedHashSetFacotry.get(81).add(ReRelation.PO);
LinkedHashSetFacotry.get(81).add(ReRelation.EC);
LinkedHashSetFacotry.get(81).add(ReRelation.DC);
MTS_H8.remove(LinkedHashSetFacotry.get(81));

LinkedHashSetFacotry.get(82).add(ReRelation.NTPP); 
LinkedHashSetFacotry.get(82).add(ReRelation.TPPi); 
LinkedHashSetFacotry.get(82).add(ReRelation.PO);
LinkedHashSetFacotry.get(82).add(ReRelation.EQ);
MTS_H8.remove(LinkedHashSetFacotry.get(82));

LinkedHashSetFacotry.get(83).add(ReRelation.NTPP); 
LinkedHashSetFacotry.get(83).add(ReRelation.TPPi); 
LinkedHashSetFacotry.get(83).add(ReRelation.PO);
LinkedHashSetFacotry.get(83).add(ReRelation.EQ);
LinkedHashSetFacotry.get(83).add(ReRelation.EC);

MTS_H8.remove(LinkedHashSetFacotry.get(83));

LinkedHashSetFacotry.get(84).add(ReRelation.NTPP); 
LinkedHashSetFacotry.get(84).add(ReRelation.TPPi); 
LinkedHashSetFacotry.get(84).add(ReRelation.PO);
LinkedHashSetFacotry.get(84).add(ReRelation.EQ);
LinkedHashSetFacotry.get(84).add(ReRelation.DC);

MTS_H8.remove(LinkedHashSetFacotry.get(84));


LinkedHashSetFacotry.get(85).add(ReRelation.NTPP); 
LinkedHashSetFacotry.get(85).add(ReRelation.TPPi); 
LinkedHashSetFacotry.get(85).add(ReRelation.PO);
LinkedHashSetFacotry.get(85).add(ReRelation.EQ);
LinkedHashSetFacotry.get(85).add(ReRelation.EC);
LinkedHashSetFacotry.get(85).add(ReRelation.DC);
MTS_H8.remove(LinkedHashSetFacotry.get(85));

LinkedHashSetFacotry.get(86).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(86).add(ReRelation.EQ);
MTS_H8.remove(LinkedHashSetFacotry.get(86));

LinkedHashSetFacotry.get(87).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(87).add(ReRelation.EQ);
LinkedHashSetFacotry.get(87).add(ReRelation.DC);
MTS_H8.remove(LinkedHashSetFacotry.get(87));

LinkedHashSetFacotry.get(88).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(88).add(ReRelation.PO);
LinkedHashSetFacotry.get(88).add(ReRelation.EQ);
MTS_H8.remove(LinkedHashSetFacotry.get(88));

LinkedHashSetFacotry.get(89).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(89).add(ReRelation.PO);
LinkedHashSetFacotry.get(89).add(ReRelation.EQ);
LinkedHashSetFacotry.get(89).add(ReRelation.DC);
MTS_H8.remove(LinkedHashSetFacotry.get(89));


LinkedHashSetFacotry.get(90).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(90).add(ReRelation.PO);
LinkedHashSetFacotry.get(90).add(ReRelation.EQ);
LinkedHashSetFacotry.get(90).add(ReRelation.EC);
MTS_H8.remove(LinkedHashSetFacotry.get(90));

LinkedHashSetFacotry.get(91).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(91).add(ReRelation.PO);
LinkedHashSetFacotry.get(91).add(ReRelation.EQ);
LinkedHashSetFacotry.get(91).add(ReRelation.EC);
LinkedHashSetFacotry.get(91).add(ReRelation.DC);
MTS_H8.remove(LinkedHashSetFacotry.get(91));

LinkedHashSetFacotry.get(92).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(92).add(ReRelation.PO);
LinkedHashSetFacotry.get(92).add(ReRelation.TPP);
LinkedHashSetFacotry.get(92).add(ReRelation.EQ);
MTS_H8.remove(LinkedHashSetFacotry.get(92));

LinkedHashSetFacotry.get(93).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(93).add(ReRelation.PO);
LinkedHashSetFacotry.get(93).add(ReRelation.TPP);
LinkedHashSetFacotry.get(93).add(ReRelation.EQ);
LinkedHashSetFacotry.get(93).add(ReRelation.EC);
MTS_H8.remove(LinkedHashSetFacotry.get(93));



LinkedHashSetFacotry.get(94).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(94).add(ReRelation.PO);
LinkedHashSetFacotry.get(94).add(ReRelation.TPP);
LinkedHashSetFacotry.get(94).add(ReRelation.EQ);
LinkedHashSetFacotry.get(94).add(ReRelation.DC);
MTS_H8.remove(LinkedHashSetFacotry.get(94));

LinkedHashSetFacotry.get(95).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(95).add(ReRelation.PO);
LinkedHashSetFacotry.get(95).add(ReRelation.TPP);
LinkedHashSetFacotry.get(95).add(ReRelation.EQ);
LinkedHashSetFacotry.get(95).add(ReRelation.DC);
LinkedHashSetFacotry.get(95).add(ReRelation.EC);
MTS_H8.remove(LinkedHashSetFacotry.get(95));

LinkedHashSetFacotry.get(96).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(96).add(ReRelation.PO);
LinkedHashSetFacotry.get(96).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(96).add(ReRelation.EQ);
MTS_H8.remove(LinkedHashSetFacotry.get(96));

LinkedHashSetFacotry.get(97).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(97).add(ReRelation.PO);
LinkedHashSetFacotry.get(97).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(97).add(ReRelation.EQ);
LinkedHashSetFacotry.get(97).add(ReRelation.DC);
MTS_H8.remove(LinkedHashSetFacotry.get(97));



LinkedHashSetFacotry.get(98).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(98).add(ReRelation.PO);
LinkedHashSetFacotry.get(98).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(98).add(ReRelation.EQ);
LinkedHashSetFacotry.get(98).add(ReRelation.EC);
MTS_H8.remove(LinkedHashSetFacotry.get(98));

LinkedHashSetFacotry.get(99).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(99).add(ReRelation.PO);
LinkedHashSetFacotry.get(99).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(99).add(ReRelation.EQ);
LinkedHashSetFacotry.get(99).add(ReRelation.DC);
LinkedHashSetFacotry.get(99).add(ReRelation.EC);
MTS_H8.remove(LinkedHashSetFacotry.get(99));

LinkedHashSetFacotry.get(100).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(100).add(ReRelation.PO);
LinkedHashSetFacotry.get(100).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(100).add(ReRelation.TPP);
LinkedHashSetFacotry.get(100).add(ReRelation.EQ);
MTS_H8.remove(LinkedHashSetFacotry.get(100));

LinkedHashSetFacotry.get(101).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(101).add(ReRelation.PO);
LinkedHashSetFacotry.get(101).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(101).add(ReRelation.TPP);
LinkedHashSetFacotry.get(101).add(ReRelation.EQ);
LinkedHashSetFacotry.get(101).add(ReRelation.DC);
MTS_H8.remove(LinkedHashSetFacotry.get(101));



LinkedHashSetFacotry.get(102).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(102).add(ReRelation.PO);
LinkedHashSetFacotry.get(102).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(102).add(ReRelation.EQ);
LinkedHashSetFacotry.get(102).add(ReRelation.EC);
LinkedHashSetFacotry.get(102).add(ReRelation.TPP);
MTS_H8.remove(LinkedHashSetFacotry.get(102));

LinkedHashSetFacotry.get(103).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(103).add(ReRelation.PO);
LinkedHashSetFacotry.get(103).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(103).add(ReRelation.EQ);
LinkedHashSetFacotry.get(103).add(ReRelation.DC);
LinkedHashSetFacotry.get(103).add(ReRelation.EC);
LinkedHashSetFacotry.get(103).add(ReRelation.TPP);
MTS_H8.remove(LinkedHashSetFacotry.get(103));


LinkedHashSetFacotry.get(104).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(104).add(ReRelation.PO);
LinkedHashSetFacotry.get(104).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(104).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(104).add(ReRelation.EQ);
MTS_H8.remove(LinkedHashSetFacotry.get(104));

LinkedHashSetFacotry.get(105).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(105).add(ReRelation.PO);
LinkedHashSetFacotry.get(105).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(105).add(ReRelation.TPPi);
LinkedHashSetFacotry.get(105).add(ReRelation.EQ);
LinkedHashSetFacotry.get(105).add(ReRelation.DC);
MTS_H8.remove(LinkedHashSetFacotry.get(105));



LinkedHashSetFacotry.get(106).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(106).add(ReRelation.PO);
LinkedHashSetFacotry.get(106).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(106).add(ReRelation.EQ);
LinkedHashSetFacotry.get(106).add(ReRelation.EC);
LinkedHashSetFacotry.get(106).add(ReRelation.TPPi);
MTS_H8.remove(LinkedHashSetFacotry.get(106));

LinkedHashSetFacotry.get(107).add(ReRelation.NTPPi); 
LinkedHashSetFacotry.get(107).add(ReRelation.PO);
LinkedHashSetFacotry.get(107).add(ReRelation.NTPP);
LinkedHashSetFacotry.get(107).add(ReRelation.EQ);
LinkedHashSetFacotry.get(107).add(ReRelation.DC);
LinkedHashSetFacotry.get(107).add(ReRelation.EC);
LinkedHashSetFacotry.get(107).add(ReRelation.TPPi);
MTS_H8.remove(LinkedHashSetFacotry.get(107));

buildMTSMap();
buildMTSMap_H8();
buildBIMTS();
buildBIMTSMap_H8();
buildBIMTSMap();
writeToFile();

}
public static void buildBIMTS()
{
//	System.out.println("!!!!"+BIMTS.contains(72));
Iterator<LinkedHashSet<ReRelation>> iter = MTS.iterator();

while(iter.hasNext())
{
	
  Integer value = 0;
  Iterator<ReRelation> iter1 = iter.next().iterator();
  while(iter1.hasNext())
	  value += iter1.next().id;
  
 /* if(value.intValue() == 72)
	  System.out.println("@@@@"+value);*/
  BIMTS.add(value);
 }
//System.out.println("~~~~"+BIMTS.contains(72));


Iterator<LinkedHashSet<ReRelation>> iter2 = MTS_H8.iterator();

while(iter2.hasNext())
{
	
  Integer value = 0;
  Iterator<ReRelation> iter3 = iter2.next().iterator();
  while(iter3.hasNext())
	  value += iter3.next().id;
  
  BIMTS_H8.add(value);
 }




}

public static void buildBIMTSMap_H8()
{
	

	 BIMTSMAP_H8 = new HashMap<Integer,LinkedList<Integer>> ();
	for(Integer i:MTSMAP_H8.keySet())
	{
		 LinkedList<Integer> list = new LinkedList<Integer>();
	  for (LinkedHashSet<ReRelation> rels:MTSMAP_H8.get(i))
	  {
		
		 
		  Iterator<ReRelation> iter = rels.iterator();
		  int value = 0;
		  while(iter.hasNext())
		  {
			  
			  value+= iter.next().id;
			  
			  
			  
		}
		  list.add(value);
	}
		
    BIMTSMAP_H8.put(i,list);		
}
}

	public static void buildBIMTSMap()
	{
		

		 BIMTSMAP = new HashMap<Integer,LinkedList<Integer>>();
		for(Integer i:MTSMAP.keySet())
		{
			 LinkedList<Integer> list = new LinkedList<Integer>();
		  for (LinkedHashSet<ReRelation> rels:MTSMAP.get(i))
		  {
			
			 
			  Iterator<ReRelation> iter = rels.iterator();
			  int value = 0;
			  while(iter.hasNext())
			  {
				  
				  value+= iter.next().id;
				  
				  
				  
			}
			  list.add(value);
		}
			
		  BIMTSMAP.put(i, list);		
	}




}
public static void main(String args[])
{
	  MTS mts = new MTS();
	  System.out.println(mts.MTS.size());
	  System.out.println(MTS_H8.size());
	  System.out.println(MTSMAP_H8.size());
	  mts.writeToFile();
	  
}
private static void buildMTSMap()
{
	 MTSMAP =  new  HashMap<Integer,LinkedList<LinkedHashSet<ReRelation>>>();
	 Iterator<LinkedHashSet<ReRelation>> iter = MTS.iterator();
	 while (iter.hasNext())
	 {
		 LinkedHashSet<ReRelation> current =  iter.next();
		 int key = current.size();
		 if(MTSMAP.containsKey(key))
			 MTSMAP.get(key).add(current);
		 else 
		 {
			 LinkedList<LinkedHashSet<ReRelation>> newList = new LinkedList<LinkedHashSet<ReRelation>>();
			 MTSMAP.put(key, newList);
			 MTSMAP.get(key).add(current);
			 
		 }
			
	 }



}
private static void buildMTSMap_H8()
{
	 MTSMAP_H8 =  new  HashMap<Integer,LinkedList<LinkedHashSet<ReRelation>>>();
	 Iterator<LinkedHashSet<ReRelation>> iter = MTS_H8.iterator();
	 while (iter.hasNext())
	 {
		 LinkedHashSet<ReRelation> current =  iter.next();
		 int key = current.size();
		 if(MTSMAP_H8.containsKey(key))
			 MTSMAP_H8.get(key).add(current);
		 else 
		 {
			 LinkedList<LinkedHashSet<ReRelation>> newList = new LinkedList<LinkedHashSet<ReRelation>>();
			 MTSMAP_H8.put(key, newList);
			 MTSMAP_H8.get(key).add(current);
			 
		 }
			
	 }



}
private static void writeToFile()
{
	
	 try
	    {
		 FileOutputStream     fos = new FileOutputStream("MTS.obj");
		 ObjectOutputStream     out = new ObjectOutputStream(fos);
	     out.writeObject(MTS);
	     out.close();
     }
	    catch(IOException ex)
	     {
	        ex.printStackTrace();
	 }
	 try
	    {
		 FileOutputStream     fos = new FileOutputStream("MTSMAP.obj");
		 ObjectOutputStream     out = new ObjectOutputStream(fos);
	     out.writeObject(MTSMAP);
	     out.close();
  }
	    catch(IOException ex)
	     {
	        ex.printStackTrace();
	 }
	 try
	    {
		 FileOutputStream     fos = new FileOutputStream("MTS_H8.obj");
		 ObjectOutputStream     out = new ObjectOutputStream(fos);
	     out.writeObject(MTS_H8);
	     out.close();
}
	    catch(IOException ex)
	     {
	        ex.printStackTrace();
	 }
	 try
	    {
		 FileOutputStream     fos = new FileOutputStream("MTSMAP_H8.obj");
		 ObjectOutputStream     out = new ObjectOutputStream(fos);
	     out.writeObject(MTSMAP_H8);
	     out.close();
}
	    catch(IOException ex)
	     {
	        ex.printStackTrace();
	 }

	 
	 try
	    {
		 FileOutputStream     fos = new FileOutputStream("BIMTS.obj");
		 ObjectOutputStream     out = new ObjectOutputStream(fos);
	     out.writeObject(BIMTS);
	     out.close();
  }
	    catch(IOException ex)
	     {
	        ex.printStackTrace();
	 }
	 try
	    {
		 FileOutputStream     fos = new FileOutputStream("BIMTSMAP.obj");
		 ObjectOutputStream     out = new ObjectOutputStream(fos);
	     out.writeObject(BIMTSMAP);
	     out.close();
}
	    catch(IOException ex)
	     {
	        ex.printStackTrace();
	 }
	 try
	    {
		 FileOutputStream     fos = new FileOutputStream("BIMTS_H8.obj");
		 ObjectOutputStream     out = new ObjectOutputStream(fos);
	     out.writeObject(BIMTS_H8);
	     out.close();
}
	    catch(IOException ex)
	     {
	        ex.printStackTrace();
	 }
	 try
	    {
		 FileOutputStream     fos = new FileOutputStream("BIMTSMAP_H8.obj");
		 ObjectOutputStream     out = new ObjectOutputStream(fos);
	     out.writeObject(BIMTSMAP_H8);
	     out.close();
}
	    catch(IOException ex)
	     {
	        ex.printStackTrace();
	 }
	 
	 

}
private static void loadTable() throws IOException, ClassNotFoundException
{
	
	 FileInputStream fos;

		fos = new FileInputStream("MTS.obj");
		 ObjectInputStream     out = new ObjectInputStream(fos);
		 MTS = (LinkedHashSet<LinkedHashSet<ReRelation>>) out.readObject();

	
	 FileInputStream fos1;

		 fos1 = new FileInputStream("MTSMAP.obj");
		 ObjectInputStream     out1 = new ObjectInputStream(fos1);
		 MTSMAP =  (HashMap<Integer, LinkedList<LinkedHashSet<ReRelation>>>) out1.readObject();
		 
		 FileInputStream fos2;

			fos2 = new FileInputStream("MTS_H8.obj");
			 ObjectInputStream     out2 = new ObjectInputStream(fos2);
			 MTS_H8 = (LinkedHashSet<LinkedHashSet<ReRelation>>) out2.readObject();

		
		 FileInputStream fos3;

			 fos3 = new FileInputStream("MTSMAP_H8.obj");
			 ObjectInputStream     out3 = new ObjectInputStream(fos3);
			 MTSMAP_H8 =  (HashMap<Integer, LinkedList<LinkedHashSet<ReRelation>>>) out3.readObject();
		
			 FileInputStream fos4;

				fos4 = new FileInputStream("BIMTS.obj");
				 ObjectInputStream     out4 = new ObjectInputStream(fos4);
				 BIMTS =  (LinkedHashSet<Integer>) out4.readObject();

			
			 FileInputStream fos5;

				 fos5 = new FileInputStream("BIMTSMAP.obj");
				 ObjectInputStream     out5 = new ObjectInputStream(fos5);
				 BIMTSMAP =   (HashMap<Integer, LinkedList<Integer>>) out5.readObject();
				 
				 FileInputStream fos6;

					fos6 = new FileInputStream("BIMTS_H8.obj");
					 ObjectInputStream     out6 = new ObjectInputStream(fos6);
					 BIMTS_H8 =  (LinkedHashSet<Integer>) out6.readObject();

				
				 FileInputStream fos7;

					 fos7 = new FileInputStream("BIMTSMAP_H8.obj");
					 ObjectInputStream     out7 = new ObjectInputStream(fos7);
					 BIMTSMAP_H8 =   (HashMap<Integer, LinkedList<Integer>>) out7.readObject();
   


}

public static boolean inMTS(int rel, String type)
{
    return getBIMTS(type).contains(rel);
}
/**
 * Only add one relation. 
 * Maybe should consider the situation where NTPP(2) + NTPPI(2) < PO (5)
 * @throws Exception 
 * 
 * 
 * **/
public static int extendToMTS(int rel,String type) throws Exception
{
   if(getBIMTS(type).contains(rel))
   { 
	   return rel;}
   
   for(Relation newRel:Relation.values())
   {
	   int crel = rel;
	   crel |= newRel.id;
	   if (inMTS(crel,type))
	   {
		 
		   return crel; 
	   }
	   
	   
   }
   // some relation like NTPPI,NTPP,EQ need two additional relations --- refine later...
   for(Relation newRel:Relation.values())
   {
	   int crel = rel|newRel.id;
	   
	   for(Relation newRel1:Relation.values()){
	   
	   int crel1 = crel | newRel1.id;
	   if (inMTS(crel1,type))
	   {
		 
		   return crel1; 
	   }
	   }
	   
   }

   // some relation like NTPPI,NTPP,EQ need three additional relations --- refine later...
   for(Relation newRel:Relation.values())
   {
	   int crel = rel|newRel.id;
	   
	   for(Relation newRel1:Relation.values()){
	   
	   int crel1 = crel | newRel1.id;
	   
	   for(Relation newRel2:Relation.values()){
	   int crel2 = crel1 | newRel2.id;
		   if (inMTS(crel2,type))
	   {
		 
		   return crel2; 
	   }
	   }
	   }
	   
   }
   
   
   
   
   
   System.out.println("Failure In Extension ");
   throw new Exception();
  


}
/**
 * Find out proper extension set
 * @param num: the number of relations to be added
 * **/
/*public static int extendToMTS(int rel,int num,String type)
{
    int crel = rel;
  for (int index = 0;index < num;index ++)
  {
	  
	  
  }
  while(!inMTS(crel,type))
   {
	 
		for(int j = 0;j<8;j++)
		{	
			crel |= (j<<1);
		}
	   
	   
   }
	
	
	return crel;
}*/

}





















class LinkedHashSetFacotry
{
static ArrayList<LinkedHashSet> hashset = new ArrayList<LinkedHashSet>();
static LinkedHashSet<ReRelation> get(int num)
{
	if (num > hashset.size()-1)
	{
	LinkedHashSet newHashSet = new LinkedHashSet();
	hashset.add(newHashSet);
	}
  	return hashset.get(num);
  	

}
public static ArrayList<LinkedHashSet> getHashset() {
	return hashset;
}
public static void setHashset(ArrayList<LinkedHashSet> hashset) {
	LinkedHashSetFacotry.hashset = hashset;
}	
}