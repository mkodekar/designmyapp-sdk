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
