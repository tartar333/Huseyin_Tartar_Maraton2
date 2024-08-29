package org.Maraton2;


import org.Maraton2.Models.RestoranOlusturucu;
import org.Maraton2.Services.MusteriService;
import org.Maraton2.Services.RestoranService;
import org.Maraton2.Repositories.RestoranRepository;
import org.Maraton2.Repositories.MusteriRepository;
import org.Maraton2.Repositories.RezervasyonRepository;
import org.Maraton2.Services.RezervasyonService;

import java.util.Scanner;

public class Runner {
	private static final Scanner scanner = new Scanner(System.in);
	
	// Servislerin başlatılması
	private static final RestoranService restoranService = new RestoranService();
	private static final MusteriService musteriService = new MusteriService();
	private static final RezervasyonService rezervasyonService = new RezervasyonService();
	
	// Repositorylerin başlatılması
	private static final RestoranRepository restoranRepository = new RestoranRepository(scanner, restoranService);
	private static final MusteriRepository musteriRepository = new MusteriRepository(musteriService, scanner);
	private static final RezervasyonRepository rezervasyonRepository = new RezervasyonRepository(scanner, rezervasyonService, musteriService, restoranService);
	
	// RestoranOlusturucu
	private static final RestoranOlusturucu restoranOlusturucu = new RestoranOlusturucu(scanner, restoranService);
	
	public static void main(String[] args) {
		try {
			restoranOlusturucu.restoranOlustur();
		}
		catch (Exception e) {
			System.out.println("Restoranlar Oluşturulamadı");
		}
		while (true) {
			System.out.println("Ana Menüye Hoşgeldiniz...");
			System.out.println("1. Restoran İşlemleri");
			System.out.println("2. Müşteri İşlemleri");
			System.out.println("3. Rezervasyon İşlemleri");
			System.out.println("0. Çıkış Yap");
			System.out.print("Yapmak İstediğiniz İşlemi Seçin :");
			
			int secim = scanner.nextInt();
			scanner.nextLine();
			
			switch (secim) {
				case 1:
					restoranRepository.restoranIslemleriMenu();
					break;
				case 2:
					musteriRepository.musteriIslemleriMenu();
					break;
				case 3:
					rezervasyonRepository.rezervasyonIslemleriMenu();
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