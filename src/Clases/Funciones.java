/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author jose_
 */
public class Funciones {

    public File documento;
    public boolean cargado;
    
    public Funciones() {
        this.cargado = false;
        this.documento = null;
    }
    
    
    public String cargarArchivo() {
        String info_txt = "";
        JFileChooser jf = new JFileChooser();
        jf.showOpenDialog(null);
        File archivo = jf.getSelectedFile();
        this.documento = archivo;
        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    info_txt += line + "\n";
                }
            }
            System.out.println(info_txt);
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de lectura");
        }
        return info_txt;
    }

    public HashTable separacionInfo(String info_txt) throws IOException {
        String cambio = info_txt.replace(',', '\n').replace('.', '\n').replace(':', '\n').replace(';', '\n').replace('?', '\n').replace('¿', '\n').replace('!', '\n').replace('¡', '\n').replace('/', '\n').replace(' ', '\n').replace('*' , '\n').replace('-', '\n').replace('_', '\n').replace('…', '\n').replace('[' , '\n').replace(']' , '\n');
        int tamaño = cambio.length();
        HashTable tabla = new HashTable(tamaño);
        BufferedReader bufReader = new BufferedReader(new StringReader(cambio));
        String line = null;
        while ((line = bufReader.readLine()) != null) {
            tabla.Hashing(line);
        }

        return tabla;
    }
    
    public int hashing(String texto, int num) {
        int resultado = 0;
        if (num == -1) {
            for (int i = 0; i < texto.length(); i++) {
                int ascii = texto.charAt(i);
                resultado = (resultado * 7 + ascii) % 499;
            }
        } else {
            resultado = num * 7;
            int ascii = texto.charAt(texto.length() - 1);
            resultado += ascii;
            resultado %= 499;
        }
        return resultado;
    }

    public void rabinKarp(String frase) {
        String texto = "";
        try {
            FileReader fr = new FileReader(documento);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    texto += line + "\n";
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de lectura");
        }
        if (texto.length() >= frase.length()) {
            int hash1 = hashing(frase, -1);
            int temp = -1;
            int factor = 1;
            for (int i = 1; i < frase.length(); i++) {
                factor *= 7;
                factor %= 499;
            }
            boolean found = false;
            for (int i = 0; i < texto.length() - frase.length() + 1 && !found; i++) {
                String segment = texto.substring(i, i + frase.length());
                int hash2 = hashing(segment, temp);
                if (hash1 == hash2) {
                    if (segment.equals(frase)) {
                        found = true;
                        temp = i;
                        break;
                    }
                }
                int ascii = segment.charAt(0);
                temp = hash2 + 499 - ((factor * ascii) % 499);
            }
            if (found) {
                JOptionPane.showMessageDialog(null, "El fragmento fue encontrado en la posición " + temp + ", su aparición fue marcada en el artículo correspondiente.");
                texto = texto.replaceAll(frase, "**" + frase + "**");
                String ruta = documento.getAbsolutePath();
                try {
                    try (PrintWriter pw = new PrintWriter(ruta)) {
                        pw.print(texto);
                    }
                } catch (Exception f) {
                    JOptionPane.showMessageDialog(null, "Error al encontrar la ruta del archivo");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No encontrado");
            }
        } else {
            JOptionPane.showMessageDialog(null, "la frase ingresada es más larga que el texto a revisar.");
        }
    }

}

        /**try {
            if (!"".equals(info_txt)) {
                String[] arr_txt = info_txt.split("\n");
                int linea = 1;
                while (!arr_txt[linea].equals("Clientes")) {
                    String[] atributos = arr_txt[linea].split(",");
                    Restaurante newRest = new Restaurante(atributos[0].charAt(0), atributos[1], atributos[2]);
                    this.restaurantesGuardado.addAtTheEnd(newRest);
                    linea += 1;
                }
                linea += 1;
                while (!arr_txt[linea].equals("Pedidos")) {
                    String[] atributos = arr_txt[linea].split(",");
                    Cliente newClie = new Cliente(Integer.parseInt(atributos[0]), atributos[1], atributos[2], atributos[3]);
                    this.clientesGuardado.addAtTheEnd(newClie);
                    linea += 1;
                }
                linea += 1;
                while (!arr_txt[linea].equals("Rutas")) {
                    String[] atributos = arr_txt[linea].split(",");
                    Pedido newPedi = new Pedido(atributos[0], atributos[1], atributos[2]);
                    this.pedidosGuardado.addAtTheEnd(newPedi);
                    linea++;
                }
                linea += 1;
                int tamaño = clientesGuardado.size + restaurantesGuardado.size;
                this.grafoGuardado = new GrafoMA(tamaño);
                for (int i = linea; i < arr_txt.length; i++) {
                    String[] atributos = arr_txt[i].split(",");
                    int firstIndex;
                    int secondIndex;
                    try {
                        firstIndex = Integer.parseInt(atributos[0]) - 1;
                    } catch (Exception e) {
                        firstIndex = this.clientesGuardado.size;
                        Restaurante temp = this.restaurantesGuardado.first;
                        while (temp.key != atributos[0].charAt(0)) {
                            firstIndex += 1;
                            temp = temp.next;
                        }
                    }
                    try {
                        secondIndex = Integer.parseInt(atributos[1]) - 1;
                    } catch (Exception e) {
                        secondIndex = this.clientesGuardado.size;
                        Restaurante temp = this.restaurantesGuardado.first;
                        while (temp.key != atributos[1].charAt(0)) {
                            secondIndex += 1;
                            temp = temp.next;
                        }
                    }
                    this.grafoGuardado.añadirVertice(firstIndex, secondIndex, Integer.parseInt(atributos[2]));
                }
                JOptionPane.showMessageDialog(null, "Archivo cargado en el sistema.");
                this.full = true;
            } else {
                JOptionPane.showMessageDialog(null, "El archivo esta vacío");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de lectura");
        }*/


