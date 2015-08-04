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


import mobi.designmyapp.common.container.model.MigrationDescriptor;
import mobi.designmyapp.common.engine.model.Template;
import mobi.designmyapp.sdk.builder.AndroidBuilder;
import mobi.designmyapp.sdk.builder.ContainerBuilder;
import mobi.designmyapp.sdk.builder.IosBuilder;
import mobi.designmyapp.sdk.processor.ArchiveProcessor;
import mobi.designmyapp.sdk.processor.ContentProcessor;
import mobi.designmyapp.sdk.processor.PriceProcessor;
import mobi.designmyapp.sdk.processor.UploadProcessor;
import mobi.designmyapp.sdk.validator.ContentValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Loïc Ortola on 7/7/14.
 * The Generator is the most important class of all.
 * It will be read by the generation engine to import your code.
 * Every Generator is associated with your template tag (unique identifier of your template).
 * Warning: Your generator class MUST be in the mobi.designmyapp.template.generator package!
 */
public abstract class Generator<T extends Template> {

  private final Class<T> type;
  private final Map<String, MigrationDescriptor> migrationDescriptorMap = new HashMap<>();
  private final Set<String> versions = new TreeSet<>();
  private final Set<String> staticResources = new HashSet<>();

  /**
   * Constructor.
   *
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
   * Retrieve the template unique tag.
   *
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
   * Retrieve the template static resources files
   * such as data or image files.
   *
   * @return filename string list
   */
  public final List<String> getStaticResources() {
    return Collections.unmodifiableList(new ArrayList<>(staticResources));
  }

  /**
   * Retrieve template available versions.
   * Default value is 1.0.0 and will be overridden as soon as one is provided.
   * Warning: Versions will always be sorted with their natural ordering.
   * example: {"1.0.0", "1.1.0", "1.2.0", "2.0.0"}
   *
   * @return the list of available versions
   */
  public final List<String> getVersions() {
    return Collections.unmodifiableList(versions.isEmpty() ? Arrays.asList("1.0.0") : new ArrayList<>(versions));
  }

  /**
   * Retrieve the migration descriptor for the desired migration.
   *
   * @param from the old version
   * @param to   the new version
   * @return the migration descriptor
   */
  public final MigrationDescriptor getMigrationDescriptor(String from, String to) {
    return migrationDescriptorMap.get(from + "-" + to);
  }

  /**
   * Add a static resource path to the existing static resources.
   *
   * @param path the desired path
   */
  protected final void addStaticResource(String path) {
    staticResources.add(path);
  }

  /**
   * Add a version to the versions supported.
   *
   * @param version the version to add
   */
  protected final void addVersion(String version) {
    versions.add(version);
  }

  /**
   * Add versions to the versions supported.
   *
   * @param versions the versions to add
   */
  protected final void addVersions(String... versions) {
    this.versions.addAll(Arrays.asList(versions));
  }

  /**
   * Add a migration descriptor to the map.
   *
   * @param migrationDescriptor the migration descriptor
   */
  protected final void addMigrationDescriptor(MigrationDescriptor migrationDescriptor) {
    migrationDescriptorMap.put(migrationDescriptor.getVersionFrom() + "-" + migrationDescriptor.getVersionTo(), migrationDescriptor);
  }

}
