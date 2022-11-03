package prr.app.client;

import prr.core.Network;
import prr.core.exception.IllegalModeException;
import prr.core.exception.UnknownKeyException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Enable client notifications.
 */
class DoEnableClientNotifications extends Command<Network> {

  DoEnableClientNotifications(Network receiver) {
    super(Label.ENABLE_CLIENT_NOTIFICATIONS, receiver);
    addStringField("id", Message.key());
  }

  @Override
  protected final void execute() throws CommandException {
    String clientId = stringField("id");
    try {
      _receiver.enableClientNotifications(clientId);
    } catch (UnknownKeyException e) {
      throw new UnknownClientKeyException(clientId);
    } catch (IllegalModeException e) {
      _display.addLine(Message.clientNotificationsAlreadyEnabled());
      _display.display();
    }
  }
}
