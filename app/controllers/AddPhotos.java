package controllers;


import models.MyPhoto;
import models.S3File;
import models.SystemUser1;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Http.MultipartFormData.*;

import java.io.IOException;

public class AddPhotos extends Controller {


    public static Result addMyPhotos() throws IOException {

        SystemUser1 u = SystemUser1.findUserById(session("currentUserId"));
        String fileName = "";
        Http.MultipartFormData b = request().body().asMultipartFormData();
        FilePart picture = b.getFile("myphotos-upload");
        if (picture != null) {
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
