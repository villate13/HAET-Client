/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.haet.client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan
 */
public class client {

    public static void main(String[] args) {
        final String HOST = "127.0.0.1";
        final int PORT = 1313;

        DataInputStream in=null;
        DataInputStream input =null;
        DataOutputStream out=null;

        try {
            Socket sc = new Socket(HOST, PORT);
            System.out.println("...");

            in = new DataInputStream(new BufferedInputStream(sc.getInputStream()));//cleinte
            out = new DataOutputStream(sc.getOutputStream());
            input = new DataInputStream(System.in);//terminal

            
        } catch (UnknownHostException u) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, u);
        } catch (IOException i) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, i);
        }

        // string to read message from input
        String command = "";

        while (true) {
            try {
                command = in.readUTF();
                try {
                    Process process = Runtime.getRuntime().exec(command);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                    String line, x = "";
                    while ((line = reader.readLine()) != null) {
                        x += line + "\n";
                    }

                    out.writeUTF(x);

                    process.waitFor();
                } catch (Exception ex) {
                    Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (IOException i) {
                Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, i);
            }
        }
        /*
        try {
            Socket sc = new Socket(HOST, PORT);
            
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            String ip = sc.getRemoteSocketAddress().toString();
            System.out.println( ip);
            out.writeUTF("ifconfig");
            
            System.out.println( "Hora actual con la clase date:" );
            System.out.println( "\t" + new Date() );
            
            String message = in.readUTF();
            
            System.out.println(message);
            
            
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }

}
