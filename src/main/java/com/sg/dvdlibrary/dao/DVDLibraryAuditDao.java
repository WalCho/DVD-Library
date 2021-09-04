package com.sg.dvdlibrary.dao;

public interface DVDLibraryAuditDao {

    void writeAuditEntry(String entry) throws DVDLibraryPersistenceException;
}
