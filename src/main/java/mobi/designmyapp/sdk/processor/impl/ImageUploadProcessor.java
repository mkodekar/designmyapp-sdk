package mobi.designmyapp.sdk.processor.impl;


import mobi.designmyapp.common.api.model.UploadRequest;
import mobi.designmyapp.common.api.model.Image;
import mobi.designmyapp.common.api.utils.UtilsFactory;
import mobi.designmyapp.sdk.processor.UploadProcessor;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loïc Ortola on 7/30/14
 * ImageUploadProcessor implementation
 */
public class ImageUploadProcessor implements UploadProcessor<Image> {

  public static final String NAMESPACE = "image";

  private List<String> validExtensions;

  public ImageUploadProcessor() {
    validExtensions = new ArrayList<>();
    validExtensions.add("png");
    validExtensions.add("jpg");
    validExtensions.add("jpeg");
  }

  @Override
  public String getNamespace() {
    return null;
  }

  @Override
  public Image process(UploadRequest request, File destDir) throws IOException {

    File tmpFile = new File(destDir, request.getOriginalFilename()+".tmp");

    // Write the stream to a new file
    UtilsFactory.getIOUtils().copyInputStreamToFile(request.getObj(), tmpFile);
    //TODO
    String destFileName = DigestUtils.shaHex(String.valueOf(new FileInputStream(tmpFile)))+
        UtilsFactory.getIOUtils().getExtension(request.getOriginalFilename());

    File destFile = new File(destDir,destFileName);
    if(!destFile.exists())
      UtilsFactory.getIOUtils().moveFile(tmpFile, destFile);

    return Image.builder()
        .fileName(destFileName)
        .originalName(request.getOriginalFilename())
        .prepareUrl()
          .appId(request.getAppId())
          .namespace(NAMESPACE)
          .portal(request.getPortalName())
          .build();

  }

  @Override
  public List<String> getValidExtensions() {
    return validExtensions;
  }
}
