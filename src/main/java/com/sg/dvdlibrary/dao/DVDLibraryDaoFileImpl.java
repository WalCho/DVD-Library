package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class DVDLibraryDaoFileImpl implements DVDLibraryDao{

    public static final String LIBRARY_FILE = "dvdLibrary.txt";
    public static final String DELIMITER = "::";

    private final Map<String, DVD> title2Dvd = new HashMap<>();

    @Override
    public DVD addDvd(String title, DVD dvd) throws DVDLibraryPersistenceException {
        loadLibrary();
        DVD newDvd = title2Dvd.put(title, dvd);
        writeLibrary();
        return newDvd;
    }

    @Override
    public DVD removeDvd(String title) throws DVDLibraryPersistenceException {
        loadLibrary();
        DVD removedDvd = title2Dvd.remove(title);
        writeLibrary();
        return removedDvd;
    }

    @Override
    public List<DVD> getAllDvds() throws DVDLibraryPersistenceException {
        loadLibrary();
        return new ArrayList(title2Dvd.values());
    }

    @Override
    public DVD getDvd(String title) throws DVDLibraryPersistenceException {
        loadLibrary();
        return title2Dvd.get(title);
    }

    private DVD unmarshallDvd(String dvdAsText) {
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String title = dvdTokens[0];
        DVD dvdFromFile = new DVD(title);
        dvdFromFile.setDirectorsName(dvdTokens[1]);
        dvdFromFile.setStudio(dvdTokens[2]);
        dvdFromFile.setReleaseDate(dvdTokens[3]);
        dvdFromFile.setMpaaRating(dvdTokens[4]);
        dvdFromFile.setUserRating(dvdTokens[5]);
        return dvdFromFile;
    }

    private void loadLibrary() throws DVDLibraryPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryPersistenceException("-_- Could not load roster data into memory.", e);
        }
        String currentLine;
        DVD currentDvd;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDvd = unmarshallDvd(currentLine);
            title2Dvd.put(currentDvd.getTitle(), currentDvd);
        }
        scanner.close();
    }

    private String marshallDvd(DVD aDvd) {
        String dvdAsText = aDvd.getTitle() + DELIMITER;
        dvdAsText += aDvd.getDirectorsName() + DELIMITER;
        dvdAsText += aDvd.getStudio() + DELIMITER;
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;
        dvdAsText += aDvd.getMpaaRating() + DELIMITER;
        dvdAsText += aDvd.getUserRating();
        return dvdAsText;
    }

    private void writeLibrary() throws DVDLibraryPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryPersistenceException("Could not save student data.", e);
        }
        String dvdAsText;
        List<DVD> dvdList = this.getAllDvds();
        for (DVD currentDvd : dvdList) {
            dvdAsText = marshallDvd(currentDvd);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }
}
