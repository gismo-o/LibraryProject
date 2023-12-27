import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class main {

    public static void main(String[] args) {
        Kullanici kullanici = new Kullanici("Ahmet", "Yılmaz", "ahmet123", "11111111111", "Erkek", "sifre123");
        LibraryLogic girisEkran = new LibraryLogic(kullanici);
        girisEkran.setVisible(true); // Giriş ekranını görünür hale getirme

    	
		KitapGoruntule kitapGoruntule=new KitapGoruntule();
       
    	kitapGoruntule.kitapBilgileriDosyasi();
    	
    	
        // Kullanıcı bilgilerini dosyaya yazma
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("kullanicilar.txt"))) {
            writer.write(kullanici.getIsim() + "," + kullanici.getSoyisim() + "," + kullanici.getKullaniciAdi() + "," 
            			 + kullanici.getTc() + "," + kullanici.getCinsiyet()+ "," + kullanici.getSifre());
            writer.newLine();
            System.out.println("Kullanıcı bilgileri dosyaya yazıldı.");
        } catch (IOException e) {
            System.err.println("Dosya yazma hatası: " + e.getMessage());
        }
    }

}



