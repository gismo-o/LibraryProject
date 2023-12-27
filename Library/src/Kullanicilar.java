

class Kullanici {
    private String isim;
    private String soyisim;
    private String tc;
    private String cinsiyet;
    private String kullaniciAdi;
    private String sifre;

    public Kullanici(String isim, String soyisim, String kullaniciAdi, String tc, String cinsiyet, String sifre) {
        this.isim = isim;
        this.soyisim = soyisim;
        this.tc = tc;
        this.cinsiyet = cinsiyet;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
    }

    public String getIsim() {
        return isim;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public String getTc() {
        return tc;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }
}


