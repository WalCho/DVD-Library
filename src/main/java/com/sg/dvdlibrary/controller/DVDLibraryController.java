package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

import java.util.List;

public class DVDLibraryController {

    private DVDLibraryView view = new DVDLibraryView();
    private DVDLibraryDao dao = new DVDLibraryDaoFileImpl();
    private UserIO io = new UserIOConsoleImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    listDvds();
                    break;
                case 2:
                case 7:
                    viewDvd();
                    break;
                case 3:
                case 4:
                    createDvd();
                    break;
                case 5:
                    removeDvd();
                    break;
                case 6:
                    io.print("ADD/EDIT/DELETE MANY DVDs");
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

    private void createDvd() {
        view.displayCreateDvdBanner();
        DVD newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }

    private void listDvds() {
        view.displayAllBanner();
        List<DVD> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    private void viewDvd() {
        view.displayDvdBanner();
        String title = view.getDvdTitleChoice();
        DVD dvd = dao.getDvd(title);
        view.displayDvd(dvd);
    }

    private void removeDvd() {
        view.displayRemoveDvdBanner();
        String title = view.getDvdTitleChoice();
        DVD removedDvd = dao.removeDvd(title);
        view.displayRemoveResult(removedDvd);
    }
}
