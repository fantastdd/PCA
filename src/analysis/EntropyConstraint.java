package analysis;
public class EntropyConstraint implements Comparable<EntropyConstraint> 
{
 private int i;
 private int j;
 private int entropy;
public EntropyConstraint(int i, int j, int entropy) {
	super();
	this.i = i;
	this.j = j;
	this.entropy = entropy;
}
public EntropyConstraint() {
	// TODO Auto-generated constructor stub
}
public int getI() {
	return i;
}
public void setI(int i) {
	this.i = i;
}
public int getJ() {
	return j;
}
public void setJ(int j) {
	this.j = j;
}
public int getEntropy() {
	return entropy;
}
public void setEntropy(int entropy) {
	this.entropy = entropy;
}
public int compareTo(EntropyConstraint o) {
	// TODO Auto-generated method stub
	//if (o instanceof EntropyConstraint)
		return ((Integer)(this.getEntropy())).compareTo( ((EntropyConstraint) o).getEntropy());         
	
	//return 0;
}
public String toString()
{
   String result = i + "  "+ j + "   " + this.entropy;
   return result;

}

public int hashCode()
{
	int hashcode = 0;
		
  	  hashcode = (i*789389)+j;
  	  
  	
	return hashcode;
	
}
public boolean equals(Object obj) {
	EntropyConstraint o = (EntropyConstraint)obj;
	return (this.hashCode()== obj.hashCode());
    }


}