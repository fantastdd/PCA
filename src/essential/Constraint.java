package essential;
import java.io.Serializable;
import java.util.LinkedHashSet;

// only record the index
public class Constraint<AnyType> implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -1260154269007889580L;
private int Relations;
public AnyType source;
public AnyType destination;

public Constraint(int relations, AnyType source,
		AnyType destination) {
	super();
	Relations = relations;
	this.source = source;
	this.destination = destination;
}
public Constraint(AnyType source,
		AnyType destination) {
	super();
	this.source = source;
	this.destination = destination;
}
public String toString()
{
  String result = this.source + "------" + Relation.translateToString(Relations)+"-------->" + this.destination;	
  return result;
}





public int getRelations() {
	return Relations;
}







public void setRelations(int relations) {
	Relations = relations;
}


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


}
