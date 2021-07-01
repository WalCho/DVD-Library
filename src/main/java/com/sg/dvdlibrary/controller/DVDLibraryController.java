package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

public class DVDLibraryController {

    private DVDLibraryView view = new DVDLibraryView();
    private UserIO io = new UserIOConsoleImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    io.print("LIST DVDs");
                    break;
                case 2:
                    io.print("VIEW DVD");
                    break;
                case 3:
                    io.print("CREATE A DVD");
                    break;
                case 4:
                    io.print("EDIT A DVD");
                    break;
                case 5:
                    io.print("REMOVE A DVD");
                    break;
                case 6:
                    io.print("ADD/EDIT/DELETE MANY DVDs");
                    break;
                case 7:
                    io.print("SEARCH A DVD BY TITLE");
                    break;
                case 8:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }

        }
        io.print("GOOD BYE");

    }
    
    private int getMenuSelection() {
        return view.printMenuAngGetSelection();
    }
}
