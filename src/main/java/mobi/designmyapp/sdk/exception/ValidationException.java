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
