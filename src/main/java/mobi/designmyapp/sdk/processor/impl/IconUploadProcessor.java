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
import mobi.designmyapp.common.util.UtilsFactory;
import mobi.designmyapp.sdk.processor.UploadProcessor;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Loïc Ortola on 7/30/14
 * This class provides an implementation of an UploadProcessor (@see mobi.designmyapp.sdk.processor.UploadProcessor)
 * for the customIcon of the application. It furnishes a way to upload a custom icon for an application.
 */
public class IconUploadProcessor implements UploadProcessor<Image> {

  private static final String NAMESPACE = "icon";

  private final String iconFileName;

  private final List<String> validExtensions = Arrays.asList("png", "jpg", "jpeg", "gif");

  public IconUploadProcessor() {
    this.iconFileName = "icon.png";
  }

  public IconUploadProcessor(String iconFileName) {
    this.iconFileName = iconFileName;
  }

  /**
   * Retrieve namespace : representing where the uploaded file will be stored
   */
  @Override
  public String getNamespace() {
    return NAMESPACE;
  }

  /**
   * Process the upload request
   * @param request the UploadRequest. @see mobi.designmyapp.common.engine.model.UploadRequest.
   * @param destDir the destination directory. Contains all resources already uploaded through this implementation.
   * @return the uploaded image
   * @throws IOException
   */
  @Override
  public Image process(UploadRequest request, File destDir) throws IOException {

    String url = UtilsFactory.getContextService().createUrl(NAMESPACE, iconFileName, request.getApiKey());

    UtilsFactory.getImageUtils().resizeImageToIcon(request.getObj(), new File(destDir, iconFileName));

    return new Image(iconFileName, request.getOriginalFilename(), url);
  }

  /**
   * Retrieve valid extensions for this processor
   */
  @Override
  public List<String> getValidExtensions() {
    return validExtensions;
  }

}
