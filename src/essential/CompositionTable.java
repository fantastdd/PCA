package essential;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import deprecated.ReRelation;
public class CompositionTable {
private static HashMap<Key<ReRelation>,ReRelation[]> comptable;
private static HashMap<Key<ReRelation>,LinkedHashSet<ReRelation>> totaltable;
private static HashMap<Integer,Integer> biTotalTable;
private static HashMap<Integer,Integer> biCompTable;
static {
	
	  try {
			loadTable();
		} catch (IOException e) {
			buildTable();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			buildTable();
			e.printStackTrace();
		}
		  
	
	
    }
private static void loadTable() throws IOException, ClassNotFoundException
{
	
	 FileInputStream fos;

		fos = new FileInputStream("biTotalTable.obj");
	
		 ObjectInputStream     out = new ObjectInputStream(fos);
		
		 biTotalTable = (HashMap<Integer, Integer>) out.readObject();
		
	
	 FileInputStream fos1;

		 fos1 = new FileInputStream("biCompTable.obj");
		 ObjectInputStream     out1 = new ObjectInputStream(fos1);
		 biCompTable =  (HashMap<Integer, Integer>) out1.readObject();
		 
		
   


}
public static void buildEntireCompositionTable()
{
	
	totaltable = new  HashMap<Key<ReRelation>,LinkedHashSet<ReRelation>>();
	LinkedHashSet<LinkedHashSet<ReRelation>> powerSet = SetOperators.buildPowerSet();
	
	LinkedHashSet<ReRelation> spRelation = new LinkedHashSet<ReRelation>();
	LinkedHashSet<ReRelation> epRelation = new LinkedHashSet<ReRelation>();
	
    Iterator<LinkedHashSet<ReRelation>> iter = powerSet.iterator();
	while (iter.hasNext())
	{
		
		 spRelation = iter.next();
		 Iterator<LinkedHashSet<ReRelation>> desIterator = powerSet.iterator();
		 while(desIterator.hasNext())
		 {
			 epRelation = desIterator.next();
			 Key<ReRelation> key = new Key<ReRelation>();
			 key.setDestinationCollection(epRelation);
			 key.setSourceCollection(spRelation);
			 totaltable.put(key, compostionResult(spRelation,epRelation));
		 }
	}
	
	
}private static void writeToFile()
{
	
	 try
	    {
		 FileOutputStream     fos = new FileOutputStream("biTotalTable.obj");
		 ObjectOutputStream     out = new ObjectOutputStream(fos);
	     out.writeObject(biTotalTable);
	     out.close();
    }
	    catch(IOException ex)
	     {
	        ex.printStackTrace();
	 }
	 try
	    {
		 FileOutputStream     fos = new FileOutputStream("biCompTable.obj");
		 ObjectOutputStream     out = new ObjectOutputStream(fos);
	     out.writeObject(biCompTable);
	     out.close();
 }
	    catch(IOException ex)
	     {
	        ex.printStackTrace();
	 }
	
	 
	 

}
private static LinkedHashSet<ReRelation> compostionResult(Collection<ReRelation> spRel,Collection<ReRelation> epRel)
{
   LinkedHashSet<ReRelation> results = new LinkedHashSet<ReRelation>();
   for (ReRelation spr:spRel)
   {
     for (ReRelation epr:epRel)
     {
    	 Key<ReRelation> key = new Key<ReRelation>(spr,epr);
    	/*if (!comptable.containsKey(key)) 
    	 {System.out.println(spr);
    	  System.out.println(epr);}*/
    	 ReRelation[] relations = comptable.get(key);
    	 for (int i = 0;i<relations.length;i++)
    		 results.add(relations[i]);
    }
   }
   return results;




}
public static void buildBinaryTotalTable()
{
  	
 biTotalTable = new HashMap<Integer,Integer>();
for(Key<ReRelation> key:totaltable.keySet())
{
	
	Iterator<ReRelation> iter = key.getSourceCollection().iterator();
	Integer newKeyL = 0;
	Integer newKeyR = 0;
	while(iter.hasNext())
	{
		newKeyL += iter.next().id;
	}

	Iterator<ReRelation> iter1 = key.getDestinationCollection().iterator();
	
	while(iter1.hasNext())
	{
		newKeyR  += iter1.next().id;
	}
	Integer value = 0;
    
    Iterator<ReRelation> iter3 = totaltable.get(key).iterator();
    while (iter3.hasNext())
    	value += iter3.next().id;
    
    int newKey = ( newKeyL << 8 ) + newKeyR;
   if(biTotalTable.containsKey(newKey))
	   System.out.println(newKey);
    biTotalTable.put(newKey, value);
    

}

}

public static HashMap<Integer, Integer> getBiTotalTable() {
	return biTotalTable;
}
public static void setBiTotalTable(HashMap<Integer, Integer> biTotalTable) {
	CompositionTable.biTotalTable = biTotalTable;
}
public static HashMap<Integer, Integer> getBiCompTable() {
	return biCompTable;
}
public static void setBiCompTable(HashMap<Integer, Integer> biCompTable) {
	CompositionTable.biCompTable = biCompTable;
}
public static void buildBinaryCompTable()
{
  	
biCompTable = new HashMap<Integer,Integer>();
for(Key<ReRelation> key:comptable.keySet())
{
	

	
    Integer newKeyL = 0;
    Integer newKeyR = 0;
    Integer newKey = 0;
	  	
    newKeyL += key.getSource().id;
	newKeyR +=  key.getDestination().id;
     newKey = (newKeyL<<8) + newKeyR;
	Integer value = 0;
    
for(int i = 0;i<comptable.get(key).length;i++)
    	value += comptable.get(key)[i].id;

    biCompTable.put(newKey, value);

}




}

public static void main(String args[])
{
	CompositionTable ct = new CompositionTable();
//	System.out.println(ct.totaltable.size());
	
/*	LinkedHashSet<Relation> spRelation = new LinkedHashSet<Relation>();
	LinkedHashSet<Relation> epRelation = new LinkedHashSet<Relation>();
	spRelation.add(Relation.EQ);
	spRelation.add(Relation.TPP);
	spRelation.add(Relation.NTPPi);
	epRelation.add(Relation.DC);
	epRelation.add(Relation.EC);
	System.out.println(ct.compostionResult(spRelation, epRelation));*/
	
	System.out.println(Relation.translateToString(CompositionTable.LookUpTable(64, 10)));
	
/*	Key<Relation> key = new Key<Relation>(spRelation,epRelation);
	System.out.println();
	
	Key<Relation> key2 = new Key<Relation>(Relation.EC,Relation.TPP);
	System.out.println(key2.hashCode());
	for (int i = 0;i<ct.comptable.get(key2).length;i++)
	System.out.println(ct.comptable.get(key2)[i]);
	
	
	
	Key<Relation> key3 = new Key<Relation>(spRelation,epRelation);
	System.out.println(ct.totaltable.containsKey(key3));
	Key<Relation> key4 = new Key<Relation>(epRelation,spRelation);
	System.out.println(ct.totaltable.containsKey(key4));
	
	System.out.println(key3.equals(key4));
	
	LinkedHashSet<Relation> spRelation1 = new LinkedHashSet<Relation>();
	LinkedHashSet<Relation> epRelation1 = new LinkedHashSet<Relation>();
	spRelation1.add(Relation.TPP);
	spRelation1.add(Relation.EQ);
	spRelation1.add(Relation.EQ);
	System.out.println(spRelation1.size());
	epRelation1.add(Relation.EC);
	epRelation1.add(Relation.EQ);
	Key<Relation> key5 = new Key<Relation>(spRelation,epRelation);
	System.out.println(ct.totaltable.containsKey(key5));
	
	//System.out.println(SetOperators.union(spRelation, epRelation));
	
	
	spRelation1.add(Relation.DC);
	spRelation1 = (LinkedHashSet<Relation>) SetOperators.inverse(spRelation1);
	System.out.println(spRelation1);
	
	LinkedHashSet<Relation> spRelation2 = new LinkedHashSet<Relation>();
	LinkedHashSet<Relation> epRelation2 = new LinkedHashSet<Relation>();
	spRelation2.add(Relation.TPP);
	for (int i = 0; i<Relation.values().length;i++)
		epRelation2.add(Relation.values()[i]);
	System.out.println(CompositionTable.lookUpTable(spRelation2, epRelation2));
	spRelation1.clear();
	epRelation1.clear();
	spRelation1.add(Relation.TPP);
	epRelation1.add(Relation.NTPPi);
	System.out.println(ct.compostionResult(spRelation1, epRelation1));
	Key<Relation> key6 = new Key<Relation>(spRelation2,epRelation2);
	
	for (Key key:ct.totaltable.keySet())
	{
		System.out.println("Key" + ct);
		LinkedHashSet<Relation> relations = totaltable.get(key);
		Iterator iter = relations.iterator();
		while (iter.hasNext())
			System.out.print(iter.next());
	}

*/

}
public static void buildTable()
{
System.out.println("Start");
	
	comptable = new HashMap<Key<ReRelation>,ReRelation[]>();
	// i = row index, j = column index
	Key<ReRelation> key11= new Key<ReRelation>(ReRelation.DC,ReRelation.DC);
	ReRelation[] result11 = {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPP,ReRelation.NTPP,ReRelation.TPPi,
			ReRelation.NTPPi,ReRelation.EQ};
    comptable.put(key11, result11);	
    
    Key<ReRelation> key12= new Key<ReRelation>(ReRelation.DC,ReRelation.EC);
	ReRelation[] result12 = {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPP,ReRelation.NTPP};
    comptable.put(key12, result12);	
    
    Key<ReRelation> key13= new Key<ReRelation>(ReRelation.DC,ReRelation.PO);
	ReRelation[] result13 =  {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPP,ReRelation.NTPP};
    comptable.put(key13, result13);	
    
    Key<ReRelation> key14= new Key<ReRelation>(ReRelation.DC,ReRelation.TPP);
	ReRelation[] result14 =  {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPP,ReRelation.NTPP};
    comptable.put(key14, result14);	
    
    Key<ReRelation> key15= new Key<ReRelation>(ReRelation.DC,ReRelation.NTPP);
	ReRelation[] result15 =  {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPP,ReRelation.NTPP};
    comptable.put(key15, result15);	

    Key<ReRelation> key16= new Key<ReRelation>(ReRelation.DC,ReRelation.TPPi);
	ReRelation[] result16 = {ReRelation.DC};
    comptable.put(key16, result16);	
    
    Key<ReRelation> key17= new Key<ReRelation>(ReRelation.DC,ReRelation.NTPPi);
	ReRelation[] result17 = {ReRelation.DC};
    comptable.put(key17, result17);	
    
    Key<ReRelation> key18= new Key<ReRelation>(ReRelation.DC,ReRelation.EQ);
	ReRelation[] result18 = {ReRelation.DC};
    comptable.put(key18, result18);	
    
	Key<ReRelation> key21= new Key<ReRelation>(ReRelation.EC,ReRelation.DC);
	ReRelation[] result21 = {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPPi,ReRelation.NTPPi};
    comptable.put(key21, result21);	
    
    Key<ReRelation> key22= new Key<ReRelation>(ReRelation.EC,ReRelation.EC);
    ReRelation[] result22 = {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPP,ReRelation.TPPi,ReRelation.EQ};
    comptable.put(key22, result22);	
    
    Key<ReRelation> key23= new Key<ReRelation>(ReRelation.EC,ReRelation.PO);
    ReRelation[] result23 =  {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPP,ReRelation.NTPP};
    comptable.put(key23, result23);	
    
    Key<ReRelation> key24= new Key<ReRelation>(ReRelation.EC,ReRelation.TPP);
    ReRelation[] result24 =  {ReRelation.EC,ReRelation.PO,ReRelation.TPP,ReRelation.NTPP};
    comptable.put(key24, result24);	
    
    Key<ReRelation> key25= new Key<ReRelation>(ReRelation.EC,ReRelation.NTPP);
    ReRelation[] result25 =  {ReRelation.PO,ReRelation.TPP,ReRelation.NTPP};
    comptable.put(key25, result25);	

    Key<ReRelation> key26= new Key<ReRelation>(ReRelation.EC,ReRelation.TPPi);
    ReRelation[] result26 =  {ReRelation.DC,ReRelation.EC};
    comptable.put(key26, result26);	
    
    Key<ReRelation> key27= new Key<ReRelation>(ReRelation.EC,ReRelation.NTPPi);
    ReRelation[] result27 =  {ReRelation.DC};
    comptable.put(key27, result27);	
    
    Key<ReRelation> key28= new Key<ReRelation>(ReRelation.EC,ReRelation.EQ);
    ReRelation[] result28 =  {ReRelation.EC};
    comptable.put(key28, result28);	
    
	Key<ReRelation> key31= new Key<ReRelation>(ReRelation.PO,ReRelation.DC);
	ReRelation[] result31 = {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPPi,ReRelation.NTPPi};
    comptable.put(key31, result31);	
    
    Key<ReRelation> key32= new Key<ReRelation>(ReRelation.PO,ReRelation.EC);
	ReRelation[] result32 = {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPPi,ReRelation.NTPPi};
    comptable.put(key32, result32);	
    
    Key<ReRelation> key33= new Key<ReRelation>(ReRelation.PO,ReRelation.PO);
	ReRelation[] result33 =  {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPP,ReRelation.NTPP,ReRelation.TPPi,
		ReRelation.NTPPi,ReRelation.EQ};
    comptable.put(key33, result33);	
    
    Key<ReRelation> key34= new Key<ReRelation>(ReRelation.PO,ReRelation.TPP);
	ReRelation[] result34 =  {ReRelation.PO,ReRelation.TPP,ReRelation.NTPP};
    comptable.put(key34, result34);	
    
    Key<ReRelation> key35= new Key<ReRelation>(ReRelation.PO,ReRelation.NTPP);
	ReRelation[] result35 =  {ReRelation.PO,ReRelation.TPP,ReRelation.NTPP};
    comptable.put(key35, result35);	

    Key<ReRelation> key36= new Key<ReRelation>(ReRelation.PO,ReRelation.TPPi);
    ReRelation[] result36 = {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPPi,ReRelation.NTPPi};
    comptable.put(key36, result36);	
    
    Key<ReRelation> key37= new Key<ReRelation>(ReRelation.PO,ReRelation.NTPPi);
	ReRelation[] result37 = {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPPi,ReRelation.NTPPi};
    comptable.put(key37, result37);	
    
    Key<ReRelation> key38= new Key<ReRelation>(ReRelation.PO,ReRelation.EQ);
	ReRelation[] result38 = {ReRelation.PO};
    comptable.put(key38, result38);	
    
	Key<ReRelation> key41= new Key<ReRelation>(ReRelation.TPP,ReRelation.DC);
	ReRelation[] result41 = {ReRelation.DC};
    comptable.put(key41, result41);	
    
    Key<ReRelation> key42= new Key<ReRelation>(ReRelation.TPP,ReRelation.EC);
    ReRelation[] result42 = {ReRelation.DC,ReRelation.EC};
    comptable.put(key42, result42);	
    
    Key<ReRelation> key43= new Key<ReRelation>(ReRelation.TPP,ReRelation.PO);
    ReRelation[] result43 =  {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPP,ReRelation.NTPP};
    comptable.put(key43, result43);	
    
    Key<ReRelation> key44= new Key<ReRelation>(ReRelation.TPP,ReRelation.TPP);
    ReRelation[] result44 =  {ReRelation.TPP,ReRelation.NTPP};
    comptable.put(key44, result44);	
    
    Key<ReRelation> key45= new Key<ReRelation>(ReRelation.TPP,ReRelation.NTPP);
    ReRelation[] result45 =  {ReRelation.NTPP};
    comptable.put(key45, result45);	

    Key<ReRelation> key46= new Key<ReRelation>(ReRelation.TPP,ReRelation.TPPi);
    ReRelation[] result46 = {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPP,ReRelation.TPPi,ReRelation.EQ};
    comptable.put(key46, result46);	
    
    Key<ReRelation> key47= new Key<ReRelation>(ReRelation.TPP,ReRelation.NTPPi);
    ReRelation[] result47 = {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPPi,ReRelation.NTPPi};
    comptable.put(key47, result47);	
    
    Key<ReRelation> key48= new Key<ReRelation>(ReRelation.TPP,ReRelation.EQ);
    ReRelation[] result48 =  {ReRelation.TPP};
    comptable.put(key48, result48);	
    
	Key<ReRelation> key51= new Key<ReRelation>(ReRelation.NTPP,ReRelation.DC);
	ReRelation[] result51 = {ReRelation.DC};
    comptable.put(key51, result51);	
    
    Key<ReRelation> key52= new Key<ReRelation>(ReRelation.NTPP,ReRelation.EC);
	ReRelation[] result52 = {ReRelation.DC};
    comptable.put(key52, result52);	
    
    Key<ReRelation> key53= new Key<ReRelation>(ReRelation.NTPP,ReRelation.PO);
	ReRelation[] result53 =  {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPP,ReRelation.NTPP};
    comptable.put(key53, result53);	
    
    Key<ReRelation> key54= new Key<ReRelation>(ReRelation.NTPP,ReRelation.TPP);
	ReRelation[] result54 =  {ReRelation.NTPP};
    comptable.put(key54, result54);	
    
    Key<ReRelation> key55= new Key<ReRelation>(ReRelation.NTPP,ReRelation.NTPP);
	ReRelation[] result55 =  {ReRelation.NTPP};
    comptable.put(key55, result55);	

    Key<ReRelation> key56= new Key<ReRelation>(ReRelation.NTPP,ReRelation.TPPi);
	ReRelation[] result56 = {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPP,ReRelation.NTPP};
    comptable.put(key56, result56);	
    
    Key<ReRelation> key57= new Key<ReRelation>(ReRelation.NTPP,ReRelation.NTPPi);
	ReRelation[] result57 = {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.TPP,ReRelation.NTPP,ReRelation.TPPi,
			ReRelation.NTPPi,ReRelation.EQ};
    comptable.put(key57, result57);	
    
    Key<ReRelation> key58= new Key<ReRelation>(ReRelation.NTPP,ReRelation.EQ);
	ReRelation[] result58 = {ReRelation.NTPP};
    comptable.put(key58, result58);	
    
    Key<ReRelation> key61= new Key<ReRelation>(ReRelation.TPPi,ReRelation.DC);
	ReRelation[] result61 = {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.NTPPi,ReRelation.TPPi};
    comptable.put(key61, result61);	
    
    Key<ReRelation> key62= new Key<ReRelation>(ReRelation.TPPi,ReRelation.EC);
    ReRelation[] result62 = {ReRelation.EC,ReRelation.PO,ReRelation.TPPi,ReRelation.NTPPi};
    comptable.put(key62, result62);	
    
    Key<ReRelation> key63= new Key<ReRelation>(ReRelation.TPPi,ReRelation.PO);
    ReRelation[] result63 =  {ReRelation.PO,ReRelation.TPPi,ReRelation.NTPPi};
    comptable.put(key63, result63);	
    
    Key<ReRelation> key64= new Key<ReRelation>(ReRelation.TPPi,ReRelation.TPP);
    ReRelation[] result64 =  {ReRelation.PO,ReRelation.EQ,ReRelation.TPP,ReRelation.TPPi};
    comptable.put(key64, result64);	
    
    Key<ReRelation> key65= new Key<ReRelation>(ReRelation.TPPi,ReRelation.NTPP);
    ReRelation[] result65 =  {ReRelation.PO,ReRelation.TPP,ReRelation.NTPP};
    comptable.put(key65, result65);	

    Key<ReRelation> key66= new Key<ReRelation>(ReRelation.TPPi,ReRelation.TPPi);
    ReRelation[] result66 = {ReRelation.TPPi,ReRelation.NTPPi};
    comptable.put(key66, result66);	
    
    Key<ReRelation> key67= new Key<ReRelation>(ReRelation.TPPi,ReRelation.NTPPi);
    ReRelation[] result67 = {ReRelation.NTPPi};
    comptable.put(key67, result67);	
    
    Key<ReRelation> key68= new Key<ReRelation>(ReRelation.TPPi,ReRelation.EQ);
    ReRelation[] result68 =  {ReRelation.TPPi};
    comptable.put(key68, result68);	
    
    Key<ReRelation> key71= new Key<ReRelation>(ReRelation.NTPPi,ReRelation.DC);
	ReRelation[] result71 = {ReRelation.DC,ReRelation.EC,ReRelation.PO,ReRelation.NTPPi,ReRelation.TPPi};
    comptable.put(key71, result71);	
    
    Key<ReRelation> key72= new Key<ReRelation>(ReRelation.NTPPi,ReRelation.EC);
    ReRelation[] result72 = {ReRelation.PO,ReRelation.TPPi,ReRelation.NTPPi};
    comptable.put(key72, result72);	
    
    Key<ReRelation> key73= new Key<ReRelation>(ReRelation.NTPPi,ReRelation.PO);
    ReRelation[] result73 =  {ReRelation.PO,ReRelation.TPPi,ReRelation.NTPPi};
    comptable.put(key73, result73);	
    
    Key<ReRelation> key74= new Key<ReRelation>(ReRelation.NTPPi,ReRelation.TPP);
    ReRelation[] result74 =  {ReRelation.PO,ReRelation.NTPPi,ReRelation.TPPi};
    comptable.put(key74, result74);	
    
    Key<ReRelation> key75= new Key<ReRelation>(ReRelation.NTPPi,ReRelation.NTPP);
    ReRelation[] result75 =  {ReRelation.PO,ReRelation.TPP,ReRelation.NTPP,ReRelation.TPPi,ReRelation.NTPPi,ReRelation.EQ};
    comptable.put(key75, result75);	

    Key<ReRelation> key76= new Key<ReRelation>(ReRelation.NTPPi,ReRelation.TPPi);
    ReRelation[] result76 = {ReRelation.NTPPi};
    comptable.put(key76, result76);	
    
    Key<ReRelation> key77= new Key<ReRelation>(ReRelation.NTPPi,ReRelation.NTPPi);
    ReRelation[] result77 = {ReRelation.NTPPi};
    comptable.put(key77, result77);	
    
    Key<ReRelation> key78= new Key<ReRelation>(ReRelation.NTPPi,ReRelation.EQ);
    ReRelation[] result78 =  {ReRelation.NTPPi};
    comptable.put(key78, result78);	
    
	Key<ReRelation> key81= new Key<ReRelation>(ReRelation.EQ,ReRelation.DC);
	ReRelation[] result81 = {ReRelation.DC};
    comptable.put(key81, result81);	
    
    Key<ReRelation> key82= new Key<ReRelation>(ReRelation.EQ,ReRelation.EC);
   
    ReRelation[] result82 = {ReRelation.EC};
    comptable.put(key82, result82);	
    
    Key<ReRelation> key83= new Key<ReRelation>(ReRelation.EQ,ReRelation.PO);
    ReRelation[] result83 =  {ReRelation.PO};
    comptable.put(key83, result83);	
    
    Key<ReRelation> key84= new Key<ReRelation>(ReRelation.EQ,ReRelation.TPP);
    ReRelation[] result84 =  {ReRelation.TPP};
    comptable.put(key84, result84);	
    
    Key<ReRelation> key85= new Key<ReRelation>(ReRelation.EQ,ReRelation.NTPP);
    ReRelation[] result85 =  {ReRelation.NTPP};
    comptable.put(key85, result85);	

    Key<ReRelation> key86= new Key<ReRelation>(ReRelation.EQ,ReRelation.TPPi);
    ReRelation[] result86 =  {ReRelation.TPPi};
    comptable.put(key86, result86);	
    
    Key<ReRelation> key87= new Key<ReRelation>(ReRelation.EQ,ReRelation.NTPPi);
    ReRelation[] result87 =  {ReRelation.NTPPi};
    comptable.put(key87, result87);	
    
    Key<ReRelation> key88= new Key<ReRelation>(ReRelation.EQ,ReRelation.EQ);
    ReRelation[] result88 =  {ReRelation.EQ};
    comptable.put(key88, result88);	
    
    System.out.println("Succeed");
    buildEntireCompositionTable();
    buildBinaryCompTable();
    buildBinaryTotalTable();
    writeToFile();
    	




}

public static LinkedHashSet<ReRelation> lookUpTable(LinkedHashSet<ReRelation> LRE,LinkedHashSet<ReRelation> RRE)
{
return totaltable.get(new Key(LRE,RRE));	
}
public static int LookUpTable(int LRE,int RRE)
{
 int key = (LRE<<8) + RRE;
    //System.out.println(biTotalTable.size());
 	//System.out.println( biTotalTable.get(key));

	return biTotalTable.get(key);	
}
public static HashMap<Key<ReRelation>, ReRelation[]> getComptable() {
	return comptable;
}


public static void setComptable(HashMap<Key<ReRelation>, ReRelation[]> comptable) {
	CompositionTable.comptable = comptable;
}


public static HashMap<Key<ReRelation>, LinkedHashSet<ReRelation>> getTotaltable() {
	return totaltable;
}


public static void setTotaltable(
		HashMap<Key<ReRelation>, LinkedHashSet<ReRelation>> totaltable) {
	CompositionTable.totaltable = totaltable;
}


	




}
