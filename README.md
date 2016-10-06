# Multi User Chat Client

This program functons as a client for my socket based chat server-client interface. Using this you can connect to a server and communicate with other people

**In order to compile this program, you will need to include the libraries in the /libs/ folder**

## Starting the client

In order to connect to a running server, you must navigate through the Connections menu.
When prompted with a username and password, if the information is new, a new user will be created. If the username exists in the server data directory, you must enter the password associated with it

Without port forwarding a server, you can still connect locally through localhost or 127.0.0.1

## The client view

* The left contains an interface of all the people currently connected to the server
  * With sufficient permissions, you can remove people from the server by right clicking their name and selecting 'Kick'
* The center window shows all of the messages being transmitted through the server
* The bottom textbox allows the concole to talk to other people in the server if necessary

## Permission system

* Console - The host console, this user is not shown on any client
* Administrator - The highest level that user can achieve on the permission hierarchy. Has the ability to disconnect all lower users
* Moderator - The second highest level that a user can achieve. Has the ability to remove all lower users
* User - A standard user, can chat but is not allowed to remove anybody from the server

## Please note that this project is not completed
I did not finish this project and there are many things that are only partially implemented.
* Bookmarks do not work
* left clicking users and selecting their profile does nothing