package prr.app.main;

import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show global balance.
 */
class DoShowGlobalBalance extends Command<Network> {

  DoShowGlobalBalance(Network receiver) {
    super(Label.SHOW_GLOBAL_BALANCE, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
    _display.add(_receiver.showGlobalBalance());
    _display.display();
  }
}
