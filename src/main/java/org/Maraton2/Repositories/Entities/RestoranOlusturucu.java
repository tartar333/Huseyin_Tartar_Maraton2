package org.Maraton2.Repositories.Entities;

import org.Maraton2.Repositories.Entities.Enums.Durum;
import org.Maraton2.Repositories.Entities.Enums.RestoranTipi;
import java.util.Random;

public class RestoranOlusturucu {
	// İstanbul semtleri
	private static final String[] SEMTLER = {
			"Besiktas", "Kadıkoy", "Sisli", "Bakirkoy", "Fatih",
			"Uskudar", "Beyoglu", "Maltepe", "Atasehir", "Esenyurt",
			"Kagıthane", "Zeytinburnu", "Sariyer", "Tuzla", "Avcilar"
	};
	
	// Rastgele sayı üretimi için Random nesnesi
	private static final Random RANDOM = new Random();
	

	public static Restoran[] rastgeleRestoranlarOlustur(int sayi) {
		// Restoranları tutacak dizi
		Restoran[] restoranlar = new Restoran[sayi];
		
		// Verilen sayı kadar restoran oluştur
		for (int i = 0; i < sayi; i++) {
			// Restoran bilgilerini oluştur
			String id = String.valueOf(i + 1); // ID: 1, 2, 3, ...
			String adi = "Restoran " + (i + 1);
			
			String semt = SEMTLER[RANDOM.nextInt(SEMTLER.length)];
			String adresi = semt + "/İstanbul";
			
			// Telefon numarasını oluştur: 05 ile başlat ve kalan kısmı rastgele üret
			String telefon = "05" + String.format("%09d", RANDOM.nextInt(1000000000));
			
			int kapasite = 5 + RANDOM.nextInt(26); // 5 ile 30 arasında rastgele kapasite
			Durum durum = Durum.ACIK; // Varsayılan durum: ACIK
			RestoranTipi tipi = RestoranTipi.values()[RANDOM.nextInt(RestoranTipi.values().length)]; // Rastgele restoran tipi seç
			
			// Oluşturulan restoranı diziye ekle
			restoranlar[i] = new Restoran(id, adi, adresi, telefon, kapasite, durum, tipi);
		}
		
		return restoranlar;
	}
}