package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.IllegalModeException;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Silence the terminal.
 */
class DoSilenceTerminal extends TerminalCommand {

  DoSilenceTerminal(Network context, Terminal terminal) {
    super(Label.MUTE_TERMINAL, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      _receiver.setOnSilent();
    } catch (IllegalModeException e) {
      _display.addLine(Message.alreadySilent());
      _display.display();
    }
  }
}
