package com.example.managingtransactions;

import com.example.managingtransactions.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    private final static Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final BookService bookService;

    public AppRunner(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("start command line>>>>>>>>>>>>");

        System.out.println("insert normal data");
        bookService.book("Hob","Jack","Mike");

        System.out.println("print the result: ");
        for(String person: bookService.findAllBookings()){
            System.out.println("person: " + person);
        }

        System.out.println("insert abnomal data( which the name is too long...)");
        try{
            bookService.book("Sandy","SandyAndMandyGaGa","Mandy");
        }catch(RuntimeException e){
            logger.info("the error is expected because the inserted data is too long...");
            logger.error(e.getMessage());
        }


        System.out.println("print the result: ");
        for(String person: bookService.findAllBookings()){
            System.out.println("person: " + person);
        }


    }
}
