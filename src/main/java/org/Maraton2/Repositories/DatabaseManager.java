package org.Maraton2.Repositories.Entities;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class DatabaseManager {
	private Map<String, Restoran> restoranlar;
	private Map<String, Musteri> musteriler;
	private Map<String, Rezervasyon> rezervasyonlar;
	
	public DatabaseManager() {
		restoranlar = new HashMap<>();
		musteriler = new HashMap<>();
		rezervasyonlar = new HashMap<>();
	}
	
	public void executeUpdate(String query) {
		// Basit bir şekilde INSERT, UPDATE, DELETE işlemlerini yöneten bir metot
		String[] parts = query.split(" ");
		String operation = parts[0].toUpperCase();
		String tableName = parts[2];
		
		if (operation.equals("INSERT")) {
			if (tableName.equals("Restoran")) {
				// Restoran ekleme işlemi
				String restoranID = parts[5].replace("'", "");
				String adi = parts[6].replace("'", "");
				String adresi = parts[7].replace("'", "");
				String telefon = parts[8].replace("'", "");
				int kapasite = Integer.parseInt(parts[9]);
				Restoran.Durum durum = Restoran.Durum.valueOf(parts[10].replace("'", ""));
				Restoran restoran = new Restoran(restoranID, adi, adresi, telefon, kapasite, durum);
				restoranlar.put(restoranID, restoran);
			} else if (tableName.equals("Musteri")) {
				// Musteri ekleme işlemi
				String musteriID = parts[5].replace("'", "");
				String isim = parts[6].replace("'", "");
				String soyisim = parts[7].replace("'", "");
				String telefon = parts[8].replace("'", "");
				String eposta = parts[9].replace("'", "");
				Musteri musteri = new Musteri(musteriID, isim, soyisim, telefon, eposta);
				musteriler.put(musteriID, musteri);
			} else if (tableName.equals("Rezervasyon")) {
				// Rezervasyon ekleme işlemi