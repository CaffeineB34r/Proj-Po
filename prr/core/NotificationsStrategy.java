/*
package prr.core;

import java.util.ArrayList;

interface NotificationsStrategy{
    

    public enum MeansNotification{
        SMS,
        EMAIL,
        TEXTNOTIFICATIONS,
    }

    public class Notifications{
        MeansNotification mn;

        public Notifications(Terminal context) {
            this._offToSilent = new ArrayList<Client>();
            this._silentToIdle = new ArrayList<Client>();
            this._busyToIdle = new ArrayList<Client>();
            this._busyToSilent = new ArrayList<Client>();
            this._terminal = context;
        }
    

        public void processNotificatio( MeansNotification mn){
            mn.notify(context);
        }
    }

    public void notifyMeans(MeansNotification context);


    public class SmsStrategy implements NotificationsStrategy{
        
        @Override
        public void notifyMeans(MeansNotification context){
            System.out.println("Notificação é"+ context);
        }
    }

    public class EmailStrategy implements NotificationsStrategy{
        
        @Override
        public void notifyMeans(MeansNotification context){
            System.out.println("Notificação é"+ context);
        }
    }
    public class TextNotificationsSrategy implements NotificationsStrategy{
        
        @Override
        public void notifyMeans(MeansNotification context){
            System.out.println("Notificação é"+ context);
        }
    }

    
}   
 */
