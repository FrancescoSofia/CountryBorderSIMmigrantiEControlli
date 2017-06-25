package it.polito.tdp.borders.model;

import java.util.PriorityQueue;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;



public class Simulatore {
	
	
	private int passo;
	private PriorityQueue<Event> queue;
	SimpleGraph<Country,DefaultEdge> grafo;
	
	public Simulatore(SimpleGraph<Country, DefaultEdge> grafo2){
		queue =  new PriorityQueue<Event>();
		
		passo =0;
		this.grafo = grafo2;
		
	}
	
	public int getPasso() {
		return passo;
	}

	public void setPasso(int passo) {
		this.passo = passo;
	}

	public void addMigrante(int migranti,Country stato){
		
		Event e = new Event(stato,passo,migranti);
		queue.add(e);
	
		
	}
	
	public void run(){
		while(!queue.isEmpty()){
			Event e = queue.poll();
			int metaMigranti = e.getMigranti()*50/100;
			e.getStato().addMigranti(metaMigranti);
			int migranti =  metaMigranti/(Graphs.neighborListOf(grafo, e.getStato()).size());
			if(migranti >= Graphs.neighborListOf(grafo, e.getStato()).size()){
			this.passo=e.getPasso()+1;
			for(Country c :Graphs.neighborListOf(grafo, e.getStato())){
				c.addMigranti(migranti);
				queue.add(new Event(c,passo,migranti));
			}
		}
		
	}
}
}
