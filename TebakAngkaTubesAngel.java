import java.util.Random;
import java.util.Scanner;

public class TebakAngkaTubesAngel {

    // Method Mengenarate Angka 1-100
    static int generateAngka(int batasAngka) {
        Random random = new Random();
        return random.nextInt(batasAngka) + 1;
    }


    // Ini Mengecek tebakan Dan angka yang ingin di tebaknya Angel
    static boolean evaluasiTebakan(int tebakan, int angkaRahasia) {
        if (tebakan < angkaRahasia) {
            System.out.println("Terlalu Kecil");
            return false;
        }else if (tebakan > angkaRahasia) {
            System.out.println("Terlalu Besar");
            return false;
        }else {
            System.out.println("Selamat! Tebakan Anda Benar");
            return true;
        }
    }

    // Main Fungsi 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Deklarasi
        int pilihan;
        int batasAngka;
        int angkaRahasia;
        int tebakan = 0;
        int percobaan = 0;

        System.out.println("=== PERMAINAN TEBAK ANGKA ===");
        System.out.println("1. Easy (1-50)");
        System.out.println("2. Medium (1-100)");
        System.out.println("3. Hard (1-500)");
        System.out.print("Pilih tingkat kesulitan: ");

        pilihan = scanner.nextInt();
        // Mementukan Pilihan Kesulitan
        switch (pilihan) {
            case 1:
                batasAngka = 50;
                break;
        
            case 2:
                batasAngka = 100;
                break;
            case 3 :
                batasAngka = 500;
                break;

            default :
                System.out.println("Pilihan Tidak Valid");
                scanner.close();
                return;
        }

        // Menebak Angkanya
        angkaRahasia = generateAngka(batasAngka);
        System.out.println("\nTebak Angka Dari 1 Sampai" + batasAngka);

        // Lopping Sampai Tebakan Benar 
        do{
            System.out.println("Masukkan Angka Tebakan Anda : ");
            tebakan = scanner.nextInt();

            percobaan++;

            evaluasiTebakan(tebakan, angkaRahasia);
        }while(tebakan != angkaRahasia);

        System.out.println("Jumlah percobaan: " + percobaan);

        scanner.close();
    }
}