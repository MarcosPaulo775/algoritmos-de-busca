package model;

import algoritmos.DeepSearch;
import algoritmos.DijkstraSearch;
import algoritmos.WidthSearch;

public class SearchEngine {
	
	public WidthSearch width = new WidthSearch();
	public DeepSearch deep = new DeepSearch();
	public DijkstraSearch dijkstra = new DijkstraSearch();

}
