package essential;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Key<AnyType> {
	private AnyType source;
	private AnyType destination;
	private Collection<AnyType> sourceCollection;
	private Collection<AnyType> destinationCollection;
	public Key(Collection<AnyType> sourceCollection, Collection<AnyType> destinationCollection )
	{
		this.sourceCollection = sourceCollection;
		this.destinationCollection = destinationCollection;
	}
	public Key(AnyType source, AnyType destination) {
	//	super();
		this.source = source;
		this.destination = destination;
	}
	public Key(){}
	public AnyType getSource() {
		return source;
	}
	public void setSource(AnyType source) {
		this.source = source;
	}
	public AnyType getDestination() {
		return destination;
	}
	public void setDestination(AnyType destination) {
		this.destination = destination;
	}
	public Collection<AnyType> getSourceCollection() {
		return sourceCollection;
	}
	public void setSourceCollection(Collection<AnyType> sourceCollection) {
		this.sourceCollection = sourceCollection;
	}
	public Collection<AnyType> getDestinationCollection() {
		return destinationCollection;
	}
	public void setDestinationCollection(Collection<AnyType> destinationCollection) {
		this.destinationCollection = destinationCollection;
	}
    public String toString()
    {
    String 	result = "key ";
    Iterator<AnyType> iter = sourceCollection.iterator();
    	if (this.sourceCollection!= null)
    	{
    		while (iter.hasNext())
    		result+= iter.next().toString()+",";
    	
    	}
    	result+=".";
    	 Iterator<AnyType> iter1 = destinationCollection.iterator();
    	if (this.destinationCollection!= null)
    	{
    		while (iter1.hasNext())
    		result+= iter1.next().toString();
    	}
    	return result;
    	
    }
   

    public int hashCode()
    {
    	int hashcode = 0;
    	if (sourceCollection!=null & destinationCollection!=null){

    		Iterator sourceIter = sourceCollection.iterator();
    		Iterator destinationIter = destinationCollection.iterator();
    		while (sourceIter.hasNext())
    		{
    			hashcode = hashcode^sourceIter.next().hashCode();
    		}
    		hashcode = hashcode * 17;
    		while (destinationIter.hasNext())
    		{
    			hashcode = hashcode^destinationIter.next().hashCode();
    		}
    	
    	
    	}
    	if (source!=null & destination!=null){
    		
      	  hashcode = (source.hashCode()*17)^destination.hashCode();
      	  
      	
      	}
		return hashcode;
    	
    }
    public boolean equals(Object obj) {
    	Key o = (Key)obj;
    	return (this.hashCode()== obj.hashCode());
        }


}