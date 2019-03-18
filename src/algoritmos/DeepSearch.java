package algoritmos;

import java.util.ArrayList;
import java.util.List;

import model.Node;

public class DeepSearch implements SearchStrategy {

	List<Integer> way = new ArrayList<Integer>();
	boolean isFound = false;

	@Override
	public List<Integer> getWay(Node start, Node end) {

		if (this.isFound == false) {

			// Só para mostrar o caminho percorrido durante a busca
			if (start.way.size() > 0) {
				System.out.println(start.name + " - " + start.way + " -> " + start.amount);
				if (start == end) {
					System.out.println("");
				}

			} else {
				System.out.println(start.name);
			}

			if (start == end) {
				this.isFound = true;
				way = start.way;
				return way;
			} else {
				if (start.nextNode != null) {
					for (int i = 0; i < start.nextNode.size(); i++) {

						// Adiciona o caminho do no anterior
						if (start.way.size() != 0) {
							start.nextNode.get(i).way = new ArrayList<Integer>();
							for (int j = 0; j < start.way.size(); j++)
								start.nextNode.get(i).way.add(start.way.get(j));
						}

						// Soma ao caminho o no atual
						start.nextNode.get(i).addWay(start.nextNodeDistance.get(i));

						this.getWay(start.nextNode.get(i), end);
					}
				} else {
					return way;
				}
			}
		}
		return way;

	}

	@Override
	public void printWay(Node initial, List<Integer> way) {

		System.out.println(initial.name);

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
