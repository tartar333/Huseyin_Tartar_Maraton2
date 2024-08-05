package org.Maraton2.Transactions;

import org.Maraton2.Enums.Durum;
import org.Maraton2.Enums.RestoranTipi;
import org.Maraton2.Models.Restoran;
import org.Maraton2.Services.RestoranService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RestoranIslemleri {
	private final Scanner scanner;
	private final RestoranService restoranService;
	
	public RestoranIslemleri(Scanner scanner, RestoranService restoranService) {
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
					case 0:
						return; // Ana menüye dön
					default:
						System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Geçersiz giriş. Lütfen sayısal bir değer girin.");
				scanner.nextLine(); // Hatalı girişten sonra input buffer'ı temizle
			}
		}
	}
	
	public void restoranEkle() {
		System.out.println("Yeni Restoran ID:");
		String restoranID = scanner.nextLine();
		// ID kontrolü
		if (restoranService.restoranIDVarMi(restoranID)) {
			System.out.println("Bu ID ile zaten bir restoran mevcut. Lütfen başka bir ID girin.");
			return;
		}
		
		System.out.println("Restoran Adı:");
		String restoranAdi = scanner.nextLine();
		
		System.out.println("Restoran Adresi:");
		String adres = scanner.nextLine();
		
		System.out.println("Restoran Telefon Numarası (05 ile başlamalı ve 11 haneli):");
		String telefonNumarasi = scanner.nextLine();
		
		if (!isValidTelefonNumarasi(telefonNumarasi)) {
			System.out.println("Telefon numarası formatı hatalı. Lütfen 05 ile başlayan ve 11 haneli bir numara girin.");
			return;
		}
		
		System.out.println("Restoran Kapasitesi:");
		int kapasite = getValidIntegerInput();
		
		if (kapasite < 0) {
			System.out.println("Kapasite negatif olamaz.");
			return;
		}
		
		System.out.println("Restoran Tipi (1. BALIK_RESTAURANI, 2. KEBAP_RESTAURANI, 3. EV_YEMEKLERI):");
		RestoranTipi restoranTipi = getValidRestoranTipi();
		
		System.out.println("Restoran Durumu (1. ACIK, 2. KAPALI):");
		Durum durum = getValidDurum();
		
		Restoran restoran = new Restoran(restoranID, restoranAdi, adres, telefonNumarasi, kapasite, durum, restoranTipi);
		restoranService.restoranEkle(restoran);
		System.out.println("Restoran başarıyla eklendi.");
	}
	
	private void restoranSil() {
		System.out.println("Silinecek Restoranın ID'si:");
		String restoranID = scanner.nextLine();
		
		if (restoranService.restoranIDVarMi(restoranID)) {
			restoranService.restoranSil(restoranID);
			System.out.println("Restoran silindi.");
		} else {
			System.out.println("Bu ID ile bir restoran bulunamadı.");
		}
	}
	
	private void restoranlariListele() {
		List<Restoran> restoranlar = restoranService.restoranlariListele();
		if (restoranlar.isEmpty()) {
			System.out.println("Listelenecek restoran bulunamadı.");
		} else {
			for (Restoran restoran : restoranlar) {
				System.out.println(restoran);
			}
		}
	}
	
	private boolean isValidTelefonNumarasi(String telefonNumarasi) {
		return telefonNumarasi.matches("05\\d{9}");
	}
	
	private int getValidIntegerInput() {
		while (true) {
			try {
				return scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Geçersiz giriş. Lütfen sayısal bir değer girin.");
				scanner.nextLine(); // Hatalı girişten sonra input buffer'ı temizle
			}
		}
	}
	
	private RestoranTipi getValidRestoranTipi() {
		while (true) {
			try {
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
			} catch (InputMismatchException e) {
				System.out.println("Geçersiz giriş. Lütfen sayısal bir değer girin.");
				scanner.nextLine(); // Hatalı girişten sonra input buffer'ı temizle
			}
		}
	}
	
	private Durum getValidDurum() {
		while (true) {
			try {
				int durumSecim = getValidIntegerInput();
				switch (durumSecim) {
					case 1:
						return Durum.ACIK;
					case 2:
						return Durum.KAPALI;
					default:
						System.out.println("Geçersiz seçenek. Lütfen 1 veya 2 girin.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Geçersiz giriş. Lütfen sayısal bir değer girin.");
				scanner.nextLine(); // Hatalı girişten sonra input buffer'ı temizle
			}
		}
	}
}