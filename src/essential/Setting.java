package essential;

public class Setting {
private String splitSet; //h8,c8,or q8
private String lableModel; // NP8 or A

public String getSplitSet() {
	return splitSet;
}
public void setSplitSet(String splitSet) {
	this.splitSet = splitSet;
}
public String getLableModel() {
	return lableModel;
}
public void setLableModel(String lableModel) {
	this.lableModel = lableModel;
}
public Setting(String splitSet, String lableModel) {
	super();
	this.splitSet = splitSet;
	this.lableModel = lableModel;
}

}
