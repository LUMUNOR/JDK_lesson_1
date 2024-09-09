package Server.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Repository implements Keeper {

    private static final String file = "log.txt";

    public String load (){
        try
        {
            return Files.readString( Paths.get (file));
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            return "";
        }
    }

    public Boolean save(String log){
        try( FileWriter writer = new FileWriter(file,false))
        {
            writer.append(log);
            return true;
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
