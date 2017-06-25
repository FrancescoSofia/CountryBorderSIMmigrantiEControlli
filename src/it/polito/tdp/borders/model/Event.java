package it.polito.tdp.borders.model;

public class Event implements Comparable<Event> {
	
	private Country stato;
	private int passo;
	private int migranti;
	
	
	

	public Event(Country stato, int passo, int migranti) {
		super();
		this.stato = stato;
		this.passo = passo;
		this.migranti = migranti;
	}




	public Country getStato() {
		return stato;
	}




	public void setStato(Country stato) {
		this.stato = stato;
	}




	public int getPasso() {
		return passo;
	}




	public void setPasso(int passo) {
		this.passo = passo;
	}




	public int getMigranti() {
		return migranti;
	}




	public void setMigranti(int migranti) {
		this.migranti = migranti;
	}




	@Override
	public int compareTo(Event o) {
		return this.passo-o.passo;
	}

}
