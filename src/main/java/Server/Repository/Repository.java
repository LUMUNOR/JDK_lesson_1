package Server.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Repository {

    private static final String file = "log.txt";

    public String load (){
        try
        {
            String result = Files.readString( Paths.get (file));
            history.append(result);
            log.append(result);
            return result;
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Boolean save(){
        try( FileWriter writer = new FileWriter(file,true))
        {
            writer.append(history.toString());
            history.delete(0,history.length());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
