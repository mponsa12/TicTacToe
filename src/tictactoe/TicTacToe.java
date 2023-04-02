/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.Random;
import java.util.Scanner;

/**
 * Joc del tictactoe programat amb diversos mètodes.
 * @author Montse
 * @version 02.04.2023
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[][] tauler = new int[3][3];
        imprimirTauler(tauler);
        iniciJoc(tauler);
        System.out.println("");
        System.out.println("");
        imprimirTauler(tauler);
        while(true) {
            tirJugador(tauler);
            imprimirTauler(tauler);
            tirContrincant(tauler);
            imprimirTauler(tauler);
            
            int resultat = determinarGuanyador(tauler);
            if (resultat == -1) {
                } else if (resultat == 0) {
                    System.out.println("Ha guanyat el contrincant");
                    break;
                } else if (resultat == 1) {
                    System.out.println("Ha guanyat el jugador");
                    break;
                }  
        }                
                
    }
    
    /**
     * S'inicialitza el tauler de joc a "-N", on N és la llargada/amplada del tauler
     * El tauler conté "-N" si és una casella buida, "0" si és una casella amb la fitxa del contrincant i "1" si és una casella amb la fitxa del jugador
     * És a dir, aquest mètode marca totes les cel·les del tauler com a buides.
     * @param taulerJoc 
     */
    public static void iniciJoc(int[][] taulerJoc) {
        for(int i = 0; i < taulerJoc.length; i++) {
            for(int j = 0; j < taulerJoc.length; j++) {
                taulerJoc[i][j] = -3;
            }
        }
    }
    
    /**
     * Imprimeix el tauler del joc
     * L'amplada del tauler ha de ser igual a la seva llargada (tauler quadrat)
     * @param taulerJoc 
     */
    public static void imprimirTauler(int[][] taulerJoc) {  
        System.out.println("  1 2 3 ");
        for(int i = 0; i < taulerJoc.length; i++) {
            //System.out.println(" ______");            
            System.out.print("| ");
            for(int j = 0; j < taulerJoc.length; j++) {
                System.out.print(crossOrCircle(taulerJoc[i][j]) + " ");
            }
            System.out.print("| "+(i+1));
            System.out.println("");
        }
        //System.out.println(" ______");
    }
    
    /**
     * Retorna diferents caràcters segons el paràmetre 'i' del mètode
     * 'O' si és del jugador contrincant
     * 'X' si és el jugador local
     * '_' si la casella està buida
     * @param i contingut element del tauler joc
     * @return Símbol '_' si i=-3; símbol 'O' si i=0; símbol 'X' si i!=0;
     */
    public static char crossOrCircle(int i){
        if(i==-3) return '_';
        return (i==0)?'O':'X';
    }
    
    /**
     * Tradueix els elements del tauler en forma bidimensional
     * 
     * y,x -> retorn
     * 0,0 -> 0
     * 0,1 -> 1
     * 0,2 -> 2
     * ...
     * 1,0 -> 3
     * 1,1 -> 4
     * 1,2 -> 5
     * ...
     * 2,0 -> 6
     * 2,1 -> 7
     * 2,2 -> 8
     * 
     * @param x
     * @param y
     * @return 
     */
    private static int bidimensionalToUnidimensional(int x, int y){
        return (y-1)*3 + x;
    }    
    
    /**
     * Prepara el tir del Contrincant.
     * Assigna de forma aleatoria unes coordenades del tauler [x,y] amb contingut -3 i ho canvia per 0.
     * @param taulerJoc 
     */
    public static void tirContrincant(int[][] taulerJoc){
        System.out.println("");
        System.out.println("TIR CONTRINCANT");
        
        int random_x;
        int random_y;
        
        Random rand = new Random();
        
        do {
            random_x = rand.nextInt(3);
            random_y = rand.nextInt(3);
        } while(taulerJoc[random_x][random_y] != -3);
        
        taulerJoc[random_x][random_x]=0; // 0 = 'O', és a dir, la màquina        
    }
    
    /**
     * Prepara el tir del jugador local.
     * Demana per teclat que li escrivim dues coordenades [x,y], les quals tinguin com a valor del contingut -3 i canvia el seu valor per 1.
     * @param taulerJoc 
     */
    public static void tirJugador(int[][] taulerJoc){
        System.out.println("");
        System.out.println("TIR JUGADOR");
        
        Scanner sc = new Scanner(System.in);    //System.in is a standard input stream  
        System.out.print("[X] Entra la columna (1-3)");
        int x = sc.nextInt();
        System.out.print("[Y] Entra la fila (1-3)");
        int y = sc.nextInt();
        
        taulerJoc[y-1][x-1]=1; // 1 = 'X', és a dir, el jugador 
    }
    
    /**
     * Revisa totes les files (F), columnes (C) i diagonals (D) del tauler per determinar si un jugador ha guanyat o no.
     * 
     * El tauler ha de tenir una llargada/amplada fixa, que anomenem "N".
     * Un jugador guanya quan aconsegueix tenir N caselles seguides en qualsevol fila, columna o diagonal.
     * El contrincant guanya quan la suma dels valors d'una fila, columna o diagonal dóna exactament 0.
     * El jugador guanya quan la suma dels valors d'una fila, columna o diagonal dóna exactament N.
     * 
     * @param taulerJoc que representa les cel·les del tauler de joc, de mida NxN. 
     * @return "-1" si no guanya ningú; "0" si guanya el contrincant; i "1" si guanya el jugador
     */
    
    /*
    public static int determinarGuanyador(int[][] taulerJoc){
        //TODO
        throw new UnsupportedOperationException();
    }
    */
    
    public static int determinarGuanyador(int[][] taulerJoc){
        int N = taulerJoc.length;

        // Comprova files i columnes
        for (int i = 0; i < N; i++) {
            int sumaFila = 0;
            int sumaColumna = 0;
            for (int j = 0; j < N; j++) {
                sumaFila += taulerJoc[i][j];
                sumaColumna += taulerJoc[j][i];
            }
            if (sumaFila == N || sumaColumna == N) {
                return 1; // si guanya el jugador
            } else if (sumaFila == 0 || sumaColumna == 0) {
                return 0; // si guanya el contrincant
            }
        }

        // Comprova les diagonals
        int sumaDiagonal1 = 0;
        int sumaDiagonal2 = 0;
        for (int i = 0; i < N; i++) {
            sumaDiagonal1 += taulerJoc[i][i];
            sumaDiagonal2 += taulerJoc[i][N-i-1];
        }
        if (sumaDiagonal1 == N || sumaDiagonal2 == N) {
            return 1; // si guanya el jugador
        } else if (sumaDiagonal1 == 0 || sumaDiagonal2 == 0) {
            return 0; // si guanya el contrincant
        }

        // Si no s'ha trobat cap guanyador, retornar -1
        return -1;
    }
    
    
}
