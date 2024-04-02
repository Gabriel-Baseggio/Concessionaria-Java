package net.weg.topcar;

import net.weg.topcar.controller.MenuController;

public class Main {

    private static final MenuController menuController = new MenuController();

    public static void main(String[] args) {
        menuController.menuInicial();
    }

}