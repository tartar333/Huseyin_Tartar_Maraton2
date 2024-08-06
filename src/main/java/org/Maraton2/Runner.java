package org.Maraton2;

import org.Maraton2.Interfaces.IMusteriService;
import org.Maraton2.Interfaces.IRestoranService;
import org.Maraton2.Interfaces.IRezervasyonService;
import org.Maraton2.Models.RestoranOlusturucu;
import org.Maraton2.Transactions.RestoranIslemleri;
import org.Maraton2.Transactions.MusteriIslemleri;
import org.Maraton2.Transactions.RezervasyonIslemleri;
import org.Maraton2.Repositories.MusteriRepository;
import org.Maraton2.Repositories.RezervasyonRepository;
import org.Maraton2.Repositories.RestoranRepository;
import org.Maraton2.Services.RestoranService;
import org.Maraton2.Services.MusteriService;
import org.Maraton2.Services.RezervasyonService;

import java.util.Scanner;

public class Runner {
	private static final Scanner scanner = new Scanner(System.in);
	// Repositories
	private static final RestoranRepository restoranRepository = new RestoranRepository();
	private static final MusteriRepository musteriRepository = new MusteriRepository();
	private static final RezervasyonRepository rezervasyonRepository = new RezervasyonRepository();
	
	// Services
	private static final IRestoranService restoranService = new RestoranService(restoranRepository);
	private static final IMusteriService musteriService = new MusteriService(musteriRepository);
	private static final IRezervasyonService rezervasyonService = new RezervasyonService(rezervasyonRepository);
	
	
	// RestoranOlusturucu
	private static final RestoranOlusturucu restoranOlusturucu = new RestoranOlusturucu(scanner, restoranService);
	
	public static void main(String[] args) {
		// Restoranlar oluşturuluyor
		restoranOlusturucu.restoranOlustur();
		
		// İşlem sınıflarının oluşturulması
		RestoranIslemleri restoranIslemleri = new RestoranIslemleri(scanner, restoranService);
		RezervasyonIslemleri rezervasyonIslemleri = new RezervasyonIslemleri(scanner, rezervasyonService, musteriService, restoranService);
		MusteriIslemleri musteriIslemleri = new MusteriIslemleri(musteriService, scanner);
		
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
					restoranIslemleri.restoranIslemleriMenu();
					break;
				case 2:
					musteriIslemleri.musteriIslemleriMenu();
					break;
				case 3:
					rezervasyonIslemleri.rezervasyonIslemleriMenu();
					break;
				case 0:
					System.out.println("Çıkılıyor...");
					return;
				default:
					System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
			}
		}
	}
}