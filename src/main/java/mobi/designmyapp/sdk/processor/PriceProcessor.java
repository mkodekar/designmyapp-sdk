/*
 Copyright 2014 eBusiness Information

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

import mobi.designmyapp.common.model.Generation;
import mobi.designmyapp.common.model.Pricing;

import mobi.designmyapp.common.model.Price;

/**
 * Created by Loïc Ortola on 24/7/14.
 * A price processor is a descriptor used to declare your pricing computation logic
 */
public abstract class PriceProcessor<T extends Generation> {

  /**
   * Your Pricing class descriptor (used for reflection)
   */
  private final Class<? extends Pricing> type;

  /**
   * Sole constructor
   * @param type your Pricing class descriptor. Should extend Pricing.class, but rather your own implementation
   */
  public PriceProcessor(Class<? extends Pricing> type) {
    this.type = type;
  }

  /**
   * Get the Pricing class descriptor (used for reflection)
   * @return
   */
  public Class<? extends Pricing> getPricingType() {
    return this.type;
  }

  /**
   * Compute the price of your template
   * @param generation your generation object, which will be used to determine your pricing
   * @return
   */
  public abstract Price computePrice(T generation);

}
