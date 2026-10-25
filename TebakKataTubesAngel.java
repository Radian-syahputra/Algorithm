import java.util.Random;
import java.util.Scanner;

public class TebakKataTubesAngel {

    // =====================================================
    // 1. DATA
    // =====================================================

    // Daftar kata dan petunjuknya 
    static String[] daftarKata = {
        "komputer", "program", "java", "variabel", "perulangan"
    };

    static String[] daftarPetunjuk = {
        "Perangkat elektronik untuk mengolah data",
        "Kumpulan instruksi yang dijalankan komputer",
        "Bahasa pemrograman yang sedang kamu pakai",
        "Tempat menyimpan nilai dalam program",
        "Struktur kontrol untuk mengulang kode"
    };

    static int nyawaAwal = 6;

    // =====================================================
    // 2. METHOD BANTU
    // =====================================================

    // Membuat tampilan kata, contoh: "k _ m _ u t e r"
    // Huruf yang sudah ditebak ditampilkan, sisanya diganti "_"

    static String buatTampilan(String kata, String hurufDitebak) {
        String hasil = "";

        for (int i = 0; i < kata.length(); i++) {
            String huruf = String.valueOf(kata.charAt(i));

            if (hurufDitebak.contains(huruf)) {
                hasil = hasil + huruf + " ";
            } else {
                hasil = hasil + "_ ";
            }
        }

        return hasil.trim();
    }

    // Mengecek apakah input hanya berisi huruf a-z
    static boolean inputValid(String tebakan) {
        return tebakan.matches("[a-z]+");
    }

    // Menampilkan kondisi permainan saat ini
    static void tampilkanStatus(String tampilan, String petunjuk,
        int nyawa, String hurufSalah) {
        System.out.println("Kata     : " + tampilan);
        System.out.println("Petunjuk : " + petunjuk);
        System.out.println("Nyawa    : " + nyawa);
        System.out.println("Salah    : " + (hurufSalah.equals("") ? "-" : hurufSalah));
    }

    // =====================================================
    // 3. LOGIKA PERMAINAN
    // =====================================================

    // Menjalankan satu ronde. Mengembalikan true jika menang.
    static boolean mainSatuRonde(Scanner input) {

        // Pilih kata secara acak
        Random acak = new Random();
        int nomor = acak.nextInt(daftarKata.length);
        String kata = daftarKata[nomor];
        String petunjuk = daftarPetunjuk[nomor];

        // Data awal ronde
        int nyawa = nyawaAwal;
        String hurufDitebak = "";   // semua huruf yang sudah ditebak
        String hurufSalah = "";     // huruf yang salah saja
        boolean menang = false;

        // Ulangi selama masih punya nyawa dan belum menang
        while (nyawa > 0 && !menang) {

            String tampilan = buatTampilan(kata, hurufDitebak);
            tampilkanStatus(tampilan, petunjuk, nyawa, hurufSalah);

            System.out.print("Tebakanmu: ");
            String tebakan = input.nextLine().trim().toLowerCase();

            // Cek apakah input valid
            if (!inputValid(tebakan)) {
                System.out.println(">> Input tidak valid, gunakan huruf a-z.\n");
                continue;
            }

            if (tebakan.length() > 1) {
                // Pemain menebak seluruh kata
                if (tebakan.equals(kata)) {
                    menang = true;
                } else {
                    nyawa = nyawa - 1;
                    System.out.println(">> Salah! Bukan itu katanya.\n");
                }

            } else {
                // Pemain menebak satu huruf
                if (hurufDitebak.contains(tebakan)) {
                    System.out.println(">> Huruf itu sudah pernah ditebak.\n");
                    continue;
                }

                hurufDitebak = hurufDitebak + tebakan;

                if (kata.contains(tebakan)) {
                    System.out.println(">> Benar! Huruf ada di dalam kata.\n");

                    // Cek apakah semua huruf sudah terbuka
                    String tampilanBaru = buatTampilan(kata, hurufDitebak);
                    if (!tampilanBaru.contains("_")) {
                        menang = true;
                    }
                } else {
                    nyawa = nyawa - 1;
                    hurufSalah = hurufSalah + tebakan + " ";
                    System.out.println(">> Salah! Huruf tidak ada di dalam kata.\n");
                }
            }
        }

        // Tampilkan hasil ronde
        System.out.println("---------------------------------");
        if (menang) {
            System.out.println("SELAMAT, KAMU MENANG!");
        } else {
            System.out.println("KAMU KALAH.");
        }
        System.out.println("Katanya adalah: " + kata.toUpperCase());
        System.out.println("---------------------------------");

        return menang;
    }

    // =====================================================
    // 4. METHOD MAIN
    // =====================================================

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int jumlahMenang = 0;
        int jumlahKalah = 0;
        String mainLagi = "y";

        System.out.println("=== GAME TEBAK KATA ===");
        System.out.println("Tebak huruf satu per satu, atau ketik seluruh kata.");
        System.out.println("Kamu punya " + nyawaAwal + " nyawa.\n");

        while (mainLagi.equalsIgnoreCase("y")) {

            boolean hasil = mainSatuRonde(input);

            if (hasil) {
                jumlahMenang = jumlahMenang + 1;
            } else {
                jumlahKalah = jumlahKalah + 1;
            }

            System.out.println("Skor -> Menang: " + jumlahMenang
                    + " | Kalah: " + jumlahKalah);

            System.out.print("\nMain lagi? (y/n): ");
            mainLagi = input.nextLine().trim();
            System.out.println();
        }

        System.out.println("Terima kasih sudah bermain!");
        input.close();
    }
}