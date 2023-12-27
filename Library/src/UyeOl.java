import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UyeOl extends JFrame{
    private JTextField isimField;
    private JTextField soyisimField;
    private JTextField tcField;
    private JTextField kullaniciAdiField;
    private JRadioButton erkekRadioButton;
    private JRadioButton kadinRadioButton;
    private JPasswordField sifreField;

    public UyeOl() {
        super("Üye Ol");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel isimLabel = new JLabel("İsim:");
        isimField = new JTextField();

        JLabel soyisimLabel = new JLabel("Soyisim:");
        soyisimField = new JTextField();

        JLabel tcLabel = new JLabel("TC Kimlik No:");
        tcField = new JTextField();

        JLabel kullaniciAdiLabel = new JLabel("Kullanıcı Adı:");
        kullaniciAdiField = new JTextField();

        JLabel cinsiyetLabel = new JLabel("Cinsiyet:");
        erkekRadioButton = new JRadioButton("Erkek");
        kadinRadioButton = new JRadioButton("Kadın");

        JLabel sifreLabel = new JLabel("Şifre:");
        sifreField = new JPasswordField();

        JButton uyeOlButton = new JButton("Üye Ol");

        setLayout(new BorderLayout());
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(20));

        box.add(isimLabel);
        box.add(isimField);
        box.add(soyisimLabel);
        box.add(soyisimField);
        box.add(kullaniciAdiLabel);
        box.add(kullaniciAdiField);
        box.add(tcLabel);
        box.add(tcField);

        // Cinsiyet için radio buttonları grup olarak belirleme
        ButtonGroup cinsiyetGrup = new ButtonGroup();
        cinsiyetGrup.add(erkekRadioButton);
        cinsiyetGrup.add(kadinRadioButton);

        box.add(cinsiyetLabel);
        box.add(erkekRadioButton);
        box.add(kadinRadioButton);
        box.add(sifreLabel);
        box.add(sifreField);

        box.add(Box.createVerticalStrut(20));
        box.add(uyeOlButton);

        uyeOlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uyeOl();
            }
        });

        add(box, BorderLayout.CENTER);
        setVisible(true);
    }

    private void uyeOl() {
        // Kullanıcı inputlarını alma
        String isim = isimField.getText().trim();
        String soyisim = soyisimField.getText().trim();
        String kullaniciAdi = kullaniciAdiField.getText().trim();
        String tc = tcField.getText().trim();
        String cinsiyet = erkekRadioButton.isSelected() ? "Erkek" : (kadinRadioButton.isSelected() ? "Kadın" : "");
        String sifre = new String(sifreField.getPassword());

        // Boş bırakılan yerler var mı kontrol etme
        if (isim.isEmpty() || soyisim.isEmpty() || kullaniciAdi.isEmpty() || tc.isEmpty() || cinsiyet.isEmpty() || sifre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurunuz.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            
        // TC numarası 11 haneli mi kontrol etme
        } else if (tc.length() != 11 || !tc.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "TC Kimlik Numarası 11 haneli rakam olmalıdır.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            
        } else {
                // Bilgileri dosyaya yazma
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("kullanicilar.txt", true))) {
                    writer.write(isim + ", " + soyisim + ", " + kullaniciAdi + ", " + tc + ", " + cinsiyet + ", " + sifre + "\n");
                    JOptionPane.showMessageDialog(this, "Kayıt işleminiz başarıyla gerçekleşmiştir.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                    // Pencereyi kapatma
                    dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Kayıt işlemi sırasında bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
        }
    }
}