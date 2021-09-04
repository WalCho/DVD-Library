package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryAuditDao;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DVDLibraryServiceLayerImpl implements DVDLibraryServiceLayer {

    DVDLibraryAuditDao auditDao;
    DVDLibraryDao dao;

    @Autowired
    public DVDLibraryServiceLayerImpl(DVDLibraryDao dao, DVDLibraryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void createDvd(DVD dvd) throws
            DVDLibraryDuplicateIdException,
            DVDLibraryDataValidationException,
            DVDLibraryPersistenceException {
        if (dao.getDvd(dvd.getTitle()) != null) {
            throw new DVDLibraryDuplicateIdException("ERROR: Could not create DVD. Title " + dvd.getTitle() + " already exists");
        }
        validateDvdData(dvd);
        dao.addDvd(dvd.getTitle(), dvd);
        auditDao.writeAuditEntry("DVD " + dvd.getTitle() + " CREATED");
    }

    @Override
    public List<DVD> getAllDvds() throws DVDLibraryPersistenceException {
        return dao.getAllDvds();
    }

    @Override
    public DVD getDvd(String title) throws DVDLibraryPersistenceException {
        return dao.getDvd(title);
    }

    @Override
    public DVD removeDvd(String title) throws DVDLibraryPersistenceException {
        DVD removedDvd = dao.removeDvd(title);
        auditDao.writeAuditEntry("DVD " + title + " REMOVED");
        return removedDvd;
    }

    private void validateDvdData(DVD dvd) throws DVDLibraryDataValidationException {
        if (dvd.getReleaseDate() == null
                || dvd.getReleaseDate().trim().length() == 0
                || dvd.getDirectorsName() == null
                || dvd.getDirectorsName().trim().length() == 0
                || dvd.getStudio() == null
                || dvd.getStudio().trim().length() == 0
                || dvd.getMpaaRating() == null
                || dvd.getMpaaRating().trim().length() == 0) {

            throw new DVDLibraryDataValidationException("ERROR: All fields [ReleaseDate, DirectorsName, Studio, MpaaRating are required");
        }
    }
}
