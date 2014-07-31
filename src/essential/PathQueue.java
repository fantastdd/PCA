package essential;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class PathQueue implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -405265022390231397L;
private LinkedHashSet<Integer> pathQueue = new LinkedHashSet<Integer>();

public int pop()
{
 Iterator<Integer> iter = pathQueue.iterator();
 Integer path = iter.next();
 pathQueue.remove(path);
 return path;

}
public void add(int path)
{
	pathQueue.add(path);
	
}
public boolean isEmpty()
{
  return pathQueue.isEmpty();	
}

}
