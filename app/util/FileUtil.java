package util;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by mbajdek on 22.04.2017.
 */
public class FileUtil extends BasicUtil {

    public static List<File> getCurrentFileNames(){
        File file = new File(getPlaceToObserve());
        List<File> toReturnList = Arrays.asList(file.listFiles());

        return toReturnList;
    }

    public static boolean deleteGivenFile(File file){
        boolean toReturn = false;
        if(file != null){
            file.delete();
            toReturn = true;
        }
        else{
            play.Logger.error("There is no file to delete");
        }
        return toReturn;
    }
}
