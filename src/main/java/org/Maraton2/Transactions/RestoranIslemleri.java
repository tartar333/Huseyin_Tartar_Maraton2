package org.Maraton2.Transactions;

import org.Maraton2.Enums.Durum;
import org.Maraton2.Enums.RestoranTipi;
import org.Maraton2.Interfaces.IRestoranService;
import org.Maraton2.Models.Restoran;
import org.Maraton2.Services.RestoranService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RestoranIslemleri {
	private final Scanner scanner;
	private final IRestoranService restoranService;
	
	public RestoranIslemleri(Scanner scanner, IRestoranService restoranService) {
		this.scanner = scanner;
		this.restoranService = restoranService;
	}
	
	public void restoranIslemleriMenu() {
		while (true) {
			try {
				
				System.out.println("Restoran İşlemleri:");
				System.out.println("1. Restoran Ekle");
				System.out.println("2. Restoran Sil");
				System.out.println("3. Restoranları Listele");
				System.out.println("4. Restoran Ara");
				System.out.println("0. Ana Menüye Dön");
				
				int secim = getValidIntegerInput();
				
				switch (secim) {
					case 1:
						
						restoranEkle();
						break;
					case 2:
						
						restoranSil();
						break;
					case 3:
						
						restoranlariListele();
						break;
					case 4:
						
						restoranAramaMenu();
						break;
					case 0:
						return;
					default:
						System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Geçersiz giriş. Lütfen sayısal bir değer girin.");
				scanner.nextLine();
			}
		}
	}
	
	// Yeni restoran eklemek için kullanıcıdan bilgi alınır
	public void restoranEkle() {
		String restoranID;
		while (true) {
			System.out.println("Yeni Restoran ID:");
			restoranID = scanner.nextLine();
			// ID kontrolü: Aynı ID'ye sahip restoran olup olmadığını kontrol eder
			if (restoranService.restoranIDVarMi(restoranID)) {
				System.out.println("Bu ID ile zaten bir restoran mevcut. Lütfen başka bir ID girin.");
			}
			else {
				break;
			}
		}
		
		System.out.println("Restoran Adı:");
		String restoranAdi = scanner.nextLine();
		
		System.out.println("Restoran Adresi:");
		String adres = scanner.nextLine();
		
		String telefonNumarasi;
		while (true) {
			System.out.println("Restoran Telefon Numarası (05 ile başlamalı ve 11 haneli):");
			telefonNumarasi = scanner.nextLine();
			if (isValidTelefonNumarasi(telefonNumarasi)) {
				break;
			}
			else {
				System.out.println("Telefon numarası formatı hatalı. Lütfen 05 ile başlayan ve 11 haneli bir numara " + "girin.");
			}
		}
		
		int kapasite;
		while (true) {
			System.out.println("Restoran Kapasitesi:");
			kapasite = getValidIntegerInput();
			if (kapasite >= 0) {
				break;
			}
			else {
				System.out.println("Kapasite negatif olamaz.");
			}
		}
		
		RestoranTipi restoranTipi = getValidRestoranTipi();
		Durum durum = getValidDurum();
		
		Restoran restoran =
				new Restoran(restoranID, restoranAdi, adres, telefonNumarasi, kapasite, durum, restoranTipi);
		restoranService.restoranEkle(restoran);
		System.out.println("Restoran başarıyla eklendi.");
	}
	
	// Restoran silmek için kullanıcıdan ID alınır
	public void restoranSil() {
		while (true) {
			System.out.println("Silinecek Restoranın ID'si:");
			String restoranID = scanner.nextLine();
			if (restoranService.restoranIDVarMi(restoranID)) {
				restoranService.restoranSil(restoranID);
				System.out.println("Restoran silindi.");
				break;
			}
			else {
				System.out.println("Bu ID ile bir restoran bulunamadı. Lütfen geçerli bir ID girin.");
			}
		}
	}
	
	// Tüm restoranları listeleme
	public void restoranlariListele() {
		List<Restoran> restoranlar = restoranService.restoranlariListele();
		if (restoranlar.isEmpty()) {
			System.out.println("Listelenecek restoran bulunamadı.");
		}
		else {
			for (Restoran restoran : restoranlar) {
				System.out.println(restoran);
			}
		}
	}
	
	// Restoran arama menüsü
	public void restoranAramaMenu() {
		while (true) {
			System.out.println("Restoran Arama:");
			System.out.println("1. Restoran Tipine Göre Ara");
			System.out.println("2. Restoran Durumuna Göre Ara");
			System.out.println("3. Restoran ID'sine Göre Ara");
			System.out.println("0. Geri Dön");
			
			int secim = getValidIntegerInput();
			scanner.nextLine();
			
			switch (secim) {
				case 1:
					restoranTipineGoreAra();
					break;
				case 2:
					restoranDurumunaGoreAra();
					break;
				case 3:
					restoranIDyeGoreAra();
					break;
				case 0:
					return;
				default:
					System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
			}
		}
	}
	
	// Restoranları tipine göre arama
	public void restoranTipineGoreAra() {
		RestoranTipi restoranTipi = getValidRestoranTipi();
		List<Restoran> restoranlar = restoranService.restoranTipineGoreAra(restoranTipi);
		if (restoranlar.isEmpty()) {
			System.out.println("Belirtilen tipte restoran bulunamadı.");
		}
		else {
			for (Restoran restoran : restoranlar) {
				System.out.println(restoran);
			}
		}
	}
	
	// Restoranları durumuna göre arama
	public void restoranDurumunaGoreAra() {
		Durum durum = getValidDurum();
		List<Restoran> restoranlar = restoranService.restoranDurumunaGoreAra(durum);
		if (restoranlar.isEmpty()) {
			System.out.println("Belirtilen durumda restoran bulunamadı.");
		}
		else {
			for (Restoran restoran : restoranlar) {
				System.out.println(restoran);
			}
		}
	}
	
	// Restoranı ID'sine göre arama
	public void restoranIDyeGoreAra() {
		System.out.println("Aranacak Restoranın ID'si:");
		String restoranID = scanner.nextLine();
		Restoran restoran = restoranService.restoranIDileAra(restoranID);
		if (restoran == null) {
			System.out.println("Bu ID ile bir restoran bulunamadı.");
		}
		else {
			System.out.println(restoran);
		}
	}
	
	// Telefon numarasının formatını kontrol eden metot
	private boolean isValidTelefonNumarasi(String telefonNumarasi) {
		return telefonNumarasi.matches("05\\d{9}"); // Telefon numarası 05 ile başlamalı ve 11 haneli olmalı
	}
	
	// Sayısal girişlerin doğruluğunu kontrol eden metot
	private int getValidIntegerInput() {
		while (true) {
			try {
				return scanner.nextInt();
			}
			catch (InputMismatchException e) {
				System.out.println("Geçersiz giriş. Lütfen sayısal bir değer girin.");
				scanner.nextLine();
			}
		}
	}
	
	// Kullanıcıdan geçerli restoran tipini seçmesini sağlayan metot
	private RestoranTipi getValidRestoranTipi() {
		while (true) {
			System.out.println("Restoran Tipi (1. BALIK_RESTAURANI, 2. KEBAP_RESTAURANI, 3. EV_YEMEKLERI):");
			System.out.print("Lütfen Birini Seçiniz: ");
			int tipSecim = getValidIntegerInput();
			switch (tipSecim) {
				case 1:
					return RestoranTipi.BALIK_RESTAURANI;
				case 2:
					return RestoranTipi.KEBAP_RESTAURANI;
				case 3:
					return RestoranTipi.EV_YEMEKLERI;
				default:
					System.out.println("Geçersiz seçenek. Lütfen 1, 2 veya 3 girin.");
			}
		}
	}
	
	// Kullanıcıdan geçerli restoran durumunu seçmesini sağlayan metot
	private Durum getValidDurum() {
		while (true) {
			System.out.println("Restoran Durumu (1. ACIK, 2. KAPALI):");
			System.out.print("Lütfen Birini Seciniz :");
			int durumSecim = getValidIntegerInput();
			switch (durumSecim) {
				case 1:
					return Durum.ACIK;
				case 2:
					return Durum.KAPALI;
				default:
					System.out.println("Geçersiz seçenek. Lütfen 1 veya 2 girin.");
			}
		}
	}
}