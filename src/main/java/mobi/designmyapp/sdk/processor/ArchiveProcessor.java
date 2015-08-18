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
package mobi.designmyapp.sdk.processor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import mobi.designmyapp.common.engine.model.UploadRequest;

/**
 * Created by Christophe Deverre on 28/07/14. {@link ArchiveProcessor}
 * is a descriptor class which enables the creation of custom CPU-bound
 * processing multiple files. This class is intended to be used in conjunction
 * with {@link ArchiveExtractorUploadProcessor}. When you upload an archive, you may
 * provide an {@link ArchiveExtractorUploadProcessor} as the
 * {@link UploadProcessor}. In this case, you can also provide an extra
 * {@link ArchiveProcessor} to have its
 * {@link #process(UploadRequest, File, List)} method triggered after extraction
 * of each file. The {@link ArchiveProcessor} can be Typed with a POJO
 * used as payload in the Http Response.
 */
public interface ArchiveProcessor<T> {



  /**
   * When uploading an archive file, the engine uses a
   * {@link ArchiveExtractorUploadProcessor} to self-extract the resource, then calls
   * this method automatically.
   *
   * @param request the @see mobi.designmyapp.common.model.UploadRequest object.
   * @param destDir the directory where processed resources are to be stored.
   * @param unhandledFiles the list of files that were ignored (not matching the @see
   *          mobi
   *          .designmyapp.sdk.processor.ArchiveProcessor#getValidExtensions()
   * @return any POJO to be serialized and returned as a payload in the Http
   *         response.
   * @throws IOException {@link IOException}
   */
  T process(UploadRequest request, File destDir, List<String> unhandledFiles) throws IOException;


  /**
   * @return a List of valid extensions accepted by the UploadProcessor (i.e. "png", "jpg", "csv", ...)
   */
  List<String> getValidExtensions();

  /**
   * The namespace identifies the UploadProcessor. The namespace should
   * represent the kind of entity which is bound to be uploaded (i.e:
   * "spreadsheet", "image", ).
   *
   * @return the current UploadProcessor Implementation namespace.
   */
  String getNamespace();

}
