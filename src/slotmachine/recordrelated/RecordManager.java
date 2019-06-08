package slotmachine.recordrelated;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecordManager {
    private static RecordManager instance;
    private List<Record> records;

    private RecordManager() {
        records = new ArrayList<>();
    }

    public static RecordManager getInstance() {
        if(instance == null) {
            instance = new RecordManager();
        }
        return instance;
    }

    public boolean saveRecords() {
        File file = new File("records.txt");
        Gson gson = new Gson();

        try {
            FileWriter writer = new FileWriter(file, false);
            writer.write(gson.toJson(records));
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean loadRecords() {
        Gson gson = new Gson();

        try {
            JsonReader reader = new JsonReader(new FileReader("records.txt"));
            records = gson.fromJson(reader, new TypeToken<List<Record>>(){}.getType());
            return true;
        } catch (FileNotFoundException exc) {
            exc.printStackTrace();
            return false;
        }

    }

    public void setRecord(int bet, List<String> reelsResult, int prize) {
        records.add(new Record(bet, reelsResult, prize));
        saveRecords();
    }

    public void getRecords() {
        System.out.println("Records: ");
        for(int i = 0; i < records.size(); i++) {
            System.out.println(records.get(i).getRecord());
        }
    }
}
