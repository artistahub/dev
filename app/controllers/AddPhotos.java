package controllers;


import models.Photo;
import models.S3File;
import models.SystemUser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;

import java.io.IOException;

public class AddPhotos extends Controller {


    public static Result addMyPhotos() throws IOException {

        SystemUser u = SystemUser.findUserById(session("currentUserId"));
        String fileName = "";
        Http.MultipartFormData b = request().body().asMultipartFormData();
        FilePart picture = b.getFile("myphotos-upload");
        if (picture != null) {
            S3File s3File = new S3File();
            s3File.name = picture.getFilename();
            s3File.file = picture.getFile();
            s3File.save();
           // MyPhoto myphoto = new MyPhoto(imageUrl, fileName, u);
            Photo photo = new Photo(u, "title 1 ", s3File.getUrl().toString(), null);
            photo.save();
          }
        return redirect( routes.Application.myPhotos() );

    }
}
