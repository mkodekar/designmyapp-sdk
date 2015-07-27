/*
 Copyright 2015 eBusiness Information
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 http://www.apache.org/licenses/LICENSE-2.0
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package mobi.designmyapp.sdk.processor.impl;

import mobi.designmyapp.common.engine.model.UploadRequest;
import mobi.designmyapp.common.util.UtilsFactory;
import mobi.designmyapp.sdk.processor.ArchiveProcessor;
import mobi.designmyapp.sdk.processor.UploadProcessor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loïc Ortola on 7/30/14
 * This class provides an implementation of an UploadProcessor (@see mobi.designmyapp.sdk.processor.UploadProcessor)
 * to add resources from a zip to the application. It furnishes a way to upload files from a zip into the application.
 */
public class ZipUploadProcessor implements UploadProcessor {

  public static final String NAMESPACE = "zip";

  private List<String> validExtensions;
  /**
   * Default constructor.
   */
  public ZipUploadProcessor() {
    validExtensions = new ArrayList<>();
    validExtensions.add("zip");
  }

  /**
   * Retrieve namespace : representing where the uploaded file will be stored.
   */
  @Override
  public String getNamespace() {
    return NAMESPACE;
  }

  /**
   * Process the upload request.
   *
   * @param request the UploadRequest. @see mobi.designmyapp.common.engine.model.UploadRequest.
   * @param destDir the destination directory. Contains all resources already uploaded through this implementation.
   * @return the uploaded resources
   * @throws IOException
   */
  @Override
  public Object process(UploadRequest request, File destDir) throws IOException {

    File tmpZipFile = new File(destDir, "tmp.zip");

    // Write the stream to a new file
    UtilsFactory.getIOUtils().copyInputStreamToFile(request.getObj(), tmpZipFile);

    String zipHash = UtilsFactory.getDigestUtils().createHash(tmpZipFile);
    File zipFile = new File(destDir, zipHash + ".zip");

    if (!zipFile.exists()) {
      UtilsFactory.getIOUtils().moveFile(tmpZipFile, zipFile);
    } else {
      tmpZipFile.delete();
    }

    List<String> unhandledFiles = new ArrayList<>();

    ArchiveProcessor archiveProcessor = ((ArchiveProcessor) request.getResources());

    UtilsFactory.getZipUtils().unzip(zipFile, archiveProcessor.getValidExtensions(), unhandledFiles);

    File zipDir = new File(destDir, zipHash);

    return archiveProcessor.process(request, zipDir, unhandledFiles);
  }

  /**
   * Retrieve valid extensions for this processor.
   */
  @Override
  public List<String> getValidExtensions() {
    return validExtensions;
  }
}
