package com.example.notificationpart2.constants;

public class Constants {



/*           5 methods of JobInfo.Builder
*
*          1. setMinimumLatency(long minLatencyMillis)  =   it specifies that the job should be delayeed of minLatencyMillis.
*          2. setPeriodic(long intervalMillis)   =  it specifies that job should repeat after intervalMillis
*          3. setPersisted(boolean isPersisted)  =  it specifies that job will be persisted after reboot of device by using boolean isPersisted
*          4. setRequiresCharging(boolean requiresCharging) =   it sets a condition that this job requires device to be in charging condition or not not by using the boolean requiresCharging
*          5. setRequiresDeviceIdle(boolean requiresDeviceIdle) =   it ensures that phone should be in idle mode or not for this job to work and this is set by the boolean requiresDeviceIdle
*
* */














    /*Info about channels

    from API 26 and above every notification must be assigned to a channel
    where developer describe the visual as well as the sound behaviour of the channel
    which is apply to all notifications come under that channel


      id is unique to every channel by which channel is identified
      title is use to set the title of channel
      in description developer describe the purpose of that channel


     These channel can be reflected in the user settings in app info

    */

    public static final String CHANNEL_ONE_ID = "ch one id";
    public static final String CHANNEL_TWO_ID = "ch two id";
    public static final String CHANNEL_FIRST_TITLE = "First channel";
    public static final String CHANNEL_SECOND_TITLE = "Second channel";
    public static final String CHANNEL_ONE_DESCRIPTION = "with sound and vibration";
    public static final String CHANNEL_TWO_DESCRIPTION = "silent";


/*

Notification is a message that displayed by the user device outside developer's app user interface

id is unique to every notification by which notification is identified
in message developer tells the data should show to the user

*/
    public static final int NOTIFICATION_FIRST_ID = 1;
    public static final int NOTIFICATION_SECOND_ID = 2;
    public static final String NOTIFICATION_FIRST_TITLE = "First";
    public static final String NOTIFICATION_SECOND_TITLE = "Second";
    public static final String NOTIFICATION_FIRST_MESSAGE= "first description with vibration and sound";
    public static final String NOTIFICATION_SECOND_MESSAGE = "second description with silence";


}
