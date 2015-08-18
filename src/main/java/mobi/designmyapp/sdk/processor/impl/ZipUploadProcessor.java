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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mobi.designmyapp.common.engine.model.UploadRequest;
import mobi.designmyapp.common.util.UtilsFactory;
import mobi.designmyapp.sdk.processor.ArchiveExtractorUploadProcessor;
import mobi.designmyapp.sdk.processor.UploadProcessor;

/**
 * Created by Loïc Ortola on 7/30/14 This class provides an implementation of an
 * {@link AssetUploadProcessorAdapter} to add resources from a zip to the
 * application. It furnishes a way to extract files from a zip into the
 * application. Process method will by extract Zip content, & call process
 * method of the wrapped ArchiveProcessor.
 */
public class ZipUploadProcessor<T> extends ArchiveExtractorUploadProcessor<T> {


  public static final String NAMESPACE = "zip";

  private List<String> validExtensions;

  /**
   * Instantiates a new zip upload processor, to handle zip archive extraction.
   */
  public ZipUploadProcessor() {

    validExtensions = new ArrayList<>();
    validExtensions.add("zip");
  }

  /**
   * Extracts the file contained in the given {@link UploadRequest}.
   *
   * @param request the upload request containing file to extract.
   * @param archiveFile the arcive file to extract.
   * @param unhandledFiles the files unhandled by the wrapped
   *          {@link UploadProcessor}
   * @return the root directory where files have been extracted.
   * @throws IOException Signals that an I/O exception has occurred while
   *           extracting.
   */
  @Override
  protected File extract(UploadRequest request, File archiveFile, List<String> unhandledFiles) throws IOException {
    return UtilsFactory.getZipUtils().unzip(archiveFile, getUploadProcessor().getValidExtensions(), unhandledFiles);
  }

  /**
   * Retrieve valid extensions for this processor.
   */
  @Override
  public List<String> getValidExtensions() {
    return validExtensions;
  }

  @Override
  public String getNamespace() {
    return NAMESPACE;
  }

}

