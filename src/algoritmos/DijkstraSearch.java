package algoritmos;

import java.util.ArrayList;
import java.util.List;

import model.Node;

public class DijkstraSearch implements SearchStrategy {

	List<Node> auxList = new ArrayList<Node>();
	List<Integer> way = new ArrayList<Integer>();
	boolean isFound = false;

	@Override
	public List<Integer> getWay(Node start, Node end) {

		if (start.way.size() == 0) {
			System.out.println(start.name);
		} else {
			System.out.println(start.name + " - " + start.way + " -> " + start.amount);
		}

		if (start == end) {
			System.out.println("");
		}

		if (this.isFound == true) {
			this.way = start.way;
			return this.way;
		} else {

			if (this.auxList.size() == 0) {
				for (int i = 0; i < start.nextNode.size(); i++) {

					if (this.auxList.contains(start.nextNode.get(i))) {

						Node node = new Node(start.nextNode.get(i).name);

						// Adiciona o caminho do no anterior
						if (start.way.size() != 0) {
							node.way = new ArrayList<Integer>();
							for (int j = 0; j < start.way.size(); j++)
								node.way.add(start.way.get(j));
						}

						// Soma ao caminho o no atual
						node.addWay(start.nextNodeDistance.get(i));

						this.auxList.add(node);

					} else {

						// Adiciona o caminho do no anterior
						if (start.way.size() != 0) {
							start.nextNode.get(i).way = new ArrayList<Integer>();
							for (int j = 0; j < start.way.size(); j++)
								start.nextNode.get(i).way.add(start.way.get(j));
						}

						// Soma ao caminho o no atual
						start.nextNode.get(i).addWay(start.nextNodeDistance.get(i));

						this.auxList.add(start.nextNode.get(i));
					}

				}

				this.sortList(this.auxList);
				this.getWay(this.auxList.get(0), end);
			} else {

				if (start.nextNode != null) {
					for (int i = 0; i < start.nextNode.size(); i++) {

						if (this.auxList.contains(start.nextNode.get(i)) || start.nextNode.get(i).name == end.name) {

							// System.out.println("CONTAINS : " + start.nextNode.get(i).name);

							Node node = new Node(start.nextNode.get(i).name);

							// Adiciona o caminho do no anterior
							if (start.way.size() != 0) {
								node.way = new ArrayList<Integer>();
								for (int j = 0; j < start.way.size(); j++)
									node.way.add(start.way.get(j));
							}

							// Soma ao caminho o no atual
							node.addWay(start.nextNodeDistance.get(i));

							this.auxList.add(node);

						} else {

							// Adiciona o caminho do no anterior
							if (start.way.size() != 0) {
								start.nextNode.get(i).way = new ArrayList<Integer>();
								for (int j = 0; j < start.way.size(); j++)
									start.nextNode.get(i).way.add(start.way.get(j));
							}

							// Soma ao caminho o no atual
							start.nextNode.get(i).addWay(start.nextNodeDistance.get(i));

							this.auxList.add(start.nextNode.get(i));
						}

						if (this.isFound == true)
							break;

					}

				}

				this.auxList.remove(0);
				if (this.auxList.get(0).name == end.name || this.auxList.get(0) == end) {
					this.isFound = true;
				}
				this.sortList(this.auxList);
				this.getWay(this.auxList.get(0), end);

			}

			return this.way;
		}

	}

	public List<Node> sortList(List<Node> list) {
		Node aux = new Node(null);

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (list.get(i).amount < list.get(j).amount) {

					aux.name = list.get(i).name;
					aux.nextNode = new ArrayList<Node>();
					aux.nextNode = list.get(i).nextNode;
					aux.nextNodeDistance = new ArrayList<Integer>();
					aux.nextNodeDistance = list.get(i).nextNodeDistance;
					aux.way = new ArrayList<Integer>();
					aux.way = list.get(i).way;
					aux.amount = list.get(i).amount;

					// *****************************************************

					list.get(i).name = list.get(j).name;
					list.get(i).nextNode = list.get(j).nextNode;
					list.get(i).nextNodeDistance = list.get(j).nextNodeDistance;
					list.get(i).way = list.get(j).way;
					list.get(i).amount = list.get(j).amount;

					// *****************************************************

					list.get(j).name = aux.name;
					list.get(j).nextNode = aux.nextNode;
					list.get(j).nextNodeDistance = aux.nextNodeDistance;
					list.get(j).way = aux.way;
					list.get(j).amount = aux.amount;

					System.out.println("XXXXXXXXXXXXXXX");
					System.out.println("list i = " + list.get(i).nextNode.get(0).way);
					System.out.println("list j = " + list.get(j).nextNode.get(0).way);
					System.out.println("aux = " + aux.nextNode.get(0).way);
					System.out.println("XXXXXXXXXXXXXXX");

				}
			}
		}

		System.out.println(" - - - - - - - - - - - - - - - -");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).name + " [" + list.get(i).amount + "]");
		}
		System.out.println(" - - - - - - - - - - - - - - - -");
		System.out.println("");
		System.out.println("");

		return list;
	}

	@Override
	public void printWay(Node initial, List<Integer> way) {

		System.out.println("* " + initial.name);

		if (way.size() > 0) {
			for (int i = 0; i < initial.nextNode.size(); i++) {
				if (initial.nextNodeDistance.get(i) == way.get(0)) {
					way.remove(0);
					this.printWay(initial.nextNode.get(i), way);
					break;
				}
			}
		}

	}

}
