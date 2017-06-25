package it.polito.tdp.borders.model;

import java.util.*;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private BordersDAO dao;
	private Map<Integer,Country> mapStati;
	private SimpleGraph<Country,DefaultEdge> grafo;
	private Map<String,Confine> mapConfini;
	private Simulatore sim;

	
	
	
	public SimpleGraph<Country, DefaultEdge> getGrafo() {
		return grafo;
	}

	public Model(){
		dao = new BordersDAO();
		mapStati = new HashMap<Integer,Country>();
		mapConfini = new HashMap<String,Confine>();
		this.dao.loadAllCountries(mapStati);
		
	}
	
	public List<Country> creaGrafo(int anno){
		this.grafo = new SimpleGraph<Country,DefaultEdge>(DefaultEdge.class);
		List<Confine> confini = this.dao.getConfini(mapStati, anno, mapConfini);
		
		if (confini == null || confini.isEmpty())
			throw new RuntimeException("Nessun confine per l'anno specificato");
		
		for(Confine c : confini){
			grafo.addVertex(c.getStato1());
			grafo.addVertex(c.getStato2());
			if(grafo.getEdge(c.getStato1(), c.getStato2())==null){
				grafo.addEdge(c.getStato1(), c.getStato2());
				c.getStato1().getStatiConfinanti().add(c.getStato2());
				c.getStato2().getStatiConfinanti().add(c.getStato1());
			}
		}
		
		List<Country> ordina = new ArrayList<Country>(grafo.vertexSet());
		Collections.sort(ordina);
		
		//System.out.println(ordina);
		
		return ordina;
		
	}
	
	public List<Country> Simula(Country stato){
		List<Country> ordina = new ArrayList<Country>(); 
		sim = new Simulatore(grafo);
		sim.addMigrante(1000, stato);
		sim.run();
		for(Country c :grafo.vertexSet()){
			if(c.getMigranti()!=0){
				ordina.add(c);
			}
		}
	Collections.sort(ordina,new ComparatorePersone());
	return ordina;
		
	}
	
	public int getPassi(){
	return	sim.getPasso();
	}

}
