package suitea;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TempDateClass {
    public static void main(String[] args) {
        Date d = new Date();
        System.out.println(d.toString());
        String date_selected = "12-04-2018"; //dd-MM-yyy

        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date dateToSelect = sd.parse(date_selected);
            System.out.println(dateToSelect.toString());
            //compare
            System.out.println(d.compareTo(dateToSelect));

            String day = new SimpleDateFormat("dd").format(dateToSelect);
            System.out.println(day);
            String month = new SimpleDateFormat("MMMM").format(dateToSelect);
            System.out.println(month.toString());
            String year = new SimpleDateFormat("yyyy").format(dateToSelect);
            System.out.println(year);


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
