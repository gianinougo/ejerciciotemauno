package com.ugo.ejerciciotemauno.Data;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import static java.lang.System.in;

public class MenuAplicacion {

	Scanner sc = new Scanner(in);
	String jsonStringaux;
	String jsonString2;


	public void MenuText() {
		System.out.println("1 - Ciudad por lat y Long");
		System.out.println("2 - Ciudad por nombre");
		System.out.println("3 - CSV");
		System.out.println("4 - Serializar");
		System.out.println("5 - Deserializaar");
		System.out.println("6 - Personaje de Marvel");
		System.out.println("7 - Exit");

	}


	public void Ejecutar() throws ParserConfigurationException, SAXException, FileNotFoundException {

		int option;

		do {

			MenuText();

			System.out.print("Enter an option: ");

			option = sc.nextInt();

			switch (option) {
				case 1:
					CiudadPorCoordenadas();
					break;
				case 2:
					CidudadNombre();
					break;
				case 3:

					break;
				case 4:
					SerializarDatos();
					break;
				case 5:
					DeserializarDatos();
					break;
				case 6:
					PersonajeMarvel();
					break;
				case 7:
					System.out.println("Exit");
					break;

				default:
					System.out.println("Invalid Option");
					break;
			}

		} while(option != 7);
	}


	private void DeserializarDatos() throws FileNotFoundException {

		Gson gson = new Gson();

		try (Reader reader = new FileReader("ubicaciones.json")) {

			Coord coord = gson.fromJson(reader, Coord.class);
			System.out.println(coord);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		System.out.println("---------------------------");
		System.out.println("---------------------------");

		try (Reader reader2 = new FileReader("nombreCiudad.json")) {

			ObjectMapper mapper = new ObjectMapper();
			Coord[] data = mapper.readValue(reader2, Coord[].class);


			for(Coord std : data) {
				System.out.println("Nombre: "+std.nameString);
				System.out.println("Temperatura: "+std.temperatura);
				System.out.println("Humedad actual: "+std.humedadactual);

			}



		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}


	private void SerializarDatos() {
		// Guardar JSon

		if (jsonStringaux != null) {
			try {
				PrintWriter pw = new PrintWriter("ubicaciones.json");
				pw.write(jsonStringaux);
				pw.flush();
				pw.close();
				System.out.println("Archivo creado");



			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("No hay datos");
		}

		if (jsonString2 != null) {
			try {
				PrintWriter pw = new PrintWriter("nombreCiudad.json");
				pw.write(jsonString2);
				pw.flush();
				pw.close();
				System.out.println("Archivo creado");



			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("No hay datos");
		}



	}


	private void PersonajeMarvel() {
		String personajeMarver;

		try {

			//1cd6e70ae3f0ca9a4bcf2d2c38768624a4fe4b424b34e5ddf6f334adb197a35f424661e90
			//MD5
			//4814ba01ccb583037ff61bebca1b1185

			System.out.print("Enter an character: ");
			personajeMarver = sc.next();


			URL url = new URL("https://gateway.marvel.com:443/v1/public/characters?name="
					+ personajeMarver +"&ts=1&apikey=b34e5ddf6f334adb197a35f424661e90&hash=4814ba01ccb583037ff61bebca1b1185");


			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			//¿Petición correcta?

			int responseCode = connection.getResponseCode();
			if (responseCode != 200) {
				throw new RuntimeException("Ocurrio un error " + responseCode);
			} else {
				// Abrir un scanner que lea el flujo de datos

				StringBuilder informationString = new StringBuilder();
				Scanner scanner = new Scanner(url.openStream());

				while (scanner.hasNext()) {
					informationString.append(scanner.nextLine());
				}

				scanner.close();

				// Pintar la información en consola

				System.out.println(informationString);


			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	private void CidudadNombre() throws ParserConfigurationException, SAXException {

		List<Coord> coordList = new ArrayList<Coord>();


		String nameCityString;

		try {

			System.out.print("Enter an City: ");
			nameCityString = sc.next();

			String url2 = "https://api.openweathermap.org/data/2.5/weather?q=" + nameCityString + "&mode=xml&appid=c493f3a45248ed9d8cb1fe7997858cff";

			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			f.setNamespaceAware(false);
			f.setValidating(false);
			DocumentBuilder b = f.newDocumentBuilder();
			URLConnection urlConnection = new URL(url2).openConnection();
			urlConnection.addRequestProperty("Accept", "application/xml");
			Document document = b.parse(urlConnection.getInputStream());

			NodeList nList = document.getElementsByTagName("city");
			NodeList nList2 = document.getElementsByTagName("temperature");
			NodeList nList3 = document.getElementsByTagName("humidity");

			System.out.println("----------------------------");

			String name1 = null;
			for (int i = 0; i < nList.getLength(); i++) {
				Node nodo = nList.item(i);
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nodo;
					System.out.println("Ciudad: " + eElement.getAttribute("name"));
					name1 = eElement.getAttribute("name");
				}
			}
			float temp1 = 0;
			for (int i = 0; i < nList2.getLength(); i++) {
				Node nodo2 = nList2.item(i);
				if (nodo2.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement2 = (Element) nodo2;
					System.out.println("temperature: " + eElement2.getAttribute("value"));
					temp1 = Float.parseFloat(eElement2.getAttribute("value"));
				}
			}
			float humidity1 = 0;
			for (int i = 0; i < nList3.getLength(); i++) {
				Node nodo3 = nList3.item(i);
				if (nodo3.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement3 = (Element) nodo3;
					System.out.println("humidity : " + eElement3.getAttribute("value") + eElement3.getAttribute(("unit")));
					humidity1 = Float.parseFloat(eElement3.getAttribute("value"));

				}
			}
			coordList.add(new Coord(name1, temp1, humidity1));

			ArrayList<String> myList = new ArrayList<String>();
			myList.add(coordList.toString());

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String jsonXml = ow.writeValueAsString(coordList);


			System.out.println(jsonXml);
			jsonString2 = jsonXml;


		}
		catch(IOException e) {
			System.out.println(e);
		}

	}


		private void CiudadPorCoordenadas()  {

		float lat;
		float lon;

		try {

			System.out.print("Enter an lat: ");
			lat = sc.nextFloat();


			System.out.print("Enter an log: ");
			lon = sc.nextFloat();


			//URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=38.3452&lon=-0.4815&appid=c493f3a45248ed9d8cb1fe7997858cff");
			URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat="+ lat +"&lon="+ lon + "&appid=c493f3a45248ed9d8cb1fe7997858cff");


			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			//Check if connect is made
			int responseCode = conn.getResponseCode();

			// 200 OK
			if (responseCode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responseCode);
			} else {

				BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
				String readAPIResponse;
				StringBuilder jsonString = new StringBuilder();

				while((readAPIResponse = br.readLine()) != null){
					jsonString.append(readAPIResponse);
				}


				JSONObject jsonObject  = new JSONObject(jsonString.toString());

				String name = (String) jsonObject.get("name");

				JSONObject main = jsonObject.getJSONObject("main");
				float temp = (float) main.getDouble("temp");
				float humidity = (float) main.getDouble("humidity");


				//System.out.println(jsonString);
				System.out.println("---------------------------");

				Coord c = new Coord(name, temp, humidity);

				c.nameString = name;
				c.temperatura = temp;
				c.humedadactual = humidity;

				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(c);

				System.out.println(json);

				jsonStringaux = json;



			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
