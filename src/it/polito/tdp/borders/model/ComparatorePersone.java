package it.polito.tdp.borders.model;

import java.util.Comparator;

public class ComparatorePersone implements Comparator<Country> {

	@Override
	public int compare(Country o1, Country o2) {
		return -(o1.getMigranti()-o2.getMigranti());
	}

}
