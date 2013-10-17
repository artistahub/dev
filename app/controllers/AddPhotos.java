package controllers;


import models.MyPhoto;
import models.ProfileImage;
import models.S3File;
import models.User;
import org.h2.util.IOUtils;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class AddPhotos extends Controller {


    public static Result addMyPhotos() throws IOException {

        User u = User.findUserById(session("user"));
        String fileName = "";
       // File file;

        Http.MultipartFormData b = request().body().asMultipartFormData();
        FilePart picture = b.getFile("myphotos-upload");
        if (picture != null) {
           // fileName = picture.getFilename();
           // String contentType = picture.getContentType();
           // file = picture.getFile();
           // FileInputStream is = new FileInputStream(file);
           // String imageUrl = "myphotos/"  + new Date().getTime() + fileName.toLowerCase().replaceAll("\\s","-");
           // System.out.println(" **************** " + imageUrl);
           // String myphotosDir = "/public/" + imageUrl;
          //  IOUtils.copy(is, new FileOutputStream(Play.application().getFile(myphotosDir)));
            S3File s3File = new S3File();
            s3File.name = picture.getFilename();
            s3File.file = picture.getFile();
            s3File.save();
           // MyPhoto myphoto = new MyPhoto(imageUrl, fileName, u);
            MyPhoto myphoto = new MyPhoto(s3File.getUrl().toString(), fileName, u);
            myphoto.save();


          }
        return redirect( routes.Application.myPhotos());

    }
}
