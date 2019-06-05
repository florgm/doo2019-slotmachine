package slotmachine.recordrelated;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Record {
    private String record;

    public Record(int result) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd h:mm a");

        this.record = "Date: " + date.format(Calendar.getInstance().getTime()) + " - Result: " + result;
    }

    public String getRecord() {
        return record;
    }

    /*public String saveRecord() {
        SimpleDateFormat date = new SimpleDateFormat("dd/mm/y");

        StringBuilder builder = new StringBuilder();
        builder.append("Date: ");
        builder.append(date.format(Calendar.getInstance().getTime()));
        builder.append("Result: ");
        builder.append(result);

        //String record = "Date: " + date.format(Calendar.getInstance().getTime()) + " Result: " + result;

        return builder.toString();
    }*/
}
