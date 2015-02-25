/*
Copyright © 2014 by eBusiness Information
All rights reserved. This source code or any portion thereof
may not be reproduced or used in any manner whatsoever
without the express written permission of eBusiness Information.
*/
package mobi.designmyapp.sdk.builder;


import mobi.designmyapp.common.engine.builder.Builder;
import mobi.designmyapp.common.engine.model.Template;

/**
 * Created by Christophe Deverre on 30/01/15.
 * The WebAppBuilder will allow you to do the final steps before your webapp is built:
 * Copy static resources to the right folder, replace template tokens, parse resource files.
 */
public abstract class WebappBuilder<T extends Template> implements Builder<T> {

  @Override
  public final Type getType() {
    return Type.WEBAPP;
  }
}