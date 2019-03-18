package model;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {

	public String name;
	public List<Node> nextNode;
	public List<Integer> nextNodeDistance;

	public List<Integer> way;
	public int amount;

	public Node(String name) {
		this.name = name;
		this.way = new ArrayList<Integer>();
	}

	public void addNextNode(Node node, int distance) {

		if (this.nextNode == null && this.nextNodeDistance == null) {

			this.nextNode = new ArrayList<Node>();
			this.nextNode.add(node);
			this.nextNodeDistance = new ArrayList<Integer>();
			this.nextNodeDistance.add(distance);

		} else {

			this.nextNode.add(node);
			this.nextNodeDistance.add(distance);

		}
	}

	public void addWay(int value) {
		this.way.add(value);
		this.setAmount();
	}

	public void setAmount() {
		this.amount = 0;
		if (this.way.size() > 0) {
			for (int i = 0; i < this.way.size(); i++) {
				this.amount += way.get(i);
			}
		}
	}

	@Override
	public int compareTo(Node o) {
		if (this.amount < o.amount) {
			return -1;
		}
		if (this.amount > o.amount) {
			return 1;
		}
		return 0;
	}

}
