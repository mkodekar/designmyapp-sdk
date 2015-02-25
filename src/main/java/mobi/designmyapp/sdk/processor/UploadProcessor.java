/*
 Copyright 2014 eBusiness Information

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


import mobi.designmyapp.common.engine.model.UploadRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Loïc Ortola on 23/07/14.
 * UploadProcessor is a descriptor class used to upload and process template specific files.
 * Provide your own implementation(s) if resource upload features are needed through the API.
 * Implementing UploadProcessor sub classes is not mandatory.
 * An UploadProcessor specifies:
 * - which extensions are valid or not
 * - which specific processing to apply before response is sent. Warning: the processing is synchronous.
 * The UploadProcessor can be Typed with a POJO used as payload in the Http Response.
 */
public interface UploadProcessor<T> {

  /**
   * The namespace identifies the UploadProcessor.
   * The namespace should represent the kind of entity which is bound to be uploaded (i.e: "spreadsheet", "image", ).
   *
   * @return the current UploadProcessor Implementation namespace.
   */
  String getNamespace();

  /**
   * Called by the engine when a file has been successfully uploaded for the current matching namespace.
   *
   * @param request the UploadRequest. @see mobi.designmyapp.common.model.UploadRequest.
   * @param destDir the destination directory. Contains all resources already uploaded through this implementation.
   * @return any POJO to be serialized and returned as a payload in the Http response.
   * @throws IOException
   */
  T process(UploadRequest request, File destDir) throws IOException;

  /**
   * @return a List of valid extensions accepted by the UploadProcessor (i.e. "png", "jpg", "csv", ...)
   */
  List<String> getValidExtensions();

}
