/**
 * 
 */
package common;

/**
 * @author czarek
 *
 */
public abstract class Node {

	public Node(int id) {
		super();
		this.id = id;
	}

	final int id;


	public abstract void handle(long time);
	
	
}
