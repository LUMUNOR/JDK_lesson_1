package org.example;

import Client.ClientGUI;
import Server.ServerGUI;

public class Main {
    public static void main(String[] args) {
        ServerGUI serverGUI = new ServerGUI();
        new ClientGUI(serverGUI);
        new ClientGUI(serverGUI);
        }
    }