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

import mobi.designmyapp.common.api.model.UploadRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Christophe Deverre on 28/07/14.
 * The ArchiveProcessor enables to create custom processing when uploading a zip file.
 * The generic ZipUploadProcessor will extract the resource, and give you the outputDirectory in the upload request object.
 */
public abstract  class ArchiveProcessor {

    private final String nameSpace;

    public ArchiveProcessor(String nameSpace) {
      this.nameSpace = nameSpace;
    }

    public final String getNameSpace() {
      return nameSpace;
    }

    public abstract Object process(UploadRequest request,File destDir, List<String> unhandledFiles) throws IOException;

    public abstract List<String> getValidExtensions();

}
