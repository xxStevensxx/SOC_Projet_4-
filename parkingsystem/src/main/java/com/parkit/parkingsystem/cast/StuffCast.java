package com.parkit.parkingsystem.cast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StuffCast {
    
    public static Date getDateCast() throws ParseException {
//		Si le OutTime est egale á ce jour la le vehicule n'est pas sorti du parking
        String firstDayUnixDate = "1970/01/01";
        Date outTimeCast = new SimpleDateFormat("yyyy/mm/dd").parse(firstDayUnixDate);
		return outTimeCast;
    }
}
