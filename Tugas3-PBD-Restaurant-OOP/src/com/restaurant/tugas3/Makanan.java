/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurant.tugas3;

/**
 *
 * @author user
 */

public class Makanan extends MenuItem {
    private String jenisMakanan;
    
    public Makanan(String nama, double harga, String kategori, String jenisMakanan) {
        super(nama, harga, kategori);
        this.jenisMakanan = jenisMakanan;
    }
    
    @Override
    public void tampilMenu() {
        System.out.printf("🍽️  %-20s | Rp%-10.0f | %-15s | Jenis: %s\n", 
                         nama, harga, kategori, jenisMakanan);
    }
    
    // Getter and Setter
    public String getJenisMakanan() {
        return jenisMakanan;
    }
    
    public void setJenisMakanan(String jenisMakanan) {
        this.jenisMakanan = jenisMakanan;
    }
}