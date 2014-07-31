package deprecated;
import java.io.Serializable;
import java.util.LinkedHashSet;



public class ReConstraint<AnyType> implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -1260154269007889580L;
private LinkedHashSet<ReRelation> Relations;
private AnyType source;
private AnyType destination;

public ReConstraint(LinkedHashSet<ReRelation> relations, AnyType source,
		AnyType destination) {
	super();
	Relations = relations;
	this.source = source;
	this.destination = destination;
}

public String toString()
{
  String result = this.source + "------" + Relations+"-------->" + this.destination;	
  return result;
}





public LinkedHashSet<ReRelation> getRelations() {
	return Relations;
}







public void setRelations(LinkedHashSet<ReRelation> relations) {
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
