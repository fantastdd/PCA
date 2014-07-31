package extend;

import essential.Relation;
import essential.MTS;

public class ExtendStrategy {

public static int extend(int rels)
{
int weight = 30; // Greedy Search with the least wights extension;
int rels1 = rels;
int rels2 = rels;

	   if((rels>>4^1)%2 == 1 &&MTS.getBIMTS("H8").contains(rels1+Relation.EQ.id))
		{
		   rels1 +=   Relation.EQ.id;;
		}
		else 
			if((rels>>3^1)%2 == 1&&MTS.getBIMTS("H8").contains(rels1+Relation.TPP.id))
			{
				 rels1 += Relation.TPP.id;
			}
			else 
				if((rels>>6^1)%2 == 1&&MTS.getBIMTS("H8").contains(rels1+Relation.TPPi.id))
				{
					 rels1 += Relation.TPPi.id;
				}
				else
					if((rels>>5^1)%2 == 1&&MTS.getBIMTS("H8").contains(rels1+Relation.NTPP.id))
					{
						 rels1 += Relation.NTPP.id;
					}
					else 
						if((rels>>7^1)%2 == 1&&MTS.getBIMTS("H8").contains(rels1+Relation.NTPPi.id))
						{
							 rels1 += Relation.NTPPi.id;
						}
						else
							if((rels^1)%2 == 1&&MTS.getBIMTS("H8").contains(rels1+Relation.DC.id))
							{
								 rels1 += Relation.DC.id;
							}
							else 
								if((rels>>1^1)%2 == 1&&MTS.getBIMTS("H8").contains(rels1+Relation.EC.id))
								{
									 rels1 += Relation.EC.id;
								}
								else 
									if((rels>>2^1)%2 == 1&&MTS.getBIMTS("H8").contains(rels1+Relation.PO.id))
									{
	
									  rels1 += Relation.PO.id;
							}
	
if (rels1 == rels||Relation.calculateWeight(rels1^rels)>=4)	 
{	
	int id1 = 0;
	int id2 = 0;
	for (int i = 0;i<8;i++)
	 {
		 rels2 = rels;
		 rels2|= 1<<i;
		 for (int j = 0;j<8;j++)
		 {
			 rels2 |= 1<<j;
			 if (Relation.calculateWeight(rels2)<weight && MTS.getBIMTS("H8").contains(rels2))	 
			 {
				 weight = Relation.calculateWeight(rels2);
				 id1 = 1<<i;
				 id2 = 1<<j;
			 }
		 }
		 
	}		
    rels2 = rels;
    rels2|= id1;
    rels2|= id2;
    if(rels1 == rels)
    	return rels2;
    if (Relation.calculateWeight(rels1^rels) > Relation.calculateWeight(rels2^rels))
    	return rels2;
}
return rels1;

}	
public static int extendFAILS(int rels)
{
do {
	// rels doesnot contain EQ. EQ = 2^4 = 16
	if((rels>>4^1)%2 == 1)
	{
	  	rels += Relation.EQ.id;
	}
	else 
		if((rels>>3^1)%2 == 1)
		{
			rels += Relation.TPP.id;
		}
		else 
			if((rels>>6^1)%2 == 1)
			{
				rels += Relation.TPPi.id;
			}
			else
				if((rels>>5^1)%2 == 1)
				{
					rels += Relation.NTPP.id;
				}
				else 
					if((rels>>7^1)%2 == 1)
					{
						rels += Relation.NTPPi.id;
					}
					else
						if((rels^1)%2 == 1)
						{
							rels += Relation.DC.id;
						}
						else 
							if((rels>>1^1)%2 == 1)
							{
								rels += Relation.EC.id;
							}
							else 
								if((rels>>2^1)%2 == 1)
								{
									rels += Relation.PO.id;
								}
	
	
                    
} while (!MTS.getBIMTS("H8").contains(rels));
return rels;
}

public static void main(String args[])
{
	int rels = 73;
	rels = ExtendStrategy.extend(127);
	System.out.println(Relation.translateToString(127));
	System.out.println(Relation.translateToString(rels));

}




}

