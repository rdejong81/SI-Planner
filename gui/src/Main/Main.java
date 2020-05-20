/*
 * Copyright © 2020 Richard de Jong.
 *
 * This file is part of gui
 *
 * gui is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * gui is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with gui.  If not, see <https://www.gnu.org/licenses/>.
 */

package Main;

import Database.DatabaseFactory;
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

        primaryStage.getIcons().add(new Image(Controller.class.getResourceAsStream("siplanner-small.png")));
        new SplashLoader(primaryStage,() -> {

                if (AppFacade.appFacade.DoLogin(new LoginController()))
                {
                    AppFacade.appFacade.showMain(new MainWindowController());
                }

        },3, (int task) -> {  // program initialization steps.
            switch(task)
            {
                case 1:// preferencesFx = PreferencesFx.of(Main.class,null);
               // preferencesFx.
                    Platform.runLater(()->{

                    });
                return "Loading windows";
                case 2: AppFacade.appFacade = new AppFacade(new DatabaseFactory());

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
