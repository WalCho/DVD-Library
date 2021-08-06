package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;

import java.util.List;

public interface DVDLibraryServiceLayer {

    void createDvd(DVD dvd) throws
            DVDLibraryDuplicateIdException,
            DVDLibraryDataValidationException,
            DVDLibraryPersistenceException;

    List<DVD> getAllDvds() throws
            DVDLibraryPersistenceException;

    DVD getDvd(String title) throws
            DVDLibraryPersistenceException;

    DVD removeDvd(String title) throws
            DVDLibraryPersistenceException;
}
