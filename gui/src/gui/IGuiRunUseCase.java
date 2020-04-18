/*

   Triggers to call a use case without having to import the main module causing a circular dependency.

 */

package gui;

public interface IGuiRunUseCase
{
   Boolean runUseCase(Controller window);
}
