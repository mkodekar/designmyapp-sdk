package mobi.designmyapp.sdk.processor;


import mobi.designmyapp.common.api.model.Template;

/**
 * Created by Loïc Ortola on 07/07/14.
 * ContentProcessor is a descriptor class used to process all CPU-bound business-logic before a template gets generated.
 * Implementing ContentProcessor is not mandatory.
 */
public interface ContentProcessor<T extends Template> {

  /**
   * Called by the engine after the template generation request has been validated by @see mobi.designmyapp.sdk.validator.ContentValidator class.
   *
   * @param template the requested template object.
   */
  public abstract void process(T template);

}
