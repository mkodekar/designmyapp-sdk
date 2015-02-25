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
 * IconUploadProcessor implementation
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

  @Override
  public String getNamespace() {
    return NAMESPACE;
  }

  @Override
  public Image process(UploadRequest request, File destDir) throws IOException {

    String url = UtilsFactory.getResourceUtils().createUrl(request.getAppId(), NAMESPACE, iconFileName, request.getPortalName());

    UtilsFactory.getImageUtils().resizeImageToIcon(request.getObj(), new File(destDir, iconFileName));

    return new Image(iconFileName,request.getOriginalFilename(),url);
  }

  @Override
  public List<String> getValidExtensions() {
    return validExtensions;
  }

}
