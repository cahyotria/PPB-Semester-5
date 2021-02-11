package com.example.a09_calculator;

public class Calculator {
    public enum Operator {Tambah, Kurang, Bagi, Kali};

    public double tambah(double Angka1, double Angka2){
        return Angka1 + Angka2;
    }
    public double kurang(double Angka1, double Angka2){
        return Angka1 - Angka2;
    }
    public double bagi(double Angka1, double Angka2){
        return Angka1 / Angka2;
    }
    public double kali(double Angka1, double Angka2){
        return Angka1 * Angka2;
    }

}
