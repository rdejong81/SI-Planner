package Facade;

import Data.DataEntity;

/**
 * Interface that receives updated data entity objects, used to refresh presentation layer with objects
 * @author Richard de Jong
 * @since 1.0
 */
public interface IDataEntityPresenter
{
    void showDataEntity(DataEntity dataEntity);     // show in presentation layer
    void hideDataEntity(DataEntity dataEntity); // remove from presentation layer
}
