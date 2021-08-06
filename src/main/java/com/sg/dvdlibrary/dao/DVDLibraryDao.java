package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;

import java.util.List;

// TODO: Add/edit/delete many DVDs in one session
public interface DVDLibraryDao {

    DVD addDvd(String title, DVD dvd) throws DVDLibraryPersistenceException;

    DVD removeDvd(String title) throws DVDLibraryPersistenceException;

    List<DVD> getAllDvds() throws DVDLibraryPersistenceException;

    DVD getDvd(String title) throws DVDLibraryPersistenceException;
}
