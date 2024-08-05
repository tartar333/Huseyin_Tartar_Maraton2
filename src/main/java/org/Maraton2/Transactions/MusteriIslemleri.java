package org.Maraton2.Transactions;

import org.Maraton2.Models.Musteri;
import org.Maraton2.Services.MusteriService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
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
				scanner.nextLine(); // Hatalı girişten sonra input buffer'ı temizle
			}
		}
	}
	
	public void musteriEkle() {
		System.out.println("Yeni Müşteri ID:");
		String musteriID = scanner.nextLine();
		scanner.nextLine();
		// ID kontrolü
		if (musteriService.musteriIDVarMi(musteriID)) {
			System.out.println("Bu ID ile zaten bir müşteri mevcut. Lütfen başka bir ID girin.");
			return;
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
		System.out.println("Silinecek Müşterinin ID'si:");
		String musteriID = scanner.nextLine();
		if (musteriService.musteriIDVarMi(musteriID)) {
			musteriService.musteriSil(musteriID);
			System.out.println("Müşteri silindi.");
		} else {
			System.out.println("Bu ID ile bir müşteri bulunamadı.");
		}
	}
	
	private void musterileriListele() {
		List<Musteri> musteriler = musteriService.musterileriListele();
		if (musteriler.isEmpty()) {
			System.out.println("Listelenecek müşteri bulunamadı.");
		} else {
			for (Musteri musteri : musteriler) {
				System.out.println(musteri);
			}
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
						scanner.nextLine();
						musteriIDileAra();
						break;
					case 2:
						scanner.nextLine();
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
		System.out.print("Müşteri ID: ");
		String musteriID = scanner.nextLine();
		Optional<Musteri> musteri = musteriService.musteriIDileAra(musteriID);
		if (musteri.isPresent()) { // isPresent() metodu, Optional nesnesinin bir değer içerip içermediğini kontrol eder ve bir boolean değer döner.
			System.out.println(musteri);
		} else {
			System.out.println("Müşteri bulunamadı.");
		}
	}
	
	private void musteriAdiIleAra() {
		System.out.print("Müşteri Adı: ");
		String isim = scanner.nextLine();
		List<Musteri> musteriler = musteriService.musteriIsimIleAra(isim);
		if (musteriler.isEmpty()) {
			System.out.println("Bu isimle müşteri bulunamadı.");
		} else {
			for (Musteri musteri : musteriler) {
				System.out.println(musteri);
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
}