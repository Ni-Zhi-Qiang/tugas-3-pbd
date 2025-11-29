/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurant.tugas3;

/**
 *
 * @nim     054128848
 * @nama    Ge Valent Nikolas
 */

import java.util.ArrayList;
import java.io.*;

public class Pesanan {
    private ArrayList<MenuItem> items;
    private ArrayList<Integer> quantities;
    private static final String STRUK_FILE = "struk_pesanan.txt";
    
    public Pesanan() {
        items = new ArrayList<>();
        quantities = new ArrayList<>();
    }
    
    public void tambahItem(MenuItem item, int quantity) {
        items.add(item);
        quantities.add(quantity);
    }
    
    public double hitungTotal() {
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            int quantity = quantities.get(i);
            
            if (item instanceof Diskon) {
                Diskon diskon = (Diskon) item;
                total += diskon.hitungHargaSetelahDiskon() * quantity;
            } else {
                total += item.getHarga() * quantity;
            }
        }
        return total;
    }
    
    public void simpanStrukKeFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STRUK_FILE))) {
            writer.println("=".repeat(50));
            writer.println("           STRUK PESANAN RESTORAN");
            writer.println("=".repeat(50));
            writer.printf("%-20s %-8s %-12s %s\n", "Item", "Qty", "Harga", "Subtotal");
            writer.println("-".repeat(50));
            
            for (int i = 0; i < items.size(); i++) {
                MenuItem item = items.get(i);
                int quantity = quantities.get(i);
                double subtotal;
                
                if (item instanceof Diskon) {
                    Diskon diskon = (Diskon) item;
                    subtotal = diskon.hitungHargaSetelahDiskon() * quantity;
                    writer.printf("%-20s %-8d Rp%-10.0f Rp%-10.0f (Diskon %.0f%%)\n",
                                 item.getNama(), quantity, diskon.hitungHargaSetelahDiskon(),
                                 subtotal, diskon.getPersentaseDiskon());
                } else {
                    subtotal = item.getHarga() * quantity;
                    writer.printf("%-20s %-8d Rp%-10.0f Rp%-10.0f\n",
                                 item.getNama(), quantity, item.getHarga(), subtotal);
                }
            }
            
            writer.println("-".repeat(50));
            writer.printf("%-40s: Rp%.0f\n", "TOTAL", hitungTotal());
            writer.println("=".repeat(50));
            writer.println("Terima kasih atas kunjungan Anda!");
            
            System.out.println("Struk berhasil disimpan ke file: " + STRUK_FILE);
        } catch (IOException e) {
            System.out.println("Error menyimpan struk: " + e.getMessage());
        }
    }
    
    public void tampilkanStruk() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("              STRUK PESANAN RESTORAN");
        System.out.println("=".repeat(60));
        System.out.printf("%-20s %-8s %-12s %s\n", "Item", "Qty", "Harga", "Subtotal");
        System.out.println("-".repeat(60));
        
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            int quantity = quantities.get(i);
            double subtotal;
            
            if (item instanceof Diskon) {
                Diskon diskon = (Diskon) item;
                subtotal = diskon.hitungHargaSetelahDiskon() * quantity;
                System.out.printf("%-20s %-8d Rp%-10.0f Rp%-10.0f\n",
                                 item.getNama(), quantity, diskon.hitungHargaSetelahDiskon(),
                                 subtotal);
                System.out.printf("  ↳ Diskon %.0f%%\n", diskon.getPersentaseDiskon());
            } else {
                subtotal = item.getHarga() * quantity;
                System.out.printf("%-20s %-8d Rp%-10.0f Rp%-10.0f\n",
                                 item.getNama(), quantity, item.getHarga(), subtotal);
            }
        }
        
        System.out.println("-".repeat(60));
        System.out.printf("%-40s: Rp%.0f\n", "TOTAL", hitungTotal());
        System.out.println("=".repeat(60));
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public void clear() {
        items.clear();
        quantities.clear();
    }
}
