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
import java.io.StringReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author jose_
 */
public class Funciones {

    
    
    public String cargarArchivo() {
        String info_txt = "";
        JFileChooser jf = new JFileChooser();
        jf.showOpenDialog(null);
        File archivo = jf.getSelectedFile();
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

    public String separacionInfo(String info_txt) throws IOException {
        String cambio = info_txt.replace(',', '\n').replace('.', '\n').replace(':', '\n').replace(';', '\n').replace('?', '\n').replace('¿', '\n').replace('!', '\n').replace('¡', '\n').replace('/', '\n').replace(' ', '\n').replace('*' , '\n').replace('-', '\n').replace('_', '\n').replace('…', '\n').replace('[' , '\n').replace(']' , '\n');
        BufferedReader bufReader = new BufferedReader(new StringReader(cambio));
        String line = null;
        while ((line = bufReader.readLine()) != null) {
            Nodo nodito = new Nodo(line);
            
        }

        return cambio;
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


