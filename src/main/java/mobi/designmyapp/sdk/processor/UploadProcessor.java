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


import mobi.designmyapp.common.model.UploadRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Loïc Ortola on 23/7/14.
 * An upload processor is a descriptor used to upload and process template specific files
 * When uploading a file, a namespace has to be provided.
 * The namespace will determine which upload processor will be used
 * The processor will be able to specify:
 * -which extensions are valid or not
 * -a specific synchronized processing to apply before response is sent
 */
public abstract class UploadProcessor<T> {

  private final String nameSpace;

  public UploadProcessor(String nameSpace) {
    this.nameSpace = nameSpace;
  }

  public final String getNameSpace() {
    return nameSpace;
  }

  public abstract T process(UploadRequest request, File destDir) throws IOException;

  public abstract List<String> getValidExtensions();
}
