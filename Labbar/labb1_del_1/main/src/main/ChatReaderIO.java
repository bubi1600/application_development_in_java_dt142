/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author robar
 */
public class ChatReaderIO {
    //private ArrayList<String> textList;
   
    String getTextFromName(String name)
    {
        String text = "";
        File file = new File(name + ".txt"); //Häntar en fil
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                text += sc.nextLine() +"\n";
            }
            return text;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChatReaderIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    

    void appendTextFromName(String name, String text)
    {
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter(name + ".txt", true));
            write.newLine();
            write.write(text);
            write.close();
            
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
