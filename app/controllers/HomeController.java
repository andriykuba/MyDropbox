package controllers;

import play.Logger;
import play.Play;
import play.mvc.*;

import services.FileObserver;
import util.FileUtil;
import views.html.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends BasicAbstractController {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {

        performObserverThread();
        List<File> filesInTheDirectory = FileUtil.getCurrentFileNames();
        List<String> fileNames = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        String localInstance = getServerInstance();
        for (File file : filesInTheDirectory) {
            fileNames.add(file.getName());
            map.put(file.getName(),localInstance+"/"+file.getName());
        }

        return ok(views.html.home.render(map , getBackendErrors()));
    }


    public Result upload(){

        return ok();
    }



}
