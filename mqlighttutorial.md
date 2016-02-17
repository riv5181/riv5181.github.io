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


####Deploy MQLight Applications in Bluemix using the `cf` tool.

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
	
	
####Add the MQLight Service and Bind it to the Applications

1. Go to the `DASHBOARD` of your Bluemix Account and go click the 

1. On the left pane, click the `Overview` link. 
	
1. Click the `ADD A SERVICE OR API` link.  You will be redirected to the `Catalog` page. 

1. Look for the `MQ Light` service and click it.
 
1. Place the Application in the `dev` space.  

1. Connect it to the `MQLight-Sender-<your name>` application. 

1. In the `Service name` text box, use any name the service.

1. Click the `CREATE` button.

1. When asked to restage your application, click the `RESTAGE` button.  Wait for your application to restage.

1. Open another browser tab (do not close the browser tab containing your Bluemix account).  Go to `http://myfirstapp-<your_name>.mybluemix.net` to test if the sample application can already connect to the created PostgreSQL service.

	<br>

1. Click the `Browse` button of the sample application and choose any text file.

	
1. Click the `Upload` button.  

	This time, the upload is successful.  You will see the contents of the text file displayed on the page.   The sample application is programmed to display the contents of the PostgreSQL service.  Since the contents of the text file is displayed on the page, the contents are successfully saved in the PostgreSQL service.

	<br>

####Analyze How the Sample Application communicates with PostgreSQL Service

Reviewing the procedure above, you did two important tasks: (1) deployed the sample application and (2) created a PostgreSQL service.

It seems impossible for the sample application to be able to communicate with the PostgreSQL service since you have not updated the sample application to use the credentials of the service (e.g., username, password, IP address, port no., etc.).  

However, as demonstrated, you were able to make the sample application to communicate with the service.  This was accomplished by coding the sample application such that the database credentials needed to create the connection string are not hard coded.  Instead it uses the credentials found in the environment variable `VCAP_SERVICES` which was originally empty earlier.


1. Go back to the browser tab containing your Bluemix account.  On the left pane, click the `Environment Variables` link. 

	Recall that earlier `VCAP_SERVICES` is empty.  However, it now contains a value similar to the one you see below:

	```text
	{
	   "postgresql-9.1": [
	      {
	         "name": "postgresql-myfirstservice",
	         "label": "postgresql-9.1",
	         "plan": "100",
	         "credentials": {
	            "name": "d318bc5931cd540b694de846b004b3955",
	            "host": "198.11.228.48",
	            "hostname": "198.11.228.48",
	            "port": 5433,
	            "user": "u536f68cf365d4ee6a3a3e00cbf209c53",
	            "username": "u536f68cf365d4ee6a3a3e00cbf209c53",
	            "password": "pdf27393fb94d4d45913cd54751d346ad",
	            "uri": "postgres://u536f68cf365d4ee6a3a3e00cbf209c53:pdf27393fb94d4d45913cd54751d346ad@198.11.228.48:5433/d318bc5931cd540b694de846b004b3955"
	         }
	      }
	   ]
	}
	```

	The value above contains the credentials of the PostgreSQL service.  This value was produced when you created the service earlier.  

	Recall that you clicked the `ADD A SERVICE OR API` link earlier then created the PostgreSQL service.  Adding a service (or API) does two things:
		- create a service
		- bind the service to the application

	Binding the PostgreSQL service to the application simply instructs Bluemix to share the credentials of the PostgreSQL service to the sample application.  The credentials are shared by placing the values of the credentials to `VCAP_SERVICES`.

	However, it needs to be emphasized that even if the credentials are shared through the `VCAP_SERVICES`, this sharing is useless unless the application explicitly use the `VCAP_SERVICES` environment variable.

	You will examine the source code inside `PostgreSQLUpload.war` to see how `VCAP_SERVICES` is used.

	<br>
	
1. If you extract the contents of `PostgreSQLUpload.war` you will see the subdirectory `WEB-INF/classes/com/ibm/bluemix/samples`.  This contains several `.java` files including `PostgreSQLClient.java`.

	> You don't need to extract the contents of the `.war` file since the contents of the needed files are shown below.  However, you may use tools such as `7Zip` if you want to extract the contents.
	
	`PostgreSQLClient.java` has a method called `getConnection`:
	
	```java
		private static Connection getConnection() throws Exception {
			Map<String, String> env = System.getenv();
			
			if (env.containsKey("VCAP_SERVICES")) {
				// we are running on cloud foundry, let's grab the service details from vcap_services
				JSONParser parser = new JSONParser();
				JSONObject vcap = (JSONObject) parser.parse(env.get("VCAP_SERVICES"));
				JSONObject service = null;
				
				// We don't know exactly what the service is called, but it will contain "postgresql"
				for (Object key : vcap.keySet()) {
					String keyStr = (String) key;
					if (keyStr.toLowerCase().contains("postgresql")) {
						service = (JSONObject) ((JSONArray) vcap.get(keyStr)).get(0);
						break;
					}
				}
				
				if (service != null) {
					JSONObject creds = (JSONObject) service.get("credentials");
					String name = (String) creds.get("name");
					String host = (String) creds.get("host");
					Long port = (Long) creds.get("port");
					String user = (String) creds.get("user");
					String password = (String) creds.get("password");
					
					String url = "jdbc:postgresql://" + host + ":" + port + "/" + name;
					
					return DriverManager.getConnection(url, user, password);
				}
			}
			
			throw new Exception("No PostgreSQL service URL found. Make sure you have bound the correct services to your app.");
		}
	```

	The method parses the contents of `VCAP_SERVICES` and looks for the credentials of the PostgreSQL service.  This approach allowed the sample application to dynamically get the credentials of the service instead of hard coding it.

	The use of `VCAP_SERVICES` explains how the sample application is able to connect to the PostgreSQL service.  However, this did not explain how a database table (that stores the content of text files) was created.  Recall that you never performed a task that explicilty created a table in the PostgreSQL service.

	`PostgreSQLClient.java` has another method called `createTable`:
	
	```java
		private void createTable() throws Exception {
			String sql = "CREATE TABLE IF NOT EXISTS posts (" +
							"id serial primary key, " +
							"text text" +
						 ");";
			Connection connection = null;
			PreparedStatement statement = null;
			
			try {
				connection = getConnection();
				statement = connection.prepareStatement(sql);
				statement.executeUpdate();
			} finally {			
				if (statement != null) {
					statement.close();
				}
				
				if (connection != null) {
					connection.close();
				}
			}
		}
	```

	The `createTable` method allowed the tables to be programmatically created (i.e., no need for you to manually create the table).  In usual practices, the database administrator creates the tables.  However, the sample application was designed to programmatically create the table to easily demonstrate the use of a service in Bluemix.

	<br>
	
####Delete the Sample Application and PostgreSQL Service
You may delete applications and services that you don't anymore need.  This will free up some resources which is essential to accommodate new applications and services you want to deploy in the future.

1. Go back to the browser tab containing your Bluemix account.  In the menu, click `DASHBOARD`.  

	Notice that the widget of the sample application got updated.  An icon was added in the widget.  If you will mouse hover on the icon, you will see that the icon refers to the PostgreSQL service you created earlier.  The presence of this icon in the widget of the sample application  means that the service is bound to the application.  

	Take note that it is possible that you may have additional services bound to the same application.

	In addition, notice that a widget for the PostgreSQL service is also available.  It also has an icon that refers to the sample application.  The presence of this icon in the widget of the PostgreSQL service means that the application may use this service. 

	Take note that it is possible that you may have additional applications bound to the same service.
	
	<br>
 
1. Click the `gear` icon in the widget of the sample application.

1. Click the `Delete App` entry.  In the `Services` tab, make sure that the PostgreSQL service is selected.  In the `Routes` tab, make sure that the route (i.e., URL) is selected.

1. Click the `DELETE` button.

<br>

####End of Tutorial


