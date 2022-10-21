package prr.app.client;

import prr.core.Network;
import prr.core.exception.UnknownKeyException;

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
      String id = stringField("id");
      long pay = _receiver.getClientPayments(stringField(id));
      long owe = _receiver.getClientDebts(stringField(id));

      _display.addLine(Message.clientPaymentsAndDebts(id,pay,owe));
      _display.display();
    } catch (UnknownKeyException e) {
      throw new UnknownClientKeyException(e.getKey());
    }
  }
}
