package main;

import model.Node;
import model.SearchEngine;

public class Main {

	public static void main(String[] args) {

		SearchEngine searchEngine = new SearchEngine();

		Node GO = new Node("Goiânia");

		Node BV = new Node("Bela Vista");
		Node ED = new Node("Edéia");
		Node HI = new Node("Hidrolândia");
		Node SI = new Node("Silvânia");

		Node PO = new Node("Pontalina");
		Node PI = new Node("Piracanjuba");
		Node VI = new Node("Vianópolis");

		Node MO = new Node("Morrinhos");
		Node OR = new Node("Orizona");
		Node IP = new Node("Ipameri");
		Node CA = new Node("Caldas Novas");

		GO.addNextNode(HI, 33);
		GO.addNextNode(BV, 45);
		GO.addNextNode(SI, 67);
		GO.addNextNode(ED, 124);

		HI.addNextNode(PI, 70);
		HI.addNextNode(MO, 90);
		PI.addNextNode(CA, 78);

		BV.addNextNode(CA, 117);

		SI.addNextNode(VI, 18);
		VI.addNextNode(OR, 46);
		OR.addNextNode(IP, 97);
		IP.addNextNode(CA, 61);

		ED.addNextNode(PO, 69);
		PO.addNextNode(MO, 124);
		MO.addNextNode(CA, 57);

		// searchEngine.deep.printWay(GO, searchEngine.deep.getWay(GO, PO));
		// searchEngine.width.printWay(GO, searchEngine.width.getWay(GO, IP));
		searchEngine.dijkstra.printWay(GO, searchEngine.dijkstra.getWay(GO, MO));

	}

}
