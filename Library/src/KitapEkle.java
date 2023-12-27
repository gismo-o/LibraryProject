
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class KitapEkle extends JFrame {
    private JTextField kitapAdiField;
    private JTextField yazarField;
    private JTextField yayineviField;
    private JTextField turField;

    public KitapEkle() {
        super("Kitap Ekle");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel kitapAdiLabel = new JLabel("Kitap Adı:");
        kitapAdiField = new JTextField();

        JLabel yazarLabel = new JLabel("Yazar:");
        yazarField = new JTextField();

        JLabel yayineviLabel = new JLabel("Yayınevi:");
        yayineviField = new JTextField();

        JLabel turLabel = new JLabel("Tür:");
        turField = new JTextField();

        JButton ekleButton = new JButton("Ekle");
        JButton iptalButton = new JButton("İptal");

        setLayout(new BorderLayout());
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(20));

        box.add(kitapAdiLabel);
        box.add(kitapAdiField);
        box.add(yazarLabel);
        box.add(yazarField);
        box.add(yayineviLabel);
        box.add(yayineviField);
        box.add(turLabel);
        box.add(turField);

        box.add(Box.createVerticalStrut(20));
        box.add(ekleButton);
        box.add(iptalButton);

        ekleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                kitapEkle();
            }
        });

        iptalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(box, BorderLayout.CENTER);
        setVisible(true);
    }

    private void kitapEkle() {
        String kitapAdi = kitapAdiField.getText().trim();
        String yazar = yazarField.getText().trim();
        String yayinevi = yayineviField.getText().trim();
        String tur = turField.getText().trim();

        if (kitapAdi.isEmpty() || yazar.isEmpty() || yayinevi.isEmpty() || tur.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurunuz.", "Uyarı", JOptionPane.WARNING_MESSAGE);
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("kitaplar.txt", true))) {
                writer.write(kitapAdi + ", " + yazar + ", " + yayinevi + ", " + tur + "\n");
                JOptionPane.showMessageDialog(this, "Kitap başarıyla eklendi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Kitap eklenirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
