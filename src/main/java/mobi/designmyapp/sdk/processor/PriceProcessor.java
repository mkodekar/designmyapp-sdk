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

import mobi.designmyapp.common.engine.model.Pricing;
import mobi.designmyapp.common.engine.model.Template;

import java.math.BigDecimal;

/**
 * Created by Loïc Ortola on 24/07/14.
 * PriceProcessor is a descriptor class used to declare the pricing computation logic of a template.
 * Generics Types should be:
 * - your Template custom implementation class
 * - your Pricing custom implementation class
 */
public abstract class PriceProcessor<T extends Template, P extends Pricing> {

  /**
   * Pricing class descriptor (used for reflection)
   */
  private final Class<? extends Pricing> type;

  /**
   * @param type the Custom Pricing class. Should extend Pricing.class.
   */
  public PriceProcessor(Class<P> type) {
    if (Pricing.class.equals(type)) {
      throw new IllegalArgumentException("Cannot provide default Pricing class to the PriceProcessor constructor. Only custom implementations are allowed.");
    }
    this.type = type;
  }

  /**
   * @return the Pricing class descriptor (used for reflection)
   */
  public Class<? extends Pricing> getPricingType() {
    return this.type;
  }

  /**
   * Called by the engine to compute the price of a template.
   *
   * @param template the requested template object, used to determine the price depending on the options chosen.
   * @return the computed Price.
   */
  public abstract BigDecimal computePrice(T template);

}