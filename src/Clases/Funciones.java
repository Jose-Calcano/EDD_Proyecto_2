/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author jose_
 */
public class Funciones {

    public File documento;
    public boolean cargado;
    public HashTable tabla;
    public LinkedList lista;

    public Funciones() {
        this.cargado = false;
        this.documento = null;
        this.lista = new LinkedList();
        this.tabla = new HashTable(300);
    }

    /**
     * Funcion para cargar archivo .txt y que el programa lo lea como un String
     * el cual contenga un texto para despues pasarlo a la funcion
     * "separacionInfo" como parametro y eliminar signos de puntuacion y
     * separarlo en palabras
     *
     * @return
     */
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

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de lectura");
        }
        return info_txt;
    }

    /**
     * Description: Separa cada palabra del string "info_txt"
     *
     * @param info_txt texto completo del artículo
     * @throws IOException
     */
    public void separacionInfo(String info_txt) throws IOException {
        String cambio = info_txt.replaceAll(",", "").replaceAll(";", "").replaceAll(":", "").replace('[', ' ').replaceAll("]", "").replace('(', ' ').replace(')', ' ').replace('!', ' ').replace('¡', ' ').replace('¿', ' ').replace('?', ' ').replaceAll("\n", " ").replaceAll("  ", " ").replaceAll("   ", " ").replace(".", "").replace("%", "").replace("$", "").replace("-", "").toLowerCase();
        String a = cambio.replaceAll(" ", "\n");
        BufferedReader bufReader = new BufferedReader(new StringReader(a));
        String line = "";
        while ((line = bufReader.readLine()) != null) {
            Node nodito = new Node(line);
            tabla.insert(nodito);
            lista.addLast(nodito);

        }
    }

    /**
     * Description: Hashing rodante utilizado para el algoritmo de karp
     *
     * @param texto Segmento de texto a sacar el hash
     * @param num -1 si es la primera vez, de no ser asi es el hash del caracter
     * viejo
     * @return el hash del parametro "texto"
     */
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

    /**
     * Description: Realiza una busqueda de la frase en todo el documento
     * cargado mediante el algoritmo de Rabin-Karp, y regresa su primera
     * instancia
     *
     * @param frase frase a buscar
     */
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

    /**
     * Description: Ajusta el tamaño de una imagen a la de un Label
     *
     * @param frame el Label que se usa de referencia de tamaño
     * @param imgName nombre completo de la imagen a ajustar (debe estar en
     * Test/Resources)
     */
    public void scaleImage(JLabel frame, String imgName) {
        ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("test/Resources/" + imgName));
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        frame.setIcon(scaledIcon);
    }

    /**
     * Description: Elimina los dobles asteriscos del texto.
     */
    public void fixText() {
        String texto = "";
        try {
            FileReader fr = new FileReader(this.documento);
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
        texto = texto.replace("**", "");
        String ruta = this.documento.getAbsolutePath();
        try {
            try (PrintWriter pw = new PrintWriter(ruta)) {
                pw.print(texto);
            }
        } catch (Exception f) {
            JOptionPane.showMessageDialog(null, "Error al encontrar la ruta del archivo");
        }
    }

}
