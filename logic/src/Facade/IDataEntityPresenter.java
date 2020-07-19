/*
 *    Copyright © 2020 Richard de Jong
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
    void hideDataEntity(DataEntity dataEntity);     // remove from presentation layer
}
