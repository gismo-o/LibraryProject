import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class OduncKitapAl extends JFrame {
    private String secilenKitapAdi = "";
    private Set<String> oduncAlinanKitaplar = new HashSet<>();

    public OduncKitapAl() {
        super("Ödünç Kitap Al");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField aramaAlanı = new JTextField(20);
        JButton araButton = new JButton("Ara");
        JTable sonuclarTablosu = new JTable();
        JButton oduncAlButton = new JButton("Kitap Ödünç Al");
        JButton iptalButton = new JButton("İptal");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Kitap Adı:"));
        panel.add(aramaAlanı);
        panel.add(araButton);

        JScrollPane kaydirmaPaneli = new JScrollPane(sonuclarTablosu);
        kaydirmaPaneli.setPreferredSize(new Dimension(350, 150));

        add(panel, BorderLayout.NORTH);
        add(kaydirmaPaneli, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(oduncAlButton);
        buttonPanel.add(iptalButton);
        add(buttonPanel, BorderLayout.SOUTH);

        sonuclarTablosu.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = sonuclarTablosu.getSelectedRow();
            if (selectedRow != -1) {
                // Seçilen satırdaki kitap adını sakla
                secilenKitapAdi = (String) sonuclarTablosu.getValueAt(selectedRow, 0);
            }
        });

        araButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Arama butonuna tıklandığında secilenKitapAdi'yi temizle
                secilenKitapAdi = "";
                String arananKitapAdi = aramaAlanı.getText().trim();

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Kitap Adı");
                model.addColumn("Yazar");
                model.addColumn("Yayınevi");
                model.addColumn("Tür");

                try (BufferedReader reader = new BufferedReader(new FileReader("kitaplar.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(", ");
                        if (parts.length == 4) {
                            String kitapAdi = parts[0];
                            String yazar = parts[1];
                            String yayinevi = parts[2];
                            String tur = parts[3];

                            // Kitap adına göre filtreleme
                            if (arananKitapAdi.isEmpty() || kitapAdi.toLowerCase().contains(arananKitapAdi.toLowerCase())) {
                                model.addRow(new Object[]{kitapAdi, yazar, yayinevi, tur});
                            }
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(OduncKitapAl.this, "Dosya okuma hatası: " + ex.getMessage());
                }

                // Tabloyu temizle ve yeni verileri ekle
                sonuclarTablosu.setModel(model);
            }
        });

        oduncAlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Kitap seçili değilse uyarı ver
                if (secilenKitapAdi.isEmpty()) {
                    JOptionPane.showMessageDialog(OduncKitapAl.this, "Lütfen bir kitap seçin.");
                } else if (oduncAlinanKitaplar.contains(secilenKitapAdi)) {
                    JOptionPane.showMessageDialog(OduncKitapAl.this, secilenKitapAdi + " kitabı zaten ödünç alındı.");
                } else {
                    // Kitap ödünç alındı mesajını göster
                    JOptionPane.showMessageDialog(OduncKitapAl.this, secilenKitapAdi + " kitabı ödünç alındı!");

                    // Ödünç alınan kitapları set'e ekle
                    oduncAlinanKitaplar.add(secilenKitapAdi);

                    // Kitap bilgilerini dosyadan çek ve ödünç alınan kitabın bilgilerini dosyaya yaz
                    try (BufferedReader reader = new BufferedReader(new FileReader("kitaplar.txt"));
                         BufferedWriter writer = new BufferedWriter(new FileWriter("odunc_kitaplar.txt", true))) {

                        String satir;
                        while ((satir = reader.readLine()) != null) {
                            String[] kitapBilgileri = satir.split(", ");
                            if (kitapBilgileri.length == 4 && kitapBilgileri[0].equals(secilenKitapAdi)) {
                                // Ödünç alınan kitabın bilgilerini dosyaya yaz
                                writer.write(kitapBilgileri[0] + ", " + kitapBilgileri[1] + ", " + kitapBilgileri[2] + ", " + kitapBilgileri[3]);
                                writer.newLine();
                            }
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(OduncKitapAl.this, "Dosya işlemleri hatası: " + ex.getMessage());
                    }
                }
            }
        });

        iptalButton.addActionListener(new ActionListener() {
              
            public void actionPerformed(ActionEvent e) {
                // İptal butonuna tıklandığında pencereyi kapat
                dispose();
            }
        });
    }
}