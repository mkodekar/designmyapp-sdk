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
 * Created by Christophe Deverre on 28/07/14.
 * ArchiveProcessor is a descriptor class which enables the creation of custom CPU-bound processing after uploading a zip file.
 * Is tightly coupled to @see mobi.designmyapp.sdk.processor.impl.ZipUploadProcessor .
 * The ArchiveProcessor can be Typed with a POJO used as payload in the Http Response.
 */
public interface ArchiveProcessor<T> {

  String getNamespace();

  /**
   * When uploading a file, the engine uses a @see mobi.designmyapp.sdk.processor.impl.ZipUploadProcessor to self-extract the resource,
   * then calls this method automatically.
   *
   * @param request        the @see mobi.designmyapp.common.model.UploadRequest object.
   * @param destDir        the directory where processed resources are to be stored.
   * @param unhandledFiles the list of files that were ignored (not matching the @see mobi.designmyapp.sdk.processor.ArchiveProcessor#getValidExtensions()
   * @return any POJO to be serialized and returned as a payload in the Http response.
   * @throws IOException
   */
  T process(UploadRequest request, File destDir, List<String> unhandledFiles) throws IOException;

  /**
   * @return a List of valid extensions accepted by the UploadProcessor (i.e. "png", "jpg", "csv", ...)
   */
  List<String> getValidExtensions();

}
