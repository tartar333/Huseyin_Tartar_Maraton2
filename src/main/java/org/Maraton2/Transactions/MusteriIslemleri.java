package org.Maraton2.Transactions;

import org.Maraton2.Models.Musteri;
import org.Maraton2.Services.MusteriService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MusteriIslemleri {
	
	private final MusteriService musteriService;
	private final Scanner scanner;
	
	public MusteriIslemleri(MusteriService musteriService, Scanner scanner) {
		this.musteriService = musteriService;
		this.scanner = scanner;
	}
	
	public void musteriIslemleriMenu() {
		while (true) {
			try {
				System.out.println("Müşteri İşlemleri:");
				System.out.println("1. Müşteri Ekle");
				System.out.println("2. Müşteri Sil");
				System.out.println("3. Müşterileri Listele");
				System.out.println("4. Müşteri Ara");
				System.out.println("0. Ana Menüye Dön");
				
				int secim = getValidIntegerInput();
				
				switch (secim) {
					case 1:
						musteriEkle();
						break;
					case 2:
						musteriSil();
						break;
					case 3:
						musterileriListele();
						break;
					case 4:
						musteriAramaMenu();
						break;
					case 0:
						return; // Ana menüye dön
					default:
						System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Geçersiz giriş. Lütfen sayısal bir değer girin.");
				scanner.nextLine();
			}
		}
	}
	
	public void musteriEkle() {
		String musteriID;
		while (true) {
			System.out.println("Yeni Müşteri ID:");
			musteriID = scanner.nextLine();
			if (!isValidID(musteriID)) {
				System.out.println("Geçersiz ID formatı. Lütfen geçerli bir ID girin.");
				continue;
			}
			// ID kontrolü
			if (!musteriService.musteriIDVarMi(musteriID)) {
				break;
			} else {
				System.out.println("Bu ID ile zaten bir müşteri mevcut. Lütfen başka bir ID girin.");
			}
		}
		
		System.out.println("Müşteri Adı:");
		String ad = scanner.nextLine();
		
		System.out.println("Müşteri Soyadı:");
		String soyad = scanner.nextLine();
		
		System.out.println("Müşteri Telefon Numarası (05 ile başlamalı ve 11 haneli):");
		String telefonNumarasi = scanner.nextLine();
		
		if (!isValidTelefonNumarasi(telefonNumarasi)) {
			System.out.println("Telefon numarası formatı hatalı. Lütfen 05 ile başlayan ve 11 haneli bir numara girin.");
			return;
		}
		
		System.out.println("Müşteri Mail Adresi:");
		String mail = scanner.nextLine();
		
		if (!isValidMail(mail)) {
			System.out.println("Mail adresi formatı hatalı. Lütfen geçerli bir mail adresi girin.");
			return;
		}
		
		Musteri musteri = new Musteri(musteriID, ad, soyad, telefonNumarasi, mail);
		musteriService.musteriEkle(musteri);
		System.out.println("Müşteri başarıyla eklendi.");
	}
	
	private void musteriSil() {
		String musteriID;
		while (true) {
			System.out.println("Silinecek Müşterinin ID'si:");
			musteriID = scanner.nextLine();
			if (!isValidID(musteriID)) {
				System.out.println("Geçersiz ID formatı. Lütfen geçerli bir ID girin.");
				continue;
			}
			if (musteriService.musteriIDVarMi(musteriID)) {
				musteriService.musteriSil(musteriID);
				System.out.println("Müşteri silindi.");
				break;
			} else {
				System.out.println("Bu ID ile bir müşteri bulunamadı. Lütfen geçerli bir ID girin.");
			}
		}
	}
	
	private void musterileriListele() {
		List<Musteri> musteriler = musteriService.musterileriListele();
		for (Musteri musteri : musteriler) {
			System.out.println("Müşteri ID: " + musteri.getId() + ", İsim: " + musteri.getAdi() + ", Soyisim: " + musteri.getSoyadi() + ", Telefon: " + musteri.getTelefon() + ", Mail: " + musteri.getMail());
		}
	}
	
	private void musteriAramaMenu() {
		while (true) {
			try {
				System.out.println("Müşteri Arama:");
				System.out.println("1. Müşteri ID ile Ara");
				System.out.println("2. Müşteri Adı ile Ara");
				System.out.println("0. Geri Dön");
				
				int secim = getValidIntegerInput();
				
				switch (secim) {
					case 1:
						musteriIDileAra();
						break;
					case 2:
						musteriAdiIleAra();
						break;
					case 0:
						return; // Önceki menüye dön
					default:
						System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Geçersiz giriş. Lütfen sayısal bir değer girin.");
				scanner.nextLine(); // Hatalı girişten sonra input buffer'ı temizle
			}
		}
	}
	
	private void musteriIDileAra() {
		while (true) {
			System.out.println("Aranacak Müşteri ID:");
			String musteriID = scanner.nextLine();
			if (!isValidID(musteriID)) {
				System.out.println("Geçersiz ID formatı. Lütfen geçerli bir ID girin.");
				continue;
			}
			Musteri musteri = musteriService.musteriIDileAra(musteriID);
			if (musteri != null) {
				System.out.println("Müşteri ID: " + musteri.getId() + ", İsim: " + musteri.getAdi() + ", Soyisim: " + musteri.getSoyadi() + ", Telefon: " + musteri.getTelefon() + ", Mail: " + musteri.getMail());
				break;
			} else {
				System.out.println("Müşteri bulunamadı. Lütfen geçerli bir ID girin.");
			}
		}
	}
	
	private void musteriAdiIleAra() {
		while (true) {
			System.out.print("Müşteri Adı: ");
			String isim = scanner.nextLine();
			if (isim == null || isim.trim().isEmpty()) {
				System.out.println("Geçersiz isim formatı. Lütfen geçerli bir isim girin.");
				continue;
			}
			List<Musteri> musteriler = musteriService.musteriIsimIleAra(isim);
			if (musteriler.isEmpty()) {
				System.out.println("Bu isimle müşteri bulunamadı.");
			} else {
				for (Musteri musteri : musteriler) {
					System.out.println(musteri);
				}
				break;
			}
		}
	}
	
	private int getValidIntegerInput() {
		while (true) {
			try {
				return scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Geçersiz giriş. Lütfen sayısal bir değer girin.");
				scanner.nextLine();
			}
		}
	}
	
	private boolean isValidTelefonNumarasi(String telefonNumarasi) {
		return telefonNumarasi.matches("05\\d{9}");
	}
	
	private boolean isValidMail(String mail) {
		return mail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
	}
	
	private boolean isValidID(String id) {
		return id != null && !id.trim().isEmpty(); // Geçerlilik kontrolünü ihtiyaca göre genişletebilirsiniz
	}
}