package slotmachine.recordrelated;

import java.util.ArrayList;
import java.util.List;


//TODO que pasa con esto? Tendria que tenerlo guardado en properties?
public class RecordManager {
    private List<Record> records = new ArrayList<>();
    //private String result;

    public void setRecord(int result) {
        records.add(new Record(result));
    }

    public void getRecords() {
        for(int i = 0; i < records.size(); i++) {
            System.out.println(records.get(i).getRecord());
        }
    }
}
