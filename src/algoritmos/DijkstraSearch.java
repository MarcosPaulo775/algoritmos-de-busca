package algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Node;

public class DijkstraSearch implements SearchStrategy {

	List<Node> auxList = new ArrayList<Node>();
	List<Integer> way = new ArrayList<Integer>();
	boolean isFound = false;

	@Override
	public List<Integer> getWay(Node start, Node end) {

		if (start.name == end.name || start == end) {
			this.isFound = true;
		}

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

				if (this.auxList.size() != 0) {
					this.sortList(this.auxList);
					this.getWay(this.auxList.get(0), end);
				}

			}

			return this.way;
		}

	}

	public List<Node> sortList(List<Node> list) {

		Collections.sort(list);

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
