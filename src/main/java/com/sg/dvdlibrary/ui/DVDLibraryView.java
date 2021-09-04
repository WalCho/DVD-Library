package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DVDLibraryView {

    private final UserIO io;

    @Autowired
    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAngGetSelection() {
        io.print("Main Menu");
        io.print("1. List all DVDs");
        io.print("2. View a DVD");
        io.print("3. Create a New DVD");
        io.print("4. Edit a DVD");
        io.print("5. Remove a DVD");
        io.print("6. Add/Edit/Delete Many DVDS");
        io.print("7. Search a DVD by title");
        io.print("8. Exit");

        return io.readInt("Please select from the above choices.", 1, 8);
    }

    public DVD getNewDvdInfo() {
        String title = io.readString("Please enter DVD Title");
        String directorsName = io.readString("Please enter Director's Name");
        String studio = io.readString("Please enter Studio");
        String releaseDate = io.readString("Please enter Release Date");
        String mpaaRating = io.readString("Please enter MPAA Rating");
        String userRating = io.readString("Please enter User Rating");
        DVD currentDvd = new DVD(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setDirectorsName(directorsName);
        currentDvd.setStudio(studio);
        currentDvd.setUserRating(userRating);
        return currentDvd;
    }

    public void displayCreateDvdBanner() {
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully created. Please hit enter to continue");
    }

    public void displayDvdList(List<DVD> dvdList) {
        for (DVD currentDvd : dvdList) {
            String dvdInfo = String.format("#%s",
                    currentDvd.getTitle());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayDvdBanner() {
        io.print("=== Display DVD ===");
    }

    public String getDvdTitleChoice() {
        return io.readString("Please enter the DVD Title.");
    }

    public void displayDvd(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getDirectorsName());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getStudio());
            io.print(dvd.getMpaaRating());
            io.print(dvd.getUserRating());
            io.print("");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDvdBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveResult(DVD dvdRecord) {
        if (dvdRecord != null) {
            io.print("DVD successfully removed.");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
