/*
Copyright © 2014 by eBusiness Information
All rights reserved. This source code or any portion thereof
may not be reproduced or used in any manner whatsoever
without the express written permission of eBusiness Information.
*/
package mobi.designmyapp.sdk.builder;

import mobi.designmyapp.common.builder.Builder;
import mobi.designmyapp.common.model.Generation;

/**
 * Created by Alexandre Nunesse on 7/16/14.
 * The IosBuilder will allow you to do the final steps before your apk is built:
 * Copy assets to the right folder, replace template tokens, parse resource files.
 */
public abstract class IosBuilder<T extends Generation> implements Builder<T> {
  @Override
  public final Type getType() {
    return Type.IOS;
  }
}
