package mobi.designmyapp.sdk.processor;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import mobi.designmyapp.common.engine.model.UploadRequest;
import mobi.designmyapp.common.util.UtilsFactory;


/**
 * An {@link ArchiveExtractorUploadProcessor} is an {@link ArchiveProcessor}
 * that handles the extraction of an archive. It can hold an
 * {@link UploadProcessor}, which process method will be triggered automatically
 * when extraction is finished.
 *
 * @author Nicolas THIERION
 * @param <T>
 */
public abstract class ArchiveExtractorUploadProcessor<T> implements UploadProcessor<T> {
  private ArchiveProcessor<T> archiveProcessor;

  @Override
  public final T process(UploadRequest request, File destDir) throws IOException {
    if (archiveProcessor == null) {
      throw new IllegalStateException("Cannot call 'process' method on "
          + ArchiveExtractorUploadProcessor.class.getSimpleName()
          + "' while no UploadProcessor is set. (Did not call 'setUploadProcessor()' method");
    }

    String filename = request.getDestFilename();
    // Auto generate a filename
    if (filename == null) {
      filename = "tmp" + System.currentTimeMillis() + ".archive";
      request.setDestFilename(filename);
    }

    File tmpArchiveFile = new File(destDir, filename);

    // Write the stream to a new file
    UtilsFactory.getIOUtils().copyInputStreamToFile(request.getObj(), tmpArchiveFile);

    String zipHash = UtilsFactory.getDigestUtils().createHash(tmpArchiveFile);
    File archiveFile = new File(destDir, zipHash + ".archive");

    if (!archiveFile.exists()) {
      UtilsFactory.getIOUtils().moveFile(tmpArchiveFile, archiveFile);
    } else {
      tmpArchiveFile.delete();
    }
    LinkedList<String> unhandledFiles = new LinkedList<>();

    destDir = extract(request, archiveFile, unhandledFiles);
    return archiveProcessor.process(request, destDir, unhandledFiles);
  }

  /**
   * Extracts the file contained in the given {@link UploadRequest}.
   *
   * @param request the upload request containing file to extract.
   * @param archiveFile the arcive file to extract.
   * @param unhandledFiles the files unhandled by the wrapped
   *          {@link UploadProcessor}
   * @return the root directory where files have been extracted.
   * @throws IOException Signals that an I/O exception has occurred while
   *           extracting.
   */
  protected abstract File extract(UploadRequest request, File archiveFile, List<String> unhandledFiles)
      throws IOException;

  public void setUploadProcessor(ArchiveProcessor<T> archiveProcessor) {
    this.archiveProcessor = archiveProcessor;
  }

  public ArchiveProcessor<T> getUploadProcessor() {
    return archiveProcessor;
  }
}
