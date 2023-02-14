package org.apache.maven.cantinappdesktop.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Turn {
    private String clasS;
    /// clasS is used as a substitute for the word 'class', which is marked as a key word
    private String date;
    private int period;
    /// period = 0 means morning
    /// period = 1 means afternoon
    /// period = 2 means night

    public String getClasS() {
        return clasS;
    }

    public void setClasS(String clasS) {
        this.clasS = clasS;
    }


    public Turn(String clasS, LocalDate date, int period) {
        this.clasS = clasS;
        this.period = period;
        this.date = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
