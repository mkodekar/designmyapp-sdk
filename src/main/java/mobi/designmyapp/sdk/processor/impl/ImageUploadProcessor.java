package mobi.designmyapp.sdk.processor.impl;


import mobi.designmyapp.common.model.UploadRequest;
import mobi.designmyapp.common.utils.FileManagementUtils;
import mobi.designmyapp.common.model.Image;
import mobi.designmyapp.sdk.processor.UploadProcessor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loïc Ortola on 7/30/14
 * ImageUploadProcessor implementation
 */
public class ImageUploadProcessor extends UploadProcessor<Image> {

  public static final String NAMESPACE = "image";

  private List<String> validExtensions;

  public ImageUploadProcessor(String namespace) {
    super(namespace);
    validExtensions = new ArrayList<>();
    validExtensions.add("png");
    validExtensions.add("jpg");
    validExtensions.add("jpeg");
  }

  public ImageUploadProcessor() {
    this(NAMESPACE);
  }

  @Override
  public Image process(UploadRequest request, File destDir) throws IOException {

    File tmpFile = new File(destDir, request.getOriginalFilename()+".tmp");

    // Write the stream to a new file
    FileUtils.copyInputStreamToFile(request.getObj(), tmpFile);

    String destFileName = DigestUtils.shaHex(new FileInputStream(tmpFile))+FileManagementUtils.getExtension(request.getOriginalFilename());

    File destFile = new File(destDir,destFileName);
    if(!destFile.exists())
      FileUtils.moveFile(tmpFile, destFile);

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
