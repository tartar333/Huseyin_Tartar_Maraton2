

Fazla metod tanımlamamın nedenleri programı geliştirmek, hata kontrolü, kodun modülerliği, kodun anlaşılabilirliği,
işlevsellik sağlama amacıyla yaptığım şeyler.

RestoranOlusturucu isimli bir sınıf tanımlamamın nedenleri de aynı sekilde sorumluluk ayrımı,
kod modulerliği, yeniden kullanılabilirlik, basitlik ve bakım kolaylığı, test edilebilirlik.
Restoran oluşturma işlemini ayrı bir bileşen olarak yönetmek için tasarladım.
 Bu, kodun modülerliğini, okunabilirliğini ve bakımını artırır. Ayrıca, bu tür bir sınıf kullanımı,
 uygulamanın genişletilmesi ve test edilmesini kolaylaştırır. Bu, yazılım mühendisliği prensiplerindendir.

 private String girisTelefonNumarasi() = Gecerli bir numara girisi olana kadar kullanıcıdan telefon numarası ister.
 Bu şekilde, uygulamanın kullanıcı tarafından girilen verilerin doğruluğunu güvence altına alır ve olası hataları önler.
 private void menudenDon() = Kullanıcıya uygulamanın ana menüsüne dönme veya çıkış yapma seçeneğini sunması
 ve modulerlik sağlamak için tasarladım.
 Bu metod, uygulamanın akışını kontrol eder ve kullanıcı etkileşimlerini yönetir.
 Kullanıcıdan alınan girişe göre uygulamanın davranışını belirler

 public void run () = Uygulamanın ana akışını ve kullanıcı etkileşimlerini yönetmek için tanımladım.
 Bu metod, uygulamanın tüm işlevlerini bir araya getirir ve kullanıcıdan gelen çeşitli komutlara göre
 uygun işlemleri gerçekleştirir. Aynı zamanda uygulamanın sürekli çalışmasını sağlayan bir döngü içerir ve
 geçersiz seçimleri işlemek için hata kontrolü sağlar. Bu yapı, uygulamanın kullanıcı dostu ve işlevsel olmasını sağlar.

rezervasyonIDVarMi(String rezervasyonID) = Unique ID olusturabilmek ve olası cakısmaları önlemek icin.

public void restoranKapasitesiniGuncelle(String restoranID, int azalanKapasite) = Her rezervasyon isleminde secilen
restoranın kapasitesini azaltabilmek icin yani kapasite kontrolü.

public boolean isEmailValid(String email) = Gecerli mail girilip girilmediğini kontrol edebilmek icin.

Musteri ve Restoran arama icin detaylı arama yapabilmek adına metodlar ekledim. Kullanıcı deneyimi iyileşti.

Bahsedilen bir çok işlemi alt menülere ayırdım, menü kullanımını kolaylaştırmak için.