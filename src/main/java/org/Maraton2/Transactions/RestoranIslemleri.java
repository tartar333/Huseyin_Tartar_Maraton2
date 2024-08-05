package org.Maraton2.Transactions;

import org.Maraton2.Enums.Durum;
import org.Maraton2.Enums.RestoranTipi;
import org.Maraton2.Models.Restoran;
import org.Maraton2.Services.RestoranService;
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
			System.out.println("Restoran İşlemleri:");
			System.out.println("1. Restoran Ekle");
			System.out.println("2. Restoran Sil");
			System.out.println("3. Restoranları Listele");
			System.out.println("0. Ana Menüye Dön");
			
			int secim = scanner.nextInt();
			scanner.nextLine(); // Yeni satırı temizle
			
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
		
		if (!telefonNumarasi.matches("05\\d9}")) {
			System.out.println("Telefon numarası formatı hatalı. Lütfen 05 ile başlayan ve 11 haneli bir numara girin.");
			return;
		}
		
		System.out.println("Restoran Kapasitesi:");
		int kapasite = scanner.nextInt();
		scanner.nextLine(); // Yeni satırı temizle
		
		if (kapasite < 0) {
			System.out.println("Kapasite negatif olamaz.");
			return;
		}
		
		Restoran restoran = new Restoran(restoranID, restoranAdi, adres, telefonNumarasi, kapasite);
		restoranService.restoranEkle(restoran);
		System.out.println("Restoran başarıyla eklendi.");
	}
	
	private void restoranSil() {
		System.out.println("Silinecek Restoranın ID'si:");
		String restoranID = scanner.nextLine();
		restoranService.restoranSil(restoranID);
		System.out.println("Restoran silindi.");
	}
	
	private void restoranlariListele() {
		List<Restoran> restoranlar = restoranService.restoranlariListele();
		for (Restoran restoran : restoranlar) {
			System.out.println(restoran);
		}
	}
}