---
layout: post
title: MQ Light Tutorial
permalink: /mqlighttutorial/
---

###MQ Light Background
IBM® [MQ Light for Bluemix™](https://www.ng.bluemix.net/docs/#services/MQLight/index.html#mqlight010) is a cloud based messaging service that allows easy messaging between apps. 

In this tutorial you will learn how to use MQ Light. You will also learn how to implement the service , how it can be configured to suite one's needs and how it can be applied in your future programs. 

####Logging in to your Bluemix Account
1. Login to your Bluemix Account. 

2. If you don't have an account yet or forgot to create one since it expired, create one. 


####Downloading the `.war` files
In this part, you will download two applications to be deployed at Bluemix.


1. Create the directory `mqlighttutorial` in the root directory. 

2. Download [MQLight-Sender.war](https://github.com/riv5181/riv5181.github.io/blob/master/MQ-Light-Resources/Source%20Codes/mqlight-tutorial-sender/build/libs/MQLight-Sender.war?raw=true) and save it in the `mqlighttutorial` subdirectory. 

3. Download [MQLight-Receiver.war](https://github.com/riv5181/riv5181.github.io/blob/master/MQ-Light-Resources/Source%20Codes/mqlight-tutorial-receiver/build/libs/MQLight-Receiver.war?raw=true) and save it in the `mqlighttutorial` subdirectory. 

<br>


####Deploy MQ Light Applications in Bluemix using the `cf` tool.

1. Open a terminal window and go to the `mqlighttutorial` subdirectory.

2. Login to your Bluemix account using the `cf` tool.

	```text
	> cf login -a https://api.ng.bluemix.net -s dev
	```
	
	>When asked for a username (e-mail address) and password, enter the username and password of your Bluemix account.
	
	
	The `-a` switch allows you to specify the URL of the Bluemix region.  In this tutorial, you are using the `US South` region.  The URL of this region is `https://api.ng.bluemix.net`.

	The `-s` switch allows you to specify the space where you will deploy the application.  In this tutorial, you will be deploying the application in the `dev` space you created earlier.

	<br>
	
3. Upload the [MQLight-Sender.war](https://github.com/riv5181/riv5181.github.io/blob/master/MQ-Light-Resources/Source%20Codes/mqlight-tutorial-sender/build/libs/MQLight-Sender.war?raw=true) file to your Bluemix account.

	```text
	> cf push MQLight-Sender-<your_name> -m 256M -p MQLight-Sender.war
	```
	
4. Upload the [MQLight-Receiver.war](https://github.com/riv5181/riv5181.github.io/blob/master/MQ-Light-Resources/Source%20Codes/mqlight-tutorial-receiver/build/libs/MQLight-Receiver.war?raw=true) file to your Bluemix account.
	
	```text
	> cf push MQLight-Receiver-<your_name> -m 256M -p MQLight-Receiver.war
	```


	**Example:**
		
	```text
	> cf push MQLight-Sender-riv5181 -m 256M -p MQLight-Sender.war
	> cf push MQLight-Receiver-riv5181 -m 256M -p MQLight-Receiver.war
	```
	

	The `-m` switch allows you to specify the memory allocation of your application.

	The `-p` switch allows you to specify the location of the file containing the sample application.

	<br>
	
	
####Add the MQ Light Service and Bind it to the Applications

1. Go to the `DASHBOARD` of your Bluemix Account and go click the widget representing `MQLight-Sender-<yourname>`.

1. On the left pane, click the `Overview` link. 
	
1. Click the `ADD A SERVICE OR API` link.  You will be redirected to the `Catalog` page. 

1. Look for the `MQ Light` service and click it.
 
1. Place the Application in the `dev` space.  

1. Connect it to the `MQLight-Sender-<yourname>` application. 

1. In the `Service name` text box, use any name the service.

1. Click the `CREATE` button.

1. When asked to restage your application, click the `RESTAGE` button.  

1. While the Application is restaging, click the widget representing `MQLight-Receiver-<yourname>`

1. Add or Bind the Application with the newly created MQ Light Service.

1. Restart the `MQLight-Receiver-<yourname>` Application. 

<br>

####Analyzing and Discovering how MQ Light works

1. Go to the `DASHBOARD` of your Bluemix Account and go click the widget representing the MQ Light Service. Call the tab you are currently in the `SERVER` Tab. 

2. Open another tab and go to the `DASHBOARD` of your Bluemix Account and go click the widget representing the Sender Application. Run it and call the tab you are currently in the `SENDER` tab. From the tab, go to the `InitializationPage.jsp`. The link for the app should be called `mqlight-sender-<yourname>.mybluemix.net/InitializationPage.jsp`. 

3. Open another tab and go to the `DASHBOARD` of your Bluemix Account and go click the widget representing the Receiver Application. Run it and call the tab you are currently in the `RECEIVER` tab. From the tab, go to the `InitializationPage.jsp`. The link for the app should be called `mqlight-receiver-<yourname>.mybluemix.net/InitializationPage.jsp`. 

4. From the `RECIEVER` tab, click the `Begin Session` button and leave the topic as it is. Observe that the next page has no content whatsoever. This is because you obviously haven't sent any messages yet. When you go to the `SERVER` tab, you will notice that a client has opened up in the `Receiving` side. This is where your messages will be sent under the topic `public`. 

5. From the `SENDER` tab, click the `Begin Session` button. 

6. Type any message in the text box. Leave the topic as `public` and click the send button.

7. Go to the `RECEIVER` tab and refresh the tab itself. At this point, you should be able to see the message you typed at the `SENDER` tab, assuming you did not modify the topic. <s>If you did, skip the following steps until you reach step 20. Read it, and go step 6.</s> 

8. Go to the `SERVER` tab and notice there is now a client in the `Sending` side. This represents the sender tab. Also notice the block, which contains the message you sent in the middle portion of the page. If you delve into the details, located at the lower right corner of the block, you can see more information about it, such as the TTL, topic used and its receivers. 

9. Go back to the `SENDER` side, and type any message in the text box. This time, change the topic into anything you wish other than `public` and click send. 

10. Go to the `RECEIVER` tab, and refresh the tab itself. You should be able to notice that the message you sent at step 9 did not show up. This is because the topics used in the `RECEIVER` and `SENDER` tabs are different. You can also get more information about it if you go to the `SERVER` tab, and check out the details of the message. 

11. Push another receiver cilent to your Bluemix Account. Name this one as `MQLight-ReceiverX-<yourname>`. You can use the same .war file. DO NOT FORGET to bind it to the MQ Light Service you created earlier. 

	```text
	> cf push MQLight-ReceiverX-riv5181 -m 256M -p MQLight-Receiver.war
	```

12. Go to the `DASHBOARD` of your Bluemix Account and go click the widget representing the Receiver Application you uploaded. Run it and call the tab you are currently in the `RECEIVERX` tab. From the tab, go to the `InitializationPage.jsp`. The link for the app should be called `mqlight-receiverx-<yourname>.mybluemix.net/InitializationPage.jsp`. 

13. From the `RECIEVER` tab, click the `Begin Session` button and leave the topic as it is. Observe that the next page has no content whatsoever. This is because you obviously haven't sent any messages to this client yet. When you go to the `SERVER` tab, you will notice that there are two clients open in the `Receiving` side. 

14. From the `SENDER` tab, click the `Begin Session` button. Type any message in the text box. Leave the topic as `public` and click the send button.

15. Go to the `RECEIVER` and `RECEIVERX` tabs and refresh the tabs itself. At this point, you should be able to see the message you typed at the `SENDER` tab on both of them. 

16. Go to the `SERVER` tab and notice the block, which contains the message you sent in the middle portion of the page. If you delve into the details, located at the lower right corner of the block, you can see more information about it. Also notice that it was send to two clients. This happened because those two receiver clients and the sender clients use the same topic. 

17. Go to the `RECEIVERX` tab and and click the `Re-Initialize` button. This time, replace the topic from `public` to whatever you wish other than `public`. 

18. From the `SENDER` tab, send another message still under the `public` topic.

19. Refresh both receiver client tabs and notice that only the `RECEIVER` tab received the message. If you recall, the `RECEIVER` tab is using the `public` topic, and the `SENDER` tab sent the message under the `public` topic. You can gain more information in the `SERVER` tab and viewing the information on the message you sent from the sender client. 

20. Go pat yourself on the back for a job well done. 

<br>

####Analyze How the MQ Light Applications communicates with MQ Light Service

It seems impossible for the sample application to be able to communicate with the MQ Light service since you have not updated the client application to use the credentials of the service. 

1. From the `DASHBOARD`, click any of the application using MQ Light.  On the left pane, click the `Environment Variables` link. 

	Below is a sample output. 

	```text
	{
   	     "mqlight": [
      		{
         	     "name": "MQLight-sampleservice",
         	     "label": "mqlight",
         	     "plan": "standard",
         	     "credentials": {
            		"password": "M3UmC_)v{wB5",
            		"nonTLSConnectionLookupURI": "http://mqlightprod-lookup.ng.bluemix.net/Lookup?serviceId=692d9e47-66ad-4f12-8d4d-611adfddbd90",
            		"version": "2",
            		"connectionLookupURI": "http://mqlightprod-lookup.ng.bluemix.net/Lookup?serviceId=692d9e47-66ad-4f12-8d4d-611adfddbd90&tls=true",
            		"username": "CKZKSv6UdKQp"
         	     }
      	         }
   	      ]
	}
	```

	The value above contains the credentials of the MQ Light service.  This value was produced when you created the service earlier.  

	Recall that you clicked the `ADD A SERVICE OR API` link earlier then created the MQ Light service.  Adding a service (or API) does two things:
		- create a service
		- bind the service to the application

	Binding the MQ Light service to the application simply instructs Bluemix to share the credentials of the MQ Light service to the application.  The credentials are shared by placing the values of the credentials to `VCAP_SERVICES`.

	However, it needs to be emphasized that even if the credentials are shared through the `VCAP_SERVICES`, this sharing is useless unless the application explicitly use the `VCAP_SERVICES` environment variable.

	<br>
	
1. This is a sample code from the sender and receiver client. It is a simplified version of how sending and receiving works in MQ Light. You may look at the [Source Codes](https://github.com/riv5181/riv5181.github.io/tree/master/MQ-Light-Resources/Source%20Codes) and [Documentation](http://mqlight.github.io/java-mqlight/?cm_mc_uid=47908507829914552831905&cm_mc_sid_50200000=1455286883) if you wish. 
<br>
	Sender Client: 
	
	```java
		NonBlockingClient.create(null, new NonBlockingClientAdapter<Void>() 
        	{
            		public void onStarted(NonBlockingClient client, Void context) 
            		{
                    		client.send("public", "Hello World!", null);
                    		client.stop(null,null);
            		}
            
        	}, null);
	```
	<br>
	
	Receiver Client: 
	
	```java
		NonBlockingClient.create(null, new NonBlockingClientAdapter<Void>() 
        	{
            		public void onStarted(NonBlockingClient client, Void context) 
            		{
                		client.subscribe("public",  new DestinationAdapter<Void>() 
                		{
                    			public void onMessage(NonBlockingClient client, Void context, Delivery delivery) 
                    			{
                        			if (delivery.getType() == Delivery.Type.STRING)
                            			System.out.println(((StringDelivery)delivery).getData());
                    			}
                		}, null, null);
                
            		}
        	}, null);
	```
	
	The method `NonBlockingClient` represents the client one wishes to create. It has may capabilities such as to create the client, use it to send the message, subscribe it to a topic and terminate it after tasks are completed. When `NonBlockingClient` is created, notice that the first parameter passed is `null`. This is where the location of the MQ Light service is passed. If you were to, let's say, download the MQ Light Service to your desktop, the location of the service would be `amqp://locahost`, assuming no other security measures were placed. Take note that MQ Light uses a different port and protocol to transport its messages, which would be 5671 and 5672, using the Advanced Messaging Queueing Protocol, or the AMQP. It can also use AMQPS for a secure connection under port 5679. Since the clients use the service located in Bluemix, it uses `null`. As for the reason why, it uses the `VCAP_SERVICES` provided in the `Enviroment Variables`. 
	
	`NonBlockingClient.send` is used to send messages. If you look at the code, you can see the parameters used. The first one would be the topic used, in this case, it uses `public`, and the second parameter contains the message. Trivia, the message sent is not limited to string. You can also use this to send messages in JSON format and also other file types. 
	
	`NonBlockingClient.subscribe`, subscribes the client to the provided topic, which is `public`, for th purposes of testing. You can use other topics in the form of a string. Once the client is subscribed, the client will receive messages from ANYONE as long as they have the same topic. 
	
	`NonBlockingClient.stop`, obviously stops the client. 
	
	There are other classes that is used with the MQ Light API. One is the `ClientOptions` where you can set the Username and password that is used to a configured MQ Light Service with security options. You can also use this class, where you can set the id to anything, instead of the randomly generated ID that is seen in the `SERVER` tab, like `AUTO_38abe01` and `AUTO_cd987ab`. There are many more functions you can do with MQ Light. See the [Documentation](http://mqlight.github.io/java-mqlight/?cm_mc_uid=47908507829914552831905&cm_mc_sid_50200000=1455286883) for more details. 

	<br>
	
####Delete the Application and MQ Light Service
You may delete applications and services that you don't anymore need.  This will free up some resources which is essential to accommodate new applications and services you want to deploy in the future.

1. Go back to the browser tab containing your Bluemix account.  In the menu, click `DASHBOARD`.  

	Notice that the widget of the sample application got updated.  An icon was added in the widget.  If you will mouse hover on the icon, you will see that the icon refers to the MQ Light service you created earlier.  The presence of this icon in the widget of the sample application  means that the service is bound to the application.  

	Take note that it is possible that you may have additional services bound to the same application.

	In addition, notice that a widget for the MQ Light service is also available.  It also has an icon that refers to the sample application.  The presence of this icon in the widget of the MQ Light service means that the application may use this service. 

	Take note that it is possible that you may have additional applications bound to the same service.
	
	<br>
 
1. Click the `gear` icon in the widget of the sample application.

1. Click the `Delete App` entry.  In the `Services` tab, make sure that the MQ Light service is selected.  In the `Routes` tab, make sure that the route (i.e., URL) is selected.

1. Click the `DELETE` button.

<br>

####Resources

1. [Sender Client .war File](https://github.com/riv5181/riv5181.github.io/blob/master/MQ-Light-Resources/Source%20Codes/mqlight-tutorial-sender/build/libs/MQLight-Sender.war?raw=true)

2. [Receiver Client .war File](https://github.com/riv5181/riv5181.github.io/blob/master/MQ-Light-Resources/Source%20Codes/mqlight-tutorial-receiver/build/libs/MQLight-Receiver.war?raw=true)

3. [Source Code used for this tutorial](https://github.com/riv5181/riv5181.github.io/tree/master/MQ-Light-Resources/Source%20Codes)

4. [Documentation for MQ Light](http://mqlight.github.io/java-mqlight/?cm_mc_uid=47908507829914552831905&cm_mc_sid_50200000=1455286883)

5. [MQ Light API and Service for local use](https://developer.ibm.com/messaging/mq-light/)

6. [Developing an MQ Light application with Java](https://developer.ibm.com/messaging/2015/05/22/getting-started-with-java-apps-using-the-mq-light-service-for-bluemix/)

<br>

####End of Tutorial


