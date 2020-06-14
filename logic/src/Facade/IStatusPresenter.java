package Facade;

import Data.DataEntity;

/**
 * Interface that receives status updates, used to refresh presentation layer status information
 * @author Richard de Jong
 * @since 1.0
 */
public interface IStatusPresenter
{
    void statusTick(String statusMessage, int progress, int total);
}
