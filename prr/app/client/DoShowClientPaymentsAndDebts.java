package prr.app.client;

import prr.core.Network;
import prr.core.exception.UnknowKeyException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show the payments and debts of a client.
 */
class DoShowClientPaymentsAndDebts extends Command<Network> {

  DoShowClientPaymentsAndDebts(Network receiver) {
    super(Label.SHOW_CLIENT_BALANCE, receiver);
    addStringField("id", Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      _display.addLine(_receiver.showDebtPayments(stringField("id")));
      _display.display();
    } catch (UnknowKeyException e) {
      throw new UnknownClientKeyException(e.getKey());
    }
  }
}
