package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;

import java.util.List;

// TODO: Add/edit/delete many DVDs in one session
public interface DVDLibraryDao {

    DVD addDvd (String title, DVD dvd) throws DVDLibraryDaoException;

    DVD removeDvd(String title) throws DVDLibraryDaoException;

    List<DVD> getAllDvds() throws DVDLibraryDaoException;

    DVD getDvd(String title) throws DVDLibraryDaoException;




}
