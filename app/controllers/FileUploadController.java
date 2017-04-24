package controllers;

import com.google.common.io.Files;
import constans.AppCommunicates;
import play.Logger;
import play.mvc.Http;
import play.mvc.Result;
import util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by mbajdek on 22.04.2017.
 */
public class FileUploadController extends BasicAbstractController {


    public Result upload() {
        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> picture = body.getFile("picture");
        if (picture != null) {
            String fileName = picture.getFilename();
            String contentType = picture.getContentType();
            File file = picture.getFile();
            File fileToSave = new File(getPlaceToObserve() + "/" + picture.getFilename());
            try{
                Files.copy(file,fileToSave);
            }
            catch (IOException ioe){
                Logger.error("Unable to write file");
            }
            Logger.error("File Handled Successfully");
            return redirect(GO_HOME);
        } else {
            flash("error", "Missing file");
            return badRequest();
        }
    }

    public Result delete(String fileName){
        List<File> files = FileUtil.getCurrentFileNames();
        File fileToDelete = null;
        for (File file : files) {
            if(file.getName().equals(fileName)){
                fileToDelete = file;
                break;
            }
        }
        boolean deletionResult = FileUtil.deleteGivenFile(fileToDelete);
        if(!deletionResult){
            super.backendErrors.add(AppCommunicates.UNABLE_TO_DELETE_FILE);
        }
        return redirect(GO_HOME);
    }
}
