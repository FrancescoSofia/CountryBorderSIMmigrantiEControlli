package it.polito.tdp.borders.model;

public class Confine {
	
	private Country stato1;
	private Country stato2;
	private int year;
	private int conttype;
	
	
	public Confine(Country stato1, Country stato2, int year, int conttype) {
		super();
		this.stato1 = stato1;
		this.stato2 = stato2;
		this.year = year;
		this.conttype = conttype;
	}


	public Country getStato1() {
		return stato1;
	}


	public void setStato1(Country stato1) {
		this.stato1 = stato1;
	}


	public Country getStato2() {
		return stato2;
	}


	public void setStato2(Country stato2) {
		this.stato2 = stato2;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public int getConttype() {
		return conttype;
	}


	public void setConttype(int conttype) {
		this.conttype = conttype;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + conttype;
		result = prime * result + ((stato1 == null) ? 0 : stato1.hashCode());
		result = prime * result + ((stato2 == null) ? 0 : stato2.hashCode());
		result = prime * result + year;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Confine other = (Confine) obj;
		if (conttype != other.conttype)
			return false;
		if (stato1 == null) {
			if (other.stato1 != null)
				return false;
		} else if (!stato1.equals(other.stato1))
			return false;
		if (stato2 == null) {
			if (other.stato2 != null)
				return false;
		} else if (!stato2.equals(other.stato2))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	
	

}
