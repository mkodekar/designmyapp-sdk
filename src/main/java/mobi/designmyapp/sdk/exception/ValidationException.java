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
  public ValidationException() {
    super("Error while validating generation request.");
  }

  public ValidationException(String message) {
    super(message);
  }
}
