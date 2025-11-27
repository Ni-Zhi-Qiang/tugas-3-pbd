/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurant.tugas3;

/**
 *
 * @author user
 */

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class MenuManager {
    private ArrayList<MenuItem> daftarMenu;
    private static final String FILE_NAME = "menu_restoran.txt";
    
    public MenuManager() {
        daftarMenu = new ArrayList<>();
        loadMenuFromFile(); // Load existing menu when object is created
    }
    
    public void tambahMenu(MenuItem menu) {
        daftarMenu.add(menu);
        System.out.println("Menu berhasil ditambahkan!");
    }
    
    public void hapusMenu(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= daftarMenu.size()) {
            throw new IndexOutOfBoundsException("Index menu tidak valid!");
        }
        MenuItem removed = daftarMenu.remove(index);
        System.out.println("Menu '" + removed.getNama() + "' berhasil dihapus!");
    }
    
    public void tampilkanSemuaMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("Menu restoran masih kosong!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("📋 DAFTAR MENU RESTORAN");
        System.out.println("=".repeat(80));
        System.out.printf("%-3s %-22s %-12s %-16s %s\n", "No", "Nama", "Harga", "Kategori", "Keterangan");
        System.out.println("-".repeat(80));
        
        for (int i = 0; i < daftarMenu.size(); i++) {
            System.out.printf("%-3d ", (i + 1));
            daftarMenu.get(i).tampilMenu(); // Polymorphism in action
        }
        System.out.println("=".repeat(80));
    }
    
    public MenuItem getMenu(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= daftarMenu.size()) {
            throw new IndexOutOfBoundsException("Index menu tidak valid!");
        }
        return daftarMenu.get(index);
    }
    
    public int getJumlahMenu() {
        return daftarMenu.size();
    }
    
    // File Operations - Save menu to file
    public void saveMenuToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (MenuItem menu : daftarMenu) {
                if (menu instanceof Makanan) {
                    Makanan makanan = (Makanan) menu;
                    writer.printf("MAKANAN;%s;%.2f;%s;%s\n", 
                                 makanan.getNama(), makanan.getHarga(), 
                                 makanan.getKategori(), makanan.getJenisMakanan());
                } else if (menu instanceof Minuman) {
                    Minuman minuman = (Minuman) menu;
                    writer.printf("MINUMAN;%s;%.2f;%s;%s\n", 
                                 minuman.getNama(), minuman.getHarga(), 
                                 minuman.getKategori(), minuman.getJenisMinuman());
                } else if (menu instanceof Diskon) {
                    Diskon diskon = (Diskon) menu;
                    writer.printf("DISKON;%s;%.2f;%s;%.2f\n", 
                                 diskon.getNama(), diskon.getHarga(), 
                                 diskon.getKategori(), diskon.getPersentaseDiskon());
                }
            }
            System.out.println("Menu berhasil disimpan ke file: " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error menyimpan menu ke file: " + e.getMessage());
        }
    }
    
    // File Operations - Load menu from file
    private void loadMenuFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("File menu tidak ditemukan. Membuat menu baru...");
            return;
        }
        
        try (Scanner scanner = new Scanner(file)) {
            int count = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                
                if (parts.length >= 5) {
                    String jenis = parts[0];
                    String nama = parts[1];
                    double harga = Double.parseDouble(parts[2]);
                    String kategori = parts[3];
                    
                    switch (jenis) {
                        case "MAKANAN":
                            Makanan makanan = new Makanan(nama, harga, kategori, parts[4]);
                            daftarMenu.add(makanan);
                            count++;
                            break;
                        case "MINUMAN":
                            Minuman minuman = new Minuman(nama, harga, kategori, parts[4]);
                            daftarMenu.add(minuman);
                            count++;
                            break;
                        case "DISKON":
                            double diskon = Double.parseDouble(parts[4]);
                            Diskon itemDiskon = new Diskon(nama, harga, kategori, diskon);
                            daftarMenu.add(itemDiskon);
                            count++;
                            break;
                    }
                }
            }
            System.out.println("Berhasil memuat " + count + " menu dari file.");
        } catch (FileNotFoundException e) {
            System.out.println("File menu tidak ditemukan: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error format data dalam file: " + e.getMessage());
        }
    }
}
