/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurant.tugas3;

/**
 *
 * @author user
 */

public class Diskon extends MenuItem {
    private double persentaseDiskon;
    
    public Diskon(String nama, double harga, String kategori, double persentaseDiskon) {
        super(nama, harga, kategori);
        this.persentaseDiskon = persentaseDiskon;
    }
    
    @Override
    public void tampilMenu() {
        System.out.printf("🎁  %-20s | Rp%-10.0f | %-15s | Diskon: %.0f%%\n", 
                         nama, harga, kategori, persentaseDiskon);
    }
    
    public double hitungHargaSetelahDiskon() {
        return harga - (harga * persentaseDiskon / 100);
    }
    
    // Getter and Setter
    public double getPersentaseDiskon() {
        return persentaseDiskon;
    }
    
    public void setPersentaseDiskon(double persentaseDiskon) {
        this.persentaseDiskon = persentaseDiskon;
    }
}
