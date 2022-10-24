package com.ugo.ejerciciotemauno.Data;

import com.google.gson.annotations.SerializedName;

public class Coord {
	
	@SerializedName("Nombre")
	String nameString;
	@SerializedName("Temperatura")
	float temperatura;
	@SerializedName("Humedad")
	float humedadactual;
	
	public Coord(String nameString, float temperatura, float humedadactual) {
		super();
		this.nameString = nameString;
		this.temperatura = temperatura;
		this.humedadactual = humedadactual;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public float getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(float temperatura) {
		this.temperatura = temperatura;
	}

	public float getHumedadactual() {
		return humedadactual;
	}

	public void setHumedadactual(float humedadactual) {
		this.humedadactual = humedadactual;
	}

	@Override
	public String toString() {
		return "Nombre " + nameString + ", " +
				" Temperatura " + temperatura + ", " +
				" Humedad actual " + humedadactual;
	}




}
