import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class KitapGoruntule extends JFrame {
	private JTable kitapTable;
	private DefaultTableModel tableModel;

	public KitapGoruntule() {
		super("Kitapları Göster");
		setSize(400, 300);
		setLocationRelativeTo(null); // Pencereyi ekranın ortasına yerleştirme
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Tablo başlıkları
		String[] columnNames = {"Kitap Adı", "Yazar", "Yayınevi", "Tür"};
		
		// Boş bir tablo modeli oluşturma
		tableModel = new DefaultTableModel(columnNames, 0);
		kitapTable = new JTable(tableModel);

		
		JScrollPane scrollPane = new JScrollPane(kitapTable);
		add(scrollPane, BorderLayout.CENTER);

		// İptal butonunu oluşturma ve ActionListener ekleyerek iptal işlemini tanımlama
		JButton iptalButton = new JButton("Ana Ekrana Dön");
		iptalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Pencereyi kapatma
			}
		});

		// Butonu pencerenin altına ekleyerek BorderLayout'u kullanma
		add(iptalButton, BorderLayout.SOUTH);

		kitapBilgileriniDosyadanAl();
	}

	public void kitapBilgileriDosyasi() {
		try (FileWriter writer = new FileWriter("kitaplar.txt")) {
			// kitap bilgilerini kitaplar.txt dosyasına yazdırma

			writer.write("Sefiller, Victor Hugo, Can, Roman\n");
			writer.write("Yabancı, Albert Camus, Can, Roman\n");
			writer.write("İnsan Neyle Yaşar, Lev N.Tolstoy, İş Bankası Kültür, Kurgu\n");
			writer.write("Dönüşüm, Franz Kafka, Can, Hikaye\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void kitapBilgileriniDosyadanAl() {
		try (BufferedReader reader = new BufferedReader(new FileReader("kitaplar.txt"))) {
			String satir;
			while ((satir = reader.readLine()) != null) {
				String[] kitapBilgileri = satir.split(",");
				tableModel.addRow(kitapBilgileri);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
