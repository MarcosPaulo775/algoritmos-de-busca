package algoritmos;

import java.util.List;

import model.Node;

public interface SearchStrategy {

	public List<Integer> getWay(Node start, Node end);
	public void printWay(Node initial, List<Integer> way);

}
