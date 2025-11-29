/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.restaurant.tugas3;

/**
 *
 * @nim     054128848
 * @nama    Ge Valent Nikolas
 */

import java.util.Scanner;

public class Tugas3 {
    private static MenuManager menuManager;
    private static Pesanan pesanan;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        initializeSystem();
        runApplication();
    }
    
    private static void initializeSystem() {
        menuManager = new MenuManager();
        pesanan = new Pesanan();
        scanner = new Scanner(System.in);
        System.out.println("=== SISTEM MANAJEMEN RESTORAN ===");
        System.out.println("Program berhasil diinisialisasi!");
    }
    
    private static void runApplication() {
        boolean running = true;
        
        while (running) {
            tampilkanMenuUtama();
            String pilihan = scanner.nextLine();
            
            switch (pilihan) {
                case "1":
                    tambahMenuBaru();
                    break;
                case "2":
                    menuManager.tampilkanSemuaMenu();
                    break;
                case "3":
                    buatPesanan();
                    break;
                case "4":
                    tampilkanDanSimpanStruk();
                    break;
                case "5":
                    menuManager.saveMenuToFile();
                    break;
                case "6":
                    running = false;
                    System.out.println("Terima kasih telah menggunakan sistem!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid! Silakan pilih 1-6.");
            }
        }
        
        scanner.close();
    }
    
    private static void tampilkanMenuUtama() {
        System.out.println("\n=== MENU UTAMA ===");
        System.out.println("1. Tambah Menu Baru");
        System.out.println("2. Tampilkan Menu Restoran");
        System.out.println("3. Buat Pesanan");
        System.out.println("4. Tampilkan & Simpan Struk");
        System.out.println("5. Simpan Menu ke File");
        System.out.println("6. Keluar");
        System.out.print("Pilih menu: ");
    }
    
    private static void tambahMenuBaru() {
        System.out.println("\n=== TAMBAH MENU BARU ===");
        System.out.println("Pilih jenis menu:");
        System.out.println("1. Makanan");
        System.out.println("2. Minuman");
        System.out.println("3. Diskon");
        System.out.print("Pilihan: ");
        
        String jenis = scanner.nextLine();
        
        try {
            System.out.print("Masukkan nama: ");
            String nama = scanner.nextLine();
            
            System.out.print("Masukkan harga: ");
            double harga = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Masukkan kategori: ");
            String kategori = scanner.nextLine();
            
            switch (jenis) {
                case "1":
                    System.out.print("Masukkan jenis makanan: ");
                    String jenisMakanan = scanner.nextLine();
                    Makanan makanan = new Makanan(nama, harga, kategori, jenisMakanan);
                    menuManager.tambahMenu(makanan);
                    break;
                    
                case "2":
                    System.out.print("Masukkan jenis minuman: ");
                    String jenisMinuman = scanner.nextLine();
                    Minuman minuman = new Minuman(nama, harga, kategori, jenisMinuman);
                    menuManager.tambahMenu(minuman);
                    break;
                    
                case "3":
                    System.out.print("Masukkan persentase diskon (%): ");
                    double diskon = Double.parseDouble(scanner.nextLine());
                    Diskon itemDiskon = new Diskon(nama, harga, kategori, diskon);
                    menuManager.tambahMenu(itemDiskon);
                    break;
                    
                default:
                    System.out.println("Pilihan jenis menu tidak valid!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input harga/diskontidak valid! Harap masukkan angka.");
        }
    }
    
    private static void buatPesanan() {
        if (menuManager.getJumlahMenu() == 0) {
            System.out.println("Menu masih kosong! Silakan tambah menu terlebih dahulu.");
            return;
        }
        
        pesanan.clear();
        boolean memesan = true;
        
        System.out.println("\n=== PEMBUATAN PESANAN ===");
        menuManager.tampilkanSemuaMenu();
        
        while (memesan) {
            try {
                System.out.print("Masukkan nomor menu yang dipesan (0 untuk selesai): ");
                int nomorMenu = Integer.parseInt(scanner.nextLine());
                
                if (nomorMenu == 0) {
                    memesan = false;
                } else if (nomorMenu > 0 && nomorMenu <= menuManager.getJumlahMenu()) {
                    System.out.print("Masukkan jumlah: ");
                    int jumlah = Integer.parseInt(scanner.nextLine());
                    
                    if (jumlah > 0) {
                        MenuItem item = menuManager.getMenu(nomorMenu - 1);
                        pesanan.tambahItem(item, jumlah);
                        System.out.println(jumlah + " " + item.getNama() + " ditambahkan ke pesanan!");
                    } else {
                        System.out.println("Jumlah harus lebih dari 0!");
                    }
                } else {
                    System.out.println("Nomor menu tidak valid!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        if (!pesanan.isEmpty()) {
            System.out.println("Pesanan berhasil dibuat!");
        } else {
            System.out.println("Tidak ada pesanan yang dibuat.");
        }
    }
    
    private static void tampilkanDanSimpanStruk() {
        if (pesanan.isEmpty()) {
            System.out.println("Belum ada pesanan! Silakan buat pesanan terlebih dahulu.");
            return;
        }
        
        pesanan.tampilkanStruk();
        
        System.out.print("\nSimpan struk ke file? (y/n): ");
        String simpan = scanner.nextLine();
        if (simpan.equalsIgnoreCase("y")) {
            pesanan.simpanStrukKeFile();
        }
    }
}
