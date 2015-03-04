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


import mobi.designmyapp.common.engine.builder.Builder;
import mobi.designmyapp.common.engine.model.Template;
import mobi.designmyapp.common.instance.manager.InstanceManager;

/**
 * Created by Christophe Deverre on 30/01/15.
 * The WebAppBuilder will allow you to do the final steps before your webapp is built:
 * Copy static resources to the right folder, replace template tokens, parse resource files.
 */
public abstract class WebappBuilder<T extends Template> implements Builder<T> {

  private Class<? extends InstanceManager> instanceManagerClass;

  public WebappBuilder(Class<? extends InstanceManager> instanceManagerClass) {
    this.instanceManagerClass = instanceManagerClass;
  }

  @Override
  public final Type getType() {
    return Type.WEBAPP;
  }

  /**
   * Retrieve the InstanceManager class
   * @see mobi.designmyapp.common.instance.manager.InstanceManager
   */
  public final Class<? extends InstanceManager> getInstanceManagerType() {
    return instanceManagerClass;
  }
}