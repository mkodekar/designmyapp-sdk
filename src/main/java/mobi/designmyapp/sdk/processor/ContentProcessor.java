package mobi.designmyapp.sdk.processor;


import mobi.designmyapp.common.api.model.Generation;

/**
 * Created by Loïc Ortola on 7/7/14.
 */
public interface ContentProcessor<T extends Generation> {

  public abstract void process(T generation);

}
