package org.Maraton2.Transactions;

import org.Maraton2.Models.Musteri;
import org.Maraton2.Models.Restoran;
import org.Maraton2.Models.Rezervasyon;
import org.Maraton2.Services.MusteriService;
import org.Maraton2.Services.RezervasyonService;
import org.Maraton2.Services.RestoranService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class RezervasyonIslemleri {
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	private final Scanner scanner;
	private final RezervasyonService rezervasyonService;
	private final MusteriService musteriService;
	private final RestoranService restoranService;
	
	public RezervasyonIslemleri(Scanner scanner, RezervasyonService rezervasyonService, MusteriService musteriService,
	                            RestoranService restoranService) {
		this.scanner = scanner;
		this.rezervasyonService = rezervasyonService;
		this.musteriService = musteriService;
		this.restoranService = restoranService;
	}
	
	public void rezervasyonIslemleriMenu() {
		while (true) {
			System.out.println("Rezervasyon İşlemleri:");
			System.out.println("1. Rezervasyon Ekle");
			System.out.println("2. Rezervasyon Sil");
			System.out.println("3. Rezervasyonları Listele");
			System.out.println("4. Rezervasyon Ara");
			System.out.println("0. Ana Menüye Dön");
			
			int secim = scanner.nextInt();
			scanner.nextLine(); // Yeni satırı temizle
			
			switch (secim) {
				case 1:
					rezervasyonYap();
					break;
				case 2:
					rezervasyonSil();
					break;
				case 3:
					rezervasyonlariListele();
					break;
				case 4:
					rezervasyonAramaMenu();
					break;
				case 0:
					return; // Ana menüye dön
				default:
					System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
			}
		}
	}
	
	public void rezervasyonYap() {
		System.out.println("Rezervasyon ID:");
		String rezervasyonID = scanner.nextLine();
		
		// ID kontrolü
		if (rezervasyonService.rezervasyonIDVarMi(rezervasyonID)) {
			System.out.println("Bu ID ile zaten bir rezervasyon mevcut. Lütfen başka bir ID girin.");
			return;
		}
		
		System.out.println("Müşteri ID:");
		String musteriID = scanner.nextLine();
		Musteri musteri = musteriService.musteriAra(musteriID);
		if (musteri == null) {
			System.out.println("Müşteri bulunamadı.");
			return;
		}
		
		System.out.println("Restoran ID:");
		String restoranID = scanner.nextLine();
		Restoran restoran = restoranService.restoranIDileAra(restoranID);
		if (restoran == null) {
			System.out.println("Restoran bulunamadı.");
			return;
		}
		
		// Restoranın kapasitesi kontrolü
		if (restoran.getKapasite() <= 0) {
			System.out.println("Restoran kapasitesi dolu. Rezervasyon yapılamaz.");
			return;
		}
		
		System.out.println("Rezervasyon Tarihi ve Saati (yyyy-MM-dd HH:mm):");
		String rezervasyonTarihiStr = scanner.nextLine();
		LocalDateTime rezervasyonTarihi;
		try {
			rezervasyonTarihi = LocalDateTime.parse(rezervasyonTarihiStr, DATE_TIME_FORMATTER);
		} catch (DateTimeParseException e) {
			System.out.println("Tarih formatı hatalı. Lütfen belirtilen formatta girin.");
			return;
		}
		
		Rezervasyon rezervasyon = new Rezervasyon(rezervasyonID, musteriID, restoranID, rezervasyonTarihi);
		rezervasyonService.rezervasyonEkle(rezervasyon);
		System.out.println("Rezervasyon yapıldı.");
		
		// Restoran kapasitesini güncelle
		restoranService.restoranKapasitesiniGuncelle(restoranID, restoran.getKapasite() - 1);
	}
	
	private void rezervasyonSil() {
		System.out.println("Silinecek Rezervasyon ID'si:");
		String rezervasyonID = scanner.nextLine();
		rezervasyonService.rezervasyonSil(rezervasyonID);
		System.out.println("Rezervasyon silindi.");
	}
	
	private void rezervasyonlariListele() {
		List<Rezervasyon> rezervasyonlar = rezervasyonService.rezervasyonlariListele();
		for (Rezervasyon rezervasyon : rezervasyonlar) {
			System.out.println(rezervasyon);
		}
	}
	
	private void rezervasyonAramaMenu() {
		while (true) {
			System.out.println("Rezervasyon Arama:");
			System.out.println("1. Rezervasyon ID ile Ara");
			System.out.println("0. Geri Dön");
			
			int secim = scanner.nextInt();
			scanner.nextLine(); // Yeni satırı temizle
			
			switch (secim) {
				case 1:
					rezervasyonIDileAra();
					break;
				case 0:
					return; // Önceki menüye dön
				default:
					System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
			}
		}
	}
	
	private void rezervasyonIDileAra() {
		System.out.println("Rezervasyon ID:");
		String rezervasyonID = scanner.nextLine();
		boolean rezervasyon = rezervasyonService.rezervasyonIDVarMi(rezervasyonID);
		if (rezervasyon == true) {
			System.out.println(rezervasyon);
		} else {
			System.out.println("Rezervasyon bulunamadı.");
		}
	}
}