package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DVDLibraryController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.sg.dvdlibrary");
        appContext.refresh();
        DVDLibraryController controller = appContext.getBean("DVDLibraryController", DVDLibraryController.class);
        controller.run();
    }
}
