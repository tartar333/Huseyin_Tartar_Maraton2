package org.Maraton2.Transactions;

import org.Maraton2.Models.Musteri;
import org.Maraton2.Services.MusteriService;

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
			System.out.println("Müşteri İşlemleri:");
			System.out.println("1. Müşteri Ekle");
			System.out.println("2. Müşteri Sil");
			System.out.println("3. Müşterileri Listele");
			System.out.println("4. Müşteri Ara");
			System.out.println("0. Ana Menüye Dön");
			
			int secim = scanner.nextInt();
			scanner.nextLine(); // Yeni satırı temizle
			
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
		}
	}
	
	public void musteriEkle() {
		System.out.println("Yeni Müşteri ID:");
		String musteriID = scanner.nextLine();
		
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
		
		if (!telefonNumarasi.matches("05\\d{9}")) {
			System.out.println("Telefon numarası formatı hatalı. Lütfen 05 ile başlayan ve 11 haneli bir numara girin.");
			return;
		}
		
		System.out.println("Müşteri Mail Adresi:");
		String mail = scanner.nextLine();
		
		Musteri musteri = new Musteri(musteriID, ad, soyad, telefonNumarasi, mail);
		musteriService.musteriEkle(musteri);
		System.out.println("Müşteri başarıyla eklendi.");
	}

	private void musteriSil() {
		// Müşteri silme işlemi
		System.out.println("Silinecek Müşterinin ID'si:");
		String musteriID = scanner.nextLine();
		musteriService.musteriSil(musteriID);
		System.out.println("Müşteri silindi.");
	}
	
	private void musterileriListele() {
		// Müşterileri listeleme işlemi
		List<Musteri> musteriler = musteriService.musterileriListele();
		for (Musteri musteri : musteriler) {
			System.out.println(musteri);
		}
	}
	
	private void musteriAramaMenu() {
		// Müşteri arama menüsü
		while (true) {
			System.out.println("Müşteri Arama:");
			System.out.println("1. Müşteri ID ile Ara");
			System.out.println("2. Müşteri Adı ile Ara");
			System.out.println("0. Geri Dön");
			
			int secim = scanner.nextInt();
			scanner.nextLine(); // Yeni satırı temizle
			
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
		}
	}
	
	private void musteriIDileAra() {
		// Müşteri ID ile arama
		System.out.print("Müşteri ID: ");
		String musteriID = scanner.nextLine();
		Musteri musteri = musteriService.musteriAra(musteriID);
		if (musteri != null) {
			System.out.println(musteri);
		} else {
			System.out.println("Müşteri bulunamadı.");
		}
	}
	
	private void musteriAdiIleAra() {
		// Müşteri adı ile arama
		System.out.print("Müşteri Adı: ");
		String isim = scanner.nextLine();
		List<Musteri> musteriler = musteriService.musteriIsimIleAra(isim);
		for (Musteri musteri : musteriler) {
			System.out.println(musteri);
		}
	}
}