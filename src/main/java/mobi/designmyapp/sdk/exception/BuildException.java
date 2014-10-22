/*
Copyright © 2014 by eBusiness Information
All rights reserved. This source code or any portion thereof
may not be reproduced or used in any manner whatsoever
without the express written permission of eBusiness Information.
*/
package mobi.designmyapp.sdk.exception;

/**
 * Created by Loïc Ortola on 8/5/14.
 * Throw this unckecked exception when an error occurs during build.
 */
public class BuildException extends RuntimeException {

  private static final long serialVersionUID = 4104825554981723933L;

  /**
   * Constructs a new build exception with {@code null} as its
   * detail message and cause.
   */
  public BuildException() {
  }

  /**
   * Constructs a new build exception with the specified detail message and no cause.
   */
  public BuildException(String message) {
    super(message);
  }

  /**
   * Constructs a new build exception with the specified detail message and
   * cause.
   */
  public BuildException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new build exception with the specified cause and a
   * detail message of <tt>(cause==null ? null : cause.toString())</tt>
   * (which typically contains the class and detail message of
   * <tt>cause</tt>).
   */
  public BuildException(Throwable cause) {
    super(cause);
  }

}
