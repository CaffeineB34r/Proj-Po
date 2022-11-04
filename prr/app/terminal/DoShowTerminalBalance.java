package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show balance.
 */
class DoShowTerminalBalance extends TerminalCommand {

  DoShowTerminalBalance(Network context, Terminal terminal) {
    super(Label.SHOW_BALANCE, context, terminal);
  }

  @Override
  protected final void execute() throws CommandException {
    _display.addLine(Message.terminalPaymentsAndDebts(_receiver.getId(), Math.round(_receiver.getPayments()), Math.round(_receiver.getDebt())));
    _display.display();
  }
}
