# Ip2Proxy-Telegram-Bot

Ip2ProxyBot is a modern telegram bot that uses both BIN and CSV ip2Proxy database to retrive informations and statistics about proxies. 

Installation 
------------
- Download this project zip 
- Extract it  
- Go to [Telegram Bot Father](https://web.telegram.org/#/im?p=@BotFather) 
- Copy these [Commands] (#)
- Open the project with and ide 
- Include externals jars from the directory: [External Jars] (#)
- Fill the field "botToken" and "botUsername" in [Bot Settings File]() with data retrive from [Telegram Bot Father](https://web.telegram.org/#/im?p=@BotFather) 

Usage And Commands
------------------
Every sessions starts with the */start* command. At this point will appear a keyboard throught which you can select on option among: 
- */IsProxy*
- */Charts*
- */Info*

***/IsProxy*** is used to check if an ip is a proxy, in this case the bot will show details about the proxy, otherwise it will tell the user that the inserted ip is not a proxy. In the case the inserted ip is not valid, the bot requests for another ip.  

***/Charts*** return a keyboard throught you can choose between: 
- */typeChart* 
- */locationChart* 
 The first will return an image representin a chart with the percentuage of every type of proxy, the other will return a chart with different proxy location (in this case only country with a minimun of 2% of total proxies create a sections, otherwise they are added in "Other" section 
 
 ***/Info*** return some infos about database version 
 
3rd party libraries 
-------------------



Official BIN Library Modifications
----------------------------------
The [Official BIN Library]() has been modified to a better implemention of OOP 

Databases 
---------

Screenshots 
----------- 

Avaibility 
----------
This bot will be hosted and will be online between 01/10/2017 and 15/10/2017 at this [URL]()
(To try this bot after this period please send me an email) 
