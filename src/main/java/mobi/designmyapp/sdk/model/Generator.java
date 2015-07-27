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
package mobi.designmyapp.sdk.model;


import mobi.designmyapp.common.engine.model.Template;
import mobi.designmyapp.sdk.builder.AndroidBuilder;
import mobi.designmyapp.sdk.builder.ContainerBuilder;
import mobi.designmyapp.sdk.builder.IosBuilder;
import mobi.designmyapp.sdk.processor.ArchiveProcessor;
import mobi.designmyapp.sdk.processor.ContentProcessor;
import mobi.designmyapp.sdk.processor.PriceProcessor;
import mobi.designmyapp.sdk.processor.UploadProcessor;
import mobi.designmyapp.sdk.validator.ContentValidator;

import java.util.Arrays;
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
  private static final List<String> DEFAULT_VERSIONS = Arrays.asList(new String[]{"1.0.0"});

  /**
   * Constructor.
   * @param type the type
   */
  public Generator(Class<T> type) {
    this.type = type;
  }

  /**
   * Retrieve this generator template class.
   *
   * @return template class
   */
  public Class<T> getTemplateClass() {
    return this.type;
  }

  /**
   * Retrieve the template static resources files
   * such as data or image files.
   *
   * @return filename string list
   */
  public List<String> getStaticResources() {
    return Collections.emptyList();
  }

  /**
   * Retrieve the template unique tag.
   * @return the template tag
   */
  public abstract String getTemplateTag();

  /**
   * Retrieve template specific content validator container.
   *
   * @return @see mobi.designmyapp.sdk.validator.ContentValidator
   */
  public ContentValidator getValidator() {
    return null;
  }

  /**
   * Retrieve template specific content processor container.
   *
   * @return @see mobi.designmyapp.sdk.processor.ContentProcessor
   */
  public ContentProcessor getProcessor() {
    return null;
  }

  /**
   * Retrieve template specific content price container.
   *
   * @return @see mobi.designmyapp.sdk.processor.PriceProcessor
   */
  public PriceProcessor getPriceProcessor() {
    return null;
  }

  /**
   * Retrieve template specific upload processors containers as a list.
   *
   * @return @see mobi.designmyapp.sdk.processor.UploadProcessor
   */
  public List<UploadProcessor> getUploadProcessors() {
    return null;
  }

  /**
   * Retrieve template specific archive processor containers as a list.
   *
   * @return @see mobi.designmyapp.sdk.processor.ArchiveProcessor
   */
  public List<ArchiveProcessor> getArchiveProcessors() {
    return null;
  }

  /**
   * Retrieve template specific AndroidBuilder container.
   *
   * @return @see mobi.designmyapp.sdk.builder.AndroidBuilder
   */
  public AndroidBuilder<T> getAndroidBuilder() {
    return null;
  }

  /**
   * Retrieve template specific IosBuilder container.
   *
   * @return @see mobi.designmyapp.sdk.builder.IosBuilder
   */
  public IosBuilder<T> getIosBuilder() {
    return null;
  }

  /**
   * Retrieve template specific WebappBuilder container.
   *
   * @return @see mobi.designmyapp.sdk.builder.ContainerBuilder
   */
  public ContainerBuilder<T> getContainerBuilder() {
    return null;
  }

  /**
   * Retrieve template available versions.
   * Default value is 1.0.0 and should be overridden as soon as there is a new one.
   * Warning: Versions should always be declared in chronogical order: oldest first.
   * example: {"1.0.0", "1.1.0", "1.2.0", "2.0.0"}
   *
   * @return the list of available versions
   */
  public List<String> getVersions() {
    return DEFAULT_VERSIONS;
  }

}
