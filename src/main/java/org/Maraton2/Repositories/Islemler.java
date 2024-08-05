package org.Maraton2.Repositories;

import org.Maraton2.Repositories.Entities.*;
import org.Maraton2.Repositories.Entities.Enums.Durum;
import org.Maraton2.Repositories.Entities.Enums.RestoranTipi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Islemler {
	private final DatabaseManager dbManager;
	private final Scanner scanner;
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	public Islemler() {
		dbManager = new DatabaseManager();
		scanner = new Scanner(System.in);
		// Restoranları rastgele oluşturup ekleme
		Restoran[] restoranlar = RestoranOlusturucu.rastgeleRestoranlarOlustur(20);
		for (Restoran restoran : restoranlar) {
			dbManager.restoranEkle(restoran);
		}
	}
	
	public void run() {
		// Ana menü döngüsü
		while (true) {
			System.out.println("Ana Menü:");
			System.out.println("1. Restoran İşlemleri");
			System.out.println("2. Müşteri İşlemleri");
			System.out.println("3. Rezervasyon İşlemleri");
			System.out.println("0. Çıkış Yap");
			
			int secim = scanner.nextInt();
			scanner.nextLine();
			
			switch (secim) {
				case 1:
					restoranIslemleriMenu();
					break;
				case 2:
					musteriIslemleriMenu();
					break;
				case 3:
					rezervasyonIslemleriMenu();
					break;
				case 0:
					System.out.println("Çıkılıyor...");
					return; // Programdan çıkış
				default:
					System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
			}
		}
	}
	
	private void restoranIslemleriMenu() {
		// Restoran işlemleri menüsü
		while (true) {
			System.out.println("Restoran İşlemleri:");
			System.out.println("1. Restoran Ekle");
			System.out.println("2. Restoran Sil");
			System.out.println("3. Restoranları Listele");
			System.out.println("4. Restoran Ara");
			System.out.println("0. Ana Menüye Dön");
			
			int secim = scanner.nextInt();
			scanner.nextLine();
			
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
					return; // Ana menüye dön
				default:
					System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
			}
		}
	}
	
	private void musteriIslemleriMenu() {
		// Müşteri işlemleri menüsü
		while (true) {
			System.out.println("Müşteri İşlemleri:");
			System.out.println("1. Müşteri Ekle");
			System.out.println("2. Müşteri Sil");
			System.out.println("3. Müşterileri Listele");
			System.out.println("4. Müşteri Ara");
			System.out.println("0. Ana Menüye Dön");
			
			int secim = scanner.nextInt();
			scanner.nextLine();
			
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
	
	private void rezervasyonIslemleriMenu() {
		// Rezervasyon işlemleri menüsü
		while (true) {
			System.out.println("Rezervasyon İşlemleri:");
			System.out.println("1. Rezervasyon Yap");
			System.out.println("2. Rezervasyonları Listele");
			System.out.println("0. Ana Menüye Dön");
			
			int secim = scanner.nextInt();
			scanner.nextLine();
			
			switch (secim) {
				case 1:
					rezervasyonYap();
					break;
				case 2:
					rezervasyonlariListele();
					break;
				case 0:
					return; // Ana menüye dön
				default:
					System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
			}
		}
	}
	
	private void restoranEkle() {
		// Restoran ekleme işlemi
		while (true) {
			System.out.println("Restoran ID:");
			String restoranID = scanner.nextLine();
			if (dbManager.restoranAra(restoranID) != null) {
				System.out.println("Bu ID'ye sahip bir restoran zaten mevcut. Lütfen başka bir ID girin.");
				continue;
			}
			System.out.println("Restoran Adı:");
			String restoranAdi = scanner.nextLine();
			System.out.println("Adres:");
			String adresi = scanner.nextLine();
			System.out.println("Telefon:");
			String telefon = girisTelefonNumarasi();
			System.out.println("Kapasite:");
			int kapasite = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Durum (ACIK, KAPALI):");
			String durumStr = scanner.nextLine();
			Durum durum = Durum.valueOf(durumStr.toUpperCase());
			System.out.println("Tip (BALIK_RESTAURANI, KEBAP_RESTAURANI, EV_YEMEKLERI):");
			String tipiStr = scanner.nextLine();
			RestoranTipi tipi = RestoranTipi.valueOf(tipiStr.toUpperCase());
			
			dbManager.restoranEkle(new Restoran(restoranID, restoranAdi, adresi, telefon, kapasite, durum, tipi));
			System.out.println("Restoran eklendi.");
			break;
		}
	}
	
	private void restoranSil() {
		// Restoran silme işlemi
		System.out.println("Silinecek Restoran ID:");
		String restoranID = scanner.nextLine();
		dbManager.restoranSil(restoranID);
		System.out.println("Restoran silindi.");
	}
	
	private void musteriEkle() {
		// Müşteri ekleme işlemi
		while (true) {
			System.out.println("Müşteri ID:");
			String musteriID = scanner.nextLine();
			if (dbManager.musteriAra(musteriID) != null) {
				System.out.println("Bu ID'ye sahip bir müşteri zaten mevcut. Lütfen başka bir ID girin.");
				continue;
			}
			System.out.println("İsim:");
			String isim = scanner.nextLine();
			System.out.println("Soyisim:");
			String soyisim = scanner.nextLine();
			System.out.println("Telefon:");
			String telefon = girisTelefonNumarasi();
			System.out.println("E-posta:");
			String eposta = scanner.nextLine();
			
			Musteri musteri = new Musteri(musteriID, isim, soyisim, telefon, eposta);
			if (dbManager.musteriEkle(musteri)) {
				System.out.println("Müşteri eklendi.");
				break;
			} else {
				System.out.println("Müşteri eklenemedi. Geçersiz e-posta adresi.");
			}
		}
	}
	
	private void musteriSil() {
		// Müşteri silme işlemi
		System.out.println("Silinecek Müşteri ID:");
		String musteriID = scanner.nextLine();
		dbManager.musteriSil(musteriID);
		System.out.println("Müşteri silindi.");
	}
	
	private void rezervasyonYap() {
		// Yeni bir rezervasyon yapma işlemi
		System.out.println("Rezervasyon ID:");
		String rezervasyonID = scanner.nextLine();
		
		// ID kontrolü
		if (dbManager.rezervasyonIDVarMi(rezervasyonID)) {
			System.out.println("Bu ID ile bir rezervasyon zaten mevcut. Lütfen farklı bir ID girin.");
			return;
		}
		
		System.out.println("Müşteri ID:");
		String musteriID = scanner.nextLine();
		
		// Müşteri ID kontrolü
		if (dbManager.musteriAra(musteriID) == null) {
			System.out.println("Bu ID'ye sahip müşteri bulunamadı. Lütfen geçerli bir müşteri ID girin.");
			return;
		}
		
		System.out.println("Restoran ID:");
		String restoranID = scanner.nextLine();
		
		// Restoran ID kontrolü
		if (dbManager.restoranAra(restoranID) == null) {
			System.out.println("Bu ID'ye sahip restoran bulunamadı. Lütfen geçerli bir restoran ID girin.");
			return;
		}
		
		LocalDateTime tarihSaat = null;
		boolean tarihSaatGecerli = false;
		
		while (!tarihSaatGecerli) {
			System.out.println("Tarih ve Saat (yyyy-MM-dd HH:mm):");
			String tarihSaatStr = scanner.nextLine();
			
			try {
				tarihSaat = LocalDateTime.parse(tarihSaatStr, DATE_TIME_FORMATTER);
				tarihSaatGecerli = true;
			} catch (DateTimeParseException e) {
				System.out.println("Geçersiz tarih ve saat formatı. Lütfen tekrar deneyin.");
			}
		}
		
		System.out.println("Kişi Sayısı:");
		int kisiSayisi = scanner.nextInt();
		scanner.nextLine();
		
		Rezervasyon rezervasyon = new Rezervasyon(rezervasyonID, musteriID, restoranID, tarihSaat, kisiSayisi);
		dbManager.rezervasyonYap(rezervasyon);
		System.out.println("Rezervasyon yapıldı.");
	}
	
	
	private void restoranlariListele() {
		// Restoranları listeleme işlemi
		List<Restoran> restoranlar = dbManager.restoranlariListele();
		if (restoranlar.isEmpty()) {
			System.out.println("Listelenecek restoran bulunmuyor.");
		} else {
			for (Restoran restoran : restoranlar) {
				System.out.println(restoran);
			}
		}
	}
	
	private void musterileriListele() {
		// Müşterileri listeleme işlemi
		List<Musteri> musteriler = dbManager.musterileriListele();
		if (musteriler.isEmpty()) {
			System.out.println("Listelenecek müşteri bulunmuyor.");
		} else {
			for (Musteri musteri : musteriler) {
				System.out.println(musteri);
			}
		}
	}
	
	private void rezervasyonlariListele() {
		// Rezervasyonları listeleme işlemi
		List<Rezervasyon> rezervasyonlar = dbManager.rezervasyonlariListele();
		if (rezervasyonlar.isEmpty()) {
			System.out.println("Listelenecek rezervasyon bulunmuyor.");
		} else {
			for (Rezervasyon rezervasyon : rezervasyonlar) {
				System.out.println(rezervasyon);
			}
		}
	}
	
	private void restoranAramaMenu() {
		// Restoran arama menüsü
		while (true) {
			System.out.println("Restoran Arama:");
			System.out.println("1. ID ile Ara");
			System.out.println("2. Ad ile Ara");
			System.out.println("3. Tip ile Ara");
			System.out.println("4. Durum ile Ara");  // Yeni eklenen seçenek
			System.out.println("0. Ana Menüye Dön");
			
			int secim = scanner.nextInt();
			scanner.nextLine();
			
			switch (secim) {
				case 1:
					restoranIDileAra();
					break;
				case 2:
					restoranAdiIleAra();
					break;
				case 3:
					restoranTipiIleAra();
					break;
				case 4:
					restoranDurumuIleAra();  // Yeni eklenen seçenek
					break;
				case 0:
					return; // Ana menüye dön
				default:
					System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
			}
		}
	}
	private void restoranDurumuIleAra() {
		// Durum ile arama
		System.out.println("Aranacak Restoran Durumu (ACIK, KAPALI):");
		String durumStr = scanner.nextLine();
		Durum durum;
		try {
			durum = Durum.valueOf(durumStr.toUpperCase());
		} catch (IllegalArgumentException e) {
			System.out.println("Geçersiz durum. Lütfen ACIK veya KAPALI giriniz.");
			return;
		}
		
		List<Restoran> restoranlar = dbManager.restoranDurumuIleAra(durum);
		if (restoranlar.isEmpty()) {
			System.out.println("Bu durumda restoran bulunamadı.");
		} else {
			for (Restoran restoran : restoranlar) {
				System.out.println(restoran);
			}
		}
	}

	private void musteriAramaMenu() {
		// Müşteri arama menüsü
		while (true) {
			System.out.println("Müşteri Arama:");
			System.out.println("1. ID ile Ara");
			System.out.println("2. İsim ile Ara");
			System.out.println("0. Ana Menüye Dön");
			
			int secim = scanner.nextInt();
			scanner.nextLine();
			
			switch (secim) {
				case 1:
					musteriIDileAra();
					break;
				case 2:
					musteriIsimIleAra();
					break;
				case 0:
					return; // Ana menüye dön
				default:
					System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
			}
		}
	}
	
	private void restoranIDileAra() {
		// ID ile arama
		System.out.println("Aranacak Restoran ID:");
		String restoranID = scanner.nextLine();
		Restoran restoran = dbManager.restoranAra(restoranID);
		if (restoran != null) {
			System.out.println(restoran);
		} else {
			System.out.println("Bu ID'ye sahip restoran bulunamadı.");
		}
	}
	
	private void restoranAdiIleAra() {
		// Adı ile arama
		System.out.println("Aranacak Restoran Adı:");
		String restoranAdi = scanner.nextLine();
		List<Restoran> restoranlar = dbManager.restoranAdiIleAra(restoranAdi);
		if (restoranlar.isEmpty()) {
			System.out.println("Bu isimle restoran bulunamadı.");
		} else {
			for (Restoran restoran : restoranlar) {
				System.out.println(restoran);
			}
		}
	}
	
	private void restoranTipiIleAra() {
		// Tipi ile arama
		System.out.println("Aranacak Restoran Tipi (BALIK_RESTAURANI, KEBAP_RESTAURANI, EV_YEMEKLERI):");
		String tipiStr = scanner.nextLine();
		RestoranTipi tipi = RestoranTipi.valueOf(tipiStr.toUpperCase());
		List<Restoran> restoranlar = dbManager.restoranTipiIleAra(tipi);
		if (restoranlar.isEmpty()) {
			System.out.println("Bu tipte restoran bulunamadı.");
		} else {
			for (Restoran restoran : restoranlar) {
				System.out.println(restoran);
			}
		}
	}
	
	private void musteriIDileAra() {
		// ID ile arama
		System.out.println("Aranacak Müşteri ID:");
		String musteriID = scanner.nextLine();
		Musteri musteri = dbManager.musteriAra(musteriID);
		if (musteri != null) {
			System.out.println(musteri);
		} else {
			System.out.println("Bu ID'ye sahip müşteri bulunamadı.");
		}
	}
	
	private void musteriIsimIleAra() {
		// İsim ile arama
		System.out.println("Aranacak Müşteri İsmi:");
		String isim = scanner.nextLine();
		List<Musteri> musteriler = dbManager.musteriIsimIleAra(isim);
		if (musteriler.isEmpty()) {
			System.out.println("Bu isimle müşteri bulunamadı.");
		} else {
			for (Musteri musteri : musteriler) {
				System.out.println(musteri);
			}
		}
	}
	
	private String girisTelefonNumarasi() {
		// Telefon numarası girişi
		String telefon;
		while (true) {
			System.out.println("Telefon (Örneğin: 0545-456-7890):");
			telefon = scanner.nextLine();
			if (telefon.matches("\\d{4}-\\d{3}-\\d{4}")) {
				break;
			} else {
				System.out.println("Geçersiz telefon numarası formatı. Lütfen tekrar deneyin.");
			}
		}
		return telefon;
	}
}