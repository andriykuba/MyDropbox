package util;

import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.Play;

import java.io.File;
import java.util.List;

/**
 * Created by mbajdek on 22.04.2017.
 */
public abstract class BasicUtil {

    public static String getPlaceToObserve(){
        String place = Play.application().configuration().getString("storage.place");
        if(StringUtils.isNotBlank(place)){
            return place;
        }
        Logger.error("");
        return "";
    }

    public static String getServerInstance(){
        String instance = Play.application().configuration().getString("storage.place");
        if(StringUtils.isNotBlank(instance)){
            return instance;
        }
        Logger.error("");
        return "";
    }


}
