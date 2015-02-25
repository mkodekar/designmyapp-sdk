package mobi.designmyapp.sdk.processor.impl;


import mobi.designmyapp.common.engine.model.Image;
import mobi.designmyapp.common.engine.model.UploadRequest;
import mobi.designmyapp.common.util.DigestUtils;
import mobi.designmyapp.common.util.IOUtils;
import mobi.designmyapp.common.util.UtilsFactory;
import mobi.designmyapp.sdk.processor.UploadProcessor;

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

  public static final String DEFAULT_NAMESPACE = "image";

  private List<String> validExtensions;

  private final IOUtils ioUtils;
  private final DigestUtils digestUtils;
  private final String namespace;

  public ImageUploadProcessor(String namespace) {
    validExtensions = new ArrayList<>();
    validExtensions.add("png");
    validExtensions.add("jpg");
    validExtensions.add("jpeg");
    ioUtils = UtilsFactory.getIOUtils();
    digestUtils = UtilsFactory.getDigestUtils();
    this.namespace = namespace;
  }

  public ImageUploadProcessor() {
    this(DEFAULT_NAMESPACE);
  }

  @Override
  public String getNamespace() {
    return namespace;
  }

  @Override
  public Image process(UploadRequest request, File destDir) throws IOException {

    File tmpFile = new File(destDir, request.getOriginalFilename() + ".tmp");

    // Write the stream to a new file
    ioUtils.copyInputStreamToFile(request.getObj(), tmpFile);
    String destFileName = digestUtils.shaHex(String.valueOf(new FileInputStream(tmpFile))) +
        ioUtils.getExtension(request.getOriginalFilename());

    File destFile = new File(destDir, destFileName);
    if (!destFile.exists())
      ioUtils.moveFile(tmpFile, destFile);

    return Image.builder()
        .fileName(destFileName)
        .originalName(request.getOriginalFilename())
        .prepareUrl()
        .appId(request.getAppId())
        .namespace(namespace)
        .portal(request.getPortalName())
        .build();

  }

  @Override
  public List<String> getValidExtensions() {
    return validExtensions;
  }
}
