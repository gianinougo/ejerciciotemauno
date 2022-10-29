package com.ugo.ejerciciotemauno.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;

import java.io.Reader;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Coord {

	@SerializedName("Nombre")
	String nameString;
	@SerializedName("Temperatura")
	float temperatura;
	@SerializedName("Humedad")
	float humedadactual;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@SerializedName("Fecha")
	Date fecha;

	public Coord(){

	}
	
	public Coord(String nameString, float temperatura, float humedadactual) {
		super();
		this.nameString = nameString;
		this.temperatura = temperatura;
		this.humedadactual = humedadactual;
		//solo la fecha sin la hora
		this.fecha = new Date();
		//this.fecha = Date.from(LocalDate.now().atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
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
		return "Nombre: " + nameString + ", " +
				" Temperatura: " + temperatura + ", " +
				" Humedad actual: " + humedadactual + ", " +
				" Fecha: " + fecha;
	}


}
