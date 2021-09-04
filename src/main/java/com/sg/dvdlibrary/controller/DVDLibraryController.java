package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.service.DVDLibraryDataValidationException;
import com.sg.dvdlibrary.service.DVDLibraryDuplicateIdException;
import com.sg.dvdlibrary.service.DVDLibraryServiceLayer;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DVDLibraryController {

    private final DVDLibraryView view;
    private final DVDLibraryServiceLayer service;

    @Autowired
    public DVDLibraryController(DVDLibraryServiceLayer service, DVDLibraryView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection;
        try {
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
        } catch (DVDLibraryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAngGetSelection();
    }

    private void createDvd() throws DVDLibraryPersistenceException {
        view.displayCreateDvdBanner();
        boolean hasErrors;
        do {
            DVD currentDvd = view.getNewDvdInfo();
            try {
                service.createDvd(currentDvd);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (DVDLibraryDuplicateIdException | DVDLibraryDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void listDvds() throws DVDLibraryPersistenceException {
        view.displayAllBanner();
        List<DVD> dvdList = service.getAllDvds();
        view.displayDvdList(dvdList);
    }

    private void viewDvd() throws DVDLibraryPersistenceException {
        view.displayDvdBanner();
        String title = view.getDvdTitleChoice();
        DVD dvd = service.getDvd(title);
        view.displayDvd(dvd);
    }

    private void removeDvd() throws DVDLibraryPersistenceException {
        view.displayRemoveDvdBanner();
        String title = view.getDvdTitleChoice();
        DVD removedDvd = service.removeDvd(title);
        view.displayRemoveResult(removedDvd);
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
}
