package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.ui.DVDLibraryView;

import java.util.List;

public class DVDLibraryController {

    private DVDLibraryView view;
    private DVDLibraryDao dao;

    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try{
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
                        //io.print("ADD/EDIT/DELETE MANY DVDs");
                        break;
                    case 8:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private int getMenuSelection() {
        return view.printMenuAngGetSelection();
    }

    private void createDvd() throws DVDLibraryDaoException {
        view.displayCreateDvdBanner();
        DVD newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }

    private void listDvds() throws DVDLibraryDaoException{
        view.displayAllBanner();
        List<DVD> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    private void viewDvd() throws DVDLibraryDaoException{
        view.displayDvdBanner();
        String title = view.getDvdTitleChoice();
        DVD dvd = dao.getDvd(title);
        view.displayDvd(dvd);
    }

    private void removeDvd() throws DVDLibraryDaoException{
        view.displayRemoveDvdBanner();
        String title = view.getDvdTitleChoice();
        DVD removedDvd = dao.removeDvd(title);
        view.displayRemoveResult(removedDvd);
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
}
