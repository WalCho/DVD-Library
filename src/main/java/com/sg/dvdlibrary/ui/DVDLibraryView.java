package com.sg.dvdlibrary.ui;

public class DVDLibraryView {

    private UserIO io = new UserIOConsoleImpl();

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
}
