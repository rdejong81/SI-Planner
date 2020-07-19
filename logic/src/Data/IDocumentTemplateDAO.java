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

package Data;

import java.util.List;

/**
 * Retrieve, create and update {@link DocumentTemplate} objects from data source.
 * @author Richard de Jong
 * @version 1.0
 * @since 1.0
 * @see DocumentTemplate
 */
public interface IDocumentTemplateDAO
{
    /**
     * Request instances of {@link DocumentTemplate} in datasource.
     *
     * @return A read-only list of all instances of {@link Attribute}.
     * @since 1.0
     */
    List<DocumentTemplate> findAll();

    /**
     * Request instance of {@link DocumentTemplate} in datasource.
     *
     * @param id retrieve attribute by unique database id.
     * @return A read-only list of all instances of {@link Attribute}.
     * @since 1.0
     */
    DocumentTemplate findById(int id);

    /**
     * Insert new instance of {@link DocumentTemplate}
     *
     * @param documentTemplate instance of {@link DocumentTemplate}
     * @return true if successful
     * @since 1.0
     */
    DaoResult insertDocumentTemplate(DocumentTemplate documentTemplate);

    /**
     * Update instance of {@link DocumentTemplate} in datasource
     *
     * @param documentTemplate instance of {@link DocumentTemplate}
     * @return true if successful
     * @since 1.0
     */
    DaoResult updateDocumentTemplate(DocumentTemplate documentTemplate);

    /**
     * Delete instance of {@link DocumentTemplate} in datasource
     *
     * @param documentTemplate instance of {@link DocumentTemplate}
     * @return true if successful
     * @since 1.0
     */
    DaoResult deleteDocumentTemplate(DocumentTemplate documentTemplate);

}
