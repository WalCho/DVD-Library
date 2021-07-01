package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DVDLibraryController;

public class App {

    public static void main(String[] args) {
        DVDLibraryController controller = new DVDLibraryController();
        controller.run();
    }
}
