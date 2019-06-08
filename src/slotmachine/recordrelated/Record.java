package slotmachine.recordrelated;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Record {
    private String record;

    public Record(int bet, List<Integer> reelsResult, int prize) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd h:mm a");

        StringBuilder builder = new StringBuilder();
        builder.append("Date: ");
        builder.append(date.format(Calendar.getInstance().getTime()));
        builder.append(" - Bet: ");
        builder.append(bet);
        builder.append(" - Reels Results: ");
        builder.append(reelsResult);
        builder.append(" - The player won ");
        builder.append(prize);
        builder.append(" coins");

        record = builder.toString();
    }

    public String getRecord() {
        return record;
    }

}
