package mobi.designmyapp.sdk.processor.impl;

import mobi.designmyapp.common.api.model.UploadRequest;
import mobi.designmyapp.common.api.utils.UtilsFactory;
import mobi.designmyapp.sdk.processor.ArchiveProcessor;
import mobi.designmyapp.sdk.processor.UploadProcessor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loïc Ortola on 7/30/14
 * ZipUploadProcessor implementation
 */
public class ZipUploadProcessor implements UploadProcessor {

  public static final String NAMESPACE = "zip";

  private List<String> validExtensions;

  public ZipUploadProcessor() {
    validExtensions = new ArrayList<>();
    validExtensions.add("zip");
  }

  @Override
  public String getNamespace() {
    return null;
  }

  @Override
  public Object process(UploadRequest request, File destDir) throws IOException {

    File tmpZipFile = new File(destDir, "tmp.zip");

    // Write the stream to a new file
    UtilsFactory.getIOUtils().copyInputStreamToFile(request.getObj(), tmpZipFile);

    String zipHash = UtilsFactory.getDigestUtils().createHash(tmpZipFile);
    File zipFile = new File(destDir,zipHash+".zip");

    if (!zipFile.exists()) {
      UtilsFactory.getIOUtils().moveFile(tmpZipFile, zipFile);
    } else {
      tmpZipFile.delete();
    }

    List<String> unhandledFiles = new ArrayList<>();

    ArchiveProcessor archiveProcessor = ((ArchiveProcessor) request.getResources());

    UtilsFactory.getZipUtils().unzip(zipFile, archiveProcessor.getValidExtensions(), unhandledFiles);

    File zipDir = new File(destDir,zipHash);

    return archiveProcessor.process(request,zipDir,unhandledFiles);
  }

  @Override
  public List<String> getValidExtensions() {
    return validExtensions;
  }
}
