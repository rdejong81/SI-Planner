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

package Main;

import Database.DatabaseFactory;
import Facade.ISQLConnectionFactory;
import Facade.InboundConnectionFactory;
import Facade.InvoiceConnectionFactory;
import Inbound.InboundFactory;
import Invoice.InvoiceFactory;
import Windows.Controller;
import Windows.MainWindowController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Login.LoginController;
import Facade.AppFacade;
import Splash.SplashLoader;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage)
    {

        final ISQLConnectionFactory[] sqlConnectionFactory = new ISQLConnectionFactory[1];
        final InboundConnectionFactory[] inboundConnectionFactory = new InboundConnectionFactory[1];
        final InvoiceConnectionFactory[] invoiceConnectionFactory = new InvoiceConnectionFactory[1];

        primaryStage.getIcons().add(new Image(Controller.class.getResourceAsStream("siplanner-small.png")));
        new SplashLoader(primaryStage,() -> {
            LoginController login = new LoginController(sqlConnectionFactory[0]);
            for(String dbType : AppFacade.appFacade.getDatabaseTypes())
                login.addDbType(dbType);
            login.showAndWait();
                if (!login.getCancelled())
                {
                    MainWindowController mainWindowController = new MainWindowController();
                    mainWindowController.showAndWait();
                    AppFacade.appFacade.getDataSource().closeConnection();
                }

        },3, (int task) -> {  // program initialization steps.
            switch(task)
            {
                case 1: sqlConnectionFactory[0] = new DatabaseFactory();
                invoiceConnectionFactory[0] = new InvoiceFactory();
                inboundConnectionFactory[0] = new InboundFactory();
                return "Loading windows";
                case 2: AppFacade.appFacade = new AppFacade(sqlConnectionFactory[0],invoiceConnectionFactory[0],inboundConnectionFactory[0]);

                    return "Loaded windows...";
                case 3:

                    return "Completed.";

            }
            return "";
        });

    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
