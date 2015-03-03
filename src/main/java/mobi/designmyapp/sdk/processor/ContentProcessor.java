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
package mobi.designmyapp.sdk.processor;


import mobi.designmyapp.common.engine.model.Template;

/**
 * Created by Loïc Ortola on 07/07/14.
 * ContentProcessor is a descriptor class used to process all CPU-bound business-logic before a template gets generated.
 * Implementing ContentProcessor is not mandatory.
 */
public interface ContentProcessor<T extends Template> {

  /**
   * Called by the engine after the template generation request has been validated by @see mobi.designmyapp.sdk.validator.ContentValidator class.
   *
   * @param template the requested template object.
   */
  public abstract void process(T template);

}
