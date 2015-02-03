/*
Copyright © 2014 by eBusiness Information
All rights reserved. This source code or any portion thereof
may not be reproduced or used in any manner whatsoever
without the express written permission of eBusiness Information.
*/
package mobi.designmyapp.sdk.model;


import mobi.designmyapp.common.api.model.Template;
import mobi.designmyapp.sdk.builder.AndroidBuilder;
import mobi.designmyapp.sdk.builder.IosBuilder;
import mobi.designmyapp.sdk.builder.WebappBuilder;
import mobi.designmyapp.sdk.processor.ArchiveProcessor;
import mobi.designmyapp.sdk.processor.ContentProcessor;
import mobi.designmyapp.sdk.processor.PriceProcessor;
import mobi.designmyapp.sdk.processor.UploadProcessor;
import mobi.designmyapp.sdk.validator.ContentValidator;

import java.util.Collections;
import java.util.List;

/**
 * Created by Loïc Ortola on 7/7/14.
 * The Generator is the most important class of all.
 * It will be read by the generation engine to import your code.
 * Every Generator is associated with your template tag (unique identifier of your template).
 * Warning: Your generator class MUST be in the mobi.designmyapp.template.generator package!
 */
public abstract class Generator<T extends Template> {

  private final Class<T> type;

  public Generator(Class<T> type) {
    this.type = type;
  }

  public Class<T> getGenerationType() {
    return this.type;
  }

  public List<String> getStaticResources() {
    return Collections.emptyList();
  }

  public abstract String getTemplateTag();

  public ContentValidator getValidator() {
    return null;
  }

  public abstract ContentProcessor getProcessor();

  public PriceProcessor getPriceProcessor() {
    return null;
  }

  public List<UploadProcessor> getUploadProcessors() {
    return null;
  }

  public List<ArchiveProcessor> getArchiveProcessors() {
    return null;
  }

  public AndroidBuilder<T> getAndroidBuilder() {
    return null;
  }

  public IosBuilder<T> getIosBuilder() {
    return null;
  }

  public WebappBuilder<T> getWebappBuilder() {
    return null;
  }

}
