package essential;

public enum Relation {
	EQ(16,1),TPP(8,2),NTPP(32,2),TPPi(64,2),NTPPi(128,2),EC(2,3),DC(1,4),PO(4,5);
	public final int id;
	public final int weight;
	final public static int size = 8;
	Relation(int id,int weight){
		this.id = id;
		this.weight = weight;
	}
	

	public static int Size(){return size;} 
	public static int calculateWeight(int rels)
	{
	 int weight = 0;
	   if((rels>>4^1)%2 == 0 )
		{
		   weight += Relation.EQ.weight;
		}
		
			if((rels>>3^1)%2 == 0)
			{
				weight += Relation.TPP.weight;
			}
		
				if((rels>>6^1)%2 == 0)
				{
					weight += Relation.TPPi.weight;
				}
		
					if((rels>>5^1)%2 == 0)
					{
						weight += Relation.NTPP.weight;
					}
			
						if((rels>>7^1)%2 == 0)
						{
							weight += Relation.NTPPi.weight;
						}
				
							if((rels^1)%2 == 0)
							{
								weight += Relation.DC.weight;
							}
					
								if((rels>>1^1)%2 == 0)
								{
									weight += Relation.EC.weight;
								}
							
									if((rels>>2^1)%2 == 0)
									{
										weight += Relation.PO.weight;
									}
	return weight;
	}
	public static Relation get(int id)
	{
		for(Relation rel:Relation.values())
		{
			if(rel.id == id)
				return rel;
		}
			return null;
	}
	public static int get(String name)
	{
	   if(name.equalsIgnoreCase("DC"))
			   return 1; 
	   if(name.equalsIgnoreCase("EC"))
			   return 2; 
	   if(name.equalsIgnoreCase("PO"))
			   return 4; 
	   if(name.equalsIgnoreCase("TPP"))
			   return 8; 
	   if(name.equalsIgnoreCase("EQ"))
			   return 16; 
	   if(name.equalsIgnoreCase("NTPP"))
			   return 32; 
	   if(name.equalsIgnoreCase("TPPI"))
			   return 64; 
	   
	   if(name.equalsIgnoreCase("NTPPI"))
			   return 128; 
		return 255;
	}
	public static String translateToString(Integer i)
	{
	  String binary = Integer.toBinaryString(i);
	 // System.out.println(binary);
	  String result;
	  if(Integer.bitCount(i) == 8)
		result = "ALL";
	  else 
	   {
		  result = "( ";
		  for (int j = 0; j < binary.length();j++)
		    {     
				  char relation = binary.charAt(j);
				  if(relation == '1')
					  result+= Relation.get(1<<binary.length()-1-j)+" ";
		    }
			    result += ")";
			   }
		return result;
	}
	public static void main(String args[])
	{
		System.out.println(Relation.translateToString(160));
		int i = 255;
	/*	System.out.println(Integer.toBinaryString(i));
		System.out.println(Integer.toBinaryString(128));
		System.out.println(~4);
		System.out.println(Integer.toBinaryString(~4));
		System.out.print(Integer.bitCount(i));*/
		int j = 5;
		System.out.println(Integer.toBinaryString(j));
		System.out.println(Integer.toBinaryString(i<<3));
		for(Relation br:Relation.values())
			System.out.println(br);
		int a = 9;
		int b = 15;
		System.out.println(a^b);
		System.out.println(Integer.toBinaryString(b-a));
	}
}
