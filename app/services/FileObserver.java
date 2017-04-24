package services;

import play.Logger;
import util.FileUtil;

import javax.inject.Singleton;
import java.io.IOException;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.*;

/**
 * Created by mbajdek on 22.04.2017.
 */
public class FileObserver implements Runnable{

    private static FileObserver observerInstance = null;

    WatchService watchService;
    WatchKey watchKey;
    Path path;

    protected FileObserver() {
        try{
            watchService = FileSystems.getDefault().newWatchService();
            path = Paths.get(FileUtil.getPlaceToObserve());
            watchKey = path.register(watchService,ENTRY_DELETE,ENTRY_MODIFY);
        }
        catch (IOException ioe){
            Logger.error("not able to construct");
        }
    }

    public static FileObserver getObserverInstance(){
        if(observerInstance == null){
            observerInstance = new FileObserver();
        }
        return observerInstance;
    }

    @Override
    public void run() {
        Logger.info("Process pending. Looking for changes in the directory: " + FileUtil.getPlaceToObserve());

        while(true){
            try {
                Thread.sleep(1800);
                Logger.info("heh");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
