/*
Copyright © 2014 by eBusiness Information
All rights reserved. This source code or any portion thereof
may not be reproduced or used in any manner whatsoever
without the express written permission of eBusiness Information.
*/
package mobi.designmyapp.sdk.builder;

import mobi.designmyapp.common.api.builder.Builder;
import mobi.designmyapp.common.api.model.Generation;

/**
 * Created by Alexandre Nunesse on 7/16/14.
 * The AndroidBuilder will allow you to do the final steps before your apk is built:
 * Copy assets to the right folder, replace template tokens, parse resource files.
 */
public abstract class AndroidBuilder<U extends Generation> implements Builder<U> {

  @Override
  public void build(U generation) {

  }

  @Override
  public final Type getType() {
    return Type.ANDROID;
  }
}
