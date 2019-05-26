package slotmachine.recordrelated;

import java.util.List;

public class RecordManager{

    private List<Record> records;
    private String result;

    public void setRecord(String result) { this.result = result; }
    public List<Record>getRecord()  { return records; }
    private void addRecord() { records.add( new Record(String));}
}