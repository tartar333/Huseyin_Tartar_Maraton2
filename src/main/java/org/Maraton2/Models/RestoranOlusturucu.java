package org.Maraton2.Models;

import org.Maraton2.Enums.Durum;
import org.Maraton2.Enums.RestoranTipi;
import org.Maraton2.Services.RestoranService;

import java.util.Random;
import java.util.Scanner;

public class RestoranOlusturucu {
	private final Scanner scanner;
	private final RestoranService restoranService;
	private static final String[] RESTORAN_ADLARI =
			{"Lezzet Durağı", "Gurme Köşe", "Cafe Mavi", "Yemek Sarayı", "Simit Sarayı", "Lezzet Kosku" + "Gurme " +
					"Palace"};
	private static final RestoranTipi[] RESTORAN_TIPLERI = RestoranTipi.values();
	private static final String[] CADDELER = {"Cadde 1", "Cadde 2", "Cadde 3", "Cadde 4"};
	private static final String[] SEMTLER = {"Semt A", "Semt B", "Semt C", "Semt D"};
	private static final String[] SEHIRLER = {"Sehir X", "Sehir Y", "Sehir Z"};
	private static final Random RANDOM = new Random();
	
	public RestoranOlusturucu(Scanner scanner, RestoranService restoranService) {
		this.scanner = scanner;
		this.restoranService = restoranService;
	}
	
	private String rastgeleAdres() {
		// Rastgele adres oluşturma
		String cadde = CADDELER[RANDOM.nextInt(CADDELER.length)];
		String semt = SEMTLER[RANDOM.nextInt(SEMTLER.length)];
		String sehir = SEHIRLER[RANDOM.nextInt(SEHIRLER.length)];
		return cadde + ", " + semt + ", " + sehir;
	}
	
	private String rastgeleTelefon() {
		// Rastgele telefon numarası oluşturma
		StringBuilder telefon = new StringBuilder("05");
		for (int i = 0; i < 9; i++) {
			telefon.append(RANDOM.nextInt(10));
		}
		return telefon.toString();
	}
	
	public void restoranOlustur() {
		System.out.print("Oluşturmak istediğiniz restoran sayısı: ");
		int sayi = scanner.nextInt();
		scanner.nextLine();
		
		for (int i = 0; i < sayi; i++) {
			String restoranID = String.valueOf(i + 1); // ID'yi 1'den başlatıp artırıyoruz
			
			// Rastgele restoran adı, tipi, kapasite, adres ve telefon oluşturma
			String adi = RESTORAN_ADLARI[RANDOM.nextInt(RESTORAN_ADLARI.length)];
			RestoranTipi tipi = RESTORAN_TIPLERI[RANDOM.nextInt(RESTORAN_TIPLERI.length)];
			int kapasite = RANDOM.nextInt(50) + 1; // Kapasite 1 ila 50 arasında rastgele bir değer
			String adres = rastgeleAdres();
			String telefon = rastgeleTelefon();
			
			RestoranModel restoranModel = new RestoranModel(restoranID, adi, tipi, kapasite, Durum.ACIK, adres, telefon);
			restoranService.restoranEkle(restoranModel);
			System.out.println("Restoran eklendi: " + restoranModel);
		}
	}
	
}