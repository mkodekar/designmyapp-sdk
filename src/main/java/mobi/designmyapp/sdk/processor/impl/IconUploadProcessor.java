package mobi.designmyapp.sdk.processor.impl;

import mobi.designmyapp.common.api.model.UploadRequest;
import mobi.designmyapp.common.api.utils.UtilsFactory;
import mobi.designmyapp.common.api.model.Image;
import mobi.designmyapp.sdk.processor.UploadProcessor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loïc Ortola on 7/30/14
 * IconUploadProcessor implementation
 */
public class IconUploadProcessor extends UploadProcessor {

  public static final String NAMESPACE = "icon";

  private List<String> validExtensions;

  public IconUploadProcessor() {
    super(NAMESPACE);
    validExtensions = new ArrayList<>();
    validExtensions.add("png");
    validExtensions.add("jpg");
    validExtensions.add("jpeg");
  }

  @Override
  public Object process(UploadRequest request, File destDir) throws IOException {

    String url = UtilsFactory.getResourceUtils().createUrl(request.getAppId(), NAMESPACE, "icon.png", request.getPortalName());

    UtilsFactory.getImageUtils().resizeImageToIcon(request.getObj(), new File(destDir, "icon.png"));

    return new Image("icon.png",request.getOriginalFilename(),url);
  }

  @Override
  public List<String> getValidExtensions() {
    return validExtensions;
  }
}
