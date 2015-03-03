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
package mobi.designmyapp.sdk.validator;

import mobi.designmyapp.common.engine.model.Template;

/**
 * Created by Loïc Ortola on 07/07/14.
 * ContentValidator checks that the Template object sent is semantically valid.
 * Although the implementation is not mandatory, it is highly recommended to do so when submitting a template.
 * It will prevent illegal use of the template.
 */
public interface ContentValidator<T extends Template> {

  /**
   * The method will be called automatically by the engine at generation time.
   * Only elements which have an impact on the destination template (your business-logic) should be semantically validated here.
   * No CPU-Bound operations should be done here, as those will be handled during the processing step.
   *
   * @param obj the template object to validate
   */
  void validate(T obj);
}
