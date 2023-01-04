package ru.KhosueAv.ulearn;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Earthquake {

    private final String id;
    private final int depth;
    private final String typeOfMagnitude;
    private final Double magnitude;
    private final String state;
    private final Date date;

    public final static SimpleDateFormat DateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


    public Earthquake(String[] data) throws ParseException {
        this.id = data[0];
        this.depth = Integer.parseInt(data[1]);
        this.typeOfMagnitude = data[2];
        this.magnitude = Double.parseDouble(data[3]);
        this.state = data[4];
        this.date = new Date(
            DateFormatter.parse(data[5].split("T")[0]).getTime()
        );
    }

    public Earthquake(String id, int depth, String typeOfMagnitude, Double magnitude,
        String state, Date date) {
        this.id = id;
        this.depth = depth;
        this.typeOfMagnitude = typeOfMagnitude;
        this.magnitude = magnitude;
        this.state = state;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public int getDepth() {
        return depth;
    }

    public String getTypeOfMagnitude() {
        return typeOfMagnitude;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public String getState() {
        return state;
    }

    public Date getDate() {
        return date;
    }
}
//

//

//
//    @Override
//    public String toString() {
//        return "Earthquake{" +
//            "id='" + id + '\'' +
//            ", depth=" + depth +
//            ", typeOfMagnitude='" + typeOfMagnitude + '\'' +
//            ", magnitude=" + magnitude +
//            ", state='" + state + '\'' +
//            ", date=" + date +
//            '}';
//    }
//}
