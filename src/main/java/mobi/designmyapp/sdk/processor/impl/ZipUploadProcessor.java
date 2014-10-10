package mobi.designmyapp.sdk.processor.impl;

import mobi.designmyapp.common.model.UploadRequest;
import mobi.designmyapp.common.utils.FileManagementUtils;
import mobi.designmyapp.common.utils.ZipUtils;
import mobi.designmyapp.sdk.processor.ArchiveProcessor;
import mobi.designmyapp.sdk.processor.UploadProcessor;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loïc Ortola on 7/30/14
 * ZipUploadProcessor implementation
 */
public class ZipUploadProcessor extends UploadProcessor {

  public static final String NAMESPACE = "zip";
  private final Logger logger = LoggerFactory.getLogger(ZipUploadProcessor.class);

  private List<String> validExtensions;

  public ZipUploadProcessor() {
    super(NAMESPACE);
    validExtensions = new ArrayList<>();
    validExtensions.add("zip");
  }

  @Override
  public Object process(UploadRequest request, File destDir) throws IOException {

    File tmpZipFile = new File(destDir, "tmp.zip");

    // Write the stream to a new file
    FileUtils.copyInputStreamToFile(request.getObj(), tmpZipFile);

    String zipHash = FileManagementUtils.createHash(tmpZipFile);
    File zipFile = new File(destDir,zipHash+".zip");

    if(!zipFile.exists())
      FileManagementUtils.moveFile(tmpZipFile,zipFile);
    else
      tmpZipFile.delete();

    logger.info("Zip file saved: " + zipFile.getName());

    List<String> unhandledFiles = new ArrayList<>();

    ArchiveProcessor archiveProcessor = ((ArchiveProcessor) request.getResources());

    ZipUtils.unzip(zipFile, archiveProcessor.getValidExtensions(), unhandledFiles);

    File zipDir = new File(destDir,zipHash);

    return archiveProcessor.process(request,zipDir,unhandledFiles);
  }

  @Override
  public List<String> getValidExtensions() {
    return validExtensions;
  }
}
