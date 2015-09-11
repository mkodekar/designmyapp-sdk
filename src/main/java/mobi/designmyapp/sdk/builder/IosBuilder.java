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
import java.util.Collections;
import java.util.List;


/**
 * Created by Alexandre Nunesse on 7/16/14.
 * The IosBuilder will allow you to do the final steps before your ipa is built:
 * Copy assets to the right folder, replace template tokens, parse resource files.
 */
public abstract class IosBuilder<T extends Template> implements Builder<T> {
  @Override
  public final Type getType() {
    return Type.IOS;
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
