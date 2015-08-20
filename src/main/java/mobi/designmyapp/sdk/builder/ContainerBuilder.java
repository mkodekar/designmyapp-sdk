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
package mobi.designmyapp.sdk.builder;


import mobi.designmyapp.common.container.manager.ContainerManager;
import mobi.designmyapp.common.container.model.Container;
import mobi.designmyapp.common.engine.builder.Builder;
import mobi.designmyapp.common.engine.model.Template;

import java.util.Collections;
import java.util.List;

/**
 * Created by Christophe Deverre on 30/01/15.
 * The WebAppBuilder will allow you to do the final steps before your webapp is built:
 * Copy static resources to the right folder, replace template tokens, parse resource files.
 */
public abstract class ContainerBuilder<T extends Template> implements Builder<T> {

  @Override
  public final Type getType() {
    return Type.CONTAINER;
  }

  /**
   * This method should handle all Container launches from your ContainerManager.
   * This ensures assets have been previously compressed and made available as a bundle.zip file
   *
   * @param template         the template
   * @param containerManager the container manager
   */
  public abstract void launch(T template, ContainerManager containerManager);

  /**
   * This method is used to format what you want the API to return.
   * By default, when you start one or more containers, it will output all containers complete information.
   * Usually, only some of the links make sense for you.
   * Warning: The object returned should be serializable. It will be converted to its JSON representation.
   *
   * @param containers the containers built by one template generation
   * @return the JSON Object representation of what you wish the API to send back.
   */
  public Object formatResponse(Container... containers) {
    return containers;
  }

  /**
   * This method declares the necessary build steps related to the current template build cycle.
   * For instance, you could have the following build step keys: [ "init_containers", "download_assets", "resize_assets", "pre_rendering" ].
   * The build step keys will be exposed on the API.
   * @param template the template
   * @return the list of build step keys
   */
  public List<String> getBuildSteps(T template) {
    return Collections.emptyList();
  }
}