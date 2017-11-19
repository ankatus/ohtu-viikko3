package ohtu;

import java.util.ArrayList;

public class Submission {
    private int week;
    private int hours;
    private ArrayList<String> exercises;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public ArrayList<String> getExercises() {
        return exercises;
    }

    @Override
    public String toString() {
        String string = "viikko " + week + ":\n"
        + "  tehtyjä tehtäviä yhteensä: " + exercises.size() 
        + ", aikaa kului " + hours + " tuntia, tehdyt tehtävät: ";
    for (String s : exercises) {
        s += " " + s;
    }
    return string;
    }
    
}