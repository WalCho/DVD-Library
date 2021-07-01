package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao{

    private Map<String, DVD> title2Dvd = new HashMap<>();

    @Override
    public DVD addDvd(String title, DVD dvd) {
        return title2Dvd.put(title, dvd);
    }

    @Override
    public DVD removeDvd(String title) {
        return title2Dvd.remove(title);
    }

    @Override
    public List<DVD> getAllDvds() {
        return new ArrayList<DVD>(title2Dvd.values());
    }

    @Override
    public DVD getDvd(String title) {
        return title2Dvd.get(title);
    }
}
