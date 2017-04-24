package controllers;

import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.Play;
import play.mvc.Controller;
import services.FileObserver;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mbajdek on 22.04.2017.
 */
public class BasicAbstractController extends Controller {

    public static final String GO_HOME = "/";

    public static List<String> backendErrors = new ArrayList<>();

    public Thread thread;

    public static String getPlaceToObserve(){
        String place = Play.application().configuration().getString("storage.place");
        if(StringUtils.isNotBlank(place)){
            return place;
        }
        return StringUtils.EMPTY;
    }

    public static String getServerInstance(){
        String instance = Play.application().configuration().getString("storage.place");
        if(StringUtils.isNotBlank(instance)){
            return instance;
        }
        return StringUtils.EMPTY;
    }

    public static List<String> getBackendErrors() {
        return backendErrors;
    }

    public static void setBackendErrors(List<String> backendErrors) {
        BasicAbstractController.backendErrors = backendErrors;
    }

    public synchronized void performObserverThread(){
        Logger.info("Observer thread has been initialized");
        Runnable threadRunner = FileObserver.getObserverInstance();
        Thread observerThread = new Thread(threadRunner);
        observerThread.start();
    }
}
