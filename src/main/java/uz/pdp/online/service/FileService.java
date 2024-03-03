package uz.pdp.online.service;

import org.apache.commons.io.FileSystem;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.online.dto.SavedFileInfoDto;
import uz.pdp.online.entity.FileDetailes;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component
public class FileService {



    public  FileDetailes saveFile(String bookId, MultipartFile file, String savePathLocation) throws IOException {

        //This method returns file's file address if saved successfully otherwise returns null

        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = bookId+"."+fileExtension;
        String toSaveFilePath = savePathLocation + fileName;
        long size = file.getSize();


        System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename());
        System.out.println("file.getName() = " + file.getName());

        System.out.println("savePathLocation = " + savePathLocation);
        System.out.println("bookId = " + bookId);
        System.out.println("fileExtension = " + fileExtension);
        System.out.println("toSaveFilePath = " + toSaveFilePath);

///////////////////////////////

        OutputStream stream = Files.newOutputStream(Path.of(toSaveFilePath));
        stream.write(file.getBytes());
        stream.close();
        return new FileDetailes(bookId,fileName, toSaveFilePath, fileExtension, size);

    }
}
