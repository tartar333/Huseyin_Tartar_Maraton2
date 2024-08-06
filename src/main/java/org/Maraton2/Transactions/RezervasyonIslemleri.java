package org.Maraton2.Transactions;

import org.Maraton2.Interfaces.IMusteriService;
import org.Maraton2.Interfaces.IRestoranService;
import org.Maraton2.Interfaces.IRezervasyonService;
import org.Maraton2.Models.Musteri;
import org.Maraton2.Models.Restoran;
import org.Maraton2.Models.Rezervasyon;
import org.Maraton2.Services.MusteriService;
import org.Maraton2.Services.RezervasyonService;
import org.Maraton2.Services.RestoranService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RezervasyonIslemleri {
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	private final Scanner scanner;
	private final IRezervasyonService rezervasyonService;
	private final IMusteriService musteriService;
	private final IRestoranService restoranService;
	
	public RezervasyonIslemleri(Scanner scanner, IRezervasyonService rezervasyonService, IMusteriService musteriService,
	                            IRestoranService restoranService) {
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
			
			int secim = getValidIntegerInput(); // Kullanıcının geçerli bir sayı girmesini sağlar.
			scanner.nextLine();
			
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
					return;
				default:
					System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
			}
		}
	}
	
	public void rezervasyonYap() {
		String rezervasyonID;
		// Kullanıcının geçerli bir rezervasyon ID'si girmesini sağlar.
		while (true) {
			System.out.println("Rezervasyon ID:");
			rezervasyonID = scanner.nextLine();
			if (rezervasyonService.rezervasyonIDVarMi(rezervasyonID)) {
				System.out.println("Bu ID ile zaten bir rezervasyon mevcut. Lütfen başka bir ID girin.");
			}
			else {
				break;
			}
		}
		
		String musteriID;
		Musteri musteri;
		// Kullanıcının geçerli bir müşteri ID'si girmesini sağlar.
		while (true) {
			System.out.println("Müşteri ID:");
			musteriID = scanner.nextLine();
			musteri = musteriService.musteriIDileAra(musteriID);
			if (musteri == null) {
				System.out.println("Müşteri bulunamadı. Lütfen geçerli bir müşteri ID girin.");
			}
			else {
				break;
			}
		}
		
		String restoranID;
		Restoran restoran;
		// Kullanıcının geçerli bir restoran ID'si girmesini sağlar.
		while (true) {
			System.out.println("Restoran ID:");
			restoranID = scanner.nextLine();
			restoran = restoranService.restoranIDileAra(restoranID);
			if (restoran == null) {
				System.out.println("Restoran bulunamadı. Lütfen geçerli bir restoran ID girin.");
			}
			else {
				// Restoranın kapasitesi doluysa kullanıcıyı bilgilendirir.
				if (restoran.getKapasite() <= 0) {
					System.out.println("Restoran kapasitesi dolu. Rezervasyon yapılamaz.");
				}
				else {
					break;
				}
			}
		}
		
		LocalDateTime rezervasyonTarihi;
		// Geçerli bir tarih ve saat formatı istenir.
		while (true) {
			System.out.println("Rezervasyon Tarihi ve Saati (yyyy-MM-dd HH:mm):");
			String rezervasyonTarihiStr = scanner.nextLine();
			try {
				rezervasyonTarihi = LocalDateTime.parse(rezervasyonTarihiStr, DATE_TIME_FORMATTER);
				break;
			}
			catch (DateTimeParseException e) {
				System.out.println("Tarih formatı hatalı. Lütfen belirtilen formatta girin.");
			}
		}
		
		Rezervasyon rezervasyon = new Rezervasyon(rezervasyonID, musteriID, restoranID, rezervasyonTarihi);
		rezervasyonService.rezervasyonEkle(rezervasyon); // Rezervasyonu ekler.
		restoranService.kapasiteAzalt(restoranID); // Restoran kapasitesini azaltır.
		System.out.println("Rezervasyon başarıyla eklendi.");
	}
	
	public void rezervasyonSil() {
		while (true) {
			System.out.println("Silmek istediğiniz rezervasyonun ID'sini girin:");
			String rezervasyonID = scanner.nextLine();
			if (rezervasyonService.rezervasyonIDVarMi(rezervasyonID)) {
				rezervasyonService.rezervasyonSil(rezervasyonID);
				System.out.println("Rezervasyon başarıyla silindi.");
				break;
			}
			else {
				System.out.println("Belirtilen ID ile bir rezervasyon bulunamadı. Lütfen geçerli bir ID girin.");
			}
		}
	}
	
	public void rezervasyonlariListele() {
		List<Rezervasyon> rezervasyonlar = rezervasyonService.rezervasyonlariListele();
		if (rezervasyonlar.isEmpty()) {
			System.out.println("Hiç rezervasyon bulunmuyor.");
		}
		else {
			for (Rezervasyon rezervasyon : rezervasyonlar) {
				System.out.println(rezervasyon);
			}
		}
	}
	
	private void rezervasyonAramaMenu() {
		while (true) {
			System.out.println("Rezervasyon Arama:");
			System.out.println("1. Tarihe Göre Rezervasyonları Listele");
			System.out.println("2. ID'e Göre Rezervasyonları Listele");
			System.out.println("0. Geri Dön");
			
			int secim = getValidIntegerInput(); // Kullanıcının geçerli bir sayı girmesini sağlar.
			scanner.nextLine();
			
			switch (secim) {
				case 1:
					rezervasyonTariheGoreListele(); // Tarihe göre rezervasyonları listeler.
					break;
				case 2:
					rezervasyonIDileAra(); // ID'e göre rezervasyonlar
				case 0:
					return;
				default:
					System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
			}
		}
	}
	
	private void rezervasyonTariheGoreListele() {
		LocalDateTime tarih;
		while (true) {
			System.out.println("Rezervasyon Tarihi (yyyy-MM-dd):");
			String tarihStr = scanner.nextLine();
			try {
				tarih = LocalDateTime.parse(tarihStr + "T00:00:00");
				break;
			}
			catch (DateTimeParseException e) {
				System.out.println("Tarih formatı hatalı. Lütfen belirtilen formatta girin.");
			}
		}
		List<Rezervasyon> rezervasyonlar =
				rezervasyonService.tarihileRezervasyonlariListele(tarih); // Tarihe göre rezervasyonları listeler.
		if (rezervasyonlar.isEmpty()) {
			System.out.println("Belirtilen tarihte rezervasyon bulunamadı.");
		}
		else {
			for (Rezervasyon rezervasyon : rezervasyonlar) {
				System.out.println(rezervasyon);
			}
		}
	}
	
	private void rezervasyonIDileAra() {
		while (true) {
			System.out.print("Rezervasyon id giriniz: ");
			String id = scanner.nextLine();
			try {
				id = String.valueOf(Integer.parseInt(id));
				Rezervasyon rezervasyon = rezervasyonService.rezervasyonIDileAra(id);
				if (rezervasyon == null) {
					System.out.println("Belirtilen id ile rezervasyon bulunamadı.");
				} else {
					System.out.println(rezervasyon);
				}
				break; // Doğru id girildiğinde döngüyü kır
			} catch (NumberFormatException e) {
				System.out.println("Yanlış id girdiniz. Lütfen tekrar deneyin.");
			}
		}
	}

	
	private int getValidIntegerInput() {
		while (true) {
			try {
				return scanner.nextInt(); // Kullanıcının geçerli bir sayı girmesini sağlar.
			}
			catch (InputMismatchException e) {
				System.out.println("Geçersiz giriş. Lütfen sayısal bir değer girin.");
				scanner.nextLine();
			}
		}
	}
}