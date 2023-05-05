package michaelcychan.javabankapi;

import com.google.gson.Gson;
import michaelcychan.javabankapi.model.Bank;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadJsonData {

    public static Bank readJsonFile(String path) {
        try{
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(path));

            Bank bank = gson.fromJson(reader, Bank.class);

            reader.close();

            return bank;

        } catch (Exception ex) {
            ex.printStackTrace();
            return new Bank();
        }
    }

}
