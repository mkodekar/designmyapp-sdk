/*
Copyright © 2014 by eBusiness Information
All rights reserved. This source code or any portion thereof
may not be reproduced or used in any manner whatsoever
without the express written permission of eBusiness Information.
*/
package mobi.designmyapp.sdk.exception;

/**
 * Created by Loïc Ortola on 8/5/14.
 * Throw this exception when an error occurs during processing.
 */
public class ProcessorException extends RuntimeException {

  private static final long serialVersionUID = 5838950555025940381L;

  /**
   * Constructs a new processor exception with {@code null} as its
   * detail message and cause.
   */
  public ProcessorException() {
  }

  /**
   * Constructs a new processor exception with the specified detail message and no cause.
   */
  public ProcessorException(String message) {
    super(message);
  }

  /**
   * Constructs a new processor exception with the specified detail message and
   * cause.
   */
  public ProcessorException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new processor exception with the specified cause and a
   * detail message of <tt>(cause==null ? null : cause.toString())</tt>
   * (which typically contains the class and detail message of
   * <tt>cause</tt>).
   */
  public ProcessorException(Throwable cause) {
    super(cause);
  }

}
