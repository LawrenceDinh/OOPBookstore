# CS-151-Project

Please note you can download this project by clicking on the green "Clone or download" button

MOST UPDATED VERSION: SJSU CS 151 Project 12/2/2019

Project Report Document: https://docs.google.com/document/d/1X3oBoukzML1SQoDn3zbapv81cD69cOxALhxFRSZpQkQ/view

Project Test Cases: https://docs.google.com/document/d/1bB2-h1vtWRPT9ZE8YUumD0tjo-yt88GbgF7f46x4iJo/edit?usp=sharing

#E-Commerce System

E-Commerce System is a system designed to maintain, modify, and search a list of multiple items for sale by signed-up users.

##Description
Features: 
-Sign up for account with username and password
-Log in to account with username and password
-Edit password
-View/Edit/Delete own posted items
-Search for item by category, name, or seller name
-Post a new item

## Installation Notes
Ensure that itemList.txt and userList.txt files are present in the project directory,
or alter the read and write methods to point to the user's preferred directory for these files, before activating 
the system by running the main method in the Commerce class.

##Usage
Upon activating the system, the user is presented with a signup window to create an account. New users should create an account to access the system.
Users with valid accounts should press the button to enter their username and password. Success displays the user's dashboard.
From the dashboard, the user can elect to exit the system, open the post item window, open the item search window, open the own posted items window, or change their password.
In the post item window, the user is prompted to enter text for a title and description, and choose a category, to display as the desired post.
When the user is satisfied, they can press the Post button to complete the post or the cancel button to return to the dashboard.
In the item search window, the user can choose from a drop-down menu to search by item name, category, or seller name, then enter the desired search term in the text field and press the view button.
This displays a list of items matching the criteria chosen (if any) from all previous posts.
The user can select a post and press view to open a detailed window which includes the item description.
In the own posted items window, the user is presented with a list of their own posted items.
It retains the view details functionality of the search window, but also allows deletion of selected items, or editing of item details (name and description).
When the user is finished with any particular dashboard window, they can press the close button to return to the dashboard.

##Authors and acknowledgement
Project Authors: Benjamin Townsend, Kevin Ngo, Luat Dinh, Jinhan Han 
