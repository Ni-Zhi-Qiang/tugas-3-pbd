/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurant.tugas3;

/**
 *
 * @author user
 */

public class Minuman extends MenuItem {
    private String jenisMinuman;
    
    public Minuman(String nama, double harga, String kategori, String jenisMinuman) {
        super(nama, harga, kategori);
        this.jenisMinuman = jenisMinuman;
    }
    
    @Override
    public void tampilMenu() {
        System.out.printf("🥤  %-20s | Rp%-10.0f | %-15s | Jenis: %s\n", 
                         nama, harga, kategori, jenisMinuman);
    }
    
    // Getter and Setter
    public String getJenisMinuman() {
        return jenisMinuman;
    }
    
    public void setJenisMinuman(String jenisMinuman) {
        this.jenisMinuman = jenisMinuman;
    }
}
