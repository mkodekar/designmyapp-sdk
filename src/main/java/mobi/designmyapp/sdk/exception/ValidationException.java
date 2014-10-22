/*
Copyright © 2014 by eBusiness Information
All rights reserved. This source code or any portion thereof
may not be reproduced or used in any manner whatsoever
without the express written permission of eBusiness Information.
*/
package mobi.designmyapp.sdk.exception;

/**
 * Created by Loïc Ortola on 8/6/14.
 * Throw this exception when an error occurs during validation.
 */
public class ValidationException extends RuntimeException {

  private static final long serialVersionUID = 5237302654167629391L;

  /**
   * Constructs a new validation exception with the default detail message and no cause.
   */
  public ValidationException() {
    super("Error while validating generation request.");
  }

  /**
   * Constructs a new validation exception with the specified detail message and no cause.
   */
  public ValidationException(String message) {
    super(message);
  }

  /**
   * Constructs a new validation exception with the specified detail message and
   * cause.
   */
  public ValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new validation exception with the specified cause and a
   * detail message of <tt>(cause==null ? null : cause.toString())</tt>
   * (which typically contains the class and detail message of
   * <tt>cause</tt>).
   */
  public ValidationException(Throwable cause) {
    super(cause);
  }

}
