package deprecated;

public enum ReRelation {
	DC(1),EC(2),PO(4),TPP(8),EQ(16),NTPP(32),
	TPPi(64),NTPPi(128);
	public final int id;
	final public static int size = 8;
	ReRelation(int id){
		this.id = id;
	}

	public static int Size(){return size;} 
public static void main(String args[])
{
	System.out.println(EC.id);
	}
}

