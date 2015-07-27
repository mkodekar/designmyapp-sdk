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
 * This class provides an implementation of an UploadProcessor (@see mobi.designmyapp.sdk.processor.UploadProcessor)
 * for the images of the application. It furnishes a way to upload images in the application.
 */
public class ImageUploadProcessor implements UploadProcessor<Image> {

  public static final String DEFAULT_NAMESPACE = "image";

  private List<String> validExtensions;

  private final IOUtils ioUtils;
  private final DigestUtils digestUtils;
  private final String namespace;

  /**
   * Constructor.
   * @param namespace the namespace
   */
  public ImageUploadProcessor(String namespace) {
    validExtensions = new ArrayList<>();
    validExtensions.add("png");
    validExtensions.add("jpg");
    validExtensions.add("jpeg");
    ioUtils = UtilsFactory.getIOUtils();
    digestUtils = UtilsFactory.getDigestUtils();
    this.namespace = namespace;
  }

  /**
   * Default constructor.
   */
  public ImageUploadProcessor() {
    this(DEFAULT_NAMESPACE);
  }

  /**
   * Retrieve namespace : representing where the uploaded file will be stored.
   */
  @Override
  public String getNamespace() {
    return namespace;
  }

  /**
   * Process the upload request.
   *
   * @param request the UploadRequest. @see mobi.designmyapp.common.engine.model.UploadRequest.
   * @param destDir the destination directory. Contains all resources already uploaded through this implementation.
   * @return the uploaded image
   * @throws IOException {@link IOException}
   */
  @Override
  public Image process(UploadRequest request, File destDir) throws IOException {

    File tmpFile = new File(destDir, request.getOriginalFilename() + ".tmp");

    // Write the stream to a new file
    ioUtils.copyInputStreamToFile(request.getObj(), tmpFile);
    String destFileName = digestUtils.shaHex(String.valueOf(new FileInputStream(tmpFile))) +
        ioUtils.getExtension(request.getOriginalFilename());

    File destFile = new File(destDir, destFileName);
    if (!destFile.exists()) {
      ioUtils.moveFile(tmpFile, destFile);
    }

    return Image.builder()
        .fileName(destFileName)
        .originalName(request.getOriginalFilename())
        .prepareUrl()
        .namespace(namespace)
        .apiKey(request.getApiKey())
        .build();

  }

  /**
   * Retrieve valid extensions for this processor.
   */
  @Override
  public List<String> getValidExtensions() {
    return validExtensions;
  }
}
