import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AnaEkran extends JFrame {
    public AnaEkran() {
        super("Ana Ekran");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Ana ekran bileşenleri ve işlemleri burada tanımlanabilir.
        
       
        setLocationRelativeTo(null); // Pencereyi ekranın ortasına yerleştirme
        Box box = Box.createVerticalBox(); // Dikey düzen oluştur
        box.add(Box.createVerticalStrut(50)); // Üst kısımda boşluk bırak

        JButton mevcutKitaplarButton=new JButton("Kitapları Görüntüle");
        JButton kitapEkleButton=new JButton("Kitap Ekle");
        JButton oduncKitapButton=new JButton("Ödünç Kitap Al");
        JButton cikisButton=new JButton("Çıkış");
        
        mevcutKitaplarButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
				KitapGoruntule kitapGoruntule = new KitapGoruntule();
                kitapGoruntule.setVisible(true);
        	}
        });
        
        kitapEkleButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
				KitapEkle kitapEkle = new KitapEkle();
                kitapEkle.setVisible(true);
        	}
        });
        
        oduncKitapButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
				OduncKitapAl oduncKitap = new OduncKitapAl();
                oduncKitap.setVisible(true);
        	}
        });
        
        cikisButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        });
       
        
        
        add(mevcutKitaplarButton);
        add(kitapEkleButton);
        add(oduncKitapButton);
        add(cikisButton);
        
        //butonları ortala
        mevcutKitaplarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        kitapEkleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        oduncKitapButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cikisButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        box.add(mevcutKitaplarButton);
        box.add(Box.createVerticalStrut(20)); // Butonlar arasında boşluk bırak
        box.add(kitapEkleButton);
        box.add(Box.createVerticalStrut(20));
        box.add(oduncKitapButton);
        box.add(Box.createVerticalStrut(20));
        box.add(cikisButton);

        add(box);
        
        
        
        
        
    }
    

}
