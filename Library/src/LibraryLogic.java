import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LibraryLogic extends JFrame {
    private JTextField kullaniciAdiField;
    private JPasswordField sifreField;
    private Kullanici kullanici;

    public LibraryLogic(Kullanici kullanici) {
        super("Giriş Yap");
        setSize(400, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        this.kullanici = kullanici;

        JLabel kullaniciAdiLabel = new JLabel("Kullanıcı Adı:");
        kullaniciAdiField = new JTextField();
        kullaniciAdiLabel.setBounds(50, 30, 80, 25);
        kullaniciAdiField.setBounds(150, 30, 150, 25);

        JLabel sifreLabel = new JLabel("Şifre:");
        sifreField = new JPasswordField();
        sifreLabel.setBounds(50, 60, 80, 25);
        sifreField.setBounds(150, 60, 150, 25);

        JButton girisYapButton = new JButton("Giriş Yap");
        JButton uyeOlButton = new JButton("Üye Ol");

        girisYapButton.setBounds(150, 90, 80, 25);
        uyeOlButton.setBounds(240, 90, 80, 25);

        girisYapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                girisYap();
            }
        });

        uyeOlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UyeOl uyeOl = new UyeOl();
                uyeOl.setVisible(true);
            }
        });

        add(kullaniciAdiLabel);
        add(kullaniciAdiField);
        add(sifreLabel);
        add(sifreField);
        add(girisYapButton);
        add(uyeOlButton);

        setVisible(true);
    }

    private void girisYap() {
        String girilenKullaniciAdi = kullaniciAdiField.getText().trim();
        String girilenSifre = new String(sifreField.getPassword());

        if (kullaniciAdiVarMi(girilenKullaniciAdi, girilenSifre)) {
            JOptionPane.showMessageDialog(this, "Giriş başarılı.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            
            // Başarılı giriş durumunda ana ekranı göster
            AnaEkran anaEkran = new AnaEkran();
            anaEkran.setVisible(true);
            
            dispose(); // Giriş penceresini kapat
        } else {
            JOptionPane.showMessageDialog(this, "Kullanıcı adı veya şifre hatalı.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean kullaniciAdiVarMi(String girilenKullaniciAdi, String girilenSifre) {
        boolean kullaniciVar = false;
        try {
            FileReader fileReader = new FileReader("kullanicilar.txt"); // Kullanıcı bilgilerinin bulunduğu dosya adı
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(","); // Kullanıcı adı ve şifrenin virgülle ayrıldığını varsayalım
                String dosyaKullaniciAdi = parts[2].trim();
                String dosyaSifre = parts[5].trim();

                if (girilenKullaniciAdi.equals(dosyaKullaniciAdi) && girilenSifre.equals(dosyaSifre)) {
                    kullaniciVar = true;
                    break;
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return kullaniciVar;
    }
}



